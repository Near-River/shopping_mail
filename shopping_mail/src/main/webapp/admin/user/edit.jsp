<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
</head>
<body>
<form name="Form1" action="${pageContext.request.contextPath}/adminUser_updateUser.action" method="post">
    <input type="hidden" name="id" value="${editUser.id}"/>
    <input type="hidden" name="state" value="${editUser.state}"/>
    <input type="hidden" name="activeCode" value="${editUser.activeCode}"/>
    &nbsp;
    <table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee"
           style="border: 1px solid #8ba7e3" border="0">
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26">
                <strong>编辑用户</strong>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">用户名称：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="userName" value="${editUser.userName}" class="bg"/>
            </td>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">密码：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="password" value="${editUser.password}" class="bg"/>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">真实姓名：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="realName" value="${editUser.realName}" class="bg"/>
            </td>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">邮箱：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="email" value="${editUser.email}" class="bg"/>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">电话：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="phone" value="${editUser.phone}" class="bg"/>
            </td>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">地址：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="address" value="${editUser.address}" class="bg"/>
            </td>
        </tr>
        <tr>
            <td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
                <button type="submit" value="确定" class="button_ok">&#30830;&#23450;</button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
                <span id="Label1"></span>
            </td>
        </tr>
    </table>
</form>
</body>
</html>