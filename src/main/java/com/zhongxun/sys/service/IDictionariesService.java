package com.zhongxun.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhongxun.commons.result.Tree;
import com.zhongxun.commons.shiro.ShiroUser;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.sys.model.Dictionaries;
import com.zhongxun.sys.model.Resource;

import java.util.List;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IDictionariesService extends IService<Dictionaries> {
    List<Tree> selectAllMenu();
    void selectDataGrid(PageInfo pageInfo,Dictionaries dictionaries);
    List<Dictionaries> selectAll();

    List<Dictionaries> selectAllByPid(String pid);

    void disable(Dictionaries dictionaries);
}