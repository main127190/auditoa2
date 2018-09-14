package com.zhongxun.io.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zhongxun.io.model.IntermediaryOrgan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Augu on 2018/9/7.
 */
public interface IntermediaryOrganMapper extends BaseMapper<IntermediaryOrgan> {
    Integer insert(IntermediaryOrgan intermediaryOrgan);
    Integer updateById(IntermediaryOrgan intermediaryOrgan);
    IntermediaryOrgan selectById(IntermediaryOrgan intermediaryOrgan);

    List<IntermediaryOrgan> selectDataGrid(Pagination page,
                                           @Param("sort") String sort,
                                           @Param("order") String order,
                                           @Param("intermediaryOrgan") IntermediaryOrgan intermediaryOrgan,
                                           @Param("ids")List<Long> ids);

    void disable(String[] ids);

}
