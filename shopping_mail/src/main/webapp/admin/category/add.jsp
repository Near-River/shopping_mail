<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form name="Form1" action="${pageContext.request.contextPath}/adminCategory_add.action" method="post">
    <table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee"
           style="border: 1px solid #8ba7e3" border="0">
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26">
                <strong>添加一级分类</strong>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                一级分类名称：
            </td>
            <td class="ta_01" bgColor="#ffffff" colspan="3">
                <input type="text" name="cname" class="bg"/>
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