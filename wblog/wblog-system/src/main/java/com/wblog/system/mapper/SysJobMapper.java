package com.wblog.system.mapper;

import com.wblog.system.entity.SysJobDo;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 定时任务调度表 mapper
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Mapper
public interface SysJobMapper extends BaseMapperPlus<SysJobDo> {

}
