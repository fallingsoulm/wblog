package com.wblog.social.service.impl;

import com.wblog.social.manage.ISSocialManage ;
import java.util.List ;
import com.wblog.social.entity.SSocialDo ;
import org.springframework.stereotype.Service ;
import java.util.Collection ;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams ;
import org.springframework.beans.factory.annotation.Autowired ;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo ;
import com.wblog.common.utils.ConverUtils ;
import com.wblog.common.module.social.vo.SSocialVo ;
import com.wblog.social.service.ISSocialService ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 第三方平台表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-07-05
*/
@Service
public class SSocialServiceImpl  implements ISSocialService {


   @Autowired
   private ISSocialManage iSSocialManage;

    @Override
    public PageInfo<SSocialVo> listByPage(PageRequestParams<SSocialVo> pageRequestParams) {
        PageRequestParams<SSocialDo> requestParams = ConverUtils.converPageRequestParams(pageRequestParams, SSocialDo.class);
        PageInfo<SSocialDo> pageInfo = iSSocialManage.listByPage(requestParams);
        return ConverUtils.converPageInfo(pageInfo, SSocialVo.class);
    }

    @Override
    public List<SSocialVo> list(SSocialVo sSocialVo) {
        SSocialDo sSocialDo = ConverUtils.conver(sSocialVo, SSocialDo.class);
        List<SSocialDo> list = iSSocialManage.list(sSocialDo);
        return ConverUtils.listConver(list, SSocialVo.class);
    }

    @Override
    public SSocialVo findById(Long id) {
        SSocialDo sSocialDo = iSSocialManage.findById(id);
        return ConverUtils.conver(sSocialDo, SSocialVo.class);
    }

    @Override
    public void insert(SSocialVo sSocialVo) {
        SSocialDo sSocialDo = ConverUtils.conver(sSocialVo, SSocialDo.class);
        iSSocialManage.insert(sSocialDo);
    }

    @Override
    public void update(SSocialVo sSocialVo) {
        SSocialDo sSocialDo = ConverUtils.conver(sSocialVo, SSocialDo.class);
        iSSocialManage.update(sSocialDo);
    }

    @Override
    public void deleteByIds(Collection<Long> ids) {
        iSSocialManage.deleteBatch(ids);
    }

}