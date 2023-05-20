<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>司机账户</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            var a='${userInfo.is_driver}';
            var telephone='${userInfo.telephone}';
            if(a=="no")
            {
                $("#be_a_driver").show();
            }
            var money="RMB:"+'${userInfo.money}'+"元";
            $("#money").html(money);

            $("#add_money").click(function () {
                $.ajax({
                    type:"POST",
                    url:"/money",
                    data:{
                        telephone:telephone,
                        do:"add_money"
                    },
                    dataType:'text',
                    success:function (res) {
                        $("#money").html(res);
                    },
                    error:function () {
                    }
                });
            });
            $("#delete_money").click(function () {
                $.ajax({
                    type:"POST",
                    url:"/money",
                    data:{
                        telephone:telephone,
                        do:"delete_money"
                    },
                    dataType:'text',
                    success:function (res) {
                        $("#money").html(res);
                    },
                    error:function () {
                    }
                });
            });
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
                <td><a href="/le_driver">返回首页</a></td>
            </tr>
            <tr>
                <td><a href="/driver_message">司机信息</a></td>
            </tr>
            <tr>
                <td><a href="/driver_dingdan?page=first">历史接单</a></td>
            </tr>
            <tr>
                <td><a href="#">司机账户</a></td>
            </tr>
            <tr>
                <td><a href="/driver_out">退出登录</a></td>
            </tr>
        </table>
    </div>

    <div class="right">
        <h3>余额</h3>
        <div class="small">
            <dl>
                <dt>土豪您拥有的财富：</dt>
                <dt><i id="money"></i></dt>
                <dd></dd>
            </dl>
            <table border="1" width="150" bgcolor="white" ><!--table为表格标签，border为边框，cellspacing为格子之间间距-->
                <tr>
                    <td><button id="add_money" >充值100元</button></td><td><button id="delete_money">全部提现</button></td>
                </tr>
            </table>
        </div>
    </div>
</div>

</body>
</html>