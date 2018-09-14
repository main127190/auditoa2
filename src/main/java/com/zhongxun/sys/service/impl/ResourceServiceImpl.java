package com.zhongxun.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.zhongxun.commons.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhongxun.commons.result.Tree;
import com.zhongxun.commons.shiro.ShiroUser;
import com.zhongxun.sys.mapper.ResourceMapper;
import com.zhongxun.sys.mapper.RoleMapper;
import com.zhongxun.sys.mapper.UserRoleMapper;
import com.zhongxun.sys.model.Resource;
import com.zhongxun.sys.service.IResourceService;

/**
 *
 * Resource 表数据服务层接口实现类
 *
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {
    private static final int RESOURCE_MENU = 0; // 菜单
    private static final int DEFAULT_STATUS = 0; // 有效菜单
    private static final int STOP_STATUS = 1; // 无效菜单

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public List<Resource> selectAll() {
        EntityWrapper<Resource> wrapper = new EntityWrapper<Resource>();
        wrapper.orderBy("seq");
        return resourceMapper.selectList(wrapper);
    }

    
    public List<Resource> selectByType(Integer type) {
        EntityWrapper<Resource> wrapper = new EntityWrapper<Resource>();
        Resource resource = new Resource();
        wrapper.setEntity(resource);
        wrapper.addFilter("resource_type = {0}", type);
        wrapper.orderBy("seq");
        return resourceMapper.selectList(wrapper);
    }
    
    @Override
    public List<Tree> selectAllMenu() {
        List<Tree> trees = new ArrayList<Tree>();
        // 查询所有菜单
        List<Resource> resources = this.selectByType(RESOURCE_MENU);
        if (resources == null) {
            return trees;
        }
        for (Resource resource : resources) {
            Tree tree = new Tree();
            tree.setId(resource.getId());
            tree.setPid(resource.getPid());
            tree.setText(resource.getName());
            tree.setIconCls(resource.getIcon());
            tree.setAttributes(resource.getUrl());
            trees.add(tree);
        }
        return trees;
    }
    
    @Override
    public List<Tree> selectAllTree() {
        // 获取所有的资源 tree形式，展示
        List<Tree> trees = new ArrayList<Tree>();
        List<Resource> resources = this.selectAll();
        if (resources == null) {
            return trees;
        }
        for (Resource resource : resources) {
            Tree tree = new Tree();
            tree.setId(resource.getId());
            tree.setPid(resource.getPid());
            tree.setText(resource.getName());
            tree.setIconCls(resource.getIcon());
            tree.setAttributes(resource.getUrl());
            trees.add(tree);
        }
        return trees;
    }
    
    @Override
    public List<Tree> selectTree(ShiroUser shiroUser) {
       return childTree(shiroUser,null);
    }

    @Override
    public  List<Tree> childTree(ShiroUser shiroUser ,String id) {
        List<Tree> trees = new ArrayList<Tree>();
        // shiro中缓存的用户角色
        Set<String> roles = shiroUser.getRoles();
        if (roles == null) {
            return trees;
        }
        // 如果有超级管理员权限
        if (roles.contains("admin")) {
            //获取父节点下的所有子节点
            List<Resource> resourceList =  selectAllByPid(id);
            if (resourceList == null) {
                return trees;
            }
            initTree(trees , resourceList);
            return trees;
        }
        // 普通用户
        List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(shiroUser.getId());
        if (roleIdList == null) {
            return trees;
        }
        List<Resource> resourceLists = roleMapper.selectResourceListByRoleIdList(roleIdList, id);
        if (resourceLists == null) {
            return trees;
        }
        initTree(trees , resourceLists);
        return trees;
    }

    /**
     * 根据id查询所有的子节点
     * @param id
     * @return
     */
    private List<Resource> selectAllByPid(String id) {
        EntityWrapper<Resource> wrapper = new EntityWrapper<Resource>();
        Resource resource = new Resource();
        wrapper.setEntity(resource);
        wrapper.addFilter("status = {0}", DEFAULT_STATUS);
        if(StringUtils.isBlank(id)){
            wrapper.addFilter("pid IS NULL");
        }else{
            wrapper.addFilter("pid = {0}", id);
        }
        wrapper.orderBy("seq");
        return resourceMapper.selectList(wrapper);
    }

    private void initTree(List<Tree> trees ,List<Resource> resourceLists){
        for (Resource resource : resourceLists) {
                Tree tree = new Tree();
                tree.setId(resource.getId());
                tree.setPid(resource.getPid());
                tree.setText(resource.getName());
                tree.setIconCls(resource.getIcon());
                tree.setAttributes(resource.getUrl());
                tree.setOpenMode(resource.getOpenMode());
                trees.add(tree);
        }
    }

}