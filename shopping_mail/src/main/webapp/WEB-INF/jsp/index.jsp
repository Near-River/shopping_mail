<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Near商城</title>
    <link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
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

<div class="container index">

    <%--热门商品栏目--%>
    <div class="span24">
        <div id="hotProduct" class="hotProduct clearfix">
            <div class="title">
                <strong>热门商品</strong>
            </div>
            <ul class="tab">
                <li class="current">
                    <a href="#" target="_blank"></a>
                </li>
            </ul>
            <ul class="tabContent" style="display: block;">
                <s:if test="%{hotProducts != null}">
                    <s:iterator var="product" value="%{hotProducts}">
                        <li>
                            <a target="_blank" href="${pageContext.request.contextPath}/product_show.action?pid=${product.pid}">
                                <img src="${pageContext.request.contextPath}/${product.image}"
                                     data-original="http://storage.shopxx.net/demo-image/3.0/201301/0ff130db-0a1b-4b8d-a918-ed9016317009-thumbnail.jpg"
                                     style="display: block;"></a>
                        </li>
                    </s:iterator>
                </s:if>
            </ul>
        </div>
    </div>

    <%--最新商品栏目--%>
    <div class="span24">
        <div id="newProduct" class="newProduct clearfix">
            <div class="title">
                <strong>最新商品</strong>
                <a target="_blank"></a>
            </div>
            <ul class="tab">
                <li class="current">
                    <a href="#" target="_blank"></a>
                </li>
            </ul>
            <ul class="tabContent" style="display: block;">
                <s:if test="%{newProducts != null}">
                    <s:iterator var="product" value="%{newProducts}">
                        <li>
                            <a target="_blank" href="${pageContext.request.contextPath}/product_show.action?pid=${product.pid}">
                                <img src="${pageContext.request.contextPath}/${product.image}"
                                     data-original="http://storage.shopxx.net/demo-image/3.0/201301/0ff130db-0a1b-4b8d-a918-ed9016317009-thumbnail.jpg"
                                     style="display: block;"></a>
                        </li>
                    </s:iterator>
                </s:if>
            </ul>
        </div>
    </div>
    <%--新手指南区--%>
    <div class="span24">
        <div class="friendLink">
            <dl>
                <dt>新手指南</dt>
                <dd>
                    <a href="javascript:void(0);" target="_blank">支付方式</a>
                    |
                </dd>
                <dd>
                    <a href="javascript:void(0);" target="_blank">配送方式</a>
                    |
                </dd>
                <dd>
                    <a href="javascript:void(0);" target="_blank">售后服务</a>
                    |
                </dd>
                <dd>
                    <a href="javascript:void(0);" target="_blank">购物帮助</a>
                    |
                </dd>
                <dd>
                    <a href="javascript:void(0);" target="_blank">蔬菜卡</a>
                    |
                </dd>
                <dd>
                    <a href="javascript:void(0);" target="_blank">礼品卡</a>
                    |
                </dd>
                <dd>
                    <a href="javascript:void(0);" target="_blank">银联卡</a>
                    |
                </dd>
                <dd>
                    <a href="javascript:void(0);" target="_blank">亿家卡</a>
                    |
                </dd>

                <dd class="more">
                    <a>更多</a>
                </dd>
            </dl>
        </div>
    </div>
</div>

<%@include file="part/footer.jsp" %>

</body>
</html>