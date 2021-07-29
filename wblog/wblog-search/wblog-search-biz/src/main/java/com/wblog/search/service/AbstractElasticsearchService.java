package com.wblog.search.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.wblog.search.config.SearchProperties;
import com.wblog.search.vo.ArticleSearchVo;
import lombok.SneakyThrows;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * 抽象类
 *
 * @author luyanan
 * @since 2021/7/18
 **/
public abstract class AbstractElasticsearchService {


    @Autowired
    protected SearchProperties searchProperties;

    @Autowired
    protected RestHighLevelClient restHighLevelClient;

    /**
     * es 索引
     *
     * @return java.lang.String
     * @since 2021/7/18
     */
    protected abstract String type();

    /**
     * 根据id删除
     *
     * @param id id
     * @return void
     * @since 2021/7/18
     */
    @SneakyThrows
    public void deleteById(String id) {

        DeleteRequest deleteRequest = new DeleteRequest(type(), id);
        DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete);
    }

    /**
     * 删除
     *
     * @param entity
     * @return void
     * @since 2021/7/18
     */
    @SneakyThrows
    public void delete(Object entity) {
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(searchProperties.getInfoType());
        if (null == entity) {
            deleteByQueryRequest.setQuery(QueryBuilders.matchAllQuery());
        }
        Field[] fields =
                ReflectUtil.getFields(entity.getClass());

        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            Object fieldValue = ReflectUtil.getFieldValue(entity, field);
            if (null != fieldValue) {
                deleteByQueryRequest.setQuery(QueryBuilders.termQuery(field.getName(), fieldValue));
            }
        }
        BulkByScrollResponse bulkByScrollResponse = restHighLevelClient.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
        int batches = bulkByScrollResponse.getBatches();
        System.out.println(batches);
    }

    @SneakyThrows
    public void update(String source, String id) {
        if (StrUtil.isBlank(source) || StrUtil.isBlank(id)) {
            return;
        }
        Assert.notBlank(searchProperties.getInfoType(), "info的type不能为空");
        IndexRequest indexRequest = new IndexRequest(searchProperties.getInfoType());
        indexRequest.id(id);
        indexRequest.source(source, XContentType.JSON);
        //执行创建索引和保存数据
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index);
    }


}
