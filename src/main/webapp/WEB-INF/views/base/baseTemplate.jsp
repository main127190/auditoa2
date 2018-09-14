<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs.jsp" %>
<script type="application/javascript">
   var  index_tabs;
   var  indexTabsMenu;
    $(function(){
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

        $('#base_template_${id}').tree({
            url : '${path }/resource/childTree?id=${id}',
            parentField : 'pid',
            lines : true,
            onLoadSuccess:function (node, data){
                var opts = {
                    title : data[0].text,
                    border : false,
                    closable : true,
                    fit : true,
                    iconCls : data[0].iconCls
                };
                var url = data[0].attributes;
                if (url && url.indexOf("http") == -1) {
                    url = '${path }' + url;
                }
                if (data[0].openMode == 'iframe') {
                    opts.content = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>';
                    addTab(opts);
                } else if (url) {
                    opts.href = url;
                    addTab(opts);
                }
            },
                onClick : function(node) {
                var opts = {
                    title : node.text,
                    border : false,
                    closable : true,
                    fit : true,
                    iconCls : node.iconCls
                };
                var url = node.attributes;
                if (url && url.indexOf("http") == -1) {
                    url = '${path }' + url;
                }
                if (node.openMode == 'iframe') {
                    opts.content = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>';
                    addTab(opts);
                } else if (url) {
                     opts.href = url;
                     addTab(opts);
                }
            }
        });

    });

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
</script>
<div class="easyui-layout" data-options="fit:true,border:false">

    <div data-options="region:'center',border:true" >
        <div id="index_tabs"  style="overflow: hidden;"></div>
    </div>

    <div data-options="region:'west',border:true,split:true,title:'功能菜单'"  style="width:150px;overflow: hidden; ">
        <%--<ul id="base_template_${id}" style="width:160px;margin: 10px 10px 10px 10px"></ul>--%>
        <div class="well well-small" style="padding: 5px 5px 5px 5px;">
            <ul id="base_template_${id}"></ul>
        </div>
    </div>
</div>

<div id="tabsMenu">
    <div data-options="iconCls:'fi-loop'" type="refresh" style="font-size: 12px;">刷新</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'fi-x'" type="close" style="font-size: 12px;">关闭</div>
    <div data-options="iconCls:''" type="closeOther">关闭其他</div>
    <%--<div data-options="iconCls:''" type="closeAll">关闭所有</div>--%>
</div>