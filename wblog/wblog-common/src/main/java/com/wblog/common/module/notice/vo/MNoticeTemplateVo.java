package com.wblog.common.module.notice.vo;

import java.util.Date;

import com.sun.prism.shader.AlphaOne_LinearGradient_AlphaTest_Loader;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 通知消息之消息模板
 * </p>
 *
 * @author luyanan
 * @since 2021-06-29
 */
@ApiModel(value = "通知消息之消息模板")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MNoticeTemplateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * token,发送消息的凭证
     */
    @ApiModelProperty(value = "token,发送消息的凭证")
    private String token;

    /**
     * 消息名称
     */
    @ApiModelProperty(value = "消息名称")
    private String name;

    /**
     * 通知的消息的类型
     */
    @ApiModelProperty(value = "通知的消息的类型")
    private Integer noticeType;

    /**
     * 调用对象
     */
    @ApiModelProperty(value = "调用对象")
    private String invokeTarget;

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
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private Long updateBy;

    /**
     * 发送类型,0：立即发送, 1:定时发送
     */
    @ApiModelProperty(value = "发送类型,0：立即发送, 1:定时发送")
    private Integer sendType;

    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    private String sendTime;


    /**
     * 状态
     *
     * @since 2021/6/30
     */
    @ApiModelProperty(value = "状态")
    private Integer status;


    /**
     * 用户id
     *
     * @since 2021/6/30
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;
}
