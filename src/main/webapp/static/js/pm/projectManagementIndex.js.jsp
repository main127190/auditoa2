<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="application/javascript">
    var projectDataGrid;
    var menu;
    $(function() {
        //初始化表格数据
        initDataGrid();
        // 加载右键菜单
        initMenu();
        $('#index_tabs2').tabs({
            fit : true,
            border : false
        });

    });



    function initDataGrid(){
        projectDataGrid=$('#projectDataGrid').datagrid({
            url:basePath+'/pm/projectManagement/loadData',
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
                title : '项目名称',
                field : 'projectName'
            },  {
                width : '80',
                title : '送审函号',
                field : 'letterDelivery'
            },  {
                width : '100',
                title : '送审金额',
                align: 'right',
                field : 'trialAmount',
                formatter:function(value,rowData,rowIndex){
                    //return parseFloat(value);
                    return (parseFloat(value).toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
                }
            },  {
                width : '100',
                title : '送审日期',
                field : 'trialDate'
            },  {
                width : '80',
                title : '联系人',
                field : 'contacts'
            },  {
                width : '80',
                title : '办公电话',
                field : 'officePhone'
            },  {
                width : '80',
                title : '手机',
                field : 'mobilePhone'
            },  {
                width : '200',
                title : '项目责任单位',
                field : 'responseibilityUnit'
            },  {
                width : '80',
                title : '项目性质',
                field : 'projectNatureText'
            },  {
                width : '80',
                title : '项目类型',
                field : 'projectTypeText'
            },  {
                width : '80',
                title : '政府采购方式',
                field : 'procurementMethodText'
            },  {
                width : '80',
                title : '审核阶段',
                field : 'examineStageText'
            },  {
                width : '80',
                title : '中标合同金额',
                field : 'contractAmount'
            },  {
                width : '80',
                title : '审计费出处',
                field : 'auditFeeProvenance'
            },  {
                width : '80',
                title : '经费名称',
                field : 'auditFeeName'
            },  {
                width : '80',
                title : '采购类型描述',
                field : 'purchasingTypeDescriptionText'
            }, {
                field : 'action',
                title : '委托中介',
                width : 120,
                formatter : function(value, row, index) {
                    var str = '';
                    if(row.intermediaryOrgan!=null){
                        str += $.formatString('<a href="javascript:void(0)" class="resource-easyui-linkbutton-io"  onclick="delegate(\'{0}\');" > {1} </a>', row.id,row.intermediaryOrgan.name);
                        return str;
                    }
                        str += $.formatString('<a href="javascript:void(0)" class="resource-easyui-linkbutton-delegate" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="delegate(\'{0}\');" >委派</a>', row.id);
                    return str;
                }
            }
            ] ],
            onLoadSuccess:function(data){
                $('.resource-easyui-linkbutton-delegate').linkbutton({text:'委派'});
                $('.resource-easyui-linkbutton-io').linkbutton({plain:true});
            },
            <%--<shiro:hasPermission name="intermediary-organ:edit">--%>
            onDblClickRow: dblClickRowtoEdit ,
            onRowContextMenu: function (e, rowIndex, rowData) { //右键时触发事件
                //三个参数：e里面的内容很多，真心不明白，rowIndex就是当前点击时所在行的索引，rowData当前行的数据
                if(rowIndex==-1){return }
                e.preventDefault(); //阻止浏览器捕获右键事件
                $(this).datagrid("clearSelections"); //取消所有选中项
                if(rowIndex>=0){
                    $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
                }
                $('#menu').menu('show', {
                    //显示右键菜单
                    left: e.pageX,//在鼠标点击处显示菜单
                    top: e.pageY
                });
                e.preventDefault();  //阻止浏览器自带的右键菜单弹出
            },
            <%--</shiro:hasPermission>--%>
            toolbar : '#projectToolbar'
        });
    }

    /**
     * 添加
     */
    function projectManagementAdd() {
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 500,
            href : basePath+'/pm/projectManagement/projectManagementAdd',
            buttons : [ {
                text : '确认',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = projectDataGrid;//因为添加成功之后，需要刷新，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#projectManagementAddForm');
                    f.submit();
                }
            } ]
        });
    }
    /**
     * 删除
     */
    function deleteProjectFun() {
        var node = projectDataGrid.datagrid('getSelections');
        if (node!=null && node!=undefined && node.length>0) {
            parent.$.messager.confirm('询问', '您是否要删除选中项目？', function(b) {
                if (b) {
                    progressLoad();
                    //获取所有选中的id
                    var ids = new Array();
                    $.each(node,function(i,val){
                        ids.push(val.id);
                    });

                    $.post(basePath+'/pm/projectManagement/disable', { ids : ids.join(",")},
                            function(result) {
                                if (result.success) {
                                    parent.$.messager.alert('提示', result.msg, 'info');
                                    projectDataGrid.datagrid('reload');
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
    * 双击编辑
    * @param index
    * @param row
     */
    function dblClickRowtoEdit(index, row){
        parent.$.modalDialog({
            title : '编辑',
            width : 500,
            height : 450,
            href : basePath+'/pm/projectManagement/projectManagementAdd?id='+row.id,
            buttons : [ {
                text : '确认',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = projectDataGrid;//因为添加成功之后，需要刷新，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#projectManagementAddForm');
                    f.submit();
                }
            } ]
        });
    }

    /**
    *委派
     */
    function delegate(id){
        parent.$.modalDialog({
            title : '编辑',
            width : 600,
            height : 450,
            href : basePath+'/pm/projectManagement/delegate?id='+id,
            buttons : [ {
                text : '确认',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = projectDataGrid;//因为添加成功之后，需要刷新，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#delegateForm');
                    f.submit();
                }
            } ]
        });
    }

    function initMenu(){
        menu = $('#menu').menu({
            onClick : function(item) {
                addDetails($(item.target).attr('type'));
            }
        });
    }

    function addDetails(type){
        //获取当前选中行
        var node = projectDataGrid.datagrid('getSelected');
        parent.$.modalDialog({
            title : '添加项目详细信息',
            width : 500,
            height : 450,
            href : basePath+'/pm/projectManagement/'+type+'?id='+node.id,
            buttons : [ {
                text : '确认',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = projectDataGrid;//因为添加成功之后，需要刷新，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#projectManagementAddForm');
                    f.submit();
                }
            } ]
        });
    }
</script>
