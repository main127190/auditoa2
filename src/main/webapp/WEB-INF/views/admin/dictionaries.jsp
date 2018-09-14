<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%--<script type="text/javascript" src="js/myjs.js.jsp"><!---->script>--%>
<%--<script type="text/javascript"--%>
        <%--src="${staticPath }/static/js/admin/dictionaries.js.jsp" charset="utf-8"></script>--%>

<jsp:include   page="${staticPath }/static/js/admin/dictionaries.js.jsp" flush="true"/>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchDictionariesForm">
            <table>
                <tr>
                    <th>识别编号:</th>
                    <td><input name="icd" class="easyui-textbox"  data-options="value:'${dictionaries.icd}'" /></td>
                    <th>状态:</th>
                    <td>
                        <select name="status" class="easyui-combobox"
                                data-options="value:'${empty dictionaries.status ? -1:dictionaries.status}',width:100,editable:false,panelHeight:'auto'">
                            <option value="-1">全部</option>
                            <option value="0">正常</option>
                            <option value="1">停用</option>
                        </select>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="searchDictionariesFun();">查询</a>
                        <%--<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="cleanDictionariesFun();">清空</a>--%>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <div data-options="region:'center',border:false"  style="overflow: hidden;">
        <shiro:hasPermission name="dictionaries:list">
          <table id="dictionariesTreeGrid"></table>
        </shiro:hasPermission>
    </div>
</div>
<div id="dictionariesToolbar" style="display: none;">
    <shiro:hasPermission name="dictionaries:add">
        <a onclick="addDictionariesFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>