<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="span10 last">
    <div class="topNav clearfix">
        <ul>
            <s:if test="#session.existUser == null">
                <li id="headerLogin" class="headerLogin" style="display: list-item;">
                    <a href="${pageContext.request.contextPath}/user_loginPage.action">登录</a>|
                </li>
                <li id="headerRegister" class="headerRegister"
                    style="display: list-item;"><a
                        href="${pageContext.request.contextPath}/user_registerPage.action">注册</a>|
                </li>
            </s:if>
            <s:else>
                <li id="headerLogin" class="headerLogin" style="display: list-item;">
                    <s:property value="#session.existUser.userName"/>
                    |
                </li>
                <li id="headerLogin" class="headerLogin" style="display: list-item;">
                    <a href="${pageContext.request.contextPath}/cart_findByUid.action?pageNums=1">我的订单</a>
                    |
                </li>
                <li id="headerRegister" class="headerRegister"
                    style="display: list-item;"><a href="${pageContext.request.contextPath}/user_logout.action">退出</a>|
                </li>
            </s:else>

            <li><a href="javascript:void(0);">会员中心</a> |</li>
            <li><a href="javascript:void(0);">购物指南</a> |</li>
            <li><a href="javascript:void(0);">关于我们</a></li>
        </ul>
    </div>
    <div class="cart">
        <a href="${pageContext.request.contextPath}/show_myCart.action">购物车</a>
    </div>
    <div class="phone">
        客服热线: <strong>96008/53277764</strong>
    </div>
</div>
<div class="span24">
    <ul class="mainNav">
        <li><a href="${pageContext.request.contextPath}/index.action">首页</a></li>
        <s:iterator var="category" value="#session.categories">
            <li>| <a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=${category.cid}&pageNums=1">${category.cname}</a></li>
        </s:iterator>

    </ul>
</div>