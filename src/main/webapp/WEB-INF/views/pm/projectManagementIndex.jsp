<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<jsp:include   page="${staticPath }/static/js/pm/projectManagementIndex.js.jsp" flush="true"/>
<script type="text/javascript">


</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchUserForm">
            <table>
                <tr>
                    <th>项目名称:</th>
                    <td><input name="name" placeholder="请输入项目名称"/></td>
                    <th>创建时间:</th>
                    <td>
                        <input name="createdateStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至
                        <input  name="createdateEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="searchUserFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="cleanUserFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'项目列表'" >
        <div class="easyui-layout" style="width:100%;height:100%;">
            <div data-options="region:'center'"  style="overflow: hidden;height: 60%">
                <table id="projectDataGrid" class="easyui-datagrid" width="100%"></table>
            </div>
            <div data-options="region:'south',border:false,split:true,title:'项目过程信息'" style="height: 40%;overflow: hidden" >
                <div id="index_tabs2" style="overflow: hidden;">
                    <div title="审计过程信息" data-options="iconCls:'fi-home',border:false" style="overflow: hidden;">
                        <table id="test"  class="easyui-datagrid" width="100%"
                               data-options="fit:true,border:false,fitColumns:true">
                            <thead>
                            <tr>
                                <th data-options="field:'test0',width:100">标题</th>
                                <th data-options="field:'test2',width:100">类型</th>
                                <th data-options="field:'test3',width:100">创建人</th>
                                <th data-options="field:'test4',width:100">信息内容</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>进行采购审计面谈</td>
                                <td>审计面谈</td>
                                <td>admin</td>
                                <td>于2018年8月30日10:47:42在北京开始面谈</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div title="中介考核信息" data-options="iconCls:'fi-home',border:false" style="overflow: hidden;">
                        <table id="test1"  class="easyui-datagrid" width="100%"
                               data-options="fit:true,border:false,fitColumns:true">
                            <thead>
                            <tr>
                                <th data-options="field:'test0',width:100">标题</th>
                                <th data-options="field:'test2',width:100">类型</th>
                                <th data-options="field:'test3',width:100">创建人</th>
                                <th data-options="field:'test4',width:100">信息内容</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>中介考核</td>
                                <td>日常考核</td>
                                <td>admin</td>
                                <td>考核的不错</td>
                            </tr>
                            </tbody>
                        </table>

                    </div>  <div title="审计费核算信息" data-options="iconCls:'fi-home',border:false" style="overflow: hidden;">
                        <table id="test2"  class="easyui-datagrid" width="100%"
                               data-options="fit:true,border:false,fitColumns:true">
                            <thead>
                            <tr>
                                <th data-options="field:'test0',width:100">标题</th>
                                <th data-options="field:'test2',width:100">类型</th>
                                <th data-options="field:'test3',width:100">创建人</th>
                                <th data-options="field:'test4',width:100">信息内容</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>审计费核算</td>
                                <td>自动计算项目审计费</td>
                                <td>admin</td>
                                <td>自动计算项目审计费</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div title="审计成果信息" data-options="iconCls:'fi-home',border:false" style="overflow: hidden;">
                        <table id="test3"  class="easyui-datagrid" width="100%"
                               data-options="fit:true,border:false,fitColumns:true">
                            <thead>
                            <tr>
                                <th data-options="field:'test0',width:100">标题</th>
                                <th data-options="field:'test2',width:100">类型</th>
                                <th data-options="field:'test3',width:100">创建人</th>
                                <th data-options="field:'test4',width:100">信息内容</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>审计成果信息</td>
                                <td>挂接中介机构审核报告</td>
                                <td>admin</td>
                                <td>挂接中介机构审核报告</td>
                            </tr>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>

    </div>
    <%--<div data-options="region:'west',border:true,split:true,title:'组织机构'"  style="width:150px;overflow: hidden; ">--%>
        <%--<ul id="organizationTree2" style="width:160px;margin: 10px 10px 10px 10px"></ul>--%>
    <%--</div>--%>
</div>

<div id="projectToolbar" style="display: none;">
    <shiro:hasPermission name="/role/add">
        <a onclick="projectManagementAdd();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-x icon-red'" onclick="deleteRoleFun();" >删除</a>
    </shiro:hasPermission>
</div>