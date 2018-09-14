package com.zhongxun.io.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhongxun.commons.result.Tree;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.io.model.IntermediaryOrgan;
import com.zhongxun.sys.model.Dictionaries;

import java.util.List;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IIntermediaryOrganService extends IService<IntermediaryOrgan> {

    void selectDataGrid(PageInfo pageInfo,IntermediaryOrgan intermediaryOrgan);
    IntermediaryOrgan selectById(IntermediaryOrgan intermediaryOrgan);

    void disable(String[] ids);
}