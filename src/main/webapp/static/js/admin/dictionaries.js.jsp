<%--Created by Augu on 2018/9/4.--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="application/javascript">
var dictionariesTreeGrid;
$(function() {

    dictionariesTreeGrid = $('#dictionariesTreeGrid').treegrid({
        url :basePath+'/dictionaries/dataGrid',
        idField : 'id',
        treeField : 'name',
        loadMsg:"正在加载信息请稍候...",
        striped:true,
        rownumbers:true,
//            parentField : 'pid',
        fit : true,
        fitColumns : true,
        border : false,
        pagination:true,//底部显示工具栏组件
        pageSize:20,
        pageList:[20,30,40,50],//每页显示数
        sortName:'seq',
        sortOrder:'asc',
        frozenColumns : [ [ {
            title : '编号',
            field : 'id',
            hidden:true,
            width : 40
        } ] ],
        columns : [ [ {
            field : 'name',
            title : '描述',
            width : 150
        },{
            field : 'icd',
            title : '识别编号',
            width : 100
        }, {
            field : 'key',
            title : '键',
            width : 40
        }, {
            field : 'value',
            title : '值',
            width : 80
        }, {
            field : 'seq',
            title : '排序',
            width : 40
        }, {
            field : 'createTime',
            title : '创建时间',
            width : 140
        }, {
            field : 'pid',
            title : '上级资源ID',
            width : 150,
            hidden : true
        }, {
            field : 'status',
            title : '状态',
            width : 40,
            formatter : function(value, row, index) {
                switch (value) {
                    case 0:
                        return '正常';
                    case 1:
                        return '停用';
                }
            }
        }, {
            field : 'action',
            title : '操作',
            width : 200,
            formatter : function(value, row, index) {
                var str = '';
                if(row.status=='0'){
                    <shiro:hasPermission name="dictionaries:edit">
                    if(row.pid==null ||row.pid==undefined || row.pid =='') {
                        str += $.formatString('<a href="javascript:void(0)" class="resource-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="editDictionariesFun(\'{0}\',\'p\');" >编辑</a>', row.id);
                    }else{
                        str += $.formatString('<a href="javascript:void(0)" class="resource-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="editDictionariesFun(\'{0}\',\'c\');" >编辑</a>', row.id);
                    }
                    </shiro:hasPermission>
                    <shiro:hasPermission name="dictionaries:stop">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="resource-easyui-linkbutton-stop" data-options="plain:true,iconCls:\'fi-prohibited icon-red\'" onclick="deleteDictionariesFun(\'{0}\',\'1\');" >停用</a>', row.id);
                    </shiro:hasPermission>
                    <shiro:hasPermission name="dictionaries:addChild">
                    if(row.pid==null ||row.pid==undefined || row.pid ==''){
                        str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                        str += $.formatString('<a href="javascript:void(0)" class="resource-easyui-linkbutton-addChild" data-options="plain:true,iconCls:\'fi-share icon-green\'" onclick="addDictionariesChild(\'{0}\');" >增加子节点</a>', row.id);
                    }
                    </shiro:hasPermission>
                }else{
                    //if(row.pid==null ||row.pid==undefined || row.pid =='') {
                    //    str += $.formatString('<a href="javascript:void(0)" class="resource-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="editDictionariesFun(\'{0}\',\'p\');" >编辑</a>', row.id);
                    //    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    //}
                    <shiro:hasPermission name="dictionaries:start">
                    str += $.formatString('<a href="javascript:void(0)" class="resource-easyui-linkbutton-start" data-options="plain:true,iconCls:\'fi-power icon-yellow\'" onclick="deleteDictionariesFun(\'{0}\',\'0\');" >启用</a>', row.id);
                    </shiro:hasPermission>
                }
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.resource-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.resource-easyui-linkbutton-stop').linkbutton({text:'停用'});
            $('.resource-easyui-linkbutton-addChild').linkbutton({text:'添加节点'});
            $('.resource-easyui-linkbutton-start').linkbutton({text:'启用'});
        },
        onDblClickRow: doDblClickRow ,
        toolbar : '#dictionariesToolbar'
    });
});

//    双击行事件
function doDblClickRow(row) {
    if (row.pid == null && row.children == undefined) {
        row.state== "closed" ;
        $.post(basePath+"/dictionaries/loadChild?pid=" + row.id, function (json) {
            if (json) {
                $('#dictionariesTreeGrid').treegrid('append', {
                    parent: row.id,//父节点id
                    data: json
                });
            }
        }, "JSON");
    }
   row.state == "closed" ? dictionariesTreeGrid.treegrid('expand', row.id) : dictionariesTreeGrid.treegrid('collapse', row.id);

}

function editDictionariesFun(id,type) {
    //alert(type)
    if (id != undefined) {
        dictionariesTreeGrid.treegrid('select', id);
    }
    var node = dictionariesTreeGrid.treegrid('getSelected');

    if(node.pid !=null ){
        //当前节点是子节点，如果要编辑需要父类是可用状态
        var P_node = dictionariesTreeGrid.treegrid('find',node.pid );
        if(P_node.status=="1"){
            parent.$.messager.alert('提示', "停用字典不能进行编辑", 'info');
            return
        }
    }
    if (node) {
        parent.$.modalDialog({
            title : '编辑',
            width : 500,
            height : 350,
            href : basePath+'/dictionaries/addPage?id=' + node.id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_treeGrid = dictionariesTreeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f
                    if('p'==type){
                       f  = parent.$.modalDialog.handler.find('#dictionariesAddForm');
                    }else{
                        f  = parent.$.modalDialog.handler.find('#dictionariesAddChildForm');
                    }
                    f.submit();
                }
            } ]
        });
    }
}

function deleteDictionariesFun(id,status) {
    if (id != undefined) {
        dictionariesTreeGrid.treegrid('select', id);
    }
    var node = dictionariesTreeGrid.treegrid('getSelected');
    if(node.pid !=null ){
        //当前节点是子节点，如果要编辑需要父类是可用状态
        var P_node = dictionariesTreeGrid.treegrid('find',node.pid );
        if(P_node.status=="1"){
            parent.$.messager.alert('提示', "停用字典不能进行启用", 'info');
            return
        }
    }
    if (node) {
        parent.$.messager.confirm('询问', '您是否要停用当前节点？停用当前资源会连同子节点一起停用!', function(b) {
            if (b) {
                progressLoad();
                $.post(basePath+'/dictionaries/disable', {
                    id : node.id,
                    status : status
                }, function(result) {
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        dictionariesTreeGrid.treegrid('reload');
                        //parent.layout_west_tree.tree('reload');
                    }
                    progressClose();
                }, 'JSON');
            }
        });
    }
}

function addDictionariesFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 500,
        height : 350,
        href : basePath+'/dictionaries/addPage',
        buttons : [ {
            text : '添加',
            handler : function() {
                parent.$.modalDialog.openner_treeGrid = dictionariesTreeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#dictionariesAddForm');
                f.submit();
            }
        } ]
    });
}
function addDictionariesChild(id) {
    parent.$.modalDialog({
        title : '添加',
        width : 500,
        height : 350,
        href : basePath+'/dictionaries/addChildPage?pid='+id,
        buttons : [ {
            text : '添加',
            handler : function() {
                parent.$.modalDialog.openner_treeGrid = dictionariesTreeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#dictionariesAddChildForm');
                f.submit();
            }
        } ]
    });
}

function searchDictionariesFun(){
    dictionariesTreeGrid.treegrid('load', $.serializeObject($('#searchDictionariesForm')));
}
</script>