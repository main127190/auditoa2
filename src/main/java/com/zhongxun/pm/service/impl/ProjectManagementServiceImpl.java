package com.zhongxun.pm.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.io.mapper.IntermediaryOrganMapper;
import com.zhongxun.io.model.IntermediaryOrgan;
import com.zhongxun.io.service.IIntermediaryOrganService;
import com.zhongxun.pm.mapper.ProjectManagementMapper;
import com.zhongxun.pm.model.BaseProjectInfo;
import com.zhongxun.pm.service.IProjectManagementService;
import com.zhongxun.sys.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Augu on 2018/9/7.
 */
@Service
public class ProjectManagementServiceImpl extends ServiceImpl<ProjectManagementMapper, BaseProjectInfo> implements IProjectManagementService {
   @Autowired
   private ProjectManagementMapper projectManagementMapper ;

    /**
     * 项目更新或者添加
     * @return
     */
    public boolean insert(BaseProjectInfo baseProjectInfo) {
        //插入数据前判断数据是更新还是插入
        if(baseProjectInfo.isInsert()){
            baseProjectInfo.preInsert();
            //插入
            projectManagementMapper.insert(baseProjectInfo);
        }else{
            //更新
            baseProjectInfo.preUpdate();
            projectManagementMapper.updateById(baseProjectInfo) ;
        }
        return false;
    }


    /**
     * 分页查询表格数据
     */
    @Override
    public void selectDataGrid(PageInfo pageInfo,BaseProjectInfo baseProjectInfo) {
        Page<BaseProjectInfo> page = new Page<BaseProjectInfo>(pageInfo.getNowpage(), pageInfo.getSize());

        List<BaseProjectInfo> list = projectManagementMapper.selectDataGrid(page, pageInfo.getSort(), pageInfo.getOrder(), baseProjectInfo);
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    public BaseProjectInfo selectById(BaseProjectInfo baseProjectInfo){
        return projectManagementMapper.selectById(baseProjectInfo);
    }
    /**
     * 根据id数组,禁用中介
     * @return
     */
    public void disable(String[] ids){
        projectManagementMapper.disable(ids);
    }  /**
     * 根据id数组,禁用中介
     * @return
     */
    public void toDelegate(BaseProjectInfo baseProjectInfo){
        baseProjectInfo.preUpdate();
        projectManagementMapper.toDelegate(baseProjectInfo);
    }
}
