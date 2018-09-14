package com.zhongxun.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zhongxun.sys.model.Dictionaries;
import com.zhongxun.sys.model.Resource;
import com.zhongxun.sys.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * Resource 表数据库控制层接口
 *
 */
public interface DictionariesMapper extends BaseMapper<Dictionaries> {

    List<Dictionaries> confirmationOfRepetition(Dictionaries dictionaries);

    List<Dictionaries> confirmationOfFatherRepetition(Dictionaries dictionaries);
    List<Dictionaries> selectRoleList(Pagination page, @Param("sort") String sort,
                                      @Param("order") String order, @Param("dictionaries") Dictionaries dictionaries);

    void updateChild(Dictionaries dictionaries);

    void updateFather(Dictionaries dictionaries);

    void updateChildIcd(Dictionaries dictionaries);

    void disable(Dictionaries dictionaries);
}