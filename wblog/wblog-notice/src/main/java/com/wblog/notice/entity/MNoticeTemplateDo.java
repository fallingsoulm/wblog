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
 * 通知消息之消息模板
 * </p>
 *
 * @author luyanan
 * @since 2021-06-29
 */
@ApiModel(value = "通知消息之消息模板")
@TableName("m_notice_template")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MNoticeTemplateDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.INPUT)
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
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
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
