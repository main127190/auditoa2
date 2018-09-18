<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="application/javascript">
var delegateDataGrid;
$(function() {

    $('#delegateForm').form({
        url : '${path }/pm/projectManagement/toDelegate',
        onSubmit : function() {
            var node = delegateDataGrid.treegrid('getSelected');
            if(node!=null ){
                $("#intermediaryOrgan").val(node.id) ;
                return true ;
            }else{
                parent.$.messager.alert('提示', "必须先选中一行", 'info');
                return false ;
            }
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


    delegateDataGrid=$('#delegateDataGrid').datagrid({
        url:basePath+'/io/intermediaryOrgan/loadData',
//        fit : true,
        //striped : true,
        rownumbers : true,
        fitColumns : true,
        border: false,
        nowrap:false,
        pagination : true,
        singleSelect : true,
        idField : 'id',
        sortName : 'create_date',
        sortOrder : 'desc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40],
        columns : [ [  {
            width : '200',
            title : '中介名称',
            field : 'name'
        }, {
            width : '80',
            title : '中介简称',
            field : 'abbreviation'
        }
            ,{
            width : '80',
            title : '联系人',
            field : 'contacts'
        },{
            width : '100',
            title : '联系方式',
            field : 'contactWay'
        }
        ] ]
//        ,
//        onDblClickRow: toDelegate
    });
});

function toDelegate(index, row){
    //获取选中行
    delegateDataGrid.treegrid('unselectAll');
    delegateDataGrid.treegrid('selectRow',index);
    $('#delegateForm').submit();
}

/**
 * 提交查询表单
 */
function searchDelegateDataGridFun(){
    delegateDataGrid.datagrid('load', $.serializeObject($('#searchDelegateForm')));
}
/**
 * 清空查询表单
 */
function cleanDelegateFun(){
    $('#searchDelegateForm input').val('');
}
</script>
