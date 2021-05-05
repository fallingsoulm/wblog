package com.wblog.common.module.system.api;

import com.wblog.common.exception.BusinessException;
import com.wblog.common.exception.IMsgCode;
import com.wblog.common.module.system.vo.SysUserVo;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/12/21
 * <p>SysUserApi 的熔断</p>
 **/
@Component
public class SysUserApiFallBack implements SysUserApi {
    @Override
    public RespEntity<List<SysUserVo>> findByIds(List<Long> ids) {
        throw new BusinessException(IMsgCode.INTERNAL_SERVER_ERROR);
    }

    @Override
    public RespEntity<SysUserVo> findById(Long id) {
        throw new BusinessException(IMsgCode.INTERNAL_SERVER_ERROR);
    }

    @Override
    public RespEntity<Long> save(SysUserVo userVo) {
        throw new BusinessException(IMsgCode.INTERNAL_SERVER_ERROR);
    }

    @Override
    public RespEntity<List<String>> getDataScope(Long userId) {
        throw new BusinessException(IMsgCode.INTERNAL_SERVER_ERROR);
    }

    @Override
    public RespEntity<List<Long>> getDeptAndChildUserIdsByDeptUserId(Long userId) {
        throw new BusinessException(IMsgCode.INTERNAL_SERVER_ERROR);
    }

    @Override
    public RespEntity<List<Long>> getUserIdsByDeptUserId(Long userId) {
        throw new BusinessException(IMsgCode.INTERNAL_SERVER_ERROR);
    }

    @Override
    public RespEntity<List<Long>> listByDeptIds(List<Long> deptIds) {
        throw new BusinessException(IMsgCode.INTERNAL_SERVER_ERROR);
    }
}
