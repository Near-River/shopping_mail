<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body {
            scrollbar-arrow-color: #ffffff;
            scrollbar-base-color: #dee3f7;
        }
    </style>
</head>

<frameset rows="103,*" frameborder=0 border="0" framespacing="0">
    <frame src="${pageContext.request.contextPath}/admin/top.jsp" name="topFrame" scrolling="NO" noresize>
    <frameset cols="159,*" frameborder="0" border="0" framespacing="0">
        <frame src="${pageContext.request.contextPath}/admin/left.jsp" name="leftFrame" noresize scrolling="YES">
        <frame src="${pageContext.request.contextPath}/admin/welcome.jsp" name="mainFrame">
    </frameset>
</frameset>

</html>
