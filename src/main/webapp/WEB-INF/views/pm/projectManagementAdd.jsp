<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<jsp:include   page="${staticPath }/static/js/pm/projectManagementAdd.js.jsp" flush="true"/>

<div style="padding: 3px;">
    <form id="projectManagementAddForm" method="post"  >
        <table class="grid">
            <input type="hidden" name="id"  />
            <tr>
                <td>项目名称</td>
                <td colspan="3"><input name="projectName"  type="text" placeholder="请输入项目名称"
                                       class="easyui-validatebox" style="width:100%" data-options="required:true" ></td>
            </tr>
            <tr>
                <td>送审函号</td>
                <td>
                <input type="text" placeholder="请输入送审函号"  name="letterDelivery" class="easyui-validatebox" data-options="required:true"  />
               </td>
                <td>送审金额</td>
                <td><input name="trialAmount"  type="text" placeholder="请输入送审金额" class="easyui-validatebox" data-options="required:true"  ></td>
            </tr>
            <tr>
                <td>送审日期</td>
                <td><input name="trialDate"  type="text" placeholder="请选择送审日期"
                           class="easyui-datebox" data-options="required:true" ></td>
                <td>联系人</td>
                <td><input name="contacts"  type="text" placeholder="请输入联系人" class="easyui-validatebox" data-options="required:true"  ></td>
            </tr>
            <tr>
                <td>办公电话</td>
                <td><input name="officePhone"  type="text" placeholder="请输入办公电话" class="easyui-validatebox" ></td>
                <td>手机</td>
                <td><input name="mobilePhone"  type="text" placeholder="请输入联系手机" class="easyui-validatebox"   ></td>
            </tr>
            <tr>
                <td>项目责任单位</td>
                <td><input name="responseibilityUnit" type="text" placeholder="请输入项目责任单位" class="easyui-validatebox" data-options="required:true" ></td>
                <td>项目承建单位</td>
                <td><input name="constructionUnit" type="text" placeholder="请输入项目承建单位" class="easyui-validatebox"   ></td>
            </tr>
            <tr>
                <td>项目性质</td>
                <td>
                    <select id="projectNature"  name="projectNature" style="width:140px;height: 29px;"></select>
                </td>
                <td>项目类型</td>
                <td>
                    <select id="projectType" name="projectType" style="width:140px;height: 29px;"></select>
                </td>
            </tr>
            <tr>
                <td>政府采购方式</td>
                <td>
                    <select id="procurementMethod"  name="procurementMethod" style="width:140px;height: 29px;"></select>
                </td>
                <td>审核阶段</td>
                <td>
                    <select id="examineStage"  name="examineStage" style="width:140px;height: 29px;"></select>
                </td>
            </tr>
            <tr>
                <td>中标合同金额</td>
                <td>
                    <input  name="contractAmount" style="width:140px;height: 29px;" />
                </td>
            </tr>
            <tr>
                <td>审计费出处</td>
                <td>
                    <input  name="auditFeeProvenance" style="width:140px;height: 29px;" />
                </td>
                <td>经费名称</td>
                <td ><input name="auditFeeName" /></td>
            </tr>

            <tr>
                <td>采购类型描述</td>
                <td colspan="3">
                    <select id="purchasingTypeDescription"  name="purchasingTypeDescription" style="width:140px;height: 29px;"></select>
                    <%--<textarea  name="procurement_type"  style="width: 300px;height: 100px">${intermediaryOrgan.remarks}--%>
                    <%--</textarea>--%>
                </td>
            </tr>
        </table>
    </form>
</div>
