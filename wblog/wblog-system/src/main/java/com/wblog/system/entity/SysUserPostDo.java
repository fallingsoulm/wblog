package com.wblog.system.entity;

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
 * 用户与岗位关联表
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@ApiModel(value = "用户与岗位关联表")
@TableName("sys_user_post")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserPostDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID")
	private Long userId;

	/**
	 * 岗位ID
	 */
	@ApiModelProperty(value = "岗位ID")
	private Long postId;

}
