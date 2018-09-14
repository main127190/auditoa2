package com.zhongxun.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhongxun.commons.result.Tree;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.sys.model.ComboboxModel;
import com.zhongxun.sys.model.Dictionaries;

import java.util.List;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IComboboxService extends IService<ComboboxModel> {

    List<ComboboxModel> dictionariesComboboxByIcd(String icd);
}