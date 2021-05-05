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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@ApiModel(value = "字典类型表")
@TableName("sys_dict_type")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysDictTypeDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 字典主键
	 */
	@ApiModelProperty(value = "字典主键")
	@TableId(type = IdType.AUTO)
	private Long dictId;

	/**
	 * 字典名称
	 */
	@NotBlank(message = "字典名称不能为空")
	@Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
	@ApiModelProperty(value = "字典名称")
	@TableField(condition = SqlCondition.LIKE)
	private String dictName;

	/**
	 * 字典类型
	 */
	@NotBlank(message = "字典类型不能为空")
	@Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
	@ApiModelProperty(value = "字典类型")
	@TableField(condition = SqlCondition.LIKE)
	private String dictType;

	/**
	 * 状态（0正常 1停用）
	 */
	@ApiModelProperty(value = "状态（0正常 1停用）")
	private String status;

	/**
	 * 创建者
	 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建者")
	private String createBy;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**
	 * 更新者
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(value = "更新者")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;

}
