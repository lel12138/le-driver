<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>管理员订单查询</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/admin.css"/>
    <script type="text/javascript">
        $(function () {
            var quanxian='${quanxian}';
            $("#say").click(function () {
                var ID=$("#valuee").val();
                $.ajax({
                    type:"POST",
                    url:"/admin_select_dingdan",
                    data:{
                        ID:ID,
                    },
                    dataType:'json',
                    success:function (res) {

                        var str="";
                        str+="<tr><td>"+res.telephone+
                            "</td><td>"+res.driver_telephone+
                            "</td><td>"+res.send_time+
                            "</td><td>"+res.jiedan_time+
                            "</td><td>"+res.final_time+
                            "</td><td>"+res.use_time+
                            "</td><td>"+res.distance+
                            "</td><td>"+res.zhuangtai+
                            "</td><td>"+res.city+
                            "</td></tr>";
                        $("#a").html(str);
                        str="";
                        str+="</td><td>"+res.qidian+
                            "</td><td>"+res.zhongdian+
                            "</td><td>"+res.fangan+
                            "</td><td>"+res.kanjia+
                            "</td><td>"+res.why_kanjia+
                            "</td><td>"+res.tijia+
                            "</td><td>"+res.why_tijia+
                            "</td><td>"+res.price+
                            "</td><td>"+res.final_price+
                            "</td></tr>";
                        $("#b").html(str);
                    },
                    error:function () {
                        console.log("error")
                        $("#a").html(null);
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
            <li class="top1"><a href="/to_admin" class="top2">用户管理</a></li>
            <li class="top1"><a href="#" class="top2">订单管理</a></li>
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
                <li class="middle2"><span><a href="#">订单查询</a></span></li>
            </ul>
            <ul>
                <li class="middle2"><span><a href="/to_admin_all_dingdan?page=first">全部订单</a></span></li>
            </ul>
        </nav>
    </div>
    <div class="home">
        <div class="home1">
            <div class="home2">
                <input type="text" name="ID" id="valuee" placeholder="输入订单号" class="long"/>
                <input type="button" value="查询" id="say"/>
                <table border="1" id="little">
                    <tr id="little2">
                        <th>下单手机号</th><th>司机手机号</th><th>下单时间</th><th>接单时间</th><th>订单完成时间</th>
                        <th>预计用时</th><th>预计里程</th><th>订单状态</th><th>所在城市</th>
                        <tbody id="a" style="color: red"></tbody>
                        <th>起点</th><th>终点</th><th>所选方案</th><th>用户砍价</th><th>砍价理由</th><th>司机提价</th>
                        <th>提价理由</th><th>预计价格</th><th>最终价格</th></tr>
                        <tbody id="b" style="color: red"></tbody>
                    <div style="display:block">${error}</div>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
