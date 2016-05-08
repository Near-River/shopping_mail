<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单页面</title>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#payorder").click(function(){
                $("#orderForm").submit();
            });
        });
    </script>
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
    <s:if test="#attr.order != null">
        <div class="span24">
            <div class="step step1">
                <ul>
                    <li class="current"></li>
                    <li>生成订单成功！</li>
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
                </tr>
                <s:iterator var="orderItem" value="#attr.order.orderItems">
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
                        <td width="140">
                            <span class="subtotal">￥${orderItem.subtotal}</span>
                        </td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
            <dl id="giftItems" class="hidden" style="display: none;">
            </dl>
            <div class="total">
                <em id="promotion"></em>
                商品金额: <strong id="effectivePrice">￥${order.totalMoney}元</strong>
            </div>

            <form id="orderForm" action="${pageContext.request.contextPath}/order_payOrder.action" method="post">
                <input type="hidden" name="order.oid" value="<s:property value="order.oid"/>"/>
                <div class="span24">
                    <p>
                        收货地址：<input value="${order.addr}" name="order.addr" type="text" style="width:350px"/>
                        <br/>
                        收货人&nbsp;&nbsp;&nbsp;：<input value="${order.name}" name="order.name" type="text" style="width:150px"/>
                        <br/>
                        联系方式：<input value="${order.phone}" name="order.phone" type="text" style="width:150px"/>
                    </p>
                    <hr/>
                    <p>
                        选择银行：<br/>
                        <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
                        <img src="${pageContext.request.contextPath}/bank_img/icbc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
                        <img src="${pageContext.request.contextPath}/bank_img/bc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
                        <img src="${pageContext.request.contextPath}/bank_img/abc.bmp" align="middle"/>
                        <br/>
                        <input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
                        <img src="${pageContext.request.contextPath}/bank_img/bcc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="PINGANBANK-NET"/>平安银行
                        <img src="${pageContext.request.contextPath}/bank_img/pingan.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
                        <img src="${pageContext.request.contextPath}/bank_img/ccb.bmp" align="middle"/>
                        <br/>
                        <input type="radio" name="pd_FrpId" value="CEB-NET-B2C"/>光大银行
                        <img src="${pageContext.request.contextPath}/bank_img/guangda.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C"/>招商银行
                        <img src="${pageContext.request.contextPath}/bank_img/cmb.bmp" align="middle"/>
                    </p>
                    <hr/>
                    <p style="text-align:right">
                        <a href="javascript:void(0);" id="payorder">
                            <img src="${pageContext.request.contextPath}/images/finalbutton.gif" width="204" height="51"
                                 border="0"/>
                        </a>
                    </p>
                </div>
            </form>
        </div>
    </s:if>
    <s:else>
        <div class="span24">
            <div class="step step1">
                <span><h2>生成订单出错，请联系管理员！</h2></span>
            </div>
        </div>
    </s:else>
</div>

<%@include file="part/footer.jsp" %>

</body>
</html>