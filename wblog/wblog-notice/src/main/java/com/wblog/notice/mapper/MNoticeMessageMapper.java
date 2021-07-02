package com.wblog.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper ;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus ;
import com.wblog.notice.entity.MNoticeMessageDo ;
import org.apache.ibatis.annotations.Mapper ;
/**
 * <p>
 * 通知消息详情 mapper
 * </p>
 *
 * @author luyanan
 * @since 2021-06-29
*/
@Mapper
public interface MNoticeMessageMapper extends BaseMapperPlus<MNoticeMessageDo> {


}
