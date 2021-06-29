package com.wblog.info.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.enums.FilePathEnum;
import com.wblog.common.exception.BusinessException;
import com.wblog.common.module.info.vo.*;
import com.wblog.common.module.system.api.SysUserApi;
import com.wblog.common.module.system.vo.SysUserVo;
import com.wblog.common.redis.RedisKeyEnums;
import com.wblog.info.component.FileTemplatePlus;
import com.wblog.info.config.BlogConfigProperties;
import com.wblog.info.entity.ArticleEntity;
import com.wblog.info.event.ArticleEvent;
import com.wblog.info.event.EventSourceVo;
import com.wblog.info.manage.IArticleManage;
import com.wblog.info.mq.service.IArticleMqService;
import com.wblog.info.service.*;
import com.wblog.info.utils.MarkdownUtils;
import io.github.fallingsoulm.easy.archetype.data.file.FileTemplate;
import io.github.fallingsoulm.easy.archetype.data.id.SimpleSnowflake;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.PageInfoContentHandler;
import io.github.fallingsoulm.easy.archetype.data.redis.RedisKeyGenerator;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import io.github.fallingsoulm.easy.archetype.security.core.LoginUserService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
@Slf4j
public class ArticleServiceImpl implements IArticleService {


    @Autowired
    private IArticleManage iArticleManage;


    @Autowired
    private MybatisPlusUtils plusUtils;

    @Autowired
    private SimpleSnowflake snowflake;

    @Autowired
    private IArticleInfoService articleInfoService;

    @Autowired
    private ILabelService labelService;

    @Autowired
    private FileTemplatePlus fileTemplatePlus;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private FileTemplate fileTemplate;

    @Autowired
    private IClassifyService classifyService;

    @Autowired
    private IArticleLabelService articleLabelService;

    @Autowired
    private RedisKeyGenerator redisKeyGenerator;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BlogConfigProperties blogConfigProperties;

    @Autowired
    private IGitSynService gitSynService;


    @Autowired
    private SysUserApi sysUserApi;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private Executor executor;

    @Autowired
    private IArticleMqService articleMqService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public PageInfo<ArticleVo> findByPage(PageRequestParams<ArticleVo> pageRequestParams) {
        PageInfo<ArticleEntity> entityPageInfo = null;
        // 按照创建时间分页排序
        PageRequestParams<ArticleEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, ArticleEntity.class);
        if (null == pageRequestParams.getParams() || null == pageRequestParams.getParams().getOrderBy() ||
                (null != pageRequestParams.getParams().getOrderBy() && pageRequestParams.getParams().getOrderBy().
                        equals(ConstantEnum.ARTICLE_ORDER_BY_NEWEST.getValue()))) {


            entityPageInfo = iArticleManage.findByPage(params,
                    null == pageRequestParams.getParams() ? null : pageRequestParams.getParams().getStatusList());
        } else {
            //按照访问量分页排序
            String viewKey = redisKeyGenerator.generate(RedisKeyEnums.ARTICLE_VIEW);
            Long size = redisTemplate.opsForZSet().size(viewKey);
            List<ArticleEntity> articleEntities = null;
            if (size > 0) {
                LinkedHashSet set = (LinkedHashSet) redisTemplate.opsForZSet().reverseRange(viewKey, pageRequestParams.getOffset(), pageRequestParams.getOffset().intValue() + pageRequestParams.getPageSize().intValue() - 1);
                List<Long> articleIds = new ArrayList<>();
                for (Object o : set) {

                    articleIds.add(Long.valueOf(o.toString()));
                }
                articleEntities = this.iArticleManage.findByIds(articleIds);
                List<ArticleEntity> sortList = new ArrayList<>();
                for (Long articleId : articleIds) {
                    for (ArticleEntity articleEntity : articleEntities) {

                        if (articleId.equals(articleEntity.getId())) {
                            sortList.add(articleEntity);
                        }
                    }
                }

                articleEntities = sortList;

            } else {
                articleEntities = new ArrayList<>();
            }
            entityPageInfo = new PageInfo<ArticleEntity>(articleEntities, size.longValue(), params);
        }


        return plusUtils.convertPageInfo(entityPageInfo, ArticleVo.class, new PageInfoContentHandler<ArticleVo>() {
            @Override
            public void handler(List<ArticleVo> contentList) {
                setExtField(contentList);
                //添加url
                for (ArticleVo articleVo : contentList) {
                    articleVo.setUrl(blogConfigProperties.getArticleInfoUrl() + articleVo.getId() + "");
                }
            }
        });
    }

    /**
     * <p>设置扩展的字段</p>
     *
     * @param contentList
     * @author luyanan
     * @since 2020/6/16
     */
    private void setExtField(List<ArticleVo> contentList) {

        CompletableFuture<Void> classifyFuture = CompletableFuture.runAsync(() -> {
            //  分类名称赋值
            List<Long> classifyIds = contentList.stream().map(ArticleVo::getClassifyId).distinct().collect(Collectors.toList());
            List<ClassifyVo> classifyVoList = classifyService.findByIds(classifyIds);
            if (!classifyVoList.isEmpty()) {
                for (ArticleVo articleVo : contentList) {
                    for (ClassifyVo classifyVo : classifyVoList) {
                        if (articleVo.getClassifyId().equals(classifyVo.getId())) {
                            articleVo.setClassifyName(classifyVo.getName());
                        }
                    }
                }

            }
        }, executor);

        CompletableFuture<Void> userFurure = CompletableFuture.runAsync(() -> {
            // 用户名称赋值
            List<Long> userIds = contentList.stream().map(ArticleVo::getUserId).distinct().collect(Collectors.toList());

            List<SysUserVo> sysUserVos = sysUserApi.findByIds(userIds).getData();
            if (!sysUserVos.isEmpty()) {
                for (ArticleVo articleVo : contentList) {
                    for (SysUserVo sysUserVo : sysUserVos) {
                        if (articleVo.getUserId().equals(sysUserVo.getUserId())) {
                            articleVo.setUserName(sysUserVo.getUserName());
                            articleVo.setUserVo(sysUserVo);
                        }
                    }
                }
            }

        }, executor);


        Map<String, String> images = fileTemplate.addHost(contentList.stream().map(ArticleVo::getImage).distinct().collect(Collectors.toList()));

        List<CompletableFuture> allFuture = new ArrayList<>();
        allFuture.add(userFurure);
        allFuture.add(classifyFuture);

        for (ArticleVo articleVo : contentList) {
            CompletableFuture<Void> labelFuture = CompletableFuture.runAsync(() -> {
                List<LabelVo> labelVos = labelService.findByArticleId(articleVo.getId(), 5);
                articleVo.setLabelVos(labelVos);
            }, executor);
            allFuture.add(labelFuture);
            articleVo.setImage(images.getOrDefault(articleVo.getImage(), ""));
            articleVo.setView(getView(articleVo.getId(), articleVo.getView()));
        }
        try {
            CompletableFuture.allOf(allFuture.toArray(new CompletableFuture[allFuture.size()])).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public Long getView(Long articleId, Long dbView) {
        String viewKey = redisKeyGenerator.generate(RedisKeyEnums.ARTICLE_VIEW);
        Double score = redisTemplate.opsForZSet().score(viewKey, articleId + "");
        if (null == score || score.longValue() == 0L) {
            redisTemplate.opsForZSet().add(viewKey, articleId + "", dbView);
            return dbView;
        } else {
            return score.longValue();

        }
    }

    @Override
    public ArticleVo findById(Long id) {
        ArticleEntity articleEntity = iArticleManage.findById(id);
        if (articleEntity == null) {
            return null;
        }
        return BeanUtils.copyProperties(articleEntity, ArticleVo.class);
    }

    @Override
    public List<ArticleVo> findList(ArticleVo articleVo) {
        ArticleEntity ArticleEntity = BeanUtils.copyProperties(articleVo, ArticleEntity.class);
        List<ArticleEntity> list = iArticleManage.list(ArticleEntity);
        return BeanUtils.copyList(list, ArticleVo.class);
    }

    @Override
    public List<ArticleVo> findByIds(List<Long> ids) {
        List<ArticleEntity> entities = iArticleManage.findByIds(ids);
        List<ArticleVo> articleVos = BeanUtils.copyList(entities, ArticleVo.class);
        CompletableFuture<Void> classifyCompletableFuture = CompletableFuture.runAsync(() -> {
            // 分类名
            List<Long> classifyIds = articleVos.stream().filter(a -> null != a.getClassifyId()).map(ArticleVo::getClassifyId).distinct().collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(classifyIds)) {
                List<ClassifyVo> classifyVoList = classifyService.findByIds(classifyIds);
                for (ArticleVo articleVo : articleVos) {
                    for (ClassifyVo classifyVo : classifyVoList) {
                        if (null != articleVo.getClassifyId() && articleVo.getClassifyId().equals(classifyVo.getId())) {
                            articleVo.setClassifyName(classifyVo.getName());
                        }
                    }
                }
            }
        }, executor);
        CompletableFuture<Void> imageCompletableFuture = CompletableFuture.runAsync(() -> {
            // 头图

            List<String> collect = articleVos.stream().filter(a -> StrUtil.isNotBlank(a.getImage())).map(ArticleVo::getImage).distinct().collect(Collectors.toList());

            Map<String, String> addHosts = fileTemplatePlus.addHost(collect);
            for (ArticleVo articleVo : articleVos) {
                articleVo.setImage(addHosts.getOrDefault(articleVo.getImage(), ""));
            }
        }, executor);


        try {
            CompletableFuture.allOf(classifyCompletableFuture, imageCompletableFuture).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return articleVos;
    }

    //    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long save(ArticleVo articleVo) {
        ArticleEntity articleEntity = BeanUtils.copyProperties(articleVo, ArticleEntity.class);
        articleEntity.setId(snowflake.nextId());
        if (StrUtil.isBlank(articleVo.getContent())) {
            return null;
        }
        articleInfoService.save(ArticleInfoVo.builder().articleId(articleEntity.getId()).content(articleVo.getContent()).build());
        if (StrUtil.isBlank(articleEntity.getImage())) {
            // 随机的头图
            articleEntity.setImage(fileTemplatePlus.removeHost(fileTemplatePlus.randomImage()));
        }
        String desp = getIntroduction(articleVo.getContent());
        articleEntity.setIntroduction(desp);
        iArticleManage.insert(articleEntity);
        return articleEntity.getId();
    }

    /**
     * <p>文章介绍</p>
     *
     * @param content
     * @return {@link String}
     * @author luyanan
     * @since 2020/6/12
     */
    public String getIntroduction(String content) {
        // 介绍/截取100个字符
        String markdown = MarkdownUtils.markdownToHtml(content);
        String text = Jsoup.parse(markdown).text();
        return text.length() > 100 ? text.substring(0, 100) : text;
    }

    @Override
    public void update(ArticleVo articleVo) {
        if (StrUtil.isNotBlank(articleVo.getContent())) {
            String desp = getIntroduction(articleVo.getContent());
            articleVo.setIntroduction(desp);
            articleInfoService.update(ArticleInfoVo.builder().articleId(articleVo.getId()).content(articleVo.getContent()).build());
            ArticleEntity articleEntity = BeanUtils.copyProperties(articleVo, ArticleEntity.class);
            iArticleManage.update(articleEntity);
            // 发送修改事件
            applicationContext.publishEvent(new ArticleEvent(EventSourceVo
                    .builder()
                    .id(articleEntity.getId().toString())
                    .type(EventSourceVo.Type.UPDATE)
                    .build()));
        }

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        if (ids.isEmpty()) {
            return;
        }
        for (Long id : ids) {
            articleLabelService.delete(ArticleLabelVo.builder().articleId(id).build());
        }
        iArticleManage.deleteBatch(ids);
        // 发送删除事件
        for (Long id : ids) {
            applicationContext.publishEvent(new ArticleEvent(EventSourceVo
                    .builder()
                    .id(id.toString())
                    .type(EventSourceVo.Type.DELETE)
                    .build()));
            ;
        }
    }

    @Override
    public void deleteById(Long id) {
        articleLabelService.delete(ArticleLabelVo.builder().articleId(id).build());
        iArticleManage.deleteById(ArticleEntity.builder().id(id).build());
        applicationContext.publishEvent(new ArticleEvent(EventSourceVo
                .builder()
                .id(id.toString())
                .type(EventSourceVo.Type.DELETE)
                .build()));
    }

    @Override
    public ArticleVo info(Long id) {
        ArticleEntity articleEntity = iArticleManage.findById(id);
        if (null == articleEntity) {
            throw new BusinessException("不存在");
        }
        ArticleVo articleVo = BeanUtils.copyProperties(articleEntity, ArticleVo.class);
        // 类名
        articleVo.setClassifyName(classifyService.findById(articleVo.getClassifyId()).getName());
        // 标签名称

        List<LabelVo> labelVos = labelService.findByArticleId(articleVo.getId());
        articleVo.setLabelVos(labelVos);
        // 用户名
        SysUserVo sysUserVo = sysUserApi.findById(articleVo.getUserId()).getData();
        if (null != sysUserVo) {
            articleVo.setUserName(sysUserVo.getUserName());
            articleVo.setUserVo(sysUserVo);
        }
        ArticleInfoVo articleInfoVo = articleInfoService.findById(articleVo.getId());
        if (StrUtil.isNotBlank(articleInfoVo.getContent())) {
            articleVo.setContent(MarkdownUtils.markdownToHtmlExtensions(articleInfoVo.getContent()));
        }
        String viewKey = redisKeyGenerator.generate(RedisKeyEnums.ARTICLE_VIEW);

        //访问量
        Double view = redisTemplate.opsForZSet().incrementScore(viewKey, articleVo.getId() + "", 1);
        articleVo.setView(view.longValue());

        articleVo.setSourceVal(ConstantEnum.getByTypeAndValue(ConstantEnum.EnumType.ARTICLE_SOURCE_TYPE, articleVo.getSource()).getDesp());

        // 评论
        articleVo.setCommentabled(true);
        List<CommentVo> commentVos = commentService.findByArticleId(id);
        articleVo.setComments(commentVos);
        return articleVo;
    }

    @Override
    public PageInfo<ArticleVo> findPageByLabelId(PageRequestParams<ArticleLabelVo> pageRequestParams) {

        PageInfo<ArticleLabelVo> pageInfo = articleLabelService.findByPage(pageRequestParams);
        List<Long> articleIds = pageInfo
                .getContent()
                .stream()
                .map(ArticleLabelVo::getArticleId)
                .distinct()
                .collect(Collectors.toList());

        List<ArticleVo> articleVoList = this.findByIds(articleIds);
        setExtField(articleVoList);

        return new PageInfo(articleVoList, pageInfo.getTotalElements(), pageRequestParams);
    }

    @Override
    public void synView() {

        String viewKey = redisKeyGenerator.generate(RedisKeyEnums.ARTICLE_VIEW);
        if (!redisTemplate.hasKey(viewKey)) {
            return;
        }

        Set range = redisTemplate.opsForZSet().range(viewKey, 0, -1);
        for (Object o : range) {
            Double score = redisTemplate.opsForZSet().score(viewKey, o);
            iArticleManage.update(ArticleEntity.builder().id(

                    Long.valueOf(o.toString())).view(score.longValue()).build());
        }
//        redisTemplate.opsForZSet().keys(viewKey).forEach(a -> {
//            Double view = redisTemplate.opsForZSet().score(viewKey, a.toString());
//            iArticleManage.update(ArticleEntity.builder().id(Long.valueOf(a.toString())).view(view.longValue()).build());
//        });
        log.info("文章访问量同步结束");
    }


    @Override
    public void updateStatus(List<Long> articleIds, Integer status) {


        if (CollectionUtil.isEmpty(articleIds)) {
            return;
        }

        // 当状态为上架的时候,发布到MQ中进行处理
        if (status.equals(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue())) {
            articleMqService.enableArticle(articleIds);
            //  发布上架事件
            applicationContext.publishEvent(new ArticleEvent(EventSourceVo
                    .builder()
                    .type(EventSourceVo.Type.ENABLE_STATUS)
                    .build()));
        } else {
            if (ConstantEnum.ARTICLE_STATUS_STOP.equalsValue(status)) {

                List<ArticleEntity> entities = this.iArticleManage.findByIds(articleIds);
                for (ArticleEntity entity : entities) {
                    if (ConstantEnum.ARTICLE_STATUS_ENABLE.equalsValue(entity.getStatus())) {
                        // 当为下架并且以前的状态为上架的时候,发布下架事件
                        applicationContext.publishEvent(new ArticleEvent(EventSourceVo
                                .builder()
                                .id(entity.getId().toString())
                                .type(EventSourceVo.Type.STOP_STATUS)
                                .build()));
                    }
                }
            }
            iArticleManage.updateStatus(articleIds, status);

        }


    }

    @Override
    public Integer count(ArticleVo articleVo) {
        ArticleEntity articleEntity = BeanUtils.copyProperties(articleVo, ArticleEntity.class);
        return iArticleManage.count(articleEntity);
    }

    @Override
    public void update(Long gitId, Integer status) {

        iArticleManage.update(ArticleEntity.builder().status(status).build(),
                ArticleEntity.builder().gitId(gitId).build());

    }

    @Override
    public PageInfo<ArticleVo> findByPageToManage(PageRequestParams<ArticleVo> pageRequestParams) {
        ArticleVo articleVo = pageRequestParams.getParams();
        if (null == articleVo) {
            articleVo = new ArticleVo();
        }
        articleVo.setStatusList(Arrays.asList(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue()
                , ConstantEnum.ARTICLE_STATUS_CHECKING.getValue()
                , ConstantEnum.ARTICLE_STATUS_STOP.getValue()));
        pageRequestParams.setParams(articleVo);

        PageRequestParams<ArticleEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, ArticleEntity.class);
        PageInfo<ArticleEntity> entityPageInfo = iArticleManage.findByPage(params,
                null == pageRequestParams.getParams() ? null : pageRequestParams.getParams().getStatusList());
        return plusUtils.convertPageInfo(entityPageInfo, ArticleVo.class, contentList -> {
            setExtField(contentList);
            List<Long> articleIds = contentList.stream().map(ArticleVo::getId).distinct().collect(Collectors.toList());
            CompletableFuture<Void> gitSynVoListFuture = CompletableFuture.runAsync(() -> {
                //同步编码查询
                List<GitSynVo> gitSynVoList = gitSynService.findByArticleIds(articleIds);
                for (ArticleVo articleVo1 : contentList) {
                    for (GitSynVo gitSynVo : gitSynVoList) {
                        if (articleVo1.getId().equals(gitSynVo.getArticleId())) {
                            articleVo1.setSynCharset(gitSynVo.getCharset());
                        }
                    }
                }
            }, executor);

            CompletableFuture<Void> otherFuture = CompletableFuture.runAsync(() -> {
                Map<Long, ArticleInfoVo> articleInfoVoMap = articleInfoService.findByIdsToMap(articleIds);
                //添加url
                for (ArticleVo articleVo1 : contentList) {
                    ArticleInfoVo articleInfoVo = articleInfoVoMap.getOrDefault(articleVo1.getId(), null);
                    if (null != articleInfoVo && StrUtil.isNotBlank(articleInfoVo.getContent())) {
                        String content = MarkdownUtils.markdownToHtmlExtensions(articleInfoVo.getContent());
                        articleVo1.setContent(Jsoup.parse(content).text());
                        String reviewLabel = spiltReviewLabel(articleVo1, content);
                        articleVo1.setReviewLabel(reviewLabel);
                    }
                    articleVo1.setUrl(blogConfigProperties.getArticleInfoUrl() + articleVo1.getId() + "");
                }
            }, executor);

            try {
                CompletableFuture.allOf(otherFuture, gitSynVoListFuture).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void updateCharset(Long id, String charset) {

        GitSynVo gitSynVo = gitSynService.findOne(GitSynVo.builder().articleId(id).build());
        if (null == gitSynVo) {
            throw new BusinessException("文章不存在");
        }

        String path = gitSynVo.getPath();
        // 重新读取文章
        String content = FileUtil.readString(blogConfigProperties.getGitSynPath() + path, charset);
        // 重新入库
        articleInfoService.update(ArticleInfoVo.builder().articleId(id).content(content).build());
        // 修改编码
        gitSynService.update(GitSynVo.builder().id(gitSynVo.getId()).charset(charset).build());

        // 修改文章介绍
        String introduction = getIntroduction(content);
        iArticleManage.update(ArticleEntity.builder().id(id).introduction(introduction).build());

    }

    @Override
    public void rename(Long id) {


        ArticleInfoVo articleInfoVo = articleInfoService.findById(id);
        if (null == articleInfoVo || StrUtil.isBlank(articleInfoVo.getContent())) {
            throw new BusinessException("文章不存在");
        }

        ArticleEntity articleEntity = this.iArticleManage.findById(id);

        doReName(articleEntity.getId(), articleInfoVo.getContent(), articleEntity.getTitle());

    }

    private String doReName(Long id, String content, String defaultTitle) {
        String title = Arrays.stream(content.split("\n")).filter(a -> a.startsWith("#")).findFirst().orElse(defaultTitle);

        title = title.replace("#", "");
        this.iArticleManage.update(ArticleEntity.builder().id(id).title(title).build());
        return title;
    }

    @Override
    public PageInfo<ArticleVo> findByPageAndNotIn
            (PageRequestParams<ArticleVo> pageRequestParams, List<Long> notInIds) {


        PageRequestParams<ArticleEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, ArticleEntity.class);
        PageInfo<ArticleEntity> entityPageInfo = iArticleManage.findByPageAndNotIn(params,
                notInIds);
        return plusUtils.convertPageInfo(entityPageInfo, ArticleVo.class, contentList -> {
            setExtField(contentList);
            //添加url
            for (ArticleVo articleVo : contentList) {
                articleVo.setUrl(blogConfigProperties.getArticleInfoUrl() + articleVo.getId() + "");
            }
        });


    }

    @Override
    public void patrol() {
        log.info("文章巡查功能开始启动");
        // 增加文章巡查功能,将符合规格的处于审核状态的文章调整为上架
        plusUtils.bigDataList(ArticleEntity.builder().status(ConstantEnum.ARTICLE_STATUS_CHECKING.getValue()).build(), iArticleManage, data -> {
            if (CollectionUtil.isEmpty(data)) {
                return;
            }
            List<Long> enableIdList = new ArrayList<>();
            for (ArticleEntity articleEntity : data) {
                if (!matcherDiscardRule(articleEntity)) {
                    enableIdList.add(articleEntity.getId());
                }
            }

            this.updateStatus(enableIdList, ConstantEnum.ARTICLE_STATUS_ENABLE.getValue());
        });
    }


    /**
     * <p>匹配文章是否废弃</p>
     * 废弃规则
     * 1. 名称包含 readme
     * 2. 内容小于100个字符
     *
     * @param articleEntity
     * @return {@link boolean}
     * @author luyanan
     * @since 2020/7/24
     */
    public boolean matcherDiscardRule(ArticleEntity articleEntity) {

        if (articleEntity.getTitle().toLowerCase().contains("readme")) {
            return true;
        }
        ArticleInfoVo articleInfoVo = articleInfoService.findById(articleEntity.getId());
        if (null == articleInfoVo || articleInfoVo.getContent().length() < 100) {
            return true;
        }
        return false;
    }

    /**
     * <p>匹配审核标签</p>
     *
     * @param articleVo
     * @param content
     * @return {@link String}
     * @author luyanan
     * @since 2020/6/30
     */
    private String spiltReviewLabel(ArticleVo articleVo, String content) {
        List<String> lists = new ArrayList<>();
        if (articleVo.getTitle().toLowerCase().contains("readme")) {
            lists.add("名称为readme");
            // 当名称为readMe的时候,重命名名称
//            doReName(articleVo.getId(), content, articleVo.getTitle());
        }
        if (content.length() < 100) {
            lists.add("内容不足100");
        }
        return lists.stream().collect(Collectors.joining(","));
    }


}
