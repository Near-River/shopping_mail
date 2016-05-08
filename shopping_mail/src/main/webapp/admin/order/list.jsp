<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function showDetail(oid) {
            console.debug(oid);
            var btn = $("#" + "but" + oid).val();
            console.debug($("#" + "but" + oid).val());
            console.debug(btn);
            var detailDiv = $("#div" + oid);

            if (btn == "订单详情") {
                var url = "${pageContext.request.contextPath}/adminOrder_findOrderItemsByOid.action";
                var data = {
                    oid: oid,
                    time: new Date().getTime()
                };

                $.post(url, data, function (html) {
                    console.debug(html);
                    detailDiv.html(html);
                    console.debug("success");
                });
                $("#but" + oid).val("关闭");
                return;
            } else if (btn == "关闭") {
                document.getElementById("div" + oid).innerHTML = "";
                $("#but" + oid).val("订单详情");
            }
        }

        function showOrderDetail(oid) {
            var but = document.getElementById("but" + oid);
            var div1 = document.getElementById("div" + oid);
            if (but.value == "订单详情") {
                // 1.创建异步对象
                var xhr = createXmlHttp();
                // 2.设置监听
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4) {
                        if (xhr.status == 200) {

                            div1.innerHTML = xhr.responseText;
                        }
                    }
                };
                // 3.打开连接
                xhr.open("GET", "${pageContext.request.contextPath}/adminOrder_findOrderItemsByOid.action?oid=" + oid + "&time=" + new Date().getTime(), true);
                // 4.发送
                xhr.send(null);
                but.value = "关闭";
            } else {
                div1.innerHTML = "";
                but.value = "订单详情";
            }
        }
        function createXmlHttp() {
            var xmlHttp;
            try { // Firefox, Opera 8.0+, Safari
                xmlHttp = new XMLHttpRequest();
            }
            catch (e) {
                try {// Internet Explorer
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                }
                catch (e) {
                    try {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    catch (e) {
                    }
                }
            }
            return xmlHttp;
        }
    </script>
</head>
<body>
<br>
<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
    <tbody>
    <tr>
        <td class="ta_01" align="center" bgColor="#afd1f3"><strong>订单列表</strong></td>
    </tr>

    <tr>
        <td class="ta_01" align="center" bgColor="#f5fafe">
            <table cellspacing="0" cellpadding="1" rules="all"
                   bordercolor="gray" border="1" id="DataGrid1"
                   style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid;
                   WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse;
                   BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                    <td align="center" width="10%">序号</td>
                    <td align="center" width="10%">订单编号</td>
                    <td align="center" width="10%">订单金额</td>
                    <td align="center" width="10%">收货人</td>
                    <td align="center" width="10%">订单状态</td>
                    <td align="center" width="50%">订单详情</td>
                </tr>
                <s:iterator var="order" value="pageBean.objs" status="status">
                    <tr onmouseover="this.style.backgroundColor = 'white'"
                        onmouseout="this.style.backgroundColor = '#F5FAFE';">
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="14%">
                                ${status.count}
                        </td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="14%">
                                ${order.oid}
                        </td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="14%">
                                ${order.totalMoney}
                        </td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="14%">
                                ${order.name}
                        </td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="14%">
                            <s:if test="#order.state==1">
                                未付款
                            </s:if>
                            <s:if test="#order.state==2">
                                <a href="${pageContext.request.contextPath}/adminOrder_updateState.action?oid=${order.oid}">发货</a>
                            </s:if>
                            <s:if test="#order.state==3">
                                等待确认收货
                            </s:if>
                            <s:if test="#order.state==4">
                                订单完成
                            </s:if>
                        </td>
                        <td align="center" style="HEIGHT: 22px">
                            <input type="button" value="订单详情" id="but${order.oid}"
                                   onclick="showDetail(${order.oid})"/>
                            <div id="div${order.oid}"></div>
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
            <a href="${pageContext.request.contextPath}/adminOrder_findAllByPage.action?pageNums=1">首页</a>
            |
            &nbsp;&nbsp;&nbsp;&nbsp;
            <s:if test="pageBean.pageNums > 1">
                <a href="${pageContext.request.contextPath}/adminOrder_findAllByPage.action?pageNums=${pageBean.pageNums-1}">上一页 </a>
            </s:if>
            <s:if test="pageBean.pageNums < pageBean.pageCount">
                <a href="${pageContext.request.contextPath}/adminOrder_findAllByPage.action?pageNums=${pageBean.pageNums+1}">下一页 </a>
            </s:if>
            &nbsp;&nbsp;&nbsp;&nbsp;
            |
            <a href="${pageContext.request.contextPath}/adminOrder_findAllByPage.action?pageNums=${pageBean.pageCount}">尾页</a>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>

