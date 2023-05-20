<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>司机登录</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <script type="text/javascript" src="./js/jquery.js"></script>

    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet" type="text/css" href="./css/denglu.css"/>
</head>
<body>
<form  id="biaoge" action="/driver_denglu" method="post">
    <!--action为表单提交的服务器地址，method为请求方式，get方式把参数全部挂在网址后面，参数有限制且不安全-->

    <div class="biaodan1">欢迎司机登录</div><!--h1到h6为标题-->

    <input class="biaodan2" type="text" name="telephone" placeholder="请输入手机号" maxlength="16"/><br/><!--输入框类型为文本，value为默认显示的值-->

    <input class="biaodan2" type="password" name="password" placeholder="请输入密码" maxlength="16"/><br/><!--输入框类型为密码，最大长度为6-->

    <input class="biaodan3" type="text" name="yanzhengma" placeholder="请输入验证码" maxlength="4"/><i id="photo"></i><br/>
    <input class="biaodan4" type="submit" value="登录"/><!--确认类型-->
    <div style="display:block">${error}</div>
</form>
<script type="text/javascript">
    var s='${s}';
    console.log(s);
    $("#photo").html("<img src=\"./images/"+s+"\">");

    $("#photo").click(function () {
        $.ajax({
            type:"POST",
            url:"/change_yanzhengma",
            data:{
            },
            dataType:'text',
            success:function (res) {
                $("#photo").html("<img src=\"./images/"+res+"\">");
            },
            error:function () {
                console.log("error")
            }
        })
    })
</script>

<a href="/forget?I_am=driver" class="forget">忘记密码</a>
</body>
</html>
