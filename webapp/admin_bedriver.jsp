<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>管理员司机申请</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/admin.css"/>
    <script type="text/javascript">
        var telephone='${userInfo.telephone}';
        console.log(telephone.length);
        $(function () {
            if(telephone.length!=0)
            {
                var lujing="<img src=\"./images/"+telephone+"+1.jpg"+"\">"+"<img src=\"./images/"+telephone+"+2.jpg"+"\">"+"<img src=\"./images/"+telephone+"+3.jpg"+"\">";
                $("#tupian").html(lujing);
            }

            $("#tong").click(function () {
                window.location.assign("/admin_tonghuoju?telephone=${userInfo.telephone}&doo=tong")
            });
            $("#ju").click(function () {
                window.location.assign("/admin_tonghuoju?telephone=${userInfo.telephone}&doo=ju")
            });
        })
    </script>
</head>
<body class="all">
<div class="top">
    <div class="pingpai">
        <a>乐代驾管理</a>
    </div>

    <div class="top-select">
        <ul class="top-left">
            <li class="top1"><a href="#" class="top2">用户管理</a></li>
            <li class="top1"><a href="/to_admin_dingdan_select" class="top2">订单管理</a></li>
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
                <li class="middle2"><span><a href="/to_admin">用户查询</a></span></li>
            </ul>
            <ul>
                <li class="middle2"><span><a href="#">司机申请处理</a></span></li>
            </ul>
            <ul>
                <li class="middle2"><span><a href="/to_admin_changeuser">信息更改</a></span></li>
            </ul>
        </nav>
    </div>
    <div class="home">
        <div class="home1">
            <div class="home2">
                <div class="small2">
                    <table border="1">
                        <tr>
                            <th>用户名</th><th>手机号</th><th>紧急联系方式</th><th>所在城市</th><th>信誉分</th><th>身份证号</th><th></th><th></th>
                        </tr>
                        <tr>
                            <th>${userInfo.name}</th><th>${userInfo.telephone}</th><th>${userInfo.jinji}</th><th>${userInfo.location}</th><th>${userInfo.score}</th>
                            <th>${userInfo.ID_card_number}</th><th><input type="button" value="通过" id="tong"></th><th><input type="button" value="拒绝" id="ju"></th>
                        </tr>
                    </table>
                    <i id="tupian"></i>
                </div>
                <a href="/to_admin_bedriver?page2=up"  style="color: red">上一页</a>
                <a style="color: red">第${page2+1}页</a>
                <a href="/to_admin_bedriver?page2=down" style="color: red">下一页</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
