package com.wblog.info.news;

import com.wblog.common.exception.BusinessException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 资讯爬取工厂
 *
 * @author luyanan
 * @since 2021/7/14
 **/
@Component
public class NewsSpiderHandlerFactory {


    @Autowired
    private ObjectProvider<NewsSpiderHandler> provider;


    /**
     * 获取处理器
     *
     * @param source
     * @return com.wblog.info.news.NewsSpiderHandler
     * @since 2021/7/14
     */
    public NewsSpiderHandler getHandler(Integer source) {
        if (null == source) {
            return null;
        }
        return provider.stream().filter(a -> a.source().getValue().equals(source))
                .findFirst().orElseThrow(() -> new BusinessException("未找见:" + source + "的资讯爬虫处理器"));
    }


    /**
     * 获取所有的处理器
     *
     * @return java.util.List<com.wblog.info.news.NewsSpiderHandler>
     * @since 2021/7/14
     */
    public List<NewsSpiderHandler> getAllHandler() {
        return provider.stream().collect(Collectors.toList());
    }

}
