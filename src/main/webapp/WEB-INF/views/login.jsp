<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <title>系统登入</title>
    <meta name="keywords" content="引用的easyUI框架">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <%@ include file="/commons/basejs.jsp" %>
    <link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/login.css?v=201612202108" />
    <script type="text/javascript" src="${staticPath }/static/login.js?v=20170115" charset="utf-8"></script>

    <link href="${staticPath }/static/bootstrap/3.3.4/css_default/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <script src="${staticPath }/static/bootstrap/3.3.4/js/bootstrap.min.js"  type="text/javascript"></script>
    <link href="${staticPath }/static/awesome/4.4/css/font-awesome.min.css" rel="stylesheet" />

</head>
<body onkeydown="enterlogin();">
<div class="top_div" >
    <div class="top_div_logo" >

    </div>
</div>
<div class="top_center" >
<div style="background: rgb(255, 255, 255); margin: auto ; border: 1px solid rgb(231, 231, 231);
border-image:none;width:400px;text-align: center;position:absolute;top: 45%;
                left:50%;margin-left: -200px; ">
    <form method="post" id="loginform">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <%--<div style="width: 165px; height: 96px; position: absolute;">--%>
            <%--<div class="tou"></div>--%>
            <%--<div class="initial_left_hand" id="left_hand"></div>--%>
            <%--<div class="initial_right_hand" id="right_hand"></div>--%>
        <%--</div>--%>
        <div class="input-group " style="width: 300px;margin-left: 50px;padding: 30px 0px 10px; position: relative;height: 36px">
            <span class="input-group-addon"
                  id="sizing-addon1">  <i class="fa fa-user fa-lg"></i></span>
            <input type="text" name="username" class="form-control"

                   placeholder="请输入用户名" aria-describedby="sizing-addon1">
        </div>
        <%--<P style="padding: 30px 0px 10px; position: relative;">--%>
            <%--<span class="u_logo"></span>--%>
            <%--<input class="ipt" type="text" name="username" placeholder="请输入用户名"/>--%>
        <%--</P>--%>
        <P style="position: relative;">
        <div class="input-group" style="width: 300px;margin-left: 50px">
            <span class="input-group-addon" > <i class="fa fa-lock fa-lg"></i></span>
            <input type="password" name="password" id="password"  class="form-control" placeholder="请输入密码" aria-describedby="sizing-addon1">
        </div>
            <%--<div class="form-group" style="width: 200px">--%>
                <%--<i class="fa fa-lock fa-lg"></i>--%>
                <%--<input class="form-control ipt required" type="password" placeholder="Password" id="password" name="password" maxlength="8"/>--%>
            <%--</div>--%>
            <%--<span class="p_logo"></span>--%>
            <%--<input class="ipt" id="password" type="password" name="password" placeholder="请输入密码"/>--%>
        </P>
        <P style="padding: 10px 0px 10px; position: relative;margin-left:4px">
            <input class="captcha" type="text" name="captcha" placeholder="请输入验证码"/>
            <img id="captcha" alt="验证码" src="${path }/captcha.jpg" data-src="${path }/captcha.jpg?t=" style="vertical-align:middle;border-radius:4px;width:100px;height:35px;cursor:pointer;">
        </P>
        <%--<div style="padding: 10px 0px 10px; position: relative;">--%>
        <%--&lt;%&ndash;<div class="input-group" style="width: 300px;margin-left: 50px">&ndash;%&gt;--%>
                 <%--<img  id="captcha" alt="验证码" src="${path }/captcha.jpg" data-src="${path }/captcha.jpg?t=" style="background-color:#FFF ;border-radius:4px;width:100px;height:34px;cursor:pointer;">--%>
            <%--<input style="width: 200px" class="form-control" type="text" name="captcha" placeholder="请输入验证码"/>--%>

        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <%--</div>--%>
        <%--<P style="position: relative;text-align: left;">--%>
            <%--<input class="rememberMe" type="checkbox" name="rememberMe" value="1" checked style="vertical-align:middle;margin-left:40px;height:20px;"/> 记住密码--%>
        <%--</P>--%>
        <div style="height: 50px; line-height: 50px; margin-top: 10px;border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
            <div>

            </div>

                <%--<span style="float: left;">--%>
                    <%--&lt;%&ndash;<a style="color: rgb(204, 204, 204);" href="javascript:;">忘记密码?</a>&ndash;%&gt;--%>
                <%--</span>--%>
                <%--<span style="float: right;">--%>
                    <%--<a style="color: rgb(204, 204, 204); margin-right: 10px;" href="javascript:;">注册</a>--%>
                    <button class="btn btn-default"  style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152);
                     border-image: none; color: rgb(255, 255, 255);width: 80%;height: 40px;margin-top: 5px;justify-content: center;
                      font-weight: bold;" href="javascript:;" onclick="submitForm()">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
                <%--</span>--%>
        </div>
    </form>
</div>
</div>
<div class="top_bottom" ></div>
</body>
</html>
