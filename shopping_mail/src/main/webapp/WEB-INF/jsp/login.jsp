<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>会员登录</title>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../../js/jquery-1.9.1.min.js"></script>
    <script>
        /*实现表单验证*/
        $(function () {
            $("#username").blur(function () {
                var username = this.value.trim();
                if (username == null || username == "") {
                    alert("用户名不能为空!");
                }
            });

            $("#password").blur(function () {
                if (this.value == null || this.value == "") {
                    alert("密码不能为空!");
                }
            });

            $("#submitButton").click(function () {
                var username = $("#username").val().trim();
                var password = $("#password").val().trim();
                console.debug(username);

                if (username == null || username == "") {
                    alert("用户名不能为空!");
                    return;
                } else if (password == null || password == "") {
                    alert("密码不能为空!");
                    return;
                }
                $("#loginForm").submit();
            });
        });
    </script>
</head>
<body>

<div class="container header">
    <div class="span5">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/index.action">
                <img src="${pageContext.request.contextPath}/image/img_01/logo.jpg"
                     style="width:225px; height:45px;" alt="Near商城"/>
            </a>
        </div>
    </div>
    <div class="span9">
        <div class="headerAd">
            <img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障"
                 title="正品保障">
        </div>
    </div>

    <%@ include file="part/menu.jsp" %>

</div>
<div class="container login">
    <div class="span12">
        <div class="ad">
            <img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录"
                 title="会员登录">
        </div>
    </div>
    <div class="span12 last">
        <div class="wrap">
            <div class="main">
                <div class="title">
                    <strong>会员登录</strong>USER LOGIN
                </div>
                <div style="margin-left: 80px;color: red;"><s:actionerror/></div>
                <form id="loginForm" action="${pageContext.request.contextPath}/user_login.action" method="post"
                      novalidate="novalidate">
                    <table>
                        <tbody>
                        <tr>
                            <th>
                                用户名:
                            </th>
                            <td>
                                <input type="text" id="username" name="user.userName" class="text" maxlength="20">
                            </td>
                        </tr>
                        <tr>
                            <th>
                                密&nbsp;&nbsp;码:
                            </th>
                            <td>
                                <input type="password" id="password" name="user.password" class="text" maxlength="20"
                                       autocomplete="off">
                            </td>
                        </tr>

                        <tr>
                            <th>&nbsp;
                            </th>
                            <td>
                                <label>
                                    <input type="checkbox" id="isRememberUsername" name="isRememberUsername"
                                           value="true">记住用户名
                                </label>
                                <label>
                                    &nbsp;&nbsp;<a>找回密码</a>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <th>&nbsp;
                            </th>
                            <td>
                                <input id="submitButton" type="button" class="submit" value="登 录">
                            </td>
                        </tr>
                        <tr class="register">
                            <th>&nbsp;
                            </th>
                            <td>
                                <dl>
                                    <dt>还没有注册账号？</dt>
                                    <dd>
                                        立即注册即可体验在线购物！
                                        <a href="${pageContext.request.contextPath}/user_registerPage.action">立即注册</a>
                                    </dd>
                                </dl>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="part/footer.jsp" %>

</body>
</html>