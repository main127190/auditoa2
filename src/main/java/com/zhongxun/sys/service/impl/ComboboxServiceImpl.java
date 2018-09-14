package com.zhongxun.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhongxun.commons.result.Tree;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.sys.mapper.ComboboxMapper;
import com.zhongxun.sys.mapper.DictionariesMapper;
import com.zhongxun.sys.model.ComboboxModel;
import com.zhongxun.sys.model.Dictionaries;
import com.zhongxun.sys.service.IComboboxService;
import com.zhongxun.sys.service.IDictionariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Augu on 2018/8/28.
 */
@Service
public class ComboboxServiceImpl extends ServiceImpl<ComboboxMapper, ComboboxModel> implements IComboboxService {
    @Autowired
    private  ComboboxMapper comboboxMapper ;
    /**
     * 根据icd查询字典中的键值对
     * @param icd
     * @return
     */
    @Override
    public List<ComboboxModel> dictionariesComboboxByIcd(String icd) {
        return comboboxMapper.dictionariesComboboxByIcd(icd);
    }
}
