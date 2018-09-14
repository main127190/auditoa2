package com.zhongxun.sys.controller;

import com.zhongxun.commons.base.BaseController;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.sys.model.ComboboxModel;
import com.zhongxun.sys.service.IComboboxService;
import com.zhongxun.sys.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description：日志管理
 * @author：zhixuan.wang
 * @date：2015/10/30 18:06
 */
@Controller
@RequestMapping("/combobox")
public class ComboboxController extends BaseController {

    @Autowired
    private IComboboxService comboboxService;

    @RequestMapping("/dictionariesCombobox")
    @ResponseBody
    public Object manager(String icd) {
        return comboboxService.dictionariesComboboxByIcd(icd);
    }

}
