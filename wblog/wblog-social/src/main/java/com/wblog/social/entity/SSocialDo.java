package com.wblog.social.entity;

import java.util.Date ;
import  io.swagger.annotations.ApiModelProperty ;
import io.swagger.annotations.ApiModel ;
import com.baomidou.mybatisplus.annotation.* ;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * <p>
 * 第三方平台表
 * </p>
 *
 * @author luyanan
 * @since 2021-07-05
*/
@ApiModel(value = "第三方平台表")
@TableName("s_social")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SSocialDo implements Serializable{

private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
    * 平台
    */
    @ApiModelProperty(value = "平台")
    private String platform;

    /**
    * 客户端id
    */
    @ApiModelProperty(value = "客户端id")
    private String clientId;

    /**
    * 客户端密钥
    */
    @ApiModelProperty(value = "客户端密钥")
    private String clientSecret;

    /**
    * 应用id(企业微信使用)
    */
    @ApiModelProperty(value = "应用id(企业微信使用)")
    private String agentId;

    /**
    * 回调地址
    */
    @ApiModelProperty(value = "回调地址")
    private String redirectUri;

    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
    * 修改人
    */
    @ApiModelProperty(value = "修改人")
    private Long updateBy;

    /**
    * 修改时间
    */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
    * 用户id
    */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
    * 状态 在线/不在线
    */
    @ApiModelProperty(value = "状态 在线/不在线")
    private Integer status;

    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;



}
