<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>网上商城管理中心</title>
    <style type="text/css">
        body {
            margin: auto 40%;
            color: #FFFFFF;
            font-size: 14px
        }
    </style>
<body style="background: #278296">
<form method="post" action="${pageContext.request.contextPath }/adminUser_login.action">
    <table cellspacing="0" cellpadding="0" style="margin-top: 40%" align="center">
        <tr>
            <td style="padding-left: 50px">
                <s:actionerror />
                <table>
                    <tr>
                        <td>管理员姓名：</td>
                        <td><input type="text" name="username"/></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>管理员密码：</td>
                        <td><input type="password" name="password"/></td>
                    </tr>

                    <tr>
                        <td colspan="2" align="right"><input type="submit" value="提交"/></td>
                    </tr>

                    <tr>
                        <td colspan="2" align="right">
                            &raquo; <a href="../" style="color:#FFFFFF">返回首页</a> &raquo;
                            <a href="javascript:void(0);" style="color:white">忘记密码?</a>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <input type="hidden" name="act" value="signin"/>
</form>

</body>