package com.zhongxun.sys.controller;

import com.zhongxun.commons.base.BaseController;
import com.zhongxun.commons.shiro.ShiroUser;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.commons.utils.StringUtils;
import com.zhongxun.sys.model.Dictionaries;
import com.zhongxun.sys.model.Resource;
import com.zhongxun.sys.service.IDictionariesService;
import com.zhongxun.sys.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description：字典管理
 * @author：xz
 * @date：2018年8月28日15:01:16
 */
@Controller
@RequestMapping("/dictionaries")
public class DictionariesController extends BaseController {

    @Autowired
    private IDictionariesService dictionariesService;

    /**
     * 字典管理页面
     *
     * @return
     */
    @GetMapping("/manager")
    public String manager() {
        return "admin/dictionaries";
    }

    @PostMapping("/dataGrid")
         @ResponseBody
         public Object dataGrid(Dictionaries dictionaries ,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        dictionariesService.selectDataGrid(pageInfo,dictionaries);
        return pageInfo;
    }

    /**
     * 资源管理列表
     *
     * @return
     */
    @PostMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid() {
        return dictionariesService.selectAll();
    }

    /**
     * 添加资源页
     *
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Dictionaries dictionaries,Model model) {
//        Dictionaries  dictionaries =new Dictionaries() ;
        if(dictionaries.getId()==null){ return "admin/dictionariesAdd";}
        else{
            //根据id查询对象
          dictionaries = dictionariesService.selectById(dictionaries.getId());
            model.addAttribute("dictionaries",dictionaries);
            //判断是否是添加子节点
            if(dictionaries.getPid()!=null){
                return "admin/dictionariesAddChild";
            }
        }
        return "admin/dictionariesAdd";
    }
    /**
     * 添加子资源页
     *
     * @return
     */
    @GetMapping("/addChildPage")
    public String addChildPage(Dictionaries dictionaries,Model model) {
        Dictionaries  pdictionaries =dictionariesService.selectById(dictionaries.getPid());
        dictionaries.setIcd(pdictionaries.getIcd());
        dictionaries.setStatus(0);
        model.addAttribute("dictionaries",dictionaries);
        return "admin/dictionariesAddChild";
    }

    /**
     * 添加字典数据
     *
     * @param dictionaries
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(Dictionaries dictionaries) {
        dictionaries.setCreateTime(new Date());
        dictionaries.setCreateUser(getUserId());
        if(dictionaries.getPid()!=null) dictionaries.setIcon(Dictionaries.DEFAULT_CHILD_ICON);

        boolean insert = dictionariesService.insert(dictionaries);
        if(insert){
            return renderSuccess("添加成功！");
        }else {
            return renderError("添加失败！存在重复值");
        }

    }
    /**
     * 禁用字典数据
     *
     * @param dictionaries
     * @return
     */
    @RequestMapping("/disable")
    @ResponseBody
    public Object disable(Dictionaries dictionaries) {
        dictionariesService.disable(dictionaries);
        if(0==dictionaries.getStatus()){
            return renderSuccess("启用成功！");
        }else{
            return renderSuccess("停用成功！");
        }
    }


    /**
     * 查询所有的父级字典类型
     */
    @RequestMapping("/allTree")
    @ResponseBody
    public Object allMenu() {
        return dictionariesService.selectAllMenu();
    }


    /**
     * 查询所有的父级字典类型
     */
    @RequestMapping("/loadChild")
    @ResponseBody
    public Object loadChild(String pid) {
        //根据父id获取子类
        return dictionariesService.selectAllByPid(pid);
    }


}
