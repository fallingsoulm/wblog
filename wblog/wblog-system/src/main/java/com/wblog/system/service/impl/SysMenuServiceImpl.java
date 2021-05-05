package com.wblog.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.wblog.common.exception.BusinessException;
import com.wblog.system.SystemMsgCode;
import com.wblog.system.entity.SysMenuDo;
import com.wblog.system.entity.SysRoleDo;
import com.wblog.system.entity.SysRoleMenuDo;
import com.wblog.system.enums.SysMenuEnums;
import com.wblog.system.enums.SystemEnums;
import com.wblog.system.manage.ISysMenuManage;
import com.wblog.system.service.ISysMenuService;
import com.wblog.system.service.ISysRoleMenuService;
import com.wblog.system.service.ISysRoleService;
import com.wblog.system.vo.MetaVo;
import com.wblog.system.vo.RouterVo;
import com.wblog.system.vo.SysMenuVo;
import com.wblog.system.vo.TreeSelectVo;
import io.github.fallingsoulm.easy.archetype.framework.constant.Constants;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @Autowired
    private ISysMenuManage sysMenuManage;

    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public List<SysMenuDo> listByRoleIds(List<Long> roleIds) {
        if (CollectionUtil.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        // 查询关联的菜单id
        List<SysRoleMenuDo> sysRoleMenuDos = sysRoleMenuService.findRoleIds(roleIds);
//		// 根据菜单id查询菜单
//		return sysMenuManage.findByIds(
//				sysRoleMenuDos.stream().map(SysRoleMenuDo::getMenuId).distinct().collect(Collectors.toList()),
//				Arrays.asList(SysMenuEnums.TYPE_DIR.getCode(), SysMenuEnums.TYPE_MENU.getCode()));
        // 根据菜单id查询菜单
        return sysMenuManage.findByIds(
                sysRoleMenuDos.stream().map(SysRoleMenuDo::getMenuId).distinct().collect(Collectors.toList()));
    }

    @Override
    public List<RouterVo> getRouters(Long userId) {
        // 1. 查询角色

        List<SysRoleDo> sysRoleDos = sysRoleService.listByUserId(userId);
        if (CollectionUtil.isEmpty(sysRoleDos)) {
            return new ArrayList<>();
        }
        // 查询关联的菜单id
        List<SysRoleMenuDo> sysRoleMenuDos = sysRoleMenuService.findRoleIds(sysRoleDos.stream().map(SysRoleDo::getRoleId).distinct().collect(Collectors.toList()));
//		// 根据菜单id查询菜单
        List<SysMenuDo> sysMenuDos = sysMenuManage.findByIds(
                sysRoleMenuDos.stream().map(SysRoleMenuDo::getMenuId).distinct().collect(Collectors.toList()),
                Arrays.asList(SysMenuEnums.TYPE_DIR.getCode(), SysMenuEnums.TYPE_MENU.getCode()));
        List<SysMenuVo> menuVos = sysMenuDos.stream().map(a -> {
            SysMenuVo sysMenuVo = new SysMenuVo();
            BeanUtils.copyProperties(a, sysMenuVo);
            return sysMenuVo;
        }).collect(Collectors.toList());
        List<SysMenuVo> childPerms = getChildPerms(menuVos, 0);
        return buildMenus(childPerms);
    }

    public List<RouterVo> buildMenus(List<SysMenuVo> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenuVo menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache())));
            List<SysMenuVo> cMenus = menu.getChildren();
            if (!CollectionUtil.isEmpty(cMenus) && cMenus.size() > 0 && SysMenuEnums.TYPE_DIR.getCode().equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMeunFrame(menu)) {
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(
                        new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache())));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 根据父节点的ID获取所有的子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return java.util.List<com.wblog.system.vo.SysMenuVo>
     * @since 2021/2/7
     */
    public List<SysMenuVo> getChildPerms(List<SysMenuVo> list, int parentId) {
        List<SysMenuVo> returnList = new ArrayList<SysMenuVo>();
        for (Iterator<SysMenuVo> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenuVo t = (SysMenuVo) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     * @return void
     * @since 2021/2/7
     */
    private void recursionFn(List<SysMenuVo> list, SysMenuVo t) {
        // 得到子节点列表
        List<SysMenuVo> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuVo tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 判断是否存在子节点
     *
     * @param list
     * @param t
     * @return boolean
     * @since 2021/2/7
     */
    private boolean hasChild(List<SysMenuVo> list, SysMenuVo t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    /**
     * 得到子节点列表
     *
     * @param list
     * @param t
     * @return java.util.List<com.wblog.system.vo.SysMenuVo>
     * @since 2021/2/7
     */
    private List<SysMenuVo> getChildList(List<SysMenuVo> list, SysMenuVo t) {
        List<SysMenuVo> tlist = new ArrayList<SysMenuVo>();
        Iterator<SysMenuVo> it = list.iterator();
        while (it.hasNext()) {
            SysMenuVo n = (SysMenuVo) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    @Override
    public List<SysMenuDo> listByUserId(Long userId) {
        // 1. 查询角色

        List<SysRoleDo> sysRoleDos = sysRoleService.listByUserId(userId);
        if (CollectionUtil.isEmpty(sysRoleDos)) {
            return new ArrayList<>();
        }
        // 2. 根据角色id查询菜单列表
        return this.listByRoleIds(sysRoleDos.stream().map(a -> a.getRoleId()).distinct().collect(Collectors.toList()));
    }

    @Override
    public List<SysMenuDo> listByUserId(SysMenuDo menu, Long userId) {
        // 1. 查询角色
        List<SysRoleDo> sysRoleDos = sysRoleService.listByUserId(userId);
        if (CollectionUtil.isEmpty(sysRoleDos)) {
            return new ArrayList<>();
        }
        // 查询关联的菜单id
        List<SysRoleMenuDo> sysRoleMenuDos = sysRoleMenuService.findRoleIds(sysRoleDos.stream().map(SysRoleDo::getRoleId).distinct().collect(Collectors.toList()));
        List<Long> menuIds = sysRoleMenuDos.stream().map(SysRoleMenuDo::getMenuId).distinct().collect(Collectors.toList());
        return sysMenuManage.findByIds(menuIds, menu);
    }

    @Override
    public SysMenuDo findById(Long menuId) {
        return sysMenuManage.findById(menuId);
    }

    @Override
    public List<TreeSelectVo> treeselect(SysMenuDo menu, Long userId) {
//		List<SysMenuDo> sysMenuDos = this.listByUserId(menu, userId);
        List<SysMenuDo> sysMenuDos = this.list(menu);

        List<SysMenuVo> sysMenuVos = buildMenuTree(sysMenuDos
                .stream()
                .map(a -> BeanUtils.copyProperties(a, SysMenuVo.class))
                .collect(Collectors.toList()));
        return sysMenuVos.stream().map(TreeSelectVo::new).collect(Collectors.toList());
    }

    @Override
    public void insert(SysMenuDo menu) {
        //菜单名唯一
        if (sysMenuManage.count(SysMenuDo.builder().menuName(menu.getMenuName()).build()) > 0) {
            throw new BusinessException(SystemMsgCode.MENU_NAME_UNIQUE, menu.getMenuName());
        }
        if (SystemEnums.MENU_YES_FRAME.equalsCode(menu.getIsFrame())
                && !StrUtil.startWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            throw new BusinessException(SystemMsgCode.MENU_PATH_START_HTTP);
        }
        sysMenuManage.insert(menu);
    }

    @Override
    public void update(SysMenuDo menu) {

        //菜单名唯一
        if (sysMenuManage.count(SysMenuDo.builder().menuName(menu.getMenuName()).build()) > 0) {
            throw new BusinessException(SystemMsgCode.MENU_NAME_UNIQUE, menu.getMenuName());
        }
        if (SystemEnums.MENU_YES_FRAME.equalsCode(menu.getIsFrame())
                && !StrUtil.startWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            throw new BusinessException(SystemMsgCode.MENU_PATH_START_HTTP);
        }
        if (menu.getMenuId().equals(menu.getParentId())) {
            throw new BusinessException(SystemMsgCode.MENU_PARENT_IS_NOT_ME);
        }
        sysMenuManage.update(menu);
    }

    @Override
    public void deleteById(Long menuId) {
        sysMenuManage.deleteById(menuId);
    }

    @Override
    public List<SysMenuDo> list(SysMenuDo menu) {
        return sysMenuManage.list(menu);
    }


    /**
     * 构建下拉树结构
     *
     * @param menus 菜单列表
     * @return java.util.List<com.wblog.system.vo.SysMenuVo>
     * @since 2021/2/14
     */
    public List<SysMenuVo> buildMenuTree(List<SysMenuVo> menus) {
        List<SysMenuVo> returnList = new ArrayList<SysMenuVo>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysMenuVo dept : menus) {
            tempList.add(dept.getMenuId());
        }
        for (Iterator<SysMenuVo> iterator = menus.iterator(); iterator.hasNext(); ) {
            SysMenuVo menu = (SysMenuVo) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId())) {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return java.lang.String 路由名称
     * @since 2021/2/7
     */
    public String getRouteName(SysMenuVo menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMeunFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return java.lang.String 路由地址
     * @since 2021/2/7
     */
    public String getRouterPath(SysMenuVo menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && SysMenuEnums.TYPE_DIR.getCode().equals(menu.getMenuType())
                && SysMenuEnums.NO_FRAME.getCodeInt().equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMeunFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return java.lang.String 组件信息
     * @since 2021/2/7
     */
    public String getComponent(SysMenuVo menu) {
        String component = SysMenuEnums.LAYOUT.getCode();
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMeunFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = SysMenuEnums.PARENT_VIEW.getCode();
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return boolean 结果
     * @since 2021/2/7
     */
    public boolean isMeunFrame(SysMenuVo menu) {
        return menu.getParentId().intValue() == 0 && SysMenuEnums.TYPE_MENU.getCode().equals(menu.getMenuType())
                && menu.getIsFrame().equals(SysMenuEnums.NO_FRAME.getCodeInt());
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return boolean 结果
     * @since 2021/2/7
     */
    public boolean isParentView(SysMenuVo menu) {
        return menu.getParentId().intValue() != 0 && SysMenuEnums.TYPE_DIR.getCode().equals(menu.getMenuType());
    }

}
