package com.wblog.info.service.impl;

import cn.hutool.core.util.StrUtil;
import com.apes.hub.api.enums.ConstantEnum;
import com.apes.hub.api.enums.FilePathEnum;
import com.apes.hub.api.exception.CustomException;
import com.apes.hub.api.module.file.api.FileApi;
import com.apes.hub.api.module.file.api.FileInfoApi;
import com.apes.hub.api.module.info.vo.AlbumArticleVo;
import com.apes.hub.api.module.info.vo.AlbumVo;
import com.apes.hub.api.module.system.api.SysUserApi;
import com.apes.hub.api.module.system.vo.SysUserVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageInfoContentHandler;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.page.MybatisPlusUtils;
import com.apes.hub.info.conver.AlbumConver;
import com.apes.hub.info.entity.AlbumEntity;
import com.apes.hub.info.manage.IAlbumManage;
import com.apes.hub.info.service.IAlbumArticleService;
import com.apes.hub.info.service.IAlbumService;
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
    private AlbumConver albumConver;


    @Autowired
    private MybatisPlusUtils plusUtils;

    @Autowired
    private FileApi fileApi;

    @Autowired
    private SysUserApi sysUserApi;

    @Autowired
    private IAlbumArticleService albumArticleService;

    @Autowired
    private FileInfoApi fileInfoApi;

    @Override
    public PageInfo<AlbumVo> findByPage(PageRequestParams<AlbumVo> pageRequestParams) {
        PageRequestParams<AlbumEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, AlbumEntity.class, albumConver);
        PageInfo<AlbumEntity> entityPageInfo = iAlbumManage.findByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, AlbumVo.class, albumConver, new PageInfoContentHandler<AlbumVo>() {
            @Override
            public void handler(List<AlbumVo> contentList) {
                List<Long> userIds = contentList.stream().map(AlbumVo::getUserId).distinct().collect(Collectors.toList());

                List<SysUserVo> userVos = sysUserApi.findByIds(userIds).getData();
                for (AlbumVo albumVo : contentList) {

                    int count = albumArticleService.count(AlbumArticleVo.builder().albumId(albumVo.getId()).build());
                    albumVo.setArticleNum(count);
                    albumVo.setImage(fileApi.addHost(albumVo.getImage()));
                    for (SysUserVo userVo : userVos) {
                        if (null != albumVo.getUserId() && albumVo.getUserId().equals(userVo.getUserId())) {
                            albumVo.setUserName(userVo.getUserName());
                        }
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
        AlbumVo albumVo = albumConver.map(albumEntity, AlbumVo.class);
        albumVo.setImage(fileApi.addHost(albumVo.getImage()));
        return albumVo;
    }

    @Override
    public List<AlbumVo> findList(AlbumVo albumVo) {
        AlbumEntity AlbumEntity = albumConver.map(albumVo, AlbumEntity.class);
        List<AlbumEntity> list = iAlbumManage.findList(AlbumEntity);
        return albumConver.mapAsList(list, AlbumVo.class);
    }

    @Override
    public List<AlbumVo> findByIds(List<Long> ids) {
        List<AlbumEntity> entities = iAlbumManage.findByIds(ids);
        return albumConver.mapAsList(entities, AlbumVo.class);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long save(AlbumVo albumVo) {
        AlbumEntity albumEntity = albumConver.map(albumVo, AlbumEntity.class);

        if (StrUtil.isBlank(albumEntity.getImage())) {
            // 使用随机的图片
            albumEntity.setImage(fileInfoApi.randomFile(FilePathEnum.PUBLIC.getClassify()).getData().getUrl());

        }
        albumEntity.setImage(fileApi.spiltHost(albumEntity.getImage()));
        albumEntity.setCreateTime(new Date());

        iAlbumManage.insert(albumEntity);
        return albumEntity.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(AlbumVo albumVo) {
        AlbumEntity albumEntity = albumConver.map(albumVo, AlbumEntity.class);

//        if (StrUtil.isBlank(albumEntity.getImage())) {
//            // 使用随机的图片
//            albumEntity.setImage(fileInfoApi.randomFile(FilePathEnum.ALBUM_IMAGE.getClassify()).getData().getUrl());
//        }
        albumEntity.setImage(fileApi.spiltHost(albumEntity.getImage()));
        iAlbumManage.update(albumEntity);
        if (null != albumVo.getStatus() && albumVo.getStatus().equals(ConstantEnum.ALBUM_STATUS_ENABLE.getValue())) {
            // 当上架的时候, 需要检查该专辑下是不是有文章,如果没有文章的话,则不允许上架
            Integer count = albumArticleService.count(AlbumArticleVo.builder().albumId(albumVo.getId()).build());
            if (null == count || count.intValue() == 0) {
                throw new CustomException("当前专辑下不存在文章, 不允许上架");
            }
        }
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iAlbumManage.deleteBatch(new AlbumEntity(), ids);
    }

    @Override
    public void deleteById(Long id) {
        iAlbumManage.deleteById(AlbumEntity.builder().id(id).build());
    }


}
