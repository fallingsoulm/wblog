package com.wblog.notice.service.impl;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.exception.BusinessException;
import com.wblog.notice.entity.MNoticeTemplateDo;
import com.wblog.notice.manage.IMNoticeTemplateManage;
import com.wblog.notice.message.MessageTemplate;
import com.wblog.notice.message.MessageVo;
import com.wblog.notice.service.IMNoticeMessageService;

import java.util.List;

import io.github.fallingsoulm.easy.archetype.data.id.SimpleSnowflake;
import org.springframework.stereotype.Service;

import java.util.Collection;

import com.wblog.notice.entity.MNoticeMessageDo;
import com.wblog.common.module.notice.vo.MNoticeMessageVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import com.wblog.common.utils.ConverUtils;
import com.wblog.notice.manage.IMNoticeMessageManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知消息详情 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-06-29
 */
@Service
public class MNoticeMessageServiceImpl implements IMNoticeMessageService {


    @Autowired
    private SimpleSnowflake simpleSnowflake;
    @Autowired
    private IMNoticeMessageManage iMNoticeMessageManage;

    @Autowired
    private MessageTemplate messageTemplate;
    @Autowired
    private IMNoticeTemplateManage imNoticeTemplateManage;

    @Override
    public PageInfo<MNoticeMessageVo> listByPage(PageRequestParams<MNoticeMessageVo> pageRequestParams) {
        PageRequestParams<MNoticeMessageDo> requestParams = ConverUtils.converPageRequestParams(pageRequestParams, MNoticeMessageDo.class);
        PageInfo<MNoticeMessageDo> pageInfo = iMNoticeMessageManage.listByPage(requestParams);
        return ConverUtils.converPageInfo(pageInfo, MNoticeMessageVo.class);
    }

    @Override
    public List<MNoticeMessageVo> list(MNoticeMessageVo mNoticeMessageVo) {
        MNoticeMessageDo mNoticeMessageDo = ConverUtils.conver(mNoticeMessageVo, MNoticeMessageDo.class);
        List<MNoticeMessageDo> list = iMNoticeMessageManage.list(mNoticeMessageDo);
        return ConverUtils.listConver(list, MNoticeMessageVo.class);
    }

    @Override
    public MNoticeMessageVo findById(Long id) {
        MNoticeMessageDo mNoticeMessageDo = iMNoticeMessageManage.findById(id);
        return ConverUtils.conver(mNoticeMessageDo, MNoticeMessageVo.class);
    }

    @Override
    public void insert(MNoticeMessageVo mNoticeMessageVo) {
        MNoticeMessageDo mNoticeMessageDo = ConverUtils.conver(mNoticeMessageVo, MNoticeMessageDo.class);
        mNoticeMessageDo.setId(simpleSnowflake.nextId());
        mNoticeMessageDo.setStatus(ConstantEnum.NOTICE_STATUS_STOP.getValue());
        iMNoticeMessageManage.insert(mNoticeMessageDo);
    }

    @Override
    public void update(MNoticeMessageVo mNoticeMessageVo) {
        MNoticeMessageDo mNoticeMessageDo = ConverUtils.conver(mNoticeMessageVo, MNoticeMessageDo.class);
        iMNoticeMessageManage.update(mNoticeMessageDo);
    }

    @Override
    public void deleteByIds(Collection<Long> ids) {
        iMNoticeMessageManage.deleteBatch(ids);
    }

    @Override
    public void sendMessage(String token, String title, String content, String template) {

        MNoticeTemplateDo templateDo = imNoticeTemplateManage.findOne(MNoticeTemplateDo.builder().token(token).build());
        if (null == templateDo) {
            throw new BusinessException("token不存在");
        }

        messageTemplate.sendMessage(MessageVo
                .builder()
                .messageType(templateDo.getNoticeType())
                .sendTime(templateDo.getSendTime())
                .content(content)
                .title(title)
                .templateId(templateDo.getId())
                .invokeTarget(templateDo.getInvokeTarget())
                .sendType(templateDo.getSendType())
                .template(template)
                .build());

    }

}