package com.wblog.common.datascope;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.common.datascope.annotation.GlobalDataScope;
import com.wblog.common.module.system.api.SysDeptApi;
import com.wblog.common.module.system.api.SysRoleApi;
import com.wblog.common.module.system.api.SysUserApi;
import com.wblog.common.module.system.vo.SysRoleVo;
import com.wblog.common.module.system.vo.SysUserVo;
import io.github.fallingsoulm.easy.archetype.security.core.LoginUserService;
import io.github.fallingsoulm.easy.archetype.security.core.LoginUserVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2020/11/3
 * <p>数据权限日志切面处理</p>
 **/
@Aspect
@Component
public class GlobalDataScopeAspect {

    public static InheritableThreadLocal<UserDataScopeVo> threadLocal = new InheritableThreadLocal<>();

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private SysUserApi sysUserApi;

    @Autowired
    private SysRoleApi sysRoleApi;

    @Autowired
    private SysDeptApi sysDeptApi;

    @Pointcut("@annotation(com.wblog.common.datascope.annotation.GlobalDataScope)")
    public void dataScopePointCut() {


    }

    @Before("dataScopePointCut()")
    public void before(JoinPoint joinPoint) throws Exception {

        // 方法执行之前,先清空ThreadLocal
        threadLocal.remove();
        UserDataScopeVo userDataScopeVo = new UserDataScopeVo();
        List<Long> dataScopeUserIds = new ArrayList<>();
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            GlobalDataScope dataScope = method.getAnnotation(GlobalDataScope.class);
            LoginUserVo loginUserVo = loginUserService.getUser();
            SysUserVo user = sysUserApi.findById(loginUserVo.getUserId()).getData();
            // 当用户没登陆的时候,则按照默认的查询规则,不过滤数据
            if (null == user) {
                return;
            }
//            // 当为超级管理员的时候, 不过滤数据
//            if (user.isAdmin()) {
//                return;
//            }
            //  开始过滤
            // 1. 获取角色列表
            List<SysRoleVo> sysRoleVos = sysRoleApi.listByUserId(user.getUserId()).getData();

            // 获取所有的权限,如果包含[全部数据权限]这个权限的话,则返回空的集合, 表示查询所有
            List<String> dataScopeLists = sysRoleVos.stream().map(SysRoleVo::getDataScope).distinct().collect(Collectors.toList());
            if (dataScopeLists.contains(DataScopeEnums.DATA_SCOPE_ALL.getCode())) {
                // 全部数据权限处理
                return;
            } else if (dataScopeLists.contains(DataScopeEnums.DATA_SCOPE_SELF.getCode())) {
                //本人数据权限处理
                dataScopeUserIds.add(user.getUserId());
            }
            // 接下来处理所有部门级别的权限, 就是所有权限的总和
            List<String> deptDataScopes = dataScopeLists
                    .stream()
                    .filter(a -> a.equals(DataScopeEnums.DATA_SCOPE_ALL.getCode()) || a.equals(DataScopeEnums.DATA_SCOPE_SELF.getCode()))
                    .collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(deptDataScopes)) {
                Set<Long> deptIds = new HashSet<>();
                //获取所有的部门id
                for (String deptDataScope : deptDataScopes) {
                    if (deptDataScope.equals(DataScopeEnums.DATA_SCOPE_DEPT_AND_CHILD.getCode())) {
                        // 部门以及以下数据权限
                        deptIds.addAll(sysDeptApi.getDeptAndChildByDeptId(user.getDeptId()).getData());
                    } else if (deptDataScope.equals(DataScopeEnums.DATA_SCOPE_DEPT.getCode())) {
                        //  本部门数据权限
                        deptIds.add(user.getDeptId());
                    } else if (deptDataScope.equals(DataScopeEnums.DATA_SCOPE_CUSTOM.getCode())) {
                        //  自定义数据权限
                        deptIds.addAll(sysDeptApi.listDeptIdByUserId(user.getUserId()).getData());
                    }
                }
                // 根据部门id查询用户列表
                if (CollectionUtil.isNotEmpty(deptIds)) {
                    List<Long> data = sysUserApi.listByDeptIds(deptIds.stream().collect(Collectors.toList())).getData();
                    dataScopeUserIds.addAll(data);
                }
            }
        } finally {
            // 将用户需要过滤的数据放入到ThreadLocal 中,供后续Dao层处理
            userDataScopeVo.setUserIds(dataScopeUserIds);
            threadLocal.set(userDataScopeVo);
        }
    }


    /**
     * <p>获取权限校验的用户id</p>
     *
     * @return {@link List<Long>}
     * @author luyanan
     * @since 2020/11/4
     */
    public List<Long> dataScopeUserIds() {
        UserDataScopeVo userDataScopeVo = threadLocal.get();
        if (null == userDataScopeVo || CollectionUtil.isEmpty(userDataScopeVo.getUserIds())) {
            return new ArrayList<>();
        }
        return userDataScopeVo.getUserIds();
    }
}
