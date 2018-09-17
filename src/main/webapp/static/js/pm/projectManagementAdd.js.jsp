<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="application/javascript">
        $(function(){
            //初始化下拉框的值
            initCombobox();
            //初始化页面事件
            initEvent();

        });

        function initEvent(){
            $('#projectManagementAddForm').form({
                url : '${path }/pm/projectManagement/add',
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
//                        parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为organization.jsp页面预定义好了
                        parent.$.modalDialog.handler.dialog('close');
                    }
                }
            });
        }


    function initCombobox(){
        loadCombobox("projectNature","project_nature");
        loadCombobox("projectType","project_type");
        loadCombobox("procurementMethod","procurement_method");
        loadCombobox("examineStage","examine_stage");
        loadCombobox("purchasingTypeDescription","procurement_type");
    }

    function loadCombobox(id ,icd){

        $('#'+id).combobox({
            url : '${path }/combobox/dictionariesCombobox?icd='+icd,
            valueField:'valueField',
            textField:'textField',
            editable:false,
            onLoadSuccess:function () { //加载完成后,val[0]写死设置选中第一项
                var val = $(this).combobox("getData");
//                var defaultSelect = '0';
//
//                if(defaultSelect!=null && defaultSelect!=undefined && defaultSelect!=''){
//                    for (var a = 0; a < val.length; a++) {//2.当前下拉框的所有数据。
//                        if(defaultSelect==val[a].valueField){//3.用后台传回来的实际值与下拉框中的值进行比较，若是二者相等那么默认选中该项。
//                            $(this).combobox("select", val[a].valueField);
//                        }
//                    }
//                }else{
                    $(this).combobox("select", val[0].valueField);
//                }
            }
        });
    }

</script>
