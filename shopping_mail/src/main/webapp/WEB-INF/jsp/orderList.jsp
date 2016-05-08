<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>
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
                 title="正品保障"/>
        </div>
    </div>
    <%@ include file="part/menu.jsp" %>
</div>
<div class="container cart">
    <s:if test="pageBean.objs != null">
        <s:iterator var="order" value="pageBean.objs">
            <div class="span24">
                <div class="step step1">
                    <ul>
                        <li>订单编号：${order.oid}</li>
                        <li style="margin-left:300px;">
                            <a href="${pageContext.request.contextPath}/order_delete.action?oid=${order.oid}">删除订单</a>
                        </li>
                        <li>
                            <s:if test="#order.state==1">
                                <a href="${pageContext.request.contextPath}/order_findByOid.action?oid=${order.oid}">付款</a>
                            </s:if>
                        </li>
                    </ul>
                </div>
                <table>
                    <tbody>
                    <tr>
                        <th>图片</th>
                        <th>商品</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>小计</th>
                        <th>状态</th>
                    </tr>
                    <s:iterator var="orderItem" value="#order.orderItems">
                        <tr>
                            <td width="60">
                                <img src="${pageContext.request.contextPath}/${orderItem.product.image}">
                            </td>
                            <td>
                                <a target="_blank">${orderItem.product.pname}</a>
                            </td>
                            <td>
                                ￥${orderItem.product.shop_price}
                            </td>
                            <td class="quantity" width="60">
                                <span> ${orderItem.count} </span>
                            </td>
                            <td width="120">
                                <span class="subtotal">￥${orderItem.subtotal}</span>
                            </td>
                            <td width="140">
                                <s:if test="#order.state == 1">
                                    <span style="color:red">未付款</span>
                                </s:if>
                                <s:elseif test="#order.state == 2">
                                    <span style="color:orangered">正在发货</span>
                                </s:elseif>
                                <s:elseif test="#order.state == 3">
                                    <a href="${pageContext.request.contextPath}/order_updateState.action?oid=${order.oid}"><span style="color:orange">店家已发货</span></a>
                                </s:elseif>
                                <s:elseif test="#order.state == 4">
                                    <span style="color:green">确认收货</span>
                                </s:elseif>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </s:iterator>
        <div class="pagination">
            <span>第 ${pageBean.pageNums} / ${pageBean.pageCount} 页</span>
            <s:if test="pageBean.pageNums > 1">
                <a href="${pageContext.request.contextPath}/cart_findByUid.action?pageNums=1"
                   class="firstPage">&nbsp;</a>
                <a href="${pageContext.request.contextPath}/cart_findByUid.action?pageNums=${pageBean.pageNums-1}"
                   class="previousPage">&nbsp;</a>
            </s:if>

            <s:if test="pageBean.pageCount > 1">
                <s:iterator var="i" begin="1" end="pageBean.pageCount">
                    <s:if test="pageBean.pageNums != #i">
                        <a href="${pageContext.request.contextPath}/cart_findByUid.action?pageNums=${i}">${i}</a>
                    </s:if>
                    <s:else>
                        <span class="currentPage">${i}</span>
                    </s:else>
                </s:iterator>
            </s:if>

            <s:if test="pageBean.pageNums < pageBean.pageCount">
                <a class="nextPage"
                   href="${pageContext.request.contextPath}/cart_findByUid.action?pageNums=${pageBean.pageNums+1}">
                    &nbsp;</a>
                <a class="lastPage"
                   href="${pageContext.request.contextPath}/cart_findByUid.action?pageNums=${pageBean.pageCount}">
                    &nbsp;</a>
            </s:if>
        </div>
    </s:if>
    <s:else>
        <div class="span24">
            <div class="step step1">
                <span><h2>您还没有提交订单！</h2></span>
            </div>
        </div>
    </s:else>
</div>

<%@include file="part/footer.jsp" %>

</body>
</html>