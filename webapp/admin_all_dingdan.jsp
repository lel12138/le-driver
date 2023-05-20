<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>管理员全部订单</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/admin.css"/>
    <script type="text/javascript">
    </script>
</head>
<body class="all">
<div class="top">
    <div class="pingpai">
        <a>乐代驾管理</a>
    </div>
    <div class="top-select">
        <ul class="top-left">
            <li class="top1"><a href="/to_admin" class="top2">用户管理</a></li>
            <li class="top1"><a href="/#" class="top2">订单管理</a></li>
        </ul>
        <div class="top-right">
            <div class="top-right2">
                <li class="top1"><a class="top3">${quanxian}管理员已登录</a></li>
            </div>
        </div>
    </div>
    <div class="middle">
        <nav class="middle1">
            <ul>
                <li class="middle2"><span><a href="/to_admin_dingdan_select">订单查询</a></span></li>
            </ul>
            <ul>
                <li class="middle2"><span><a href="#">全部订单</a></span></li>
            </ul>
        </nav>
    </div>
    <div class="home">
        <div class="home1">
            <div class="home2">
                <div class="small2">
                    <table border="1">
                        <tr><th>订单号</th><th>用户手机号</th><th>司机手机号</th><th>下单时间</th><th>起点</th><th>终点</th><th>状态</th></tr>
                        <c:forEach items="${wholeDingdans}" var="w">
                            <tr><td>${w.ID}</td><td>${w.telephone}</td><td>${w.driver_telephone}</td><td>${w.send_time}</td><td>${w.qidian}</td><td>${w.zhongdian}</td><td>${w.zhuangtai}</td></tr>
                        </c:forEach>
                    </table>
                </div>
                <a href="/to_admin_all_dingdan?page=up" style="color: red">上一页</a>
                <a style="color: red">第${page+1}页</a>
                <a href="/to_admin_all_dingdan?page=down" style="color: red">下一页</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
