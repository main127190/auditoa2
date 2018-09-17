<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="application/javascript">
    var projectDataGrid;
    $(function() {
        //初始化表格数据
        initDataGrid();
        $('#index_tabs2').tabs({
            fit : true,
            border : false
        });

    });

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
//                    parent.$.modalDialog.openner_dataGrid = intermediaryDataGrid;//因为添加成功之后，需要刷新，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#projectManagementAddForm');
                    f.submit();
                }
            } ]
        });
    }

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
                field : 'projectNature'
            },  {
                width : '80',
                title : '项目类型',
                field : 'projectType'
            },  {
                width : '80',
                title : '政府采购方式',
                field : 'procurementMethod'
            },  {
                width : '80',
                title : '审核阶段',
                field : 'examineStage'
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
                field : 'purchasingTypeDescription'
            }
            ] ],
            <%--<shiro:hasPermission name="intermediary-organ:edit">--%>
            <%--onDblClickRow: dblClickRowtoEdit ,--%>
            <%--</shiro:hasPermission>--%>
            toolbar : '#projectToolbar'
        });
    }
</script>
