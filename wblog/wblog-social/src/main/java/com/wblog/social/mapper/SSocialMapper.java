package com.wblog.social.mapper;

import com.wblog.social.entity.SSocialDo ;
import com.baomidou.mybatisplus.core.mapper.BaseMapper ;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus ;
import org.apache.ibatis.annotations.Mapper ;
/**
 * <p>
 * 第三方平台表 mapper
 * </p>
 *
 * @author luyanan
 * @since 2021-07-05
*/
@Mapper
public interface SSocialMapper extends BaseMapperPlus<SSocialDo> {


}
