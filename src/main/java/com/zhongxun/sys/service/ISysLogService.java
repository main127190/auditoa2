package com.zhongxun.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.sys.model.SysLog;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ISysLogService extends IService<SysLog> {

    void selectDataGrid(PageInfo pageInfo);

}