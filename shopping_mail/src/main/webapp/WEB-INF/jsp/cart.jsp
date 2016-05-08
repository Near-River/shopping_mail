<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
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
    <s:if test="#session.existUser != null">
        <s:if test="#session.cart.cartItems.size() != 0">
            <div class="span24">
                <div class="step step1">
                    购物车信息
                </div>
                <table>
                    <tbody>
                    <tr>
                        <th>图片</th>
                        <th>商品</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>小计</th>
                        <th>操作</th>
                    </tr>
                    <s:iterator var="cartItem" value="#session.cart.cartItems">
                        <tr>
                            <td width="60">
                                <img src="${pageContext.request.contextPath}/${cartItem.product.image}">
                            </td>
                            <td>
                                <a target="_blank">${cartItem.product.pname}</a>
                            </td>
                            <td>
                                ￥${cartItem.product.shop_price}
                            </td>
                            <td class="quantity" width="60">
                                ${cartItem.count}
                            </td>
                            <td width="140">
                                <span class="subtotal">￥${cartItem.subtotal}</span>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/cart_removeCart.action?pid=${cartItem.product.pid}"
                                   class="delete">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
                <dl id="giftItems" class="hidden" style="display: none;">
                </dl>
                <div class="total">
                    <em id="promotion"></em>
                    <em>
                        登录后确认是否享有优惠
                    </em>
                    赠送积分: <em id="effectivePoint">${sessionScope.cart.totalmoney}</em>
                    商品金额: <strong id="effectivePrice">￥${sessionScope.cart.totalmoney}元</strong>
                </div>
                <div class="bottom">
                    <a href="${pageContext.request.contextPath}/cart_clearCart.action" id="clear" class="clear">清空购物车</a>
                    <a href="${pageContext.request.contextPath}/order_add.action" id="submit" class="submit">提交订单</a>
                </div>
            </div>
        </s:if>
        <s:else>
            <div class="span24">
                <div class="step step1">
                    <span><h2>亲!您还没有购物!请先去购物!</h2></span>
                </div>
            </div>
        </s:else>

    </s:if>
    <s:else>
        <div class="span24">
            <div class="step step1">
                <span><h2>亲!您还未登陆!请先登录商城!</h2></span>
            </div>
        </div>
    </s:else>
</div>

<%@include file="part/footer.jsp" %>

</body>
</html>