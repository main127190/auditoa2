/**
 * Created by Augu on 2018/9/11.
 */
//document.write("<script language=javascript src='ajaxfileupload.js'></script>");
//var path='a';
var timeout_n = 18000000;
var general_pageSize = 50;

//屏幕宽度变更
function screenWidthResize(widthSize) {
    var srcWidth = screen.width;
    if (srcWidth < 1024) {
        srcWidth = 1024;
    }
    return widthSize * srcWidth / 1024;
}

//屏幕高度变更
function screenHeightResize(heightSize) {
    var srcHeight = screen.height;
    if (srcHeight < 768) {
        srcHeight = 768;
    }
    return heightSize * srcHeight / 768;
}
//获得调整高度
function getAdjScreenHeightResize(adjHeight) {
    var srcHeight = $('#workArea1', parent.document).height();
    if (srcHeight < 400) {
        srcHeight = 400;
    }
    var adjHeight2 = (srcHeight - adjHeight);
    return adjHeight2;
}

//
function getMainPanelWidth() {
    var calWidth = screen.width * (1024 - 190) / 1024 - 30;
    return (calWidth > 768 ? calWidth : 768);
}

/**
 * 时间对象的格式化;
 */
Date.prototype.format = function (format) {
    //eg:format="YYYY-MM-dd hh:mm:ss";
    var o = {
        "M+": this.getMonth() + 1,  //month
        "D+": this.getDate(),     //day
        "h+": this.getHours(),    //hour
        "m+": this.getMinutes(),  //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds() //millisecond
    }

    if (/(Y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

function formatedateRender(value) {
    var retValue = "";
    if (value && value != null) {
        retValue = new Date(value.time).format("YYYY-MM-DD")
    }
    return retValue;
}
function formateDateTimeRender(value) {
    var retValue = "";
    if (value && value != null) {
        retValue = new Date(value.time).format("YYYY-MM-DD hh:mm:ss")
    }
    return retValue;
}
function formateFileSizeRender(value) {
    var retValue = "";
    if (value && value != null) {
        retValue = myRound(parseInt(value, 10) / 1024, 2) + "KB";
    }
    return retValue;
}
//错误提示
function view_error(msgTitle, msgText, fn) {
    $.messager.alert(msgTitle, msgText, 'error', fn);
}

//消息信息
function view_msg(msgTitle, msgText, fn) {
    $.messager.alert(msgTitle, msgText, 'info', fn);
}
//警告信息
function view_warn(msgTitle, msgText, fn) {
    $.messager.alert(msgTitle, msgText, 'warning', fn);
}

//询问对话框
function view_confirm(msgTitle, msgText, fn) {
    $.messager.confirm(msgTitle, msgText, fn);
}

//消息信息
function view_msg_diy(msgTitle, msgText, okText, fn) {
    $.messager.defaults.ok = okText;
    $.messager.alert(msgTitle, msgText, 'info', fn);
}
//警告信息
function view_warn_diy(msgTitle, msgText, okText, fn) {
    $.messager.defaults.ok = okText;
    $.messager.alert(msgTitle, msgText, 'warning', fn);
}

//询问对话框

function view_confirm_diy(msgTitle, msgText, okText, cancelText, fn) {
    $.messager.defaults.ok = okText;
    $.messager.defaults.cancel = cancelText;
    $.messager.confirm(msgTitle, msgText, fn);
}

//进度条
function f_guage() {
    $.messager.progress({
        title: "请等待",
        msg: "数据处理中",
        text: ""
    });
}
function f_close_guage() {
    $.messager.progress('close');
}
/**
 * 功能描述:对空字符串处理
 * @param {} s
 * @return {Integer}
 */
function getNvlValue(value, defaultValue) {
    if (value == null || value == undefined) {
        if (defaultValue) {
            return defaultValue;
        } else {
            return "";
        }
    } else {
        return value;
    }
}

function showCommonUploadWin(winId, url, downloadUrl, params, fileTypeFiter, callBack) {

    //var hiddenDivHtml = '<div id="upload_win_16613" style="display:none;">';
    var uploadWinHtml = '<div style="padding:5px;">';
    uploadWinHtml += '<form id="upload_form_16613" method="post" enctype="multipart/form-data">';
    uploadWinHtml += '<div style="margin-top:30px;margin-left: 20px;"><span>请选择上传文件：</span><span id="upload_span_16613"><input type="file" id="upload_file_16613" name="file" style="width:250px;"/></span></div>';
    uploadWinHtml += '<div style="margin-top:30px;text-align:center;width:100%">';
    uploadWinHtml += '<a href="javascript:void(0);" id="upload_uploadButton_16613" iconCls="icon-upload">上传</a>';
    uploadWinHtml += '<span>&nbsp;&nbsp;&nbsp;</span>';
    uploadWinHtml += '<a href="javascript:void(0);" id="upload_cancelButton_16613" iconCls="icon-cancel">取消</a>';
    uploadWinHtml += '</div>';
    if (downloadUrl != null) {
        uploadWinHtml += '<div  style="margin-top:30px;text-align:right;width:100%">';
        uploadWinHtml += '<a href="' + downloadUrl + '" id="upload_downloadButton_16613" style="color:blue;font-size:12px;text-decoration:underline;">下载模板</a>';
        uploadWinHtml += '</div>';
    }
    uploadWinHtml += '</form></div>';
    $("#"+winId).html(uploadWinHtml);

    $('#'+winId).dialog({
        title:"上传文件",
        width:300,
        height:200,
        modal:true
    })
    var uploadCallBack = callBack;

    $('#upload_uploadButton_16613').linkbutton({
        iconCls: 'icon-upload',
        onClick: function () {
            if ($("#upload_file_16613").val() == "") {
                view_warn("提示", "请选择文件");
                return false;
            }
            var fileName = $("#upload_file_16613").val();
            if (fileTypeFiter != null && fileTypeFiter.length > 0) {
                var extention = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                var fileTypeFiter2 = fileTypeFiter.toLowerCase();
                if (fileTypeFiter2.indexOf(extention) < 0) {
                    view_msg("提示", "仅支持 (" + fileTypeFiter + ") 类型的文件!");
                    return false;
                }
            }
            // *********************************************************附件大小限制
            var target = document.getElementById("upload_file_16613");
            var fileSize = 0;
            var bro = $.support ;
            var binfo = "";
            if (bro.msie) {
                var isIE = true;
            }
            if (isIE && !target.files) {
                var filePath = target.value;
                var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
                var file = fileSystem.GetFile(filePath);
                fileSize = file.Size;
            } else {
                fileSize = target.files[0].size;
            }
            var size = fileSize / 1024 / 1024;
            // 这里限制大小
            if (size > 30) {
                alert("附件不能大于30M");
                target.value = "";
                return;
            }
            //**********************************************************
            f_guage();
            $.ajaxFileUpload(
                {
                    url: url,
                    secureuri: false,
                    fileElementId: "upload_file_16613",
                    dataType: 'json',
                    data: params,
                    success: function (data, status) {
                        // console.log('file success, data.success: ' + data.success);
                        // console.log('file success, status: ' + status);

                        f_close_guage();

                        if (data.success) {
                            $('#'+winId).dialog('close');
                            //var filePath =data.msg;
                            //var arr=filePath.split('/');
                            ////alert(arr);
                            //var fileName=arr[arr.length-1];
                            //更新字段
                                                                //<li ><a href='##' >123</a> <a class="del_a" >x</a></li>
                            //$("#fileList").append("<li ><a href='##' onclick='download_(\""+arr.join('/')+"\")'>"+fileName+"</a><a href='##' onclick='delFile_(this ,\""+arr.join('/')+"\")'  class='del_a'>删除</a></li>");
                            if (uploadCallBack) {
                                uploadCallBack(data);
                            }
                        } else {
                            // console.log('file success, data.msg: ' + data.msg);
                            view_warn("警告", data.msg);
                        }
                    },
                    error: function (data, status, e) {
                        // console.log(data);
                        // console.log('file error, status: ' + status);
                        // console.log('file error, e: ' + e);
                        f_close_guage();

                        view_error("错误", "与服务器通信失败，请稍后再试！");
                    }
                }
            );
        }
    });
    $('#upload_cancelButton_16613').linkbutton({
        iconCls: 'icon-cancel',
        onClick: function () {
            $('#'+winId).dialog('close');
        }
    });
}
/**
 *
 * 值集选择-公共界面
 * 参数：title, jsonData, callBack
 * title
 *     弹出框标题
 * jsonData
 *     flexValueSetName:值集类型code
 *     colNameFlexValue:弹出框中flexValue的列名，文本框名称
 *     colNameDesc:弹出框中description的列名，文本框名称
 *     url:弹出框的查询url，暂不支持
 * callBack
 *     回调函数，用于处理弹出框选择记录后的系列操作
 */
function openFlexValueSelectCommonDialog(title, jsonData, callback) {
    var tl = (title == '' ? '选择值集列表' : title);
    $('<div></div>').dialog({
        id: 'flexChId',
        title: tl,
        width: 600,
        height: 450,
        closed: false,
        closable: false,
        cache: false,
        href: ctx + "/flexValue.do?method=flexValueSelectCommonWin",
        queryParams: {
            jsonData: jsonData
        },
        modal: true,
        buttons: [{
            text: '确定',
            handler: function () {
                var row = $('#item_datagrid').datagrid('getSelected');
                if (callback) {
                    callback(row);
                }
                $("#flexChId").dialog("destroy");
            }
        }, {
            text: '关闭',
            handler: function () {
                $("#flexChId").dialog("destroy");
            }
        }]
    });
}
/**
 *
 * 查询申请人岗位和员工类型等emp信息
 * 参数：personId, callback
 * personId
 *     一般是申请人的employeeId
 * callback
 *     回调函数，用于处理弹出框选择记录后的系列操作
 */
function queryEmployeeInfoByPersonId(personId, callback) {
    doAjax({
        url: ctx + '/user.do?method=getEmployeeInfo',
        type: "POST",
        isShowLoad: true,
        data: {
            personId: personId
        }
    }, function (data) {
        if (data.success) {
            if (callback) {
                callback(data.user);
            }
        }
    })
}
/**
 * 上传附件：调用file-service-client接口，获取objectId，然后存入附件表
 * 参数说明
 * businessId   业务数据id
 * businessType 业务类型
 * divId        上传后文件展示div的id
 * delFlag      是否允许删除：true/false
 */
function uploadAttachment(winId,businessId, businessType, divId, delFlag) {

    if (null == businessId || '' == businessId) {
        view_msg('提示', '请先保存业务数据，再上传附件！');
        return;
    }

    showCommonUploadWin(
        winId,
        path + '/fileAttach/uploadFileAttachment',
        null,
        {
            businessId: businessId,
            businessType: businessType
        },
        null,
        function () {
            view_msg('提示', '上传成功');
            // 刷新已上传文件列表
            queryUploadedFileList(businessId, businessType, divId, delFlag);

        }
    );
}
/**
 * 查询已上传文件列表
 * 参数说明
 * businessId   用于寻找文件的key
 * businessType 业务类型，用于区分文件夹
 * divId        上传后文件展示div的id
 * delFlag      是否允许删除：true/false
 */
function queryUploadedFileList(businessId, businessType, divId, delFlag) {

        $.ajax({
            type: "POST",
            url: path +"/fileAttach/fileList",
            data: {businessId:businessId},
            dataType: "json",
            success: function(data){
                var html='';
                if (data.success) {
                    var fileList =data.obj;

                    for(var i= 0 ;i<fileList.length ;i++){
                        var filePath =fileList[i].textField;
                        var arr=filePath.split('/');
                        var fileName=arr[arr.length-1];
                        //更新字段
                        html+="<li >" ;
                        html+="<a href='##' onclick='download_(\""+arr.join('/')+"\")'>"+fileName+"</a>" ;
                       if(delFlag){
                           html+= "<a href='##' onclick='delFile_(\""+businessId+"\" ,\""+divId+"\", \""+delFlag+"\",\""+arr.join('/')+"\")'  class='del_a'>x</a>" ;
                       }
                        html+= "</li>";
                    }
                }
                $("#"+divId).html(html);
            }
        });

}
/**
 * 删除单个附件
 * 参数说明
 * objectId     文件对象id
 * businessId   业务数据id
 * businessType 业务类型
 * divId        上传后文件展示div的id
 */
function deleteSingleFile(objectId, businessId, businessType, divId) {
    view_confirm('确认', '确认删除选中的附件吗？', function (r) {
        if (r) {
            doAjax({
                url: ctx + '/fileAttach/deleteSingleFile',
                type: 'POST',
                isShowLoad: true,
                data: {
                    objectId: objectId
                }
            }, function (data) {
                if (data.success) {
                    view_msg('提示', '附件删除成功!');
                    queryUploadedFileList(businessId, businessType, divId, true);
                }
            });
        }
    });
}
/**
 * 展示消息，超时自动消失
 * @param opts 覆盖默认设置
 */
function show_tip_msg(opts) {
    $.messager.show($.extend({icon:'info', title:'提示', msg:'提示信息!', timeout: 1500, showType:'slide', style:{right:'',bottom:''}}, opts));
}
/**
 * 手机端审批专用
 * 获取下一个待审批单子，并跳转到对应页面
 */
function getNextApproveBill() {
    doAjax({
        url: ctx + '/fa/faBuyback/nextApproveBill',
        type: 'POST',
        isShowLoad: true
    }, function (data) {
        if (data != null && typeof(data.url) != 'undefined' && typeof(data.bpmCode) != 'undefined') {
            window.location.href = ctx + data.url + '?bpmCode=' + data.bpmCode;
        } else {
            window.location.href = ctx + '/fa/faBuyback/forApproveBillSumIndex';
        }
    });
}

/**
 * 下载文件
 * @param url
 * @private
 */
function download_(url){
    window.location.href=path +"/fileAttach/downloadFileAttachment?url="+url ;
}
/**
 * 下载文件
 * @param url
 * @private
 */
function delFile_(businessId, divId, delFlag ,url){
    $.post(path +"/fileAttach/delFile",{url:url},function(result){
        //if (result.success) {
            view_msg('提示', '附件删除成功!');
            queryUploadedFileList(businessId, null, divId, delFlag);
        //}
    });
}