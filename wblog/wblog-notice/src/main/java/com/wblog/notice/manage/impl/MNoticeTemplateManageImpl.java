package com.wblog.notice.manage.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl ;
import com.wblog.notice.manage.IMNoticeTemplateManage ;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl ;
import com.wblog.notice.entity.MNoticeTemplateDo ;
import com.wblog.notice.mapper.MNoticeTemplateMapper ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 通知消息之消息模板 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-06-29
*/
@Service
public class MNoticeTemplateManageImpl extends ManageImpl<MNoticeTemplateMapper, MNoticeTemplateDo> implements IMNoticeTemplateManage {

}
