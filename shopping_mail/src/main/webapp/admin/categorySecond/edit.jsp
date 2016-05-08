<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
</head>
<body>
<form name="Form1" action="${pageContext.request.contextPath}/adminCategorySecond_update.action" method="post">
    <input type="hidden" name="csid" value="${categorySecond.csid}">
    &nbsp;
    <table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee"
           style="border: 1px solid #8ba7e3" border="0">
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="3" height="26">
                <strong>编辑二级分类</strong>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                二级分类名称：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="csname" value="${categorySecond.csname}" class="bg"/>
            </td>
            <td width="15%" align="center" bgColor="#f5fafe" class="ta_01" style="margin-right:1000px;">
                所属的一级分类：<s:select list="categoryList" name="category.cid" headerKey="%{categorySecond.category.cid}" headerValue="%{categorySecond.category.cname}"
                                  listKey="cid" listValue="cname" theme="simple"/>
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