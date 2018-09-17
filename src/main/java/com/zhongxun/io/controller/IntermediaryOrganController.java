package com.zhongxun.io.controller;

import com.zhongxun.commons.base.BaseController;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.commons.utils.StringUtils;
import com.zhongxun.io.model.IntermediaryOrgan;
import com.zhongxun.io.service.IIntermediaryOrganService;
import com.zhongxun.sys.model.Dictionaries;
import com.zhongxun.sys.service.IDictionariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description：中介管理
 * @author：xz
 * @date：2018年8月28日15:01:16
 */
@Controller
@RequestMapping("/io/intermediaryOrgan")
public class IntermediaryOrganController extends BaseController {
    @Autowired
    private IIntermediaryOrganService intermediaryOrganService;

    /**
     * 中介管理主页面
     *
     * @return
     */
    @GetMapping("/index")
    public String manager() {
        return "io/intermediaryOrganIndex";
    }

  /**
     * 中介添加页面
     * @return
     */
    @RequestMapping("/intermediaryOrganAdd")
    public String intermediaryOrganAdd(IntermediaryOrgan intermediaryOrgan,Model model) {

        if(intermediaryOrgan.getId()!=null){
            intermediaryOrgan = intermediaryOrganService.selectById(intermediaryOrgan);
            model.addAttribute("intermediaryOrgan",intermediaryOrgan) ;
        }
        String fileKey=null ;
        if(StringUtils.isBlank(intermediaryOrgan.getFileUrl())){
            //没有上传过文件，创建文件上传key
            fileKey =  UUID.randomUUID().toString().replaceAll("-", "");
        }else{
            fileKey= intermediaryOrgan.getFileUrl() ;
        }
        model.addAttribute("fileKey",fileKey) ;
        model.addAttribute("intermediaryOrgan",intermediaryOrgan) ;
        return "io/intermediaryOrganAdd";
    }

    /**
     * 中介添加
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(IntermediaryOrgan intermediaryOrgan) {
        intermediaryOrganService.insert(intermediaryOrgan);
        return renderSuccess("成功！");

    }
    /**
     * 加载中介数据
     * @return
     */
    @RequestMapping("/loadData")
    @ResponseBody
    public Object loadData(IntermediaryOrgan intermediaryOrgan,Integer page,
                           Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);

        intermediaryOrganService.selectDataGrid(pageInfo, intermediaryOrgan);
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
            intermediaryOrganService.disable(ids.split(","));
        }
        return renderSuccess("成功！");
    }

}
