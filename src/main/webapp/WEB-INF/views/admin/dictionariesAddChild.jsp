<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#dictionariesAddChildForm').form({
            url : '${path }/dictionaries/add',
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
                    var pid='${dictionaries.pid}' ;
//                    parent.$.modalDialog.openner_treeGrid.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了
                  var  childrenNodes=  parent.$.modalDialog.openner_treeGrid.treegrid('getChildren',pid);//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了

                 for(var i =0; i<childrenNodes.length;i++){
                     parent.$.modalDialog.openner_treeGrid.treegrid('remove',childrenNodes[i].id);
                 }
                    $.post(basePath+"/dictionaries/loadChild?pid="+pid, function (json) {
                        if (json) {
                            parent.$.modalDialog.openner_treeGrid.treegrid('append', {
                                parent: pid,//父节点id
                                data: json
                            });
                        }
                    }, "JSON");

                    //parent.layout_west_tree.tree('reload');
                    parent.$.modalDialog.handler.dialog('close');
                }else{
                    parent.$.messager.alert('提示', result.msg, 'info');
                }
            }
        });
        
    });
</script>
<div style="padding: 3px;">
    <form id="dictionariesAddChildForm" method="post">
        <table class="grid">
            <tr>
                <input type="hidden" name="pid" value="${dictionaries.pid}" />
                <input type="hidden" name="id" value="${dictionaries.id}" />
                <td>描述</td>
                <td><input name="name" type="text" placeholder="请输入字典数据描述名称" value="${dictionaries.name}"
                           class="easyui-validatebox span2" data-options="required:true" ></td>
                <td>识别编号</td>
                <td><input name="icd" type="text" placeholder="请输入字典识编号"
                           value="${dictionaries.icd}" readonly style="background-color: #dddddd"
                           class="easyui-validatebox span2" data-options="width:140,height:29" ></td>
            </tr>
            <tr>
                <td>键</td>
                <td>
                    <input name="key" value="${dictionaries.key}" class="easyui-validatebox span2" data-options="required:true"  />
                </td>
                <td>值</td>
                <td>
                    <input name="value" value="${dictionaries.value}" class="easyui-validatebox span2" data-options="required:true"  />
                </td>
            </tr>
            <tr>
                <%--<td>上级部门</td>--%>
                <%--<td ><select id="dictionariesAddPid" name="pid" style="width:140px;height: 29px;"></select></td>--%>

                <td>排序</td>
                <td><input name="seq"   value="${empty dictionaries.seq ? 0: dictionaries.seq}"
                           class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false"></td>
                    <td>状态</td>
                    <td >
                        <select name="status" class="easyui-combobox"  value="${dictionaries.status}"
                                data-options=" value:'${dictionaries.status}' ,width:200,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">正常</option>
                            <option value="1">停用</option>
                        </select>
                    </td>
            </tr>
        </table>
    </form>
</div>