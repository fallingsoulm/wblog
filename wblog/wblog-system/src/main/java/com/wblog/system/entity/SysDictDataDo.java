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
 * 字典数据表
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@ApiModel(value = "字典数据表")
@TableName("sys_dict_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysDictDataDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 字典编码
	 */
	@ApiModelProperty(value = "字典编码")
	@TableId(type = IdType.AUTO)
	private Long dictCode;

	/**
	 * 字典排序
	 */
	@ApiModelProperty(value = "字典排序")
	private Integer dictSort;

	/**
	 * 字典标签
	 */
	@NotBlank(message = "字典标签不能为空")
	@Size(min = 0, max = 100, message = "字典标签长度不能超过100个字符")
	@ApiModelProperty(value = "字典标签")
	@TableField(condition = SqlCondition.LIKE)
	private String dictLabel;

	/**
	 * 字典键值
	 */
	@NotBlank(message = "字典键值不能为空")
	@Size(min = 0, max = 100, message = "字典键值长度不能超过100个字符")
	@ApiModelProperty(value = "字典键值")
	@TableField(condition = SqlCondition.LIKE)
	private String dictValue;

	/**
	 * 字典类型
	 */
	@NotBlank(message = "字典类型不能为空")
	@Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
	@ApiModelProperty(value = "字典类型")
	@TableField(condition = SqlCondition.LIKE)
	private String dictType;

	/**
	 * 样式属性（其他样式扩展）
	 */
	@Size(min = 0, max = 100, message = "样式属性长度不能超过100个字符")
	@ApiModelProperty(value = "样式属性（其他样式扩展）")
	private String cssClass;

	/**
	 * 表格回显样式
	 */
	@ApiModelProperty(value = "表格回显样式")
	private String listClass;

	/**
	 * 是否默认（Y是 N否）
	 */
	@ApiModelProperty(value = "是否默认（Y是 N否）")
	private String isDefault;

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
