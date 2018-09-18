/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.zhongxun.pm.model;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.zhongxun.commons.base.DataEntity;
import com.zhongxun.io.model.IntermediaryOrgan;

/**
 * 审计过程信息实体类
 * @author xz
 * @version 2018年6月13日17:14:52
 */
@TableName("audit_process")
public class AuditProcess extends DataEntity<AuditProcess> {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	private Long projectId ;//项目ID
	private String name;//审计过程标题
	private String context ;//审计过程内容
	private Long type;//类型id
	private String typeName ;//类型文字

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}