package com.wblog.info.mapper;

import com.apes.hub.api.module.info.vo.LabelVo;
import com.apes.hub.data.mp3.BaseMapperPlus;
import com.apes.hub.info.entity.ArticleLabelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章与标签关联 mapper
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Mapper
public interface ArticleLabelMapper extends BaseMapperPlus<ArticleLabelEntity> {


    /**
     * <p>查询数量</p>
     *
     * @param params
     * @return {@link Integer}
     * @author luyanan
     * @since 2020/6/15
     */
    Integer findAndCount(@Param("params") LabelVo params);

    /**
     * <p>查询并且统计每个标签的数量</p>
     *
     * @param params
     * @param offset
     * @param limit
     * @return {@link List< LabelVo>}
     * @author luyanan
     * @since 2020/6/15
     */
    List<LabelVo> listAndCount(@Param("params") LabelVo params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
