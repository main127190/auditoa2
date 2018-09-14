<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公安审计信息化系统—项目审计管理子系统</title>
<script type="text/javascript">


    var index_tabs;
    var layout_west_tree;
    var indexTabsMenu;
    var indexUserMenu;

    $(function() {
        $('#index_layout').layout({fit : true});

        $.post('${path }/resource/tree',  function(result) {
            if (result.length>0) {
             var str =""
               for(var i=0 ; i<result.length;i++){
                   str+='<div id='+result[i].id+ '  onclick="(initTab('+result[i].id+',\''+result[i].text+'\',\''+result[i].iconCls+'\'))" >';
                   str+='<table width="100%">';
                   str+=' <tr><td align="center"> <i style="font-size: 40px;color: #607B8B" class="fi '+result[i].iconCls+'"></i></td></tr>';
                   str+=' <tr><td align="center">'+result[i].text+'</td></tr>';
                   str+=' </table></div>';
               }
                $("#nav").append(str);
            }
        }, 'JSON');

        index_tabs = $('#index_tabs').tabs({
            fit : true,
            border : false,
            onContextMenu : function(e, title) {
                e.preventDefault();
                indexTabsMenu.menu('show', {
                    left : e.pageX,
                    top : e.pageY
                }).data('tabTitle', title);
            },
            tools : [{
                iconCls : 'fi-home',
                handler : function() {
                    index_tabs.tabs('select', 0);
                }
            }, {
                iconCls : 'fi-loop',
                handler : function() {
                    refreshTab();
                }
            }, {
                iconCls : 'fi-x',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    var tab = index_tabs.tabs('getTab', index);
                    if (tab.panel('options').closable) {
                        index_tabs.tabs('close', index);
                    }
                }
            } ]
        });
        // 选项卡菜单
        indexTabsMenu = $('#tabsMenu').menu({
            onClick : function(item) {
                var curTabTitle = $(this).data('tabTitle');
                var type = $(item.target).attr('type');
                if (type === 'refresh') {
                    refreshTab();
                    return;
                }
                if (type === 'close') {
                    var t = index_tabs.tabs('getTab', curTabTitle);
                    if (t.panel('options').closable) {
                        index_tabs.tabs('close', curTabTitle);
                    }
                    return;
                }
                var allTabs = index_tabs.tabs('tabs');
                var closeTabsTitle = [];
                $.each(allTabs, function() {
                    var opt = $(this).panel('options');
                    if (opt.closable && opt.title != curTabTitle
                            && type === 'closeOther') {
                        closeTabsTitle.push(opt.title);
                    } else if (opt.closable && type === 'closeAll') {
                        closeTabsTitle.push(opt.title);
                    }
                });
                for ( var i = 0; i < closeTabsTitle.length; i++) {
                    index_tabs.tabs('close', closeTabsTitle[i]);
                }
            }
        });
         // 选项卡菜单
        indexUserMenu = $('#userMenu').menu({
            onClick : function(item) {
                var curTabTitle = $(this).data('tabTitle');
                var type = $(item.target).attr('type');
                if (type === 'editUserPwd') {
                    editUserPwd();
                    return;
                }if (type === 'logout') {
                    logout();
                    return;
                }

            }
        });
    });

    function initTab(id,text,iconCls){
        var opts = {
            title : text,
            border : false,
            closable : true,
            fit : true,
            iconCls :iconCls
        };
            opts.content = '<iframe src="${path }/baseTemplate?id='+id+'" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>';
            addTab(opts);

    }
   function  userMenu(but){
       indexUserMenu.menu('show', {
           left :$(but).offset().left,
           top : $(but).offset().top+15
       }).data('tabTitle', title);
   }
    function addTab(opts) {
        var t = $('#index_tabs');
        if (t.tabs('exists', opts.title)) {
            t.tabs('select', opts.title);
        } else {
            t.tabs('add', opts);
        }
    }
    
    function refreshTab() {
        var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
        var tab = index_tabs.tabs('getTab', index);
        var options = tab.panel('options');
        if (options.content) {
            index_tabs.tabs('update', {
                tab: tab,
                options: {
                    content: options.content
                }
            });
        } else {
            tab.panel('refresh', options.href);
        }
    }


    function logout(){
        $.messager.confirm('提示','确定要退出?',function(r){
            if (r){
                progressLoad();
                $.post('${path }/logout', function(result) {
                    if(result.success){
                        progressClose();
                        window.location.href='${path }';
                    }
                }, 'json');
            }
        });
    }

    function editUserPwd() {
        parent.$.modalDialog({
            title : '修改密码',
            width : 300,
            height : 250,
            href : '${path }/user/editPwdPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    var f = parent.$.modalDialog.handler.find('#editUserPwdForm');
                    f.submit();
                }
            } ]
        });
    }
</script>
<style>
    #left{
        /*position:relative;*/
        top: 0px;
        left: 0px;
        width: 100px;
        height:100%;
        background:#FFF;
        background:url(${staticPath }/static/img/bg1.jpg) center;
    }
    #nav div:hover{
        background-color:#6495ED;
    }
</style>
</head>
<body>


    <div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
        <img src="${staticPath }/static/style/images/ajax-loader.gif" style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
    </div>
    <div id="index_layout">
        <div region="north" border="false" style="overflow: hidden;background-color:#fff; background: url(${path}/static/img/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%; line-height: 20px;color: #fff;background-size:4px; font-family: Verdana, 微软雅黑,黑体">
        <%--<div data-options="region:'north',border:false" style="overflow: hidden;background: #D4D4D4">--%>
            <div  >
                <span style="float: right; padding-right: 20px; margin-top: 15px; color: #333">
                    <%--<i class="fi-torso"></i>--%>
                    <b onclick="userMenu(this)">
                        <a class="easyui-linkbutton" data-options="iconCls:'fi-torso',plain:true">  <shiro:principal></shiro:principal></a>
                      </b>&nbsp;&nbsp;
                </span>
                <span class="header"></span>
            </div>
        </div>
        <div data-options="region:'west',split:true,maxWidth:105" title="功能模块"
             style="width: 105px; background-color:#fff;overflow: hidden;overflow-y:auto; padding:0px">
            <%--<div class="well well-small" style="padding: 5px 5px 5px 5px;">--%>
                <%--<ul id="layout_west_tree"></ul>--%>
            <%--</div>--%>
                <div id="left">
                    <div id="nav"></div>
                </div>
        </div>

        <div data-options="region:'center'" style="overflow: hidden;">
            <div id="index_tabs" style="overflow: hidden;">
                <div  title="首页" data-options="iconCls:'fi-home',border:false" style="overflow: hidden;">
                    <div style="width: 100%;height: 100%;
                            background: url('${path}/static/img/home.png');
                            background-size:100% 100%;">
                    </div>
                    <style>
                        .pro_name a{color: #4183c4;}
                        .osc_git_title{background-color: #d8e5f1;}
                        .osc_git_box{background-color: #fafafa;}
                        .osc_git_box{border-color: #ddd;}
                        .osc_git_info{color: #666;}
                        .osc_git_main a{color: #4183c4;}
                    </style>
                </div>
            </div>
        </div>
        <%--<div data-options="region:'south',border:false" style="height: 30px;line-height:30px; overflow: hidden;text-align: center;background-color: #eee" >中宣</div>--%>
    </div>
    <div id="tabsMenu">
        <div data-options="iconCls:'fi-loop'" type="refresh" style="font-size: 12px;">刷新</div>
        <div class="menu-sep"></div>
        <div data-options="iconCls:'fi-x'" type="close" style="font-size: 12px;">关闭</div>
        <div data-options="iconCls:''" type="closeOther">关闭其他</div>
        <div data-options="iconCls:''" type="closeAll">关闭所有</div>
    </div>

    <div id="userMenu">
        <div data-options="iconCls:'fi-x'" type="editUserPwd" style="font-size: 12px;">修改密码</div>
        <div class="menu-sep"></div>
        <div data-options="iconCls:''" type="logout">安全退出</div>
    </div>

    <!--[if lte IE 7]>
    <div id="ie6-warning"><p>您正在使用 低版本浏览器，在本页面可能会导致部分功能无法使用。建议您升级到 <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
    <a href="http://www.mozillaonline.com/" target="_blank">Firefox</a> / <a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">Chrome</a> / <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> / <a href="http://www.operachina.com/" target="_blank">Opera</a></p></div>
    <![endif]-->
    <style>
        /*ie6提示*/
        #ie6-warning{width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
        #ie6-warning p{width:960px;margin:0 auto;}
    </style>
</body>
</html>