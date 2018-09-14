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

    /**
     * 2018年6月13日17:15:14
     * XZ
     * 保存劳动合同
     * */
//    @RequestMapping(value = {"saveLaborContract"})
//    public String saveLaborContract(HttpServletRequest request,User user, Model model,LaborContract laborContract) throws  Exception{
//        String fileName ="";
//        MultipartFile enclosure = laborContract.getEnclosure();
//        if(enclosure!=null){
//            //获取文件名
//            fileName	= enclosure.getOriginalFilename();
//            laborContract.setEnclosureUrl(fileName);
//        }
//        String savePath = request.getServletContext().getRealPath("/userfiles/upload/劳动合同" );
//        humanResourcesService.save(laborContract,savePath);
//
//        return "modules/elite_auditoa_human_resources/eliteLabourContractIndex";
//    }

    /**
     * 保存合同
     * @param laborContract 劳动合同对象
     * @param savePath 保存路径
     * @throws Exception
     */
//    @Transactional(readOnly = false)
//    public void save(LaborContract laborContract,String savePath) throws Exception{
//        super.save(laborContract);
//
//        MultipartFile enclosure = laborContract.getEnclosure();
//        if(enclosure!=null){
//            //创建文件上传路径
//            File file = new File(savePath);
//            //判断上传文件的保存目录是否存在
//            if (!file.exists() && !file.isDirectory()) {
//                System.out.println(savePath + "目录不存在，需要创建");
//                //创建目录
//                file.mkdirs();
//            }
//
//
////			long size = enclosure.getSize();
//
//            // 获取文件后缀，即文件类型
////			String fileExt = "";
////			if (fileName.contains(".")) {
////				fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
////			}
//            //设置MD5加密
////		String fileMD5 = md5File(enclosure);
//            InputStream in = enclosure.getInputStream();
//            //拼接文件路径:/后缀/年/月/日/md5/filename
//            String saveUrl = "/"  + laborContract.getId();
//            FileOutputStream out = new FileOutputStream(savePath +saveUrl);
//            //创建一个缓冲区
//            byte buffer[] = new byte[1024];
//            //判断输入流中的数据是否已经读完的标识
//            int len = 0;
//            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
//            while((len=in.read(buffer))>0){
//                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
//                out.write(buffer, 0, len);
//            }
//            //关闭输入流
//            in.close();
//            //关闭输出流
//            out.close();
//            //删除处理文件上传时生成的临时文件
//        }
//    }



    /**
     * 2018年6月13日17:15:14
     * XZ
     * 下载附件
     * */
//    @RequestMapping(value = {"downloadLaborContract"})
//    public void downloadLaborContract(HttpServletResponse resp,HttpServletRequest request,String fileName,String id, Model model) throws  Exception{
//        String path = request.getServletContext().getRealPath("/userfiles/upload/劳动合同/" +id);
//        if(fileName==null)fileName="劳动合同";
//        fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
//        File file = new File(path);
//        resp.reset();
//        resp.setContentType("application/octet-stream");
//        resp.setCharacterEncoding("utf-8");
//        resp.setContentLength((int) file.length());
//        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//        byte[] buff = new byte[1024];
//        BufferedInputStream bis = null;
//        OutputStream os = null;
//        try {
//            os = resp.getOutputStream();
//            bis = new BufferedInputStream(new FileInputStream(file));
//            int i = 0;
//            while ((i = bis.read(buff)) != -1) {
//                os.write(buff, 0, i);
//                os.flush();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                bis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }



}
