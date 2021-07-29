package com.wblog.search.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.api.ArticleViewComponent;
import com.wblog.common.module.system.api.SysUserApi;
import com.wblog.common.module.system.vo.SysUserVo;
import com.wblog.search.vo.ArticleSearchVo;
import io.github.fallingsoulm.easy.archetype.data.file.FileTemplate;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import lombok.SneakyThrows;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2021/7/18
 **/
@Service
public class InfoElasticsearchService extends AbstractElasticsearchService {

    @Autowired
    private SysUserApi sysUserApi;
    @Autowired
    private FileTemplate fileTemplate;

    @Autowired
    private ArticleViewComponent articleViewComponent;

    @Override
    protected String type() {
        return searchProperties.getInfoType();
    }

    @SneakyThrows
    public PageInfo findByPage(PageRequestParams<ArticleSearchVo> pageRequestParams) {
        // 排序, 按照创建时间排序/按照访问量排序
        ArticleSearchVo params = pageRequestParams.getParams();
        if (null == params) {
            params = new ArticleSearchVo();
        }
        if (null == params.getOrderBy()) {
            params.setOrderBy(ConstantEnum.ARTICLE_ORDER_BY_NEWEST.getValue());
        }
        SearchRequest searchRequest = new SearchRequest(type());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (ConstantEnum.ARTICLE_ORDER_BY_NEWEST.getValue().equals(params.getOrderBy())) {
            // 根据创建时间排序
            params.setOrderBy(null);
            Map<String, Object> searchField = getSearchField(params);
            if (CollectionUtil.isEmpty(searchField)) {
                searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            } else {


                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                for (Map.Entry<String, Object> entry : searchField.entrySet()) {
                    QueryBuilder matchQuery = null;
                    if (entry.getValue() instanceof String) {
                        if (StrUtil.isNotBlank((String) entry.getValue())) {
                            matchQuery = QueryBuilders.matchQuery(entry.getKey(), entry.getValue());
                            boolQueryBuilder.must(matchQuery);
                        }
                    } else {
                        matchQuery = QueryBuilders.termQuery(entry.getKey(), entry.getValue());
                        boolQueryBuilder.must(matchQuery);
                    }


                }


                searchSourceBuilder.query(boolQueryBuilder);

            }
            // 分页
            searchSourceBuilder.from(pageRequestParams.getOffset()).size(pageRequestParams.getPageSize());
        } else if (ConstantEnum.ARTICLE_ORDER_BY_VIEW.getValue().equals(params.getOrderBy())) {
            // 根据访问量排序

            LinkedHashSet<String> articleIds = articleViewComponent.getListByView(pageRequestParams);
//            searchSourceBuilder.query(QueryBuilders.termsQuery("id", articleIds));
            searchSourceBuilder.query(QueryBuilders.idsQuery().addIds(articleIds.toArray(new String[]{})));
        }

        // 排除字段
        searchSourceBuilder.fetchSource(null, "content");
        searchSourceBuilder.sort("createTime", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);


        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        List<ArticleSearchVo> articleSearchVos = Arrays.stream(response.getHits().getHits()).map(a -> JSON.parseObject(a.getSourceAsString(), ArticleSearchVo.class)).collect(Collectors.toList());


        // 用户id
        List<Long> userIdList = articleSearchVos.stream().map(a -> a.getUserId()).filter(a -> null != a).collect(Collectors.toList());
        List<SysUserVo> sysUserVos = null;
        if (CollectionUtil.isNotEmpty(userIdList)) {
            sysUserVos = sysUserApi.findByIds(userIdList).getData();
        }
        for (ArticleSearchVo searchVo : articleSearchVos) {
            searchVo.setImage(fileTemplate.addHost(searchVo.getImage()));
            if (CollectionUtil.isNotEmpty(searchVo.getLabelVos())) {
                // 这里只返回给前端10个标签
                searchVo.setLabelVos(searchVo.getLabelVos().subList(0,
                        (searchVo.getLabelVos().size() > 10 ? 10 : searchVo.getLabelVos().size())));
                searchVo.setView(articleViewComponent.getView(searchVo.getClassify(), searchVo.getId(), null));
                if (CollectionUtil.isNotEmpty(sysUserVos)) {
                    for (SysUserVo sysUserVo : sysUserVos) {
                        if (null != searchVo.getUserId() && searchVo.getUserId().equals(sysUserVo.getUserId())) {
                            searchVo.setUserName(sysUserVo.getUserName());
                        }
                    }
                }
            }
        }
        return new PageInfo(articleSearchVos, response.getHits().getTotalHits().value, pageRequestParams);
    }


    /**
     * 查询的字段
     *
     * @param articleSearchVo 查询参数
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @since 2021/7/24
     */
    private Map<String, Object> getSearchField(ArticleSearchVo articleSearchVo) {
        Field[] fields =
                ReflectUtil.getFields(articleSearchVo.getClass());
        Map<String, Object> result = new HashMap<>(fields.length);

        Long labelId = null;
        if (null != articleSearchVo.getLabelId()) {
            labelId = articleSearchVo.getLabelId();
        }
        articleSearchVo.setLabelId(null);
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            Object fieldValue = ReflectUtil.getFieldValue(articleSearchVo, field);
            if (null == fieldValue) {
                continue;
            }

            result.put(field.getName(), fieldValue);
        }
        if (null != labelId) {
            result.put("labelVos.id", labelId);
        }
        return result;
    }

    @SneakyThrows
    public List<ArticleSearchVo> findByIds(List<Long> ids, Integer classify) {
        if (CollectionUtil.isEmpty(ids)) {
            return new ArrayList<>();
        }
        SearchRequest searchRequest = new SearchRequest(type());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder
                .query(QueryBuilders.idsQuery()
                        .addIds(ids.stream().map(a -> classify + "-" + a).collect(Collectors.toList()).toArray(new String[]{})));

        // 排除字段
        searchSourceBuilder.fetchSource(null, "content");
        searchSourceBuilder.sort("createTime", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        if (response.status().getStatus() != RestStatus.OK.getStatus()) {
            return new ArrayList<>();
        }

        List<ArticleSearchVo> articleSearchVos = Arrays.stream(response.getHits().getHits()).map(a -> JSON.parseObject(a.getSourceAsString(), ArticleSearchVo.class)).collect(Collectors.toList());

        return articleSearchVos;
    }

}
