package com.wblog.common.module.search.dto;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author luyanan
 * @since 2020/12/16
 * <p>存储的dto</p>
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {

    /**
     * <p>id</p>
     *
     * @author luyanan
     * @since 2020/12/16
     */
    private String id;
    /**
     * <p>标题</p>
     *
     * @author luyanan
     * @since 2020/12/16
     */
    private String title;

    /**
     * <p>介绍</p>
     *
     * @author luyanan
     * @since 2020/12/16
     */
    private String introduction;

    /**
     * <p>头图</p>
     *
     * @author luyanan
     * @since 2020/12/16
     */
    private String image;

    /**
     * <p>内容</p>
     *
     * @author luyanan
     * @since 2020/12/16
     */
    private String content;

    /**
     * <p>来源</p>
     *
     * @author luyanan
     * @see ""
     * @since 2020/12/16
     */
    private Integer source;


    /**
     * <p>归属</p>
     *
     * @author luyanan
     * @see com.apes.hub.api.enums.ConstantEnum
     * @since 2020/12/16
     */
    private Integer belong;

    /**
     * <p>创建时间</p>
     *
     * @author luyanan
     * @since 2020/12/16
     */
    private Date createTime;

    /**
     * <p>创建用户Id</p>
     *
     * @author luyanan
     * @since 2020/12/16
     */
    private Long userId;

    public String getSearchId() {
        if (StrUtil.isNotBlank(this.id) && null != belong) {
            return this.belong + "-" + this.id;
        }
        return null;
    }


}
