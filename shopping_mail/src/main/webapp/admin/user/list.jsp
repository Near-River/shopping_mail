<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<br>
<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
    <tbody>
    <tr>
        <td class="ta_01" align="center" bgColor="#afd1f3"><strong>用户列表</strong></td>
    </tr>
    <tr>
    </tr>
    <tr>
        <td class="ta_01" align="center" bgColor="#f5fafe">
            <table cellspacing="0" cellpadding="1" rules="all"
                   bordercolor="gray" border="1" id="DataGrid1"
                   style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid;
                   WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse;
                   BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                    <td align="center" width="18%">序号</td>
                    <td align="center" width="17%">用户名称</td>
                    <td align="center" width="17%">真实姓名</td>
                    <td align="center" width="17%">邮箱账号</td>
                    <td width="7%" align="center">编辑</td>
                    <td width="7%" align="center">删除</td>
                </tr>
                <s:iterator var="user" value="pageBean.objs" status="status">
                    <tr onmouseover="this.style.backgroundColor = 'white'"
                        onmouseout="this.style.backgroundColor = '#F5FAFE';">
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">${status.count}</td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">${user.userName}</td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">${user.realName}</td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">${user.email}</td>
                        <td align="center" style="HEIGHT: 22px">
                            <a href="${pageContext.request.contextPath}/adminUser_editUser.action?id=${user.id}">
                                <img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0"
                                     style="CURSOR: hand">
                            </a>
                        </td>
                        <td align="center" style="HEIGHT: 22px">
                            <a href="${pageContext.request.contextPath}/adminUser_deleteUser.action?id=${user.id}">
                                <img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16"
                                     border="0" style="CURSOR: hand">
                            </a>
                        </td>
                    </tr>
                </s:iterator>
            </table>
        </td>
    </tr>
    <tr align="center">
        <td colspan="7">
            第 ${pageBean.pageNums} / ${pageBean.pageCount} 页</span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/adminUser_findAllByPage.action?pageNums=1">首页</a>
            |
            &nbsp;&nbsp;&nbsp;&nbsp;
            <s:if test="pageBean.pageNums > 1">
                <a href="${pageContext.request.contextPath}/adminUser_findAllByPage.action?pageNums=${pageBean.pageNums-1}">上一页 </a>
            </s:if>
            <s:if test="pageBean.pageNums < pageBean.pageCount">
                <a href="${pageContext.request.contextPath}/adminUser_findAllByPage.action?pageNums=${pageBean.pageNums+1}">下一页 </a>
            </s:if>
            &nbsp;&nbsp;&nbsp;&nbsp;
            |
            <a href="${pageContext.request.contextPath}/adminUser_findAllByPage.action?pageNums=${pageBean.pageCount}">尾页</a>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>

