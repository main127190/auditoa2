<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="application/javascript">
var intermediaryDataGrid;
$(function() {
    $('#intermediaryTest').treegrid({
        data:[ {
            "id": 6,
            "test0": "第一次审核",
            "test1": "admin",
            "test2": "没有错误，可以评高分",
            "pid": 3,
            "test3": 80,
            "test4": "2015-12-06 13:12:18",
            "iconCls": "fi-folder"
        },{
            "id": 20,
            "test0": "第二次审核",
            "test1": "admin",
            "test2": "数据存在错误，扣分",
            "pid": 3,
            "test3": -100,
            "test4": "2015-12-06 13:12:18",
            "iconCls": "fi-folder"
        }, {
            "id": 7,
            "test0": "第三次审核",
            "test1": "admin",
            "test2": "没有出现任何错误，给以高分",
            "pid": 5,
            "test3": 100,
            "test4": "2018-08-28 13:14:15",
            "iconCls": "fi-folder"
        }, {
            "id": 3,
            "test0": "办公用无线电采购",
            "test1": "admin",
            "test2": "-",
            "pid": null,
            "test3": -20,
            "test4": "2015-10-01 13:10:42",
            "iconCls": "fi-folder"
        }, {
            "id": 5,
            "test0": "最新训练器材采购项目",
            "test1": "admin",
            "test2": "-",
            "pid": null,
            "test3": 100,
            "test4": "2015-12-06 12:15:30",
            "iconCls": "fi-folder"
        }],
        idField : 'id',
        treeField : 'test0',
        parentField : 'pid',
        fit : true,
        fitColumns : true,
        border : false,
        frozenColumns : [ [ {
            title : 'id',
            field : 'id',
            width : 40,
            hidden : true
        } ] ],
        columns : [ [ {
            field : 'test0',
            title : '项目名称',
            width : 200
        },{
            field : 'test1',
            title : '审核人',
            width : 80
        }, {
            field : 'test2',
            title : '奖罚原因',
            width : 200
        }, {
            field : 'test3',
            title : '奖罚分值',
            width : 40
        },  {
            width : 80,
            title : '结束时间',
            field : 'test4'
        } ] ]
    });

    intermediaryDataGrid=$('#intermediaryDataGrid').datagrid({
        url:basePath+'/io/intermediaryOrgan/loadData',
        fit : true,
        //striped : true,
        rownumbers : true,
        //fitColumns : true,
        border: false,
        nowrap:false,
        pagination : true,
        singleSelect : false,
        idField : 'id',
        sortName : 'create_date',
        sortOrder : 'desc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100 ],
        columns : [ [{
            field : 'id',
            checkbox : true
        },  {
            width : '200',
            title : '中介名称',
            field : 'name'
        }, {
            width : '80',
            title : '中介简称',
            field : 'abbreviation'
        }, {
            width : '80',
            title : '公司资质',
            field : 'aptitudeText'
        }, {
            width : '80',
            title : '合同编号',
            field : 'contractNumber'
        }
        //    , {
        //    width : '80',
        //    title : '责任部门',
        //    field : 'organization.name',
        //    //sortable : true,
        //    formatter : function(value, row, index) {
        //        return row.organization.name
        //    }
        //}
            ,{
            width : '80',
            title : '联系人',
            field : 'contacts'
        },{
            width : '100',
            title : '联系方式',
            field : 'contactWay'
        },{
            width : '160',
            title : '地址',
            field : 'address'
            //sortable : true
        },{
            width : '160',
            title : '创建时间',
            field : 'createDate',
            sortable : true
        } , {
            width : '80',
            title : '评级',
            field : 'test5'
            //sortable : true
        },{
            width : '200',
            title : '备注',
            field : 'remarks',
            formatter:remarkFormater
        }

        ] ],
        onLoadSuccess:function(data){
            $('.user-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.user-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        <shiro:hasPermission name="intermediary-organ:edit">
            onDblClickRow: dblClickRowtoEdit ,
        </shiro:hasPermission>
        toolbar : '#intermediaryToolbar'
    });


    //$('#organizationTree3').tree({
    //    url : basePath+'/organization/tree',
    //    parentField : 'pid',
    //    lines : true,
    //    onClick : function(node) {
    //        intermediaryDataGrid.datagrid('load', {
    //                'organization.id': node.id
    //            });
    //    }
    //});
    $('#index_tabs3').tabs({
        fit : true,
        border : false
    });

});
function dblClickRowtoEdit(index, row){
    parent.$.modalDialog({
        title : '编辑',
        width : 500,
        height : 450,
        href : basePath+'/io/intermediaryOrgan/intermediaryOrganAdd?id='+row.id,
        buttons : [ {
            text : '确认',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = intermediaryDataGrid;//因为添加成功之后，需要刷新，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#intermediaryOrganAddForm');
                f.submit();
            }
        } ]
    });
}
/**
 * 添加
 */
function intermediaryOrganIndex() {
    parent.$.modalDialog({
        title : '添加',
        width : 500,
        height : 450,
        href : basePath+'/io/intermediaryOrgan/intermediaryOrganAdd',
        buttons : [ {
            text : '确认',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = intermediaryDataGrid;//因为添加成功之后，需要刷新，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#intermediaryOrganAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 添加
 */
function deleteIntermediaryOrganFun() {

    var node = intermediaryDataGrid.datagrid('getSelections');
    if (node!=null && node!=undefined && node.length>0) {

        parent.$.messager.confirm('询问', '您是否要删除选中中介？', function(b) {
            if (b) {
                progressLoad();
                //获取所有选中的id
                var ids = new Array();
                $.each(node,function(i,val){
                    ids.push(val.id);
                });

                $.post(basePath+'/io/intermediaryOrgan/disable', { ids : ids.join(",")},
                    function(result) {
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        intermediaryDataGrid.datagrid('reload');
                    }
                    progressClose();
                }, 'JSON');
            }
        });
    }else{
        parent.$.messager.alert('提示', "必须先选中最少一行", 'info');
    }
}

/**
 * 提交查询表单
 */
function searchIntermediaryOrganFun(){

    intermediaryDataGrid.datagrid('load', $.serializeObject($('#searchIntermediaryOrganForm')));
}
/**
 * 清空查询表单
 */
function cleanIntermediaryOrganFun(){
    $('#searchIntermediaryOrganForm input').val('');
}

function remarkFormater(value, row, index) {
    //alert("value="+value+"  index="+index);
    var content = value+'';
    var abValue = value +'';
    if(value!=null && value != undefined && value.length>=15){
        abValue = value.substring(0,15) + "...";
        content = '<span  href="javascript:;"  title="' + value + '" class="easyui-tooltip">' + abValue + '</span >';
    }   return content;
}
</script>
