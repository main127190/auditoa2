package com.zhongxun.pm.controller;

import com.zhongxun.commons.base.BaseController;
import com.zhongxun.commons.utils.PageInfo;
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
     * 项目添加页面
     * @return
     */
    @RequestMapping("/projectManagementAdd")
    public String projectManagementAdd(BaseProjectInfo baseProjectInfo ,Model model) {

//        if(intermediaryOrgan.getId()!=null){
//            intermediaryOrgan = intermediaryOrganService.selectById(intermediaryOrgan);
//            model.addAttribute("intermediaryOrgan",intermediaryOrgan) ;
//        }
//        String fileKey=null ;
//        if(StringUtils.isBlank(intermediaryOrgan.getFileUrl())){
//            //没有上传过文件，创建文件上传key
//            fileKey =  UUID.randomUUID().toString().replaceAll("-", "");
//        }else{
//            fileKey= intermediaryOrgan.getFileUrl() ;
//        }
//        model.addAttribute("fileKey",fileKey) ;
        model.addAttribute("intermediaryOrgan",baseProjectInfo) ;
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


}
