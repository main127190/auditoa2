package com.zhongxun.io.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.io.mapper.IntermediaryOrganMapper;
import com.zhongxun.io.model.IntermediaryOrgan;
import com.zhongxun.io.service.IIntermediaryOrganService;
import com.zhongxun.sys.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Augu on 2018/9/7.
 */
@Service
public class IntermediaryOrganServiceImpl extends ServiceImpl<IntermediaryOrganMapper, IntermediaryOrgan> implements IIntermediaryOrganService {
   @Autowired
   private IntermediaryOrganMapper intermediaryOrganMapper ;
   @Autowired
   private OrganizationMapper organizationMapper ;

    /**
     * 中介更新或者添加
     * @param intermediaryOrgan
     * @return
     */
    public boolean insert(IntermediaryOrgan intermediaryOrgan) {
        //插入数据前判断数据是更新还是插入
        if(intermediaryOrgan.isInsert()){
            intermediaryOrgan.preInsert();
            //插入
            intermediaryOrganMapper.insert(intermediaryOrgan);
        }else{
            //更新
            intermediaryOrgan.preUpdate();
            intermediaryOrganMapper.updateById(intermediaryOrgan) ;
        }

        return false;
    }
    /**
     * 分页查询表格数据
     */
    @Override
    public void selectDataGrid(PageInfo pageInfo,IntermediaryOrgan dictionaries) {
        Page<IntermediaryOrgan> page = new Page<IntermediaryOrgan>(pageInfo.getNowpage(), pageInfo.getSize());
        List<Long> ids=null ;
        if(dictionaries.getOrganization()!=null && dictionaries.getOrganization().getId()!=null){
            ids=new ArrayList();
            ids.add(dictionaries.getOrganization().getId());
            //获取该部门以及所有子部门ID
            ids.addAll(lowerlevelDepartment(dictionaries.getOrganization().getId()));
        }
        List<IntermediaryOrgan> list = intermediaryOrganMapper.selectDataGrid(page, pageInfo.getSort(), pageInfo.getOrder(), dictionaries,ids);
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }



    /**
     * 根据ID查询数据
     * @param intermediaryOrgan
     * @return
     */
    public IntermediaryOrgan selectById(IntermediaryOrgan intermediaryOrgan){
        return intermediaryOrganMapper.selectById(intermediaryOrgan);
    }


    /**
     * 根据id数组,禁用中介
     * @return
     */
    public void disable(String[] ids){
         intermediaryOrganMapper.disable(ids);
    }

    //根据部门id获取所有的下级部门
    private List lowerlevelDepartment(Long id) {
        List<Long> all = new ArrayList<>();
        List<Long> ids =  organizationMapper.lowerlevelDepartment(id);
        if(ids!=null && ids.size()>0){
            all.addAll(ids);
            for(Long cId :ids){
                List<Long> cids =   lowerlevelDepartment(cId);
                if(cids!=null && cids.size()>0){
                    all.addAll(cids) ;
                }
            }
        }
        return all ;
    }

}
