package com.wblog.notice.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.notice.manage.IMNoticeTemplateManage;
import com.wblog.notice.entity.MNoticeTemplateDo;
import io.github.fallingsoulm.easy.archetype.data.id.SimpleSnowflake;
import io.github.fallingsoulm.easy.archetype.security.core.LoginUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import com.wblog.common.module.notice.vo.MNoticeTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import com.wblog.common.utils.ConverUtils;
import com.wblog.notice.service.IMNoticeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知消息之消息模板 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-06-29
 */
@Service
public class MNoticeTemplateServiceImpl implements IMNoticeTemplateService {

    @Autowired
    private SimpleSnowflake simpleSnowflake;

    @Autowired
    private LoginUserService loginUserService;
    @Autowired
    private IMNoticeTemplateManage iMNoticeTemplateManage;

    @Override
    public PageInfo<MNoticeTemplateVo> listByPage(PageRequestParams<MNoticeTemplateVo> pageRequestParams) {
        PageRequestParams<MNoticeTemplateDo> requestParams = ConverUtils.converPageRequestParams(pageRequestParams, MNoticeTemplateDo.class);
        PageInfo<MNoticeTemplateDo> pageInfo = iMNoticeTemplateManage.listByPage(requestParams);
        return ConverUtils.converPageInfo(pageInfo, MNoticeTemplateVo.class);
    }

    @Override
    public List<MNoticeTemplateVo> list(MNoticeTemplateVo mNoticeTemplateVo) {
        MNoticeTemplateDo mNoticeTemplateDo = ConverUtils.conver(mNoticeTemplateVo, MNoticeTemplateDo.class);
        List<MNoticeTemplateDo> list = iMNoticeTemplateManage.list(mNoticeTemplateDo);
        return ConverUtils.listConver(list, MNoticeTemplateVo.class);
    }

    @Override
    public MNoticeTemplateVo findById(Long id) {
        MNoticeTemplateDo mNoticeTemplateDo = iMNoticeTemplateManage.findById(id);
        return ConverUtils.conver(mNoticeTemplateDo, MNoticeTemplateVo.class);
    }

    @Override
    public void insert(MNoticeTemplateVo mNoticeTemplateVo) {
        MNoticeTemplateDo mNoticeTemplateDo = ConverUtils.conver(mNoticeTemplateVo, MNoticeTemplateDo.class);
        mNoticeTemplateDo.setId(simpleSnowflake.nextId());
        mNoticeTemplateDo.setToken(IdUtil.simpleUUID());
        mNoticeTemplateDo.setStatus(ConstantEnum.NOTICE_STATUS_STOP.getValue().toString());
        mNoticeTemplateDo.setUserId(loginUserService.getUserId());
        iMNoticeTemplateManage.insert(mNoticeTemplateDo);
    }

    @Override
    public void update(MNoticeTemplateVo mNoticeTemplateVo) {
        MNoticeTemplateDo mNoticeTemplateDo = ConverUtils.conver(mNoticeTemplateVo, MNoticeTemplateDo.class);
        iMNoticeTemplateManage.update(mNoticeTemplateDo);
    }

    @Override
    public void deleteByIds(Collection<Long> ids) {
        iMNoticeTemplateManage.deleteBatch(ids);
    }

}