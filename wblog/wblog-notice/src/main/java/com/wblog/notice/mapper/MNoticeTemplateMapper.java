package com.wblog.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper ;
import com.wblog.notice.entity.MNoticeTemplateDo ;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus ;
import org.apache.ibatis.annotations.Mapper ;
/**
 * <p>
 * 通知消息之消息模板 mapper
 * </p>
 *
 * @author luyanan
 * @since 2021-06-29
*/
@Mapper
public interface MNoticeTemplateMapper extends BaseMapperPlus<MNoticeTemplateDo> {


}
