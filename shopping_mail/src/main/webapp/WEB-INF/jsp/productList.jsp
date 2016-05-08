<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Near商城</title>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>
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
<div class="container productList">
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
        <div id="result" class="result table clearfix">
            <ul>
                <s:if test="pageBean.objs != null">
                    <s:iterator var="obj" value="pageBean.objs">
                        <li>
                            <a href="${pageContext.request.contextPath}/product_show.action?pid=<s:property value="#obj.pid"/>">
                                <img src="${pageContext.request.contextPath}/${obj.image}" width="170" height="170"
                                     style="display: inline-block;">
                                <span style='color:green'>${obj.pname}</span>
                                <span class="price">商城价： ￥${obj.shop_price}</span>
                            </a>
                        </li>
                    </s:iterator>
                </s:if>
            </ul>
        </div>
        <div class="pagination">
            <span>第 ${pageBean.pageNums} / ${pageBean.pageCount} 页</span>
            <s:if test="cid != null">
                <s:if test="pageBean.pageNums > 1">
                    <a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&pageNums=1"
                       class="firstPage">&nbsp;</a>
                    <a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&pageNums=${pageBean.pageNums-1}"
                       class="previousPage">&nbsp;</a>
                </s:if>

                <s:if test="pageBean.pageCount > 1">
                    <s:iterator var="i" begin="1" end="pageBean.pageCount">
                        <s:if test="pageBean.pageNums != #i">
                            <a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&pageNums=${i}">${i}</a>
                        </s:if>
                        <s:else>
                            <span class="currentPage">${i}</span>
                        </s:else>
                    </s:iterator>
                </s:if>

                <s:if test="pageBean.pageNums < pageBean.pageCount">
                    <a class="nextPage"
                       href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&pageNums=${pageBean.pageNums+1}">
                        &nbsp;</a>
                    <a class="lastPage"
                       href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&pageNums=${pageBean.pageCount}">
                        &nbsp;</a>
                </s:if>
            </s:if>

            <s:if test="csid != null">
                <s:if test="pageBean.pageNums > 1">
                    <a href="${pageContext.request.contextPath}/product_findByCsid.action?cid=<s:property value="cid"/>&pageNums=1"
                       class="firstPage">&nbsp;</a>
                    <a href="${pageContext.request.contextPath}/product_findByCsid.action?cid=<s:property value="cid"/>&pageNums=${pageBean.pageNums-1}"
                       class="previousPage">&nbsp;</a>
                </s:if>

                <s:if test="pageBean.pageCount > 1">
                    <s:iterator var="i" begin="1" end="pageBean.pageCount">
                        <s:if test="pageBean.pageNums != #i">
                            <a href="${pageContext.request.contextPath}/product_findByCsid.action?cid=<s:property value="cid"/>&pageNums=${i}">${i}</a>
                        </s:if>
                        <s:else>
                            <span class="currentPage">${i}</span>
                        </s:else>
                    </s:iterator>
                </s:if>

                <s:if test="pageBean.pageNums < pageBean.pageCount">
                    <a class="nextPage"
                       href="${pageContext.request.contextPath}/product_findByCsid.action?cid=<s:property value="cid"/>&pageNums=${pageBean.pageNums+1}">
                        &nbsp;</a>
                    <a class="lastPage"
                       href="${pageContext.request.contextPath}/product_findByCsid.action?cid=<s:property value="cid"/>&pageNums=${pageBean.pageCount}">
                        &nbsp;</a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>

<%@include file="part/footer.jsp" %>

</body>
</html>