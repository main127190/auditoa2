package com.zhongxun.pm.controller;

import com.zhongxun.commons.base.BaseController;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.commons.utils.StringUtils;
import com.zhongxun.pm.model.BaseProjectInfo;
import com.zhongxun.pm.service.IProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @description：项目管理
 * @author：xz
 * @date：2018年8月28日15:01:16
 */
@Controller
@RequestMapping("/pm/projectManagement")
public class ProjectManagementController extends BaseController {
    @Autowired
    private IProjectManagementService projectManagementService ;
    /**
     * 项目管理页面
     * @return
     */
    @GetMapping("/index")
    public String manager() {
        return "pm/projectManagementIndex";
    }

    /**
     * 跳转到委派页面
     * @return
     */
    @GetMapping("/delegate")
    public String delegate(Long id,Model model) {
        model.addAttribute("id",id) ;
        return "pm/delegate";
    }

    /**
     * 项目添加页面
     * @return
     */
    @RequestMapping("/projectManagementAdd")
    public String projectManagementAdd(BaseProjectInfo baseProjectInfo ,Model model) {

        if(baseProjectInfo.getId()!=null){
            baseProjectInfo = projectManagementService.selectById(baseProjectInfo);
        }
        model.addAttribute("baseProjectInfo",baseProjectInfo) ;
        return "pm/projectManagementAdd";
    }

    /**
     * 中介添加
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(BaseProjectInfo baseProjectInfo) {
        projectManagementService.insert(baseProjectInfo);
        return renderSuccess("成功！");

    }
    /**
     * 中介添加
     * @return
     */
    @RequestMapping("/loadData")
    @ResponseBody
    public Object loadData(BaseProjectInfo baseProjectInfo,Integer page,
                           Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);

        projectManagementService.selectDataGrid(pageInfo, baseProjectInfo);
        return pageInfo;

    }

    /**
     * 根据ID禁用中介公司
     * @return
     */
    @RequestMapping("/disable")
    @ResponseBody
    public Object disable(String ids) {
        if(StringUtils.isNotBlank(ids)){
            projectManagementService.disable(ids.split(","));
        }
        return renderSuccess("删除成功！");
    }

    /**
     * 为项目委派中介
     * @return
     */
    @RequestMapping("/toDelegate")
    @ResponseBody
    public Object toDelegate(BaseProjectInfo baseProjectInfo) {
        projectManagementService.toDelegate(baseProjectInfo);
        return renderSuccess("删除成功！");
    }
    /**
     *
     * @return
     */
    @RequestMapping("/addAuditProcess")
    public String addAuditProcess(BaseProjectInfo baseProjectInfo,Model model) {
        projectManagementService.toDelegate(baseProjectInfo);
        model.addAttribute("baseProjectInfo", baseProjectInfo) ;
        return "pm/addAuditProcess";
    }
}
