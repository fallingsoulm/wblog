package com.wblog.info.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.apes.hub.api.enums.ConstantEnum;
import com.apes.hub.api.module.info.vo.AlbumArticleVo;
import com.apes.hub.api.module.info.vo.ArticleVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageInfoContentHandler;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.page.MybatisPlusUtils;
import com.apes.hub.core.security.LoginUserManage;
import com.apes.hub.info.conver.AlbumArticleConver;
import com.apes.hub.info.entity.AlbumArticleEntity;
import com.apes.hub.info.manage.IAlbumArticleManage;
import com.apes.hub.info.service.IAlbumArticleService;
import com.apes.hub.info.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章与专辑关联 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
@Service
@Slf4j
public class AlbumArticleServiceImpl implements IAlbumArticleService {


    @Autowired
    private IAlbumArticleManage iAlbumArticleManage;


    @Autowired
    private AlbumArticleConver albumArticleConver;


    @Autowired
    private MybatisPlusUtils plusUtils;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private LoginUserManage loginUserManage;

    @Override
    public PageInfo<AlbumArticleVo> findByPage(PageRequestParams<AlbumArticleVo> pageRequestParams) {
        PageRequestParams<AlbumArticleEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, AlbumArticleEntity.class, albumArticleConver);
        PageInfo<AlbumArticleEntity> entityPageInfo = iAlbumArticleManage.findByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, AlbumArticleVo.class, albumArticleConver, new PageInfoContentHandler<AlbumArticleVo>() {
            @Override
            public void handler(List<AlbumArticleVo> contentList) {
                List<Long> articleIds = contentList.stream().map(AlbumArticleVo::getArticleId).distinct().collect(Collectors.toList());
                List<ArticleVo> articleVoList =
                        articleService.findByIds(articleIds);
                for (AlbumArticleVo albumArticleVo : contentList) {
                    for (ArticleVo articleVo : articleVoList) {
                        if (albumArticleVo.getArticleId().equals(articleVo.getId())) {
                            albumArticleVo.setArticleVo(articleVo);
                        }
                    }
                }
            }
        });
    }

    @Override
    public AlbumArticleVo findById(Long id) {
        AlbumArticleEntity albumArticleEntity = iAlbumArticleManage.findById(id);
        if (albumArticleEntity == null) {
            return null;
        }
        return albumArticleConver.map(albumArticleEntity, AlbumArticleVo.class);
    }

    @Override
    public List<AlbumArticleVo> findList(AlbumArticleVo albumArticleVo) {
        AlbumArticleEntity AlbumArticleEntity = albumArticleConver.map(albumArticleVo, AlbumArticleEntity.class);
        List<AlbumArticleEntity> list = iAlbumArticleManage.findList(AlbumArticleEntity);
        return albumArticleConver.mapAsList(list, AlbumArticleVo.class);
    }

    @Override
    public List<AlbumArticleVo> findByIds(List<Long> ids) {
        List<AlbumArticleEntity> entities = iAlbumArticleManage.findByIds(ids);
        return albumArticleConver.mapAsList(entities, AlbumArticleVo.class);
    }

    @Override
    public Long save(AlbumArticleVo albumArticleVo) {
        AlbumArticleEntity albumArticleEntity = albumArticleConver.map(albumArticleVo, AlbumArticleEntity.class);
        iAlbumArticleManage.insert(albumArticleEntity);
        return albumArticleEntity.getId();
    }

    @Override
    public void update(AlbumArticleVo albumArticleVo) {
        AlbumArticleEntity albumArticleEntity = albumArticleConver.map(albumArticleVo, AlbumArticleEntity.class);
        iAlbumArticleManage.update(albumArticleEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iAlbumArticleManage.deleteBatch(new AlbumArticleEntity(), ids);
    }

    @Override
    public void deleteById(Long id) {
        iAlbumArticleManage.deleteById(AlbumArticleEntity.builder().id(id).build());
    }

    @Override
    public Integer count(AlbumArticleVo albumArticleVo) {
        AlbumArticleEntity albumArticleEntity = albumArticleConver.map(albumArticleVo, AlbumArticleEntity.class);
        return iAlbumArticleManage.count(albumArticleEntity);
    }

    @Override
    public PageInfo<ArticleVo> notAssociatedArticles(PageRequestParams<AlbumArticleVo> pageRequestParams) {
        // 查询出来关联的文章列表
        Long albumId = pageRequestParams.getParams().getAlbumId();
        List<Long> articleIds = this.iAlbumArticleManage
                .findList(AlbumArticleEntity.builder().albumId(albumId).build())
                .stream()
                .map(AlbumArticleEntity::getArticleId)
                .distinct()
                .collect(Collectors.toList());

        ArticleVo articleVo = pageRequestParams.getParams().getArticleVo();
        if (null == articleVo) {
            articleVo = new ArticleVo();
        }
        articleVo.setUserId(loginUserManage.userId());
        articleVo.setStatus(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue());
        PageRequestParams<ArticleVo> params = new PageRequestParams<>();
        params.setPageSize(pageRequestParams.getPageSize());
        params.setPageIndex(pageRequestParams.getPageIndex());
        params.setParams(articleVo);
        return articleService.findByPageAndNotIn(params, articleIds);
    }

    @Override
    public void saveBatch(List<Long> articleIds, Long albumId) {

        if (CollectionUtil.isEmpty(articleIds)) {
            return;
        }
        // 查询当前专辑下最大的游标
        Integer maxOrderNum = this.iAlbumArticleManage.maxOrderNum(albumId);

        if (null == maxOrderNum) {
            maxOrderNum = 0;
        }
        for (Long articleId : articleIds) {
            maxOrderNum++;
            Integer count = this.iAlbumArticleManage.count(AlbumArticleEntity.builder().albumId(albumId).articleId(articleId).build());
            if (null != count && count.intValue() > 0) {
                continue;
            }
            this.iAlbumArticleManage.insert(AlbumArticleEntity.builder().orderNum(maxOrderNum).articleId(articleId).albumId(albumId).build());
        }

    }

    @Override
    public List<ArticleVo> findArticle(Long albumId) {


        List<Long> articleIds = iAlbumArticleManage.findList(AlbumArticleEntity.builder().albumId(albumId).build())
                .stream().sorted(Comparator.comparing(AlbumArticleEntity::getOrderNum))
                .map(AlbumArticleEntity::getArticleId).collect(Collectors.toList());

        List<ArticleVo> articleVoList = articleService.findByIds(articleIds);
        List<ArticleVo> sortList = new LinkedList<>();
        for (Long articleId : articleIds) {
            for (ArticleVo articleVo : articleVoList) {
                if (articleId.equals(articleVo.getId())) {
                    sortList.add(articleVo);
                }
            }
        }
        return sortList;
    }

}
