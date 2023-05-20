<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>管理员用户信息修改</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/admin.css"/>
    <script type="text/javascript">
        $(function () {
            var quanxian='${quanxian}';
            if(quanxian=="超级")
            {
                $("#cang").show();
            }
            
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
                <li class="middle2"><span id="cang" style="display: none"><a href="/to_admin_bedriver?page2=first">司机申请处理</a></span></li>
            </ul>
            <ul>
                <li class="middle2"><span><a href="#">信息更改</a></span></li>
            </ul>
        </nav>
    </div>
    <div class="home">
        <div class="home1">
            <div class="home2">
                <form action="/admin_change_user" method="post">
                <div style="display:block">${error}</div>
                <label>手机号：</label><input type="text" name="telephone" ><br/>
                <label>紧急联系电话：</label><input type="text" name="jinji" ><br/>
                <label>常用地址：</label><textarea  name="location" ></textarea><br/>
                <label>车牌号：</label><input type="text" name="car_number" ><br/>
                <label>车辆类型：</label><input type="text" name="car_type" ><br/>
                <label>信誉分：</label><input type="text" name="score" ><br/>
                <input class="biaodan" type="submit" value="提交" >
            </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
