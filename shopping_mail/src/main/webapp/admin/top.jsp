<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style type="text/css">
        BODY {
            MARGIN: 0px;
            BACKGROUND-COLOR: #ffffff;
            FONT-SIZE: 12px;
            COLOR: #000000
        }

        TD {
            FONT-SIZE: 12px;
            COLOR: #000000
        }

        TH {
            FONT-SIZE: 12px;
            COLOR: #000000
        }
    </style>
    <link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css">
</HEAD>
<body>
<table width="100%" height="70px" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td width="100%" bgcolor="#8a2be2" style="font:36px bolder; text-align:center; color:white">网上商城管理系统</td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="30px" valign="bottom">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="85%" align="left">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span color="#000000">
                            <script language="JavaScript">
                                tmpDate = new Date();
                                date = tmpDate.getDate();
                                month = tmpDate.getMonth() + 1;
                                year = tmpDate.getFullYear();
                                document.write(year);
                                document.write("年");
                                document.write(month);
                                document.write("月");
                                document.write(date);
                                document.write("日 ");

                                myArray = new Array(6);
                                myArray[0] = "星期日"
                                myArray[1] = "星期一"
                                myArray[2] = "星期二"
                                myArray[3] = "星期三"
                                myArray[4] = "星期四"
                                myArray[5] = "星期五"
                                myArray[6] = "星期六"
                                weekday = tmpDate.getDay();
                                if (weekday == 0 | weekday == 6) {
                                    document.write(myArray[weekday])
                                }
                                else {
                                    document.write(myArray[weekday])
                                }
                            </script>
                        </span>
                    </td>
                    <td width="15%">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="155" valign="bottom">
                                    用户名：<span style="color:darkblue"><s:property value="#session.administrator.username"/></span>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
