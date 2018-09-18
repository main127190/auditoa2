<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<jsp:include   page="${staticPath }/static/js/pm/projectManagementAdd.js.jsp" flush="true"/>

<div style="padding: 3px;">
    <form id="projectManagementAddForm" method="post"  >
        <table class="grid">
            <input type="hidden" name="id"   value="${baseProjectInfo.id}" />
            <tr>
                <td>审计过程标题</td>
                <td colspan="3"><input name="projectName"  type="text" placeholder="请输入项目名称"
                                       value="${baseProjectInfo.projectName}"
                                       class="easyui-validatebox" style="width:100%" data-options="required:true" ></td>
            </tr>
            <tr>
                <td>审计过程类型</td>
                <td>
                    <select id="projectNature"  name="projectNature" style="width:140px;height: 29px;"></select>
                </td>
            </tr>
            <tr>
                <td>过程信息</td>
                <td   colspan="3">
                    <textarea name="auditFee" >${baseProjectInfo.auditFee}</textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
