/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.zhongxun.commons.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhongxun.commons.shiro.ShiroUser;
import com.zhongxun.commons.utils.StringUtils;
import com.zhongxun.sys.model.User;
import org.apache.shiro.SecurityUtils;

import java.util.Date;

/**
 * 数据Entity类
 * @author jeeplus
 * @version 2014-05-16
 */
public abstract class DataEntity<T> extends BaseEntity<T> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	protected String remarks;	// 备注
	protected ShiroUser createBy;	// 创建者
	protected Date createDate;	// 创建日期
	protected ShiroUser updateBy;	// 更新者
	protected Date updateDate;	// 更新日期
	protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）
	
	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	public boolean isInsert(){
		if(this.getId()==null)return true ;
		return false;
	}

	/**
	 * 插入之前执行方法，需要手动调用
	 */
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		ShiroUser user =	(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user.getId()!=null){
			if(createBy !=null && createBy.getId()!=null){
				this.updateBy=this.createBy;
			}else{
			this.updateBy = user;
			this.createBy = user;
			}
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	public void preUpdate(){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user.getId()!=null){
			this.updateBy = user;
		}
		this.updateDate = new Date();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public ShiroUser getCreateBy() {
		return createBy;
	}

	public void setCreateBy(ShiroUser createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public ShiroUser getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(ShiroUser updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
