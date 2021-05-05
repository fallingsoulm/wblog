package com.wblog.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.wblog.common.exception.BusinessException;
import com.wblog.system.SystemMsgCode;
import com.wblog.system.entity.SysDeptDo;
import com.wblog.system.entity.SysRoleDo;
import com.wblog.system.entity.SysUserDo;
import com.wblog.system.enums.SystemEnums;
import com.wblog.system.manage.ISysDeptManage;
import com.wblog.system.service.ISysDeptService;
import com.wblog.system.service.ISysRoleService;
import com.wblog.system.service.ISysUserService;
import com.wblog.system.vo.SysDeptVo;
import com.wblog.system.vo.TreeSelectVo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService {

    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysDeptManage iSysDeptManage;

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public List<TreeSelectVo> treeselect(SysDeptDo sysDeptDo) {

        List<SysDeptDo> sysDeptDos = iSysDeptManage.list(sysDeptDo);

        if (CollectionUtil.isEmpty(sysDeptDos)) {
            return new ArrayList<>();
        }
        List<SysDeptVo> deptTrees = buildDeptTree(sysDeptDos.stream().map(a -> {
            SysDeptVo sysDeptVo = new SysDeptVo();
            BeanUtils.copyProperties(a, sysDeptVo);
            return sysDeptVo;
        }).collect(Collectors.toList()));
        return deptTrees.stream().map(TreeSelectVo::new).collect(Collectors.toList());
    }

    @Override
    public List<SysDeptDo> list(SysDeptDo dept) {
        return iSysDeptManage.list(dept);
    }

    @Override
    public SysDeptDo findById(Long deptId) {
        return iSysDeptManage.findById(deptId);
    }

    @Override
    public List<Long> listByRoleId(Long roleId) {


        SysRoleDo sysRoleDo = sysRoleService.findById(roleId);
        List<Long> deptIds = this.iSysDeptManage.listByRoleId(roleId, sysRoleDo.getDeptCheckStrictly());
        return deptIds;
    }

    @Override
    public void insertDept(SysDeptDo dept) {
        // 检验唯一
        if (CollectionUtil.isNotEmpty(this.iSysDeptManage.list(SysDeptDo.builder().deptName(dept.getDeptName()).build()))) {
            throw new BusinessException(SystemMsgCode.DEPT_NAME_UNIQUE);
        }
        SysDeptDo info = iSysDeptManage.findById(dept.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (SystemEnums.STATUS_DISABLE.getCode().equals(info.getStatus())) {
            throw new BusinessException(SystemMsgCode.DEPT_STATUS_DISABLE);
        }
        dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        this.iSysDeptManage.insert(dept);

    }

    @Override
    public void updateDept(SysDeptDo dept) {

        // 判断部门名称唯一
        SysDeptDo oldDept = this.iSysDeptManage.findById(dept.getDeptId());
        if (dept.getDeptName().equals(oldDept.getDeptName()) &&
                CollectionUtil.isNotEmpty(this.iSysDeptManage.list(SysDeptDo.builder().deptName(dept.getDeptName()).build()))) {
            throw new BusinessException(SystemMsgCode.DEPT_NAME_UNIQUE);
        } else if (dept.getParentId().equals(dept.getDeptId())) {
            throw new BusinessException(SystemMsgCode.DEPT_PARENT_IS_NOT_ME);
        } else if (SystemEnums.STATUS_DISABLE.getCode().equals(dept.getStatus())
                && iSysDeptManage.selectNormalChildrenDeptById(dept.getDeptId()) > 0) {
            throw new BusinessException(SystemMsgCode.DEPT_HAV_DISABLE_CHILD_DEPT);
        }
        SysDeptDo newParentDept = iSysDeptManage.findById(dept.getParentId());
        if (ObjectUtil.isNotNull(newParentDept) && ObjectUtil.isNotNull(oldDept)) {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
        }
        iSysDeptManage.update(dept);
        if (SystemEnums.STATUS_NORMAL.equals(dept.getStatus())) {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(dept);
        }
    }

    @Override
    public void deleteById(Long deptId) {
        // 1. 是否存在下级部门
        if (Optional.ofNullable(this.iSysDeptManage.count(SysDeptDo.builder().parentId(deptId).build())).orElse(0) > 0) {
            throw new BusinessException(SystemMsgCode.DEPT_HAS_CHILD);
        }
        // 2. 是否存在用户
        if (Optional.ofNullable(this.sysUserService.count(SysUserDo.builder().deptId(deptId).build())).orElse(0) > 0) {
            throw new BusinessException(SystemMsgCode.DEPT_HAS_USER);
        }
        iSysDeptManage.deleteById(deptId);

    }

    /**
     * 修改该部门的父级部门的状态
     *
     * @param dept 当前部门
     * @return void
     * @since 2021/2/12
     */
    private void updateParentDeptStatus(SysDeptDo dept) {
        String updateBy = dept.getUpdateBy();
        dept = iSysDeptManage.findById(dept.getDeptId());
        dept.setUpdateBy(updateBy);
        iSysDeptManage.updateDeptStatus(dept);

    }

    /**
     * 修改子元素关系
     *
     * @param deptId       被修改的部门id
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     * @return void
     * @since 2021/2/12
     */
    private void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors) {

        List<SysDeptDo> children = iSysDeptManage.selectChildrenDeptById(deptId);
        for (SysDeptDo child : children) {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0) {
            iSysDeptManage.updateDeptChildren(children);
        }
    }

    public List<SysDeptVo> buildDeptTree(List<SysDeptVo> depts) {
        List<SysDeptVo> returnList = new ArrayList<SysDeptVo>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysDeptVo dept : depts) {
            tempList.add(dept.getDeptId());
        }
        for (Iterator<SysDeptVo> iterator = depts.iterator(); iterator.hasNext(); ) {
            SysDeptVo dept = (SysDeptVo) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId())) {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty()) {
            returnList = depts;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     * @return void
     * @since 2021/2/11
     */
    private void recursionFn(List<SysDeptVo> list, SysDeptVo t) {
        // 得到子节点列表
        List<SysDeptVo> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDeptVo tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }


    /**
     * 得到子节点列表
     *
     * @param list
     * @param t
     * @return java.util.List<com.wblog.system.vo.SysDeptVo>
     * @since 2021/2/11
     */
    private List<SysDeptVo> getChildList(List<SysDeptVo> list, SysDeptVo t) {
        List<SysDeptVo> tlist = new ArrayList<SysDeptVo>();
        Iterator<SysDeptVo> it = list.iterator();
        while (it.hasNext()) {
            SysDeptVo n = (SysDeptVo) it.next();
            if (!Objects.isNull(n.getParentId()) && n.getParentId().longValue() == t.getDeptId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否包含子节点
     *
     * @param list
     * @param t
     * @return boolean
     * @since 2021/2/11
     */
    private boolean hasChild(List<SysDeptVo> list, SysDeptVo t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
