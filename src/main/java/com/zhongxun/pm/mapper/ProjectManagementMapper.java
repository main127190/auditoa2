package com.zhongxun.pm.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zhongxun.io.model.IntermediaryOrgan;
import com.zhongxun.pm.model.BaseProjectInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Augu on 2018/9/7.
 */
public interface ProjectManagementMapper extends BaseMapper<BaseProjectInfo> {
    Integer insert(BaseProjectInfo baseProjectInfo);
    Integer updateById(BaseProjectInfo baseProjectInfo);
    BaseProjectInfo selectById(BaseProjectInfo baseProjectInfo);

    List<BaseProjectInfo> selectDataGrid(Pagination page,
                                           @Param("sort") String sort,
                                           @Param("order") String order,
                                           @Param("baseProjectInfo") BaseProjectInfo baseProjectInfo);

    void disable(String[] ids);

    void toDelegate(BaseProjectInfo baseProjectInfo);
}
