package com.zhongxun.sys.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhongxun.commons.utils.JsonUtils;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 资源
 *
 */
public class Dictionaries implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	public static String DEFAULT_ICON="fi-folder-add";
	public static String DEFAULT_CHILD_ICON="fi-book";

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 资源名称 */
	private String name;
	/** 键 */
//	@TableField(value = "dictionaries_key")
	private String key;
	/** 值 */
	private String value;

	/** 字典编号 */
	private String icd;


	/** 父级资源id */
	private Long pid;

	/** 排序 */
	private Integer seq;

	/** 状态 */
	private Integer status;

	/** 图标 */
	@JsonProperty("iconCls")
	private String icon=DEFAULT_ICON;

	/** 创建时间 */
	@TableField(value = "create_time")
	private Date createTime;
	/** 创建人 */
	@TableField(value = "create_user")
	private Long createUser;


	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIcd() {
		return icd;
	}

	public void setIcd(String icd) {
		this.icd = icd;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
