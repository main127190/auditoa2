package com.zhongxun.sys.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhongxun.commons.utils.JsonUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *2018年9月10日17:26:09
 *下拉框数据模型
 * xz
 */
public class ComboboxModel implements Serializable {
	private String valueField ;//值
	private String textField ;//显示值
	private boolean  selected ;//默认选中
	private List<ComboboxModel> Cc ;//级联下拉框，子级下拉框

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getTextField() {
		return textField;
	}

	public void setTextField(String textField) {
		this.textField = textField;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public List<ComboboxModel> getCc() {
		return Cc;
	}

	public void setCc(List<ComboboxModel> cc) {
		Cc = cc;
	}
}
