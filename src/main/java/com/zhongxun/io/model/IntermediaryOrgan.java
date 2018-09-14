/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.zhongxun.io.model;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.zhongxun.commons.base.DataEntity;
import com.zhongxun.sys.model.Organization;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 劳动合同
 * @author xz
 * @version 2018年6月13日17:14:52
 */
@TableName("intermediary_organ")
public class IntermediaryOrgan extends DataEntity<IntermediaryOrgan> {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	private String name;//中介名称
	private String abbreviation;//中介简称
	private String aptitude;//公司资质
	private String aptitudeText;//公司资质显示值
	private String contractNumber;//合同编号
	private String contacts;//联系人
	private String contactWay;//联系方式
	private String address;//地址
	private String fileUrl;//地址

	//上传附件
	private MultipartFile enclosure ;
	//部门
	private Organization organization ;//中介管理部门

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAptitude() {
		return aptitude;
	}

	public void setAptitude(String aptitude) {
		this.aptitude = aptitude;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public MultipartFile getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(MultipartFile enclosure) {
		this.enclosure = enclosure;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getAptitudeText() {
		return aptitudeText;
	}

	public void setAptitudeText(String aptitudeText) {
		this.aptitudeText = aptitudeText;
	}
}