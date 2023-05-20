<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>管理员用户查找</title>
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
            $("#say").click(function () {
                var telephone=$("#valuee").val();
                $.ajax({
                    type:"POST",
                    url:"/admin_select_message",
                    data:{
                        telephone:telephone,
                        quanxian:quanxian
                    },
                    dataType:'json',
                    success:function (res) {
                        var str="";
                        str+="<tr><td>"+res.name+
                                "</td><td>"+res.is_driver+
                                "</td><td>"+res.money+
                                "</td><td>"+res.jinji+
                                "</td><td>"+res.location+
                                "</td><td>"+res.car_number+
                                "</td><td>"+res.car_type+
                                "</td><td>"+res.score+
                                "</td><td>"+res.id_card_number+
                                "</td><td>"+res.password+
                                "</td></tr>";
                        $("#b").html(str);
                    },
                    error:function () {
                        $("#b").html(null);
                    }
                });
            })
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
                    <li class="middle2"><span><a href="#">用户查询</a></span></li>
                </ul>
                <ul>
                    <li class="middle2"><span id="cang" style="display: none"><a href="/to_admin_bedriver?page2=first">司机申请处理</a></span></li>
                </ul>
                <ul>
                    <li class="middle2"><span><a href="/to_admin_changeuser">信息更改</a></span></li>
                </ul>
            </nav>
        </div>
        <div class="home">
            <div class="home1">
                <div class="home2">
                    <input type="text" name="telephone" id="valuee" placeholder="输入用户手机号"/>
                    <input type="button" value="查询" id="say"/>
                    <table border="1">
                        <tr>
                            <th>用户名</th><th>是否司机</th><th>余额</th><th>紧急联系方式</th><th>所在城市</th>
                            <th>车牌号</th><th>车辆类型</th><th>信誉分</th><th>身份证号</th><th>密码</th>
                            <tbody id="b"></tbody>
                        </tr>
                        <div style="display:block">${error}</div>
                    </table>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
