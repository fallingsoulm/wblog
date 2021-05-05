package com.wblog.system.entity;

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
 * 定时任务调度日志表
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@ApiModel(value = "定时任务调度日志表")
@TableName("sys_job_log")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysJobLogDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 任务日志ID
	 */
	@ApiModelProperty(value = "任务日志ID")
	@TableId(type = IdType.AUTO)
	private Long jobLogId;

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
	 * 日志信息
	 */
	@ApiModelProperty(value = "日志信息")
	private String jobMessage;

	/**
	 * 执行状态（0正常 1失败）
	 */
	@ApiModelProperty(value = "执行状态（0正常 1失败）")
	private String status;

	/**
	 * 异常信息
	 */
	@ApiModelProperty(value = "异常信息")
	private String exceptionInfo;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

}
