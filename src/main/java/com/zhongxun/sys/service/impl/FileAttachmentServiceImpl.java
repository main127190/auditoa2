package com.zhongxun.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhongxun.sys.mapper.ComboboxMapper;
import com.zhongxun.sys.mapper.FileAttachmentMapper;
import com.zhongxun.sys.model.ComboboxModel;
import com.zhongxun.sys.service.IComboboxService;
import com.zhongxun.sys.service.IFileAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Augu on 2018/8/28.
 */
@Service
public class FileAttachmentServiceImpl extends ServiceImpl<FileAttachmentMapper, ComboboxModel> implements IFileAttachmentService {
    @Autowired
    private  FileAttachmentMapper fileAttachmentMapper ;

    @Override
    public void saveFileConfig(ComboboxModel comboboxModel) {
        fileAttachmentMapper.saveFileConfig(comboboxModel);
    }

    @Override
    public void deleteFile(String url) {
        fileAttachmentMapper.deleteFile(url);
    }

    @Override
    public List<ComboboxModel> fileList(String businessId) {
        return fileAttachmentMapper.fileList(businessId);
    }
}
