<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%--<%@ include file="/commons/basejs.jsp" %>--%>
<jsp:include   page="${staticPath }/static/js/io/intermediaryOrganIndex.js.jsp" flush="true"/>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchIntermediaryOrganForm">
            <table>
                <tr>
                    <th>中介名称:</th>
                    <td><input name="name" placeholder="请输入中介名称"/></td>
                    <th>简称:</th>
                    <td><input name="abbreviation" placeholder="请输入简称"/></td>
                    <th>状态:</th>
                    <shiro:hasPermission name="intermediary-organ:check-delete">
                        <th>
                            <select  name="delFlag" class="easyui-combobox" data-options="width:80,height:29,editable:false,panelHeight:'auto'">
                                <option value="0">正常</option>
                                <option value="1">停用</option>
                             </select>
                        </th>
                    </shiro:hasPermission>
                    <th>创建时间:</th>
                    <td>
                        <input  name="createDate" type="text" class="easyui-datetimebox" data-options="editable:false,prompt:'请选择时间'" />
                       至
                        <input  name="updateDate"  type="text" class="easyui-datetimebox" data-options="editable:false,prompt:'请选择时间'" />
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="searchIntermediaryOrganFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="cleanIntermediaryOrganFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>

    </div>
    <div data-options="region:'center',border:true,title:'中介列表'" >
        <div class="easyui-layout" style="width:100%;height:100%;">
            <div data-options="region:'center'"  style="overflow: hidden;height: 60%">
                <table id="intermediaryDataGrid" class="easyui-datagrid" width="100%"></table>
            </div>
            <div data-options="region:'south',split:true,border:false,title:'中介项目信息'" style="height: 40%;overflow: hidden" >
                <div id="index_tabs3" style="overflow: hidden;">
                    <div title="中介考核信息" data-options="iconCls:'fi-home',border:false" style="overflow: hidden;">
                        <table id="intermediaryTest"  width="100%" ></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--<div data-options="region:'west',split:true,border:true,title:'组织机构'"  style="width:150px;overflow: hidden; ">--%>
        <%--<ul id="organizationTree3" style="width:160px;margin: 10px 10px 10px 10px"></ul>--%>
    <%--</div>--%>
</div>

<div id="intermediaryToolbar" style="display: none;">
    <shiro:hasPermission name="intermediary-organ:add">
        <a onclick="intermediaryOrganIndex();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="intermediary-organ:delete">
        <%--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-pencil icon-blue'" onclick="deleteRoleFun();" >编辑</a>--%>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-x icon-red'" onclick="deleteIntermediaryOrganFun();" >删除</a>
    </shiro:hasPermission>
</div>