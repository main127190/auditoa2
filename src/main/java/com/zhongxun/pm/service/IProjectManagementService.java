package com.zhongxun.pm.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.io.model.IntermediaryOrgan;
import com.zhongxun.pm.model.BaseProjectInfo;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IProjectManagementService extends IService<BaseProjectInfo> {
    void selectDataGrid(PageInfo pageInfo, BaseProjectInfo baseProjectInfo);

    BaseProjectInfo selectById(BaseProjectInfo baseProjectInfo);
    void disable(String[] ids);

    void toDelegate(BaseProjectInfo baseProjectInfo);
}