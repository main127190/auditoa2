/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.zhongxun.pm.model;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhongxun.commons.base.DataEntity;
import com.zhongxun.io.model.IntermediaryOrgan;
import com.zhongxun.sys.model.Organization;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 项目
 * @author xz
 * @version 2018年6月13日17:14:52
 */
@TableName("project_info")
public class BaseProjectInfo extends DataEntity<BaseProjectInfo> {
	public int PROJECT_STATUS_REGISTER=0 ;
	public int PROJECT_STATUS_DELEGATE=1 ;
	public int PROJECT_STATUS_FINISH=-1 ;


	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	private String projectName;//项目名称
	private String letterDelivery;//送审函号
	private Long trialAmount;//送审金额
	private String trialDate;//送审日期
	private String contacts;//联系人
	private String officePhone;//办公电话
	private String mobilePhone;//手机
	private String responseibilityUnit;//项目责任单位
	private String constructionUnit;//项目承建单位
	private String projectNature;//项目性质
	private String projectType;//项目类型
	private String procurementMethod;//政府采购方式
	private String examineStage;//审核阶段
	private Long contractAmount;//中标合同金额
	private String auditFeeProvenance;//审计费出处
	private String auditFeeName;//经费名称
	private Long purchasingTypeDescription;//采购类型描述
	private String reportNumber ;//报告编号

	private Long fixedAmount ;//审定金额
	private Long reductionRatio ;//审减比例
	private Long deductions ;//审减额
	private Long auditFee ;//审计费
	private Long supplementaryDay ;//补充材料天数

	//数据库不含有的字段
	private String projectNatureText;//项目性质显示值
	private String projectTypeText;//项目类型显示值
	private String procurementMethodText;//政府采购方式显示值
	private String examineStageText;//审核阶段显示值
	private String purchasingTypeDescriptionText;//采购类型描述显示值

	private IntermediaryOrgan intermediaryOrgan ;//委托的中介


	private int status ; //状态，0:新登记项目 ,1:以委派项目,-1:完成项目

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getLetterDelivery() {
		return letterDelivery;
	}

	public void setLetterDelivery(String letterDelivery) {
		this.letterDelivery = letterDelivery;
	}

	public Long getTrialAmount() {
		return trialAmount;
	}

	public void setTrialAmount(Long trialAmount) {
		this.trialAmount = trialAmount;
	}

	public String getTrialDate() {
		return trialDate;
	}

	public void setTrialDate(String trialDate) {
		this.trialDate = trialDate;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getResponseibilityUnit() {
		return responseibilityUnit;
	}

	public void setResponseibilityUnit(String responseibilityUnit) {
		this.responseibilityUnit = responseibilityUnit;
	}

	public String getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	public String getProjectNature() {
		return projectNature;
	}

	public void setProjectNature(String projectNature) {
		this.projectNature = projectNature;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProcurementMethod() {
		return procurementMethod;
	}

	public void setProcurementMethod(String procurementMethod) {
		this.procurementMethod = procurementMethod;
	}

	public String getExamineStage() {
		return examineStage;
	}

	public void setExamineStage(String examineStage) {
		this.examineStage = examineStage;
	}

	public Long getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Long contractAmount) {
		this.contractAmount = contractAmount;
	}

	public String getAuditFeeProvenance() {
		return auditFeeProvenance;
	}

	public void setAuditFeeProvenance(String auditFeeProvenance) {
		this.auditFeeProvenance = auditFeeProvenance;
	}

	public String getAuditFeeName() {
		return auditFeeName;
	}

	public void setAuditFeeName(String auditFeeName) {
		this.auditFeeName = auditFeeName;
	}

	public Long getPurchasingTypeDescription() {
		return purchasingTypeDescription;
	}

	public void setPurchasingTypeDescription(Long purchasingTypeDescription) {
		this.purchasingTypeDescription = purchasingTypeDescription;
	}

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public Long getFixedAmount() {
		return fixedAmount;
	}

	public void setFixedAmount(Long fixedAmount) {
		this.fixedAmount = fixedAmount;
	}

	public Long getReductionRatio() {
		return reductionRatio;
	}

	public void setReductionRatio(Long reductionRatio) {
		this.reductionRatio = reductionRatio;
	}

	public Long getDeductions() {
		return deductions;
	}

	public void setDeductions(Long deductions) {
		this.deductions = deductions;
	}

	public Long getAuditFee() {
		return auditFee;
	}

	public void setAuditFee(Long auditFee) {
		this.auditFee = auditFee;
	}

	public Long getSupplementaryDay() {
		return supplementaryDay;
	}

	public void setSupplementaryDay(Long supplementaryDay) {
		this.supplementaryDay = supplementaryDay;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getProjectNatureText() {
		return projectNatureText;
	}

	public void setProjectNatureText(String projectNatureText) {
		this.projectNatureText = projectNatureText;
	}

	public String getProjectTypeText() {
		return projectTypeText;
	}

	public void setProjectTypeText(String projectTypeText) {
		this.projectTypeText = projectTypeText;
	}

	public String getProcurementMethodText() {
		return procurementMethodText;
	}

	public void setProcurementMethodText(String procurementMethodText) {
		this.procurementMethodText = procurementMethodText;
	}

	public String getExamineStageText() {
		return examineStageText;
	}

	public void setExamineStageText(String examineStageText) {
		this.examineStageText = examineStageText;
	}

	public String getPurchasingTypeDescriptionText() {
		return purchasingTypeDescriptionText;
	}

	public void setPurchasingTypeDescriptionText(String purchasingTypeDescriptionText) {
		this.purchasingTypeDescriptionText = purchasingTypeDescriptionText;
	}

	public IntermediaryOrgan getIntermediaryOrgan() {
		return intermediaryOrgan;
	}

	public void setIntermediaryOrgan(IntermediaryOrgan intermediaryOrgan) {
		this.intermediaryOrgan = intermediaryOrgan;
	}
}