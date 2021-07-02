package com.wblog.notice.manage.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl ;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl ;
import com.wblog.notice.entity.MNoticeMessageDo ;
import com.wblog.notice.mapper.MNoticeMessageMapper ;
import com.wblog.notice.manage.IMNoticeMessageManage ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 通知消息详情 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-06-29
*/
@Service
public class MNoticeMessageManageImpl extends ManageImpl<MNoticeMessageMapper, MNoticeMessageDo> implements IMNoticeMessageManage {

}
