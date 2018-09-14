package com.zhongxun.sys.controller;

import com.zhongxun.commons.base.BaseController;
import com.zhongxun.commons.result.Result;
import com.zhongxun.commons.utils.DateUtils;
import com.zhongxun.commons.utils.JSONUtil;
import com.zhongxun.commons.utils.PageInfo;
import com.zhongxun.commons.utils.StringUtils;
import com.zhongxun.sys.model.ComboboxModel;
import com.zhongxun.sys.model.Dictionaries;
import com.zhongxun.sys.service.IDictionariesService;
import com.zhongxun.sys.service.IFileAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description：字典管理
 * @author：xz
 * @date：2018年8月28日15:01:16
 */
@Controller
@RequestMapping("/fileAttach")
public class FileAttachmentController extends BaseController {

    @Autowired
    private IFileAttachmentService fileAttachmentService;

    /**
     * 上传单个附件
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "uploadFileAttachment")
    public void uploadFileAttachment(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String businessId =request.getParameter("businessId");
        String savePath = request.getServletContext().getRealPath("userFiles");

        logger.info("附件上传");
        try {
            ComboboxModel comboboxModel = new ComboboxModel();

            comboboxModel.setTextField(save(file ,savePath,request.getParameter("businessType")));
            comboboxModel.setValueField(businessId);
            //保存文件到数据库文件表
            fileAttachmentService.saveFileConfig(comboboxModel);
            resultMap.put("success", new Boolean(true));
            resultMap.put("msg", comboboxModel.getTextField());
        } catch (Exception e) {
            resultMap.put("success", new Boolean(false));
            resultMap.put("msg", (null != e.getMessage() && !"".equals(e.getMessage())) ? e.getMessage() : "未知异常");//MqConstants.UNKNOWN_EXCEPTION_MESSAGE
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
        JSONUtil.writeToResponse(response, "UTF-8", JSONUtil.serialize(resultMap));
        }
    }

    /**
     * 下载单个附件
     */
    @ResponseBody
    @RequestMapping(value = "downloadFileAttachment")
    public void downloadFileAttachment(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String savePath = request.getServletContext().getRealPath("userFiles");
        String path =savePath+"/"+url;
        //截取文件名称
        String fileName = getFileName(url);
//        fileName = new String(fileName.getBytes());

        String agent = request.getHeader("User-Agent").toUpperCase(); //获得浏览器信息并转换为大写
        if (agent.indexOf("MSIE") > 0 || (agent.indexOf("GECKO")>0 && agent.indexOf("RV:11")>0)) {  //IE浏览器和Edge浏览器
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {  //其他浏览器
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        }

        File file = new File(path);
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    public String save(MultipartFile enclosure,String savePath,String filePath) throws Exception{
        String saveUrl ="";
        if(enclosure!=null){
            String fileName = enclosure.getOriginalFilename();
            filePath+="/"+DateUtils.format(new Date(),0);
            savePath=savePath+"/"+filePath;
            //创建文件上传路径
            File file = new File(savePath);
            //判断上传文件的保存目录是否存在
            if (!file.exists() && !file.isDirectory()) {
                System.out.println(savePath + "目录不存在，需要创建");
                //创建目录
                file.mkdirs();
            }
            InputStream in = enclosure.getInputStream();
            saveUrl = savePath+"/"+fileName  ;
            FileOutputStream out = new FileOutputStream(saveUrl);
            filePath=filePath+"/"+fileName ;
            //创建一个缓冲区
            byte buffer[] = new byte[1024];
            //判断输入流中的数据是否已经读完的标识
            int len = 0;
            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
            while((len=in.read(buffer))>0){
                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                out.write(buffer, 0, len);
            }
            //关闭输入流
            in.close();
            //关闭输出流
            out.close();
            //删除处理文件上传时生成的临时文件
        }
        return  filePath ;
    }




    /**
     * 查询附件信息
     *
     * @param request
     * @param response
     */
//    @ResponseBody
//    @RequestMapping(value = "listFileAttachment")
//    public List<FileAttachment> listFileAttachment(HttpServletRequest request, HttpServletResponse response, FileAttachment fileAttachment) {
//
//        return fileAttachmentService.findListByBiz(fileAttachment);
//    }

    /**
     * 删除单个附件
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "delFile")
    public void delFile(String url ,HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map resultMap = new HashMap();
//        String savePath = request.getServletContext().getRealPath("userFiles");
//        String path =savePath+"/"+url;
        try {
//            File file = new File(path);
//            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
//            String msg ="";
//            if (file.exists() && file.isFile()) {
//                if (file.delete()) {
//                    msg ="删除文件成功！";
//                } else {
//                    msg ="删除文件失败！";
//                }
//            } else {
//                msg ="删除文件不存在！";
//            }
            //删除表中关联
            fileAttachmentService.deleteFile(url);
            resultMap.put("success", new Boolean(true));
            resultMap.put("msg", "成功删除");
        } catch (Exception e) {
            resultMap.put("success", new Boolean(false));
            resultMap.put("msg", e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            JSONUtil.writeToResponse(response, "UTF-8", JSONUtil.serialize(resultMap));
        }
    }

  /**
     * 删除单个附件
     */
    @ResponseBody
    @RequestMapping(value = "fileList")
    public Object fileList(String businessId ) throws IOException{
       List<ComboboxModel> fileList = fileAttachmentService.fileList(businessId);
        if(fileList!=null && fileList.size()>0){
            return renderSuccess(fileList);
        }
        Result result = new Result();
        result.setSuccess(false);
        return result;
    }

    /**
     * 根据路径获取文件名称
     * @param url
     * @return
     */
    private String getFileName(String url) {
        if(StringUtils.isNotBlank(url)){
            String[] split = url.split("/");
            return split[split.length-1];
        }else {
            return "文件下载";
        }
    }

}
