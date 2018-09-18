<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%--<%@ include file="/commons/basejs.jsp" %>--%>
<jsp:include   page="${staticPath }/static/js/pm/delegate.js.jsp" flush="true"/>

<%--<div class="easyui-layout" data-options="fit:true,border:false">--%>
    <%--<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">--%>
<div style="width: 100%;height: 400px">
        <form id="searchDelegateForm">
            <table>
                <tr>
                    <th>中介名称:</th>
                    <td><input name="name" placeholder="请输入中介名称"/></td>
                    <th>简称:</th>
                    <td><input name="abbreviation" placeholder="请输入简称"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="searchDelegateDataGridFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="cleanDelegateFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    <%--</div>--%>

    <%--<div data-options="region:'center',border:true,title:'中介列表'" >--%>
        <div class="easyui-layout" style="width:100%;height:100%;">
            <div data-options="region:'center'"  style="overflow: hidden;height: 60%">
                <form id="delegateForm" method="post"  >
                    <input type="hidden" name="id" value="${id}" />
                    <input type="hidden" id="intermediaryOrgan" name="intermediaryOrgan.id"  />
                </form>
                <table id="delegateDataGrid"  ></table>
            </div>
        </div>
</div>
    <%--</div>--%>
<%--</div>--%>
<%--<div id="dictionariesToolbar" style="display: none;">--%>
        <%--<a onclick="toDelegate();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">委派</a>--%>
<%--</div>--%>