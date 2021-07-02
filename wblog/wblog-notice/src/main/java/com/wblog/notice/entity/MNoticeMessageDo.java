package com.wblog.notice.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 通知消息详情
 * </p>
 *
 * @author luyanan
 * @since 2021-06-29
 */
@ApiModel(value = "通知消息详情")
@TableName("m_notice_message")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MNoticeMessageDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "")
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private Long templateId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String content;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 发送时间
     */

    @ApiModelProperty(value = "发送时间")
    private Date sendTime;

    /**
     * 消息模板,html/json
     */
    @ApiModelProperty(value = "消息模板,html/json")
    private Integer template;


    /**
     * 状态
     *
     * @since 2021/6/30
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

}
