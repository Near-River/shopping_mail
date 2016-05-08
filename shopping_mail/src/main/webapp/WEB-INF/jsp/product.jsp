<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Near商城</title>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../../js/jquery-1.9.1.min.js"></script>
    <script>
        $(function () {
            $("#increase").click(function () {
                var quantity = $("#quantity").val();
                // console.debug(quantity);
                $("#quantity").val(parseInt(quantity) + 1);
            });

            $("#decrease").click(function () {
                var quantity = $("#quantity").val();
                $("#quantity").val(parseInt(quantity) - 1);
            });

            $("#addCart").click(function () {
                $("#addCartForm").submit();
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
<div class="container productContent">
    <div class="span6">
        <div class="hotProductCategory">
            <s:if test="#session.categories != null">
                <s:iterator var="category" value="#session.categories">
                    <dl>
                        <dt>
                            <a href="${pageContext.request.contextPath}/product_findByCid.action?cid=${category.cid}&pageNums=1"> ${category.cname} </a>
                        </dt>
                        <s:if test="#category.categorySeconds != null">
                            <s:iterator var="cs" value="#category.categorySeconds">
                                <dd>
                                    <a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=${category.cid}&pageNums=1">${cs.csname}</a>
                                </dd>
                            </s:iterator>
                        </s:if>
                    </dl>
                </s:iterator>
            </s:if>
        </div>
    </div>
    <div class="span18 last">
        <div class="productImage">
            <a title="" style="outline-style: none; text-decoration: none;" id="zoom"
               href="javascript:void(0);" rel="gallery"/>
            <div class="zoomPad">
                <img style="opacity: 1;" title="" class="medium"
                     src="${pageContext.request.contextPath }/<s:property value="model.image"/>"/>
                <div style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;"
                     class="zoomPup"></div>
                <div style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;"
                     class="zoomWindow">
                    <div style="width: 368px;" class="zoomWrapper">
                        <div style="width: 100%; position: absolute; display: none;" class="zoomWrapperTitle"></div>
                        <div style="width: 0%; height: 0px;" class="zoomWrapperImage">
                            <img src="${pageContext.request.contextPath }/<s:property value="model.image"/>"
                                 style="position: absolute; border: 0px none; display: block; left: -432px; top: 0px;">
                        </div>
                    </div>
                </div>
                <div style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;" class="zoomPreload">
                    Loading zoom
                </div>
            </div>
            </a>
        </div>

        <div class="name"><s:property value="model.pname"/></div>
        <div class="sn">
            <div>编号：<s:property value="model.pid"/></div>
        </div>
        <div class="info">
            <dl>
                <dt>亿家价:</dt>
                <dd>
                    <strong>￥：<s:property value="model.shop_price"/>元/份</strong>
                    参 考 价：
                    <del>￥<s:property value="model.market_price"/>元/份</del>
                </dd>
            </dl>
            <dl>
                <dt>促销:</dt>
                <dd>
                    <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
                </dd>
            </dl>
            <dl>
                <dt></dt>
                <dd>
                    <span>    </span>
                </dd>
            </dl>
        </div>
        <div class="action">
            <form id="addCartForm" action="${pageContext.request.contextPath}/cart_addCart.action">
                <dl class="quantity">
                    <dt>购买数量:</dt>
                    <dd>
                        <input id="quantity" name="quantity" value="1" maxlength="4" onpaste="return false;"
                               type="text">
                        <div>
                            <span id="increase" class="increase">&nbsp;</span>
                            <span id="decrease" class="decrease">&nbsp;</span>
                        </div>
                    </dd>
                    <dd>
                        件
                    </dd>
                </dl>
                <div class="buy">
                    <input type="hidden" name="pid" value="${model.pid}"/>
                    <input id="addCart" class="addCart" value="加入购物车" type="button">
                </div>
            </form>
        </div>
        <div id="bar" class="bar">
            <ul>
                <li id="introductionTab">
                    <a href="#introduction">商品介绍</a>
                </li>

            </ul>
        </div>
        <div id="introduction" name="introduction" class="introduction">
            <div class="title">
                <strong><s:property value="model.pdesc"/></strong>
            </div>
            <div>
                <img src="${pageContext.request.contextPath }/<s:property value="model.image"/>"/>
            </div>
        </div>
    </div>
</div>

<%@include file="part/footer.jsp" %>

</body>
</html>