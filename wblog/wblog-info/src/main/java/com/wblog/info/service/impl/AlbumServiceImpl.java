package com.wblog.info.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.enums.FilePathEnum;
import com.wblog.common.exception.BusinessException;
import com.wblog.common.module.info.vo.AlbumArticleVo;
import com.wblog.common.module.info.vo.AlbumVo;
import com.wblog.common.module.system.api.SysUserApi;
import com.wblog.common.module.system.vo.SysUserVo;
import com.wblog.info.component.FileTemplatePlus;
import com.wblog.info.entity.AlbumEntity;
import com.wblog.info.manage.IAlbumManage;
import com.wblog.info.service.IAlbumArticleService;
import com.wblog.info.service.IAlbumService;
import io.github.fallingsoulm.easy.archetype.data.file.FileTemplate;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.PageInfoContentHandler;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章专辑 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
@Service
@Slf4j
public class AlbumServiceImpl implements IAlbumService {


    @Autowired
    private IAlbumManage iAlbumManage;


    @Autowired
    private MybatisPlusUtils plusUtils;


    @Autowired
    private SysUserApi sysUserApi;

    @Autowired
    private IAlbumArticleService albumArticleService;


    @Autowired
    private FileTemplatePlus fileTemplate;

    @Override
    public PageInfo<AlbumVo> findByPage(PageRequestParams<AlbumVo> pageRequestParams) {
        PageRequestParams<AlbumEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, AlbumEntity.class);
        PageInfo<AlbumEntity> entityPageInfo = iAlbumManage.listByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, AlbumVo.class, contentList -> {
            List<Long> userIds = contentList.stream().map(AlbumVo::getUserId).distinct().collect(Collectors.toList());

            List<SysUserVo> userVos = sysUserApi.findByIds(userIds).getData();
            for (AlbumVo albumVo : contentList) {

                int count = albumArticleService.count(AlbumArticleVo.builder().albumId(albumVo.getId()).build());
                albumVo.setArticleNum(count);
                albumVo.setImage(fileTemplate.addHost(albumVo.getImage()));
                for (SysUserVo userVo : userVos) {
                    if (null != albumVo.getUserId() && albumVo.getUserId().equals(userVo.getUserId())) {
                        albumVo.setUserName(userVo.getUserName());
                    }
                }
            }


        });
    }

    @Override
    public AlbumVo findById(Long id) {
        AlbumEntity albumEntity = iAlbumManage.findById(id);
        if (albumEntity == null) {
            return null;
        }
        AlbumVo albumVo = BeanUtils.copyProperties(albumEntity, AlbumVo.class);
        albumVo.setImage(fileTemplate.addHost(albumVo.getImage()));
        return albumVo;
    }

    @Override
    public List<AlbumVo> findList(AlbumVo albumVo) {
        AlbumEntity AlbumEntity = BeanUtils.copyProperties(albumVo, AlbumEntity.class);
        List<AlbumEntity> list = iAlbumManage.list(AlbumEntity);
        return BeanUtils.copyList(list, AlbumVo.class);
    }

    @Override
    public List<AlbumVo> findByIds(List<Long> ids) {
        List<AlbumEntity> entities = iAlbumManage.findByIds(ids);
        return BeanUtils.copyList(entities, AlbumVo.class);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long save(AlbumVo albumVo) {
        AlbumEntity albumEntity = BeanUtils.copyProperties(albumVo, AlbumEntity.class);

        if (StrUtil.isBlank(albumEntity.getImage())) {
            // 使用随机的图片
            String randomImage = fileTemplate.randomImage();
            if (StrUtil.isBlank(randomImage)) {
                throw new BusinessException("随机的图片获取异常");
            }
            albumEntity.setImage(randomImage);

        }
        albumEntity.setImage(fileTemplate.removeHost(albumEntity.getImage()));
        albumEntity.setCreateTime(new Date());

        iAlbumManage.insert(albumEntity);
        return albumEntity.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(AlbumVo albumVo) {
        AlbumEntity albumEntity = BeanUtils.copyProperties(albumVo, AlbumEntity.class);

//        if (StrUtil.isBlank(albumEntity.getImage())) {
//            // 使用随机的图片
//            albumEntity.setImage(fileInfoApi.randomFile(FilePathEnum.ALBUM_IMAGE.getClassify()).getData().getUrl());
//        }
        albumEntity.setImage(fileTemplate.removeHost(albumEntity.getImage()));
        iAlbumManage.update(albumEntity);
        if (null != albumVo.getStatus() && albumVo.getStatus().equals(ConstantEnum.ALBUM_STATUS_ENABLE.getValue())) {
            // 当上架的时候, 需要检查该专辑下是不是有文章,如果没有文章的话,则不允许上架
            Integer count = albumArticleService.count(AlbumArticleVo.builder().albumId(albumVo.getId()).build());
            if (null == count || count.intValue() == 0) {
                throw new BusinessException("当前专辑下不存在文章, 不允许上架");
            }
        }
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iAlbumManage.deleteBatch(ids);
    }

    @Override
    public void deleteById(Long id) {
        iAlbumManage.deleteById(AlbumEntity.builder().id(id).build());
    }


}
