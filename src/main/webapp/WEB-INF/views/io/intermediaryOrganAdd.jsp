<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript"
        src="${staticPath }/static/js/common/ajaxfileupload.js" charset="utf-8"></script>
<script type="text/javascript"
        src="${staticPath }/static/js/common/fileUpdata.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {
        $('#aptitude').combobox({
            url : '${path }/combobox/dictionariesCombobox?icd=company_qualification',
            valueField:'valueField',
            textField:'textField',
            editable:false,
            onLoadSuccess:function () { //加载完成后,val[0]写死设置选中第一项
                var val = $(this).combobox("getData");
               var defaultSelect = '${intermediaryOrgan.aptitude}';

                if(defaultSelect!=null && defaultSelect!=undefined && defaultSelect!=''){
                    for (var a = 0; a < val.length; a++) {//2.当前下拉框的所有数据。
                        if(defaultSelect==val[a].valueField){//3.用后台传回来的实际值与下拉框中的值进行比较，若是二者相等那么默认选中该项。
                            $(this).combobox("select", val[a].valueField);
                        }
                    }
                }else{
                    $(this).combobox("select", val[0].valueField);
                }
            }
        });


        <%--$('#organizationAddPid').combotree({--%>
            <%--url : '${path }/organization/tree',--%>
            <%--parentField : 'pid',--%>
            <%--lines : true,--%>
            <%--panelHeight : 'auto'--%>
        <%--});--%>
        
        $('#intermediaryOrganAddForm').form({
            url : '${path }/io/intermediaryOrgan/add',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为organization.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                }
            }
        });
        queryUploadedFileList('${fileKey}', 'INTERMEDIARY', 'fileList', true);
        $("#fileUpload").click(function(){
            uploadAttachment("win",'${fileKey}', 'INTERMEDIARY','fileList', true);
        });


    });
</script>
<style>
    .del_a{
        font-size: 15px;color: red;margin-left: 10px
    }
</style>
<div style="padding: 3px;">
    <form id="intermediaryOrganAddForm" method="post" enctype="multipart/form-data" >
        <table class="grid">
            <input type="hidden" name="id" value="${intermediaryOrgan.id}" />
            <tr>
                <td>中介名称</td>
                <td><input name="name" value="${intermediaryOrgan.name}" type="text" placeholder="请输入中介名称" class="easyui-validatebox" data-options="required:true" ></td>
                <td>中介简称</td>
                <td><input name="abbreviation" value="${intermediaryOrgan.abbreviation}" type="text" placeholder="请输入中介简称" class="easyui-validatebox"  ></td>
            </tr>
            <tr>
                <td>公司资质</td>
                <td>
                <select id="aptitude" name="aptitude" style="width:140px;height: 29px;"></select>
                    <%--<select name="aptitude" class="easyui-combobox"--%>
                            <%--data-options="value:'${intermediaryOrgan.aptitude}',width:200,height:29,editable:false,panelHeight:'auto'">--%>
                        <%--<option value="0">正常</option>--%>
                        <%--<option value="1">停用</option>--%>
                    <%--</select>--%>
               </td>
                <td>合同编号</td>
                <td><input name="contractNumber" value="${intermediaryOrgan.contractNumber}" type="text" placeholder="请输入合同编号" class="easyui-validatebox" data-options="required:true"  ></td>
            </tr>
            <tr>
                <td>联系人</td>
                <td><input name="contacts" value="${intermediaryOrgan.contacts}" type="text" placeholder="请输入联系人" class="easyui-validatebox" data-options="required:true" ></td>
                <td>联系方式</td>
                <td><input name="contactWay" value="${intermediaryOrgan.contactWay}" type="text" placeholder="请输入联系方式" class="easyui-validatebox" data-options="required:true"  ></td>
            </tr>
            <tr>
                <td>地址</td>
                <td colspan="3"><input name="address" value="${intermediaryOrgan.address}" style="width: 300px;"/></td>
            </tr>
            <%--<tr>--%>
                <%--<td>中介管理部门</td>--%>
                <%--<td colspan="3"><select id="organizationAddPid" name="organization.id"  style="width:200px;height: 29px;"--%>
                                        <%--data-options="value:'${intermediaryOrgan.organization.id}'"></select>--%>
            <%--</tr>--%>
            <tr>
                <td><label>附件列表:</label><br>
                    <input type="hidden" name="fileUrl" value="${fileKey}" />
                    <button id="fileUpload" type="button" class="easyui-linkbutton">上传附件</button>
                </td>
                <td colspan="5">
                    <ul id="fileList"></ul>
                </td>
            </tr>
            <tr>
                <td>备注</td>
                <td colspan="3">
                    <textarea  name="remarks"  style="width: 300px;height: 100px">${intermediaryOrgan.remarks}
                    </textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="win"></div>