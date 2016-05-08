<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<table width="100%">
    <tr>
        <td>图片</td>
        <td>数量</td>
        <td>总金额</td>
        <td>买家</td>
    </tr>
    <s:iterator var="orderItem" value="orderItemList">
        <tr>
            <td><img width="40" height="45" src="${ pageContext.request.contextPath }/${orderItem.product.image}"></td>
            <td>${orderItem.count}</td>
            <td>${orderItem.subtotal}</td>
            <td>${orderItem.order.name}</td>
        </tr>
    </s:iterator>
</table>