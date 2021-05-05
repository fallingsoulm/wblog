package com.wblog.web.security;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.wblog.system.entity.SysMenuDo;
import com.wblog.system.entity.SysRoleDo;
import com.wblog.system.entity.SysUserDo;
import com.wblog.system.service.ISysMenuService;
import com.wblog.system.service.ISysRoleService;
import com.wblog.system.service.ISysUserService;

import io.github.fallingsoulm.easy.archetype.security.core.LoginUserVo;
import io.github.fallingsoulm.easy.archetype.security.security.AbstractUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义用户信息获取
 *
 * @author luyanan
 * @since 2021/1/30
 **/
@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl extends AbstractUserDetailsService {
    private final ISysUserService sysUserService;
    private final ISysMenuService sysMenuService;
    private final ISysRoleService sysRoleService;

    @Override
    protected LoginUserVo loginUserVo(String username) {
        SysUserDo sysUserDo = sysUserService.findByUserName(username);
        if (null == sysUserDo) {
            throw new UsernameNotFoundException("用户:" + username + "不存在");
        }
        Set<String> perms = new HashSet<>();
        List<SysMenuDo> sysMenuDos = sysMenuService.listByUserId(sysUserDo.getUserId());
        if (CollectionUtil.isNotEmpty(sysMenuDos)) {
            sysMenuDos.stream().forEach(a -> {
                if (StrUtil.isNotBlank(a.getPerms())) {
                    perms.addAll(Arrays.asList(a.getPerms().split(",").clone()));
                }
            });
        }
        // 查询角色
        Set<String> roleKeys = sysRoleService.listByUserId(sysUserDo.getUserId()).stream().map(SysRoleDo::getRoleKey).distinct().collect(Collectors.toSet());

        return LoginUserVo
                .builder()
                .userName(sysUserDo.getUserName())
                .password(sysUserDo.getPassword())
                .nickName(sysUserDo.getNickName())
                .userId(sysUserDo.getUserId())
                .permissions(perms)
                .roles(roleKeys)
                .userType(sysUserDo.getUserType()).build();

    }

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// 查询用户信息
//		SysUserDo sysUserDo = sysUserService.findByUserName(username);
//		if (null == sysUserDo) {
//			throw new UsernameNotFoundException("用户:" + username + "不存在");
//		}
//
//		LoginUser loginUser = new LoginUser();
//
//		loginUser.setPassword(sysUserDo.getPassword());
//		Set<String> perms = new HashSet<>();
//		List<SysMenuDo> sysMenuDos = sysMenuService.listByUserId(sysUserDo.getUserId());
//		if (CollectionUtil.isNotEmpty(sysMenuDos)) {
//			sysMenuDos.stream().forEach(a -> {
//				if (StrUtil.isNotBlank(a.getPerms())) {
//					perms.addAll(Arrays.asList(a.getPerms().split(",").clone()));
//				}
//			});
//			List<SimpleGrantedAuthority> authorityList = perms.stream().map(a -> {
//				SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(a);
//				return simpleGrantedAuthority;
//			}).collect(Collectors.toList());
//			loginUser.setAuthorities(authorityList);
//		}
//		// 查询角色
//		Set<Long> roleIds = sysUserRoleService.list(SysUserRoleDo.builder().userId(sysUserDo.getUserId()).build()).stream().map(SysUserRoleDo::getRoleId).distinct().collect(Collectors.toSet());
//
//		loginUser.setUsername(JSON.toJSONString(LoginUserVo
//				.builder()
//				.userName(sysUserDo.getUserName())
//				.nickName(sysUserDo.getNickName())
//				.userId(sysUserDo.getUserId())
//				.permissions(perms)
//				.roleIds(roleIds)
//				.userType(sysUserDo.getUserType()).build()));
//		loginUser.setEnabled(sysUserDo.getStatus().equals("0"));
//		return loginUser;
//	}

}
