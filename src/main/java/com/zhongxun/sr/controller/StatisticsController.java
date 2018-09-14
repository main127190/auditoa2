package com.zhongxun.sr.controller;

import com.zhongxun.commons.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description：统计页面
 * @author：xz
 * @date：2018年8月28日15:01:16
 */
@Controller
@RequestMapping("/sr/statistics")
public class StatisticsController extends BaseController {

    /**
     * 字典管理页面
     *
     * @return
     */
    @GetMapping("/index")
    public String manager() {
        return "sr/statisticsIndex";
    }

}
