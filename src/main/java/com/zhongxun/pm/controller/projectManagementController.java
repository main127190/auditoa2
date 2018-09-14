package com.zhongxun.pm.controller;

import com.zhongxun.commons.base.BaseController;
import com.zhongxun.sys.model.Dictionaries;
import com.zhongxun.sys.service.IDictionariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @description：项目管理
 * @author：xz
 * @date：2018年8月28日15:01:16
 */
@Controller
@RequestMapping("/pm/projectManagement")
public class projectManagementController extends BaseController {

    /**
     * 字典管理页面
     *
     * @return
     */
    @GetMapping("/index")
    public String manager() {
        return "pm/projectManagementIndex";
    }

}
