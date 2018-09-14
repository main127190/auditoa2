package com.zhongxun.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhongxun.sys.model.ComboboxModel;

import java.util.List;

/**
 *
 * Resource 表数据库控制层接口
 *
 */
public interface FileAttachmentMapper extends BaseMapper<ComboboxModel> {
    void saveFileConfig(ComboboxModel comboboxModel);

    void deleteFile(String url);

    List<ComboboxModel> fileList(String businessId);
}