package com.zhongxun.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhongxun.sys.model.ComboboxModel;

import java.util.List;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IFileAttachmentService extends IService<ComboboxModel> {
    void saveFileConfig(ComboboxModel comboboxModel);

    void deleteFile(String url);

    List<ComboboxModel> fileList(String businessId);
}