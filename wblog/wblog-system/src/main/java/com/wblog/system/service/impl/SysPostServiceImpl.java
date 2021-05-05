package com.wblog.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.common.exception.BusinessException;
import com.wblog.system.SystemMsgCode;
import com.wblog.system.entity.SysPostDo;
import com.wblog.system.entity.SysUserPostDo;
import com.wblog.system.manage.ISysPostManage;
import com.wblog.system.service.ISysPostService;
import com.wblog.system.service.ISysUserPostService;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 岗位信息表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysPostServiceImpl implements ISysPostService {

    @Autowired
    private ISysPostManage iSysPostManage;

    @Autowired
    private ISysUserPostService sysUserPostService;

    @Override
    public List<SysPostDo> list(SysPostDo sysPostDo) {
        return iSysPostManage.list(sysPostDo);
    }

    @Override
    public List<SysPostDo> listByUserId(Long userId) {

        List<SysUserPostDo> userPostDos = sysUserPostService.list(SysUserPostDo.builder().userId(userId).build());

        if (CollectionUtil.isEmpty(userPostDos)) {
            return new ArrayList<>();
        }
        //根据岗位id查询
        return iSysPostManage.findByIds(userPostDos.stream().map(a -> a.getPostId()).distinct().collect(Collectors.toList()));
    }

    @Override
    public PageInfo<SysPostDo> findByPage(PageRequestParams<SysPostDo> pageRequestParams) {
        return iSysPostManage.listByPage(pageRequestParams);
    }

    @Override
    public SysPostDo findById(Long postId) {
        return iSysPostManage.findById(postId);
    }

    @Override
    public void insert(SysPostDo post) {
        // 岗位名称唯一
        if (iSysPostManage.count(SysPostDo.builder().postName(post.getPostName()).build()) > 0) {
            throw new BusinessException(SystemMsgCode.POST_NAME_UNIQUE);
        }
        // 岗位编码唯一
        if (iSysPostManage.count(SysPostDo.builder().postCode(post.getPostCode()).build()) > 0) {
            throw new BusinessException(SystemMsgCode.POST_CODE_UNIQUE);
        }
        iSysPostManage.insert(post);

    }

    @Override
    public void update(SysPostDo post) {
        SysPostDo oldPost = iSysPostManage.findById(post.getPostId());
        // 岗位名称唯一
        if (!oldPost.getPostName().equals(post.getPostName()) &&
                iSysPostManage.count(SysPostDo.builder().postName(post.getPostName()).build()) > 0) {
            throw new BusinessException(SystemMsgCode.POST_NAME_UNIQUE);
        }
        // 岗位编码唯一
        if (!oldPost.getPostCode().equals(post.getPostCode()) &&
                iSysPostManage.count(SysPostDo.builder().postCode(post.getPostCode()).build()) > 0) {
            throw new BusinessException(SystemMsgCode.POST_CODE_UNIQUE);
        }
        iSysPostManage.update(post);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iSysPostManage.deleteBatch(ids);
    }
}
