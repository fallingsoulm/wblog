package com.wblog.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 定时任务调度表
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@ApiModel(value = "定时任务调度表")
@TableName("sys_job")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysJobDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 任务ID
	 */
	@ApiModelProperty(value = "任务ID")
	@TableId(type = IdType.AUTO)
	private Long jobId;

	/**
	 * 任务名称
	 */
	@ApiModelProperty(value = "任务名称")
	private String jobName;

	/**
	 * 任务组名
	 */
	@ApiModelProperty(value = "任务组名")
	private String jobGroup;

	/**
	 * 调用目标字符串
	 */
	@ApiModelProperty(value = "调用目标字符串")
	private String invokeTarget;

	/**
	 * cron执行表达式
	 */
	@ApiModelProperty(value = "cron执行表达式")
	private String cronExpression;

	/**
	 * 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
	 */
	@ApiModelProperty(value = "计划执行错误策略（1立即执行 2执行一次 3放弃执行）")
	private String misfirePolicy;

	/**
	 * 是否并发执行（0允许 1禁止）
	 */
	@ApiModelProperty(value = "是否并发执行（0允许 1禁止）")
	private String concurrent;

	/**
	 * 状态（0正常 1暂停）
	 */
	@ApiModelProperty(value = "状态（0正常 1暂停）")
	private String status;

	/**
	 * 创建者
	 */
	@ApiModelProperty(value = "创建者")
	private String createBy;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**
	 * 更新者
	 */
	@ApiModelProperty(value = "更新者")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

	/**
	 * 备注信息
	 */
	@ApiModelProperty(value = "备注信息")
	private String remark;

}
