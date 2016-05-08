<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<div id="divcontent">
    <table width="850px" border="0" cellspacing="0">
        <tr>
            <td style="padding:30px; text-align:center">
                <table width="80%" border="0" cellspacing="0" style="margin-top:70px">
                    <tr>
                        <td style="width: 98px;"><img
                                src="${pageContext.request.contextPath}/images/IconTexto_WebDev_009.jpg" width="128"
                                height="128"/></td>
                        <td style="padding-top:30px">
                            <span style="font-weight:bold; color:#FF0000">
                                <s:actionmessage/>
                                <s:actionerror/>
                            </span>
                            <br/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            ${requestScope.register_result}
                            <br/>
                            <br/>
                            <a href="${ pageContext.request.contextPath }/index.action">首页</a>&nbsp;&nbsp;
                            <a href="${ pageContext.request.contextPath }/user_registPage.action">注册</a>&nbsp;&nbsp;
                            <a href="${ pageContext.request.contextPath }/user_loginPage.action">登录</a>
                        </td>
                    </tr>
                </table>
                <h1>&nbsp;</h1></td>
        </tr>
    </table>
</div>
</body>
</html>