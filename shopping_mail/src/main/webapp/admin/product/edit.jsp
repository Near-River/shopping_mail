<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
</head>
<body>
<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/adminProduct_update.action"
      method="post" enctype="multipart/form-data">
    <input type="hidden" name="pid" value="${product.pid}">
    <input type="hidden" name="image" value="${product.image}">
    <input type="hidden" name="pdate" value="${product.pdate}">
    <table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee"
           style="border: 1px solid #8ba7e3" border="0">
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26">
                <strong>编辑商品</strong>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">商品名称：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="pname" value="${product.pname}" class="bg"/>
            </td>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">是否热门：</td>
            <td class="ta_01" bgColor="#ffffff">
                <select name="is_hot">
                    <option value="1" <s:if test="product.is_hot==1">selected</s:if>>是</option>
                    <option value="0" <s:if test="product.is_hot==0">selected</s:if>>否</option>
                </select>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">市场价格：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="market_price" value="${product.market_price}" class="bg"/>
            </td>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">商城价格：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="shop_price" value="${product.shop_price}" class="bg"/>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">商品图片：</td>
            <td class="ta_01" bgColor="#ffffff" colspan="3"><input type="file" name="upload"/></td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">所属的二级分类：</td>
            <td class="ta_01" bgColor="#ffffff" colspan="3">
                <s:select list="csList" name="categorySecond.csid" headerKey="%{product.categorySecond.csid}"
                          headerValue="%{product.categorySecond.csname}"
                          listKey="csid" listValue="csname" theme="simple"/>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">商品描述：</td>
            <td class="ta_01" bgColor="#ffffff" colspan="3">
                <textarea name="pdesc" rows="10" cols="30">${product.pdesc}</textarea>
            </td>
        </tr>
        <tr>
            <td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
                <button type="submit" value="确定" class="button_ok">&#30830;&#23450;</button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
                <span id="Label1"></span>
            </td>
        </tr>
    </table>
</form>
</body>
</html>