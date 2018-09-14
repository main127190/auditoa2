package com.zhongxun.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhongxun.sys.model.Organization;

import java.util.List;

/**
 *
 * Organization 表数据库控制层接口
 *
 */
public interface OrganizationMapper extends BaseMapper<Organization> {
    List<Long> lowerlevelDepartment(Long id);

}