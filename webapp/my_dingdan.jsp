<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>历史订单</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            var a='${userInfo.is_driver}';
            if(a=="no")
            {
                $("#be_a_driver").show();
            }
        })
    </script>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet" type="text/css" href="./css/three.css"/>
</head>
<body>

<div>
    <div class="left">

        <table border="1" bgcolor="white"><!--table为表格标签，border为边框，cellspacing为格子之间间距-->
            <tr>
                <td><a href="/le_daijia">返回首页</a></td>
            </tr>
            <tr>
                <td><a href="/my_message">个人信息</a></td>
            </tr>
            <tr>
                <td><a href="#">历史订单</a></td>
            </tr>
            <tr>
                <td><a href="/my_zhanghu">我的账户</a></td>
            </tr>
            <tr>
                <td><a href="/out">退出登录</a></td>
            </tr>
            <tr id="be_a_driver">
                <td><a href="/be_a_driver">司机申请</a></td>
            </tr>
        </table>
    </div>

    <div class="right">
        <h3>您的历史订单：</h3>
        <div class="small2">
            <table border="1">
            <tr><th>司机手机号</th><th>下单时间</th><th>起点</th><th>终点</th><th>状态</th><th></th></tr>
            <c:forEach items="${wholeDingdans}" var="w">
                <tr><td>${w.driver_telephone}</td><td>${w.send_time}</td><td>${w.qidian}</td><td>${w.zhongdian}</td><td>${w.zhuangtai}</td>
                    <td><a href="about?ID=${w.ID}&I_am=user">详情</a></td></tr>
            </c:forEach>
            </table>
        </div>
        <a href="/my_dingdan?page=up">上一页</a>
        <a>第${page+1}页</a>
        <a href="/my_dingdan?page=down">下一页</a>
    </div>


</div>
</body>
</html>