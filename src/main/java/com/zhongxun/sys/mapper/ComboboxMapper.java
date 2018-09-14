package com.zhongxun.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zhongxun.sys.model.ComboboxModel;
import com.zhongxun.sys.model.Dictionaries;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * Resource 表数据库控制层接口
 *
 */
public interface ComboboxMapper extends BaseMapper<ComboboxModel> {

    List<ComboboxModel> dictionariesComboboxByIcd(String icd);
}