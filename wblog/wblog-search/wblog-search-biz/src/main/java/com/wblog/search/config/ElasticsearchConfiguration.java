package com.wblog.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * elasticsearch的配置类
 *
 * @author luyanan
 * @since 2021/7/18
 **/
@Configuration
public class ElasticsearchConfiguration {


    @Value("${es.host}")
    private String host;


    @Value("${es.port}")
    private Integer port;


    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, "http")));
    }


}
