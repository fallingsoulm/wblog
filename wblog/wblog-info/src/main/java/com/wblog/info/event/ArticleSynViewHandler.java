//package com.wblog.info.event;
//
//import com.wblog.info.service.IArticleService;
//import io.github.fallingsoulm.easy.archetype.framework.spring.event.handler.IShutdownHandler;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.ContextClosedEvent;
//import org.springframework.stereotype.Component;
//
///**
// * 文章同步访问量
// *
// * @author luyanan
// * @since 2021/6/27
// **/
//@Component
//@Slf4j
//public class ArticleSynViewHandler implements IShutdownHandler {
//    @Autowired
//    private IArticleService articleService;
//
//    @Override
//    public void run(ContextClosedEvent contextClosedEvent) {
//        log.info("同步文章访问量");
//        articleService.synView();
//    }
//}
