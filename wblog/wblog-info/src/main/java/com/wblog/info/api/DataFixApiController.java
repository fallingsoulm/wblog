package com.wblog.info.api;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.component.FileTemplatePlus;
import com.wblog.info.entity.NewsEntity;
import com.wblog.info.entity.NewsInfoEntity;
import com.wblog.info.manage.INewsInfoManage;
import com.wblog.info.manage.INewsManage;
import com.wblog.info.mq.service.ILabelMqService;
import com.wblog.info.news.NewsSpiderHandler;
import com.wblog.info.news.NewsSpiderHandlerFactory;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 数据修复接口
 *
 * @author luyanan
 * @since 2021/7/17
 **/
@Slf4j
@RestController
@RequestMapping("data/fix")
public class DataFixApiController {

    @Autowired
    private INewsManage newsManage;

    @Autowired
    private INewsInfoManage newsInfoManage;

    @Autowired
    private MybatisPlusUtils mybatisPlusUtils;

    @Autowired
    private NewsSpiderHandlerFactory newsSpiderHandlerFactory;

    @Autowired
    private FileTemplatePlus fileTemplatePlus;

    @Autowired
    private ILabelMqService labelMqService;

    /**
     * 资讯内容修复
     *
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity
     * @since 2021/7/17
     */
    @ApiOperation(value = "资讯内容修复")
    @GetMapping("news/info")
    public RespEntity newsInfo() {
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 10,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        mybatisPlusUtils.bigDataList(NewsEntity.builder().build(), newsManage, data -> {

            for (NewsEntity news : data) {
                executorService.execute(() -> {
                    try {
                        NewsInfoEntity infoEntity = newsInfoManage.findById(news.getId());
                        if (null != infoEntity) {
                            return;
                        }
                        // 修复头图
                        String image = fileTemplatePlus.removeHost(fileTemplatePlus.randomImage());
                        newsManage.update(NewsEntity.builder().id(news.getId()).image(image).build());
                        NewsSpiderHandler handler = newsSpiderHandlerFactory.getHandler(news.getSource());
                        NewsInfoEntity content = handler.getContent(BeanUtils.copyProperties(news, NewsVo.class));
                        if (null == content) {
                            return;
                        }
                        newsInfoManage.insert(content);
                        labelMqService.articleAddLabel(news.getId(), ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue());
                        log.info("修复资讯,id:{},image:{},content:{}", news.getId(), image, content.getContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

        });
        return RespEntity.success();
    }

}
