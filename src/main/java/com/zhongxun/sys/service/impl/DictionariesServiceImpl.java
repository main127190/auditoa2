package com.zhongxun.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhongxun.commons.result.Tree;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.sys.mapper.DictionariesMapper;
import com.zhongxun.sys.mapper.ResourceMapper;
import com.zhongxun.sys.model.Dictionaries;
import com.zhongxun.sys.model.Resource;
import com.zhongxun.sys.model.Role;
import com.zhongxun.sys.service.IDictionariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Augu on 2018/8/28.
 */
@Service
public class DictionariesServiceImpl extends ServiceImpl<DictionariesMapper, Dictionaries> implements IDictionariesService {

    @Autowired
    private DictionariesMapper dictionariesMapper;
    @Override
    public List<Dictionaries> selectAll() {
        EntityWrapper<Dictionaries> wrapper = new EntityWrapper<Dictionaries>();
        wrapper.orderBy("seq");
        return dictionariesMapper.selectList(wrapper);
    }

    @Override
    public void selectDataGrid(PageInfo pageInfo,Dictionaries dictionaries) {
        Page<Dictionaries> page = new Page<Dictionaries>(pageInfo.getNowpage(), pageInfo.getSize());
        List<Dictionaries> list = dictionariesMapper.selectRoleList(page, pageInfo.getSort(), pageInfo.getOrder(),dictionaries);
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }


    @Override
    public List<Tree> selectAllMenu() {
        List<Tree> trees = new ArrayList<Tree>();
        // 查询所有菜单
        List<Dictionaries> resources = this.selectByType(null);
        if (resources == null) {
            return trees;
        }
        for (Dictionaries dictionaries : resources) {
            Tree tree = new Tree();
            tree.setId(dictionaries.getId());
            tree.setPid(dictionaries.getPid());
            tree.setText(dictionaries.getName());
            trees.add(tree);
        }
        return trees;
    }

    /**
     * 2018年9月4日10:59:51
     * 数据字典插入
     */
    @Override
    public boolean insert(Dictionaries dictionaries) {
        if(Dictionaries.DEFAULT_CHILD_ICON.equals(dictionaries.getIcon())){
            //查询该父类下是否存在重复的键值对
           List<Dictionaries>cl = dictionariesMapper.confirmationOfRepetition(dictionaries);
            if(cl!=null && cl.size()>0){
                return false ;
            }
            if(dictionaries.getId()!=null){
                dictionariesMapper.updateChild(dictionaries);
                return true;
            }
        }else{
            //是否存在相同的编码
            List<Dictionaries>cl = dictionariesMapper.confirmationOfFatherRepetition(dictionaries);
            if(cl!=null && cl.size()>0){
                return false ;
            }
            if(dictionaries.getId()!=null){
                //更新父节点数据
                dictionariesMapper.updateFather(dictionaries);
                //更新子节点数据
                dictionariesMapper.updateChildIcd(dictionaries);
                return true;
            }
        }

        super.insert(dictionaries);
        return true;
    }


    public List<Dictionaries> selectByType(Integer type) {
        EntityWrapper<Dictionaries> wrapper = new EntityWrapper<Dictionaries>();
        Dictionaries dictionaries = new Dictionaries();
        wrapper.setEntity(dictionaries);
        wrapper.addFilter("pid is {0}", type);
        wrapper.orderBy("seq");
        return dictionariesMapper.selectList(wrapper);
    }


    public List<Dictionaries> selectAllByPid(String pid){
        EntityWrapper<Dictionaries> wrapper = new EntityWrapper<Dictionaries>();
        wrapper.addFilter("pid = {0}", pid);
        wrapper.orderBy("seq");
        return dictionariesMapper.selectList(wrapper);
    }

    @Override
    public void disable(Dictionaries dictionaries) {
        dictionariesMapper.disable(dictionaries);
    }


}
