package com.wblog.social.manage.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl ;
import com.wblog.social.manage.ISSocialManage ;
import com.wblog.social.mapper.SSocialMapper ;
import com.wblog.social.entity.SSocialDo ;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 第三方平台表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-07-05
*/
@Service
public class SSocialManageImpl extends ManageImpl<SSocialMapper, SSocialDo> implements ISSocialManage {

}
