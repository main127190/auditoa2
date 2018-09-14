package com.zhongxun.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.zhongxun.commons.result.Tree;
import com.zhongxun.sys.model.Organization;

/**
 *
 * Organization 表数据服务层接口
 *
 */
public interface IOrganizationService extends IService<Organization> {

    List<Tree> selectTree();

    List<Organization> selectTreeGrid();

}