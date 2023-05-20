<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人信息</title>
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
                <td><a href="#">个人信息</a></td>
            </tr>
            <tr>
                <td><a href="/my_dingdan?page=first">历史订单</a></td>
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
        <h3>个人信息</h3>
        <div class="small">
            <dl>
                <dt>用户名：${userInfo.name}</dt>
                <dt>紧急联系电话：${userInfo.jinji}</dt>
                <dt>所在城市：${userInfo.location}</dt>
                <dt>车牌号：${userInfo.car_number}</dt>
                <dt>车辆类型：${userInfo.car_type}</dt>
                <dt>信誉分：${userInfo.score}</dt>
                <dd></dd>
            </dl>
        </div>
        <h3>编辑信息  <a  id="shiming" href="/shiming?I_am=user">实名认证</a></h3>
        <div class="small">
            <form action="/update_message" method="post">
                <div style="display:block">${error}</div>
                <label>密码：</label><input type="text" name="password" ><br/>
                <label>用户名：</label><input type="text" name="name" ><br/>
                <label>紧急联系电话：</label><input type="text" name="jinji" ><br/>
                <label>常用地址：</label><textarea  name="location" ></textarea><br/>
                <label>车牌号：</label><input type="text" name="car_number" ><br/>
                <label>车辆类型：</label><input type="text" name="car_type" ><br/>

                <input class="biaodan" type="submit" value="提交" >
            </form>
        </div>
    </div>
</div>
</body>
</html>