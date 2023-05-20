<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>详情</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <script type="text/javascript" src="./js/jquery.js"></script>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet" type="text/css" href="./css/three.css"/>
    <script type="text/javascript">
        $(function () {
            $("#GO").click(function () {
                window.location.assign("/xiadan2?ID=${need.ID}");
            })
        })
    </script>
</head>
<body>

<table border="1">
    <tr>
        <td>订单号:${need.ID}</td>
    </tr>
    <tr>
        <td>下单手机号:${need.telephone}</td>
    </tr>
    <tr>
        <td>司机手机号:${need.driver_telephone}</td>
    </tr>
    <tr>
        <td>下单时间:${need.send_time}</td>
    </tr>
    <tr>
        <td>接单时间:${need.jiedan_time}</td>
    </tr>
    <tr>
        <td>订单完成时间:${need.final_time}</td>
    </tr>
    <tr>
        <td>预计用时:${need.use_time}</td>
    </tr>
    <tr>
        <td>预计里程:${need.distance}</td>
    </tr>
    <tr>
        <td>订单状态:${need.zhuangtai}</td>
    </tr>
    <tr>
        <td>所在城市:${need.city}</td>
    </tr>
    <tr>
        <td>起点:${need.qidian}</td>
    </tr>
    <tr>
        <td>终点:${need.zhongdian}</td>
    </tr>
    <tr>
        <td>所选方案:${need.fangan}</td>
    </tr>
    <tr>
        <td><button id="GO">继续等待！</button></td>
    </tr>
    <tr>
        <td>用户砍价:${need.kanjia}</td>
    </tr>
    <tr>
        <td>砍价理由:${need.why_kanjia}</td>
    </tr>
    <tr>
        <td>司机提价:${need.tijia}</td>
    </tr>
    <tr>
        <td>提价理由:${need.why_tijia}</td>
    </tr>
    <tr>
        <td>预计价格:${need.price}</td>
    </tr>
    <tr>
        <td>最终价格:${need.final_price}</td>
    </tr>

</table>
</body>