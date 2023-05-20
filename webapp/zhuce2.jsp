<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册填写</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <script type="text/javascript" src="./js/jquery.js"></script>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet" type="text/css" href="./css/denglu.css"/>
</head>
<body>

<form  id="biaoge" action="/zhuce3" method="post" >
    <!--action为表单提交的服务器地址，method为请求方式，get方式把参数全部挂在网址后面，参数有限制且不安全-->

    <div class="biaodan1">新用户注册</div><!--h1到h6为标题-->

    <input class="biaodan2" type="text" name="name" placeholder="请输入您的用户名" maxlength="16"/><br/><!--输入框类型为文本，value为默认显示的值-->

    <input class="biaodan2" type="password" name="password" placeholder="请输入您的密码" maxlength="16"/><br/>

    <input class="biaodan4" type="submit" value="注册"/><!--确认类型-->
    <div style="display:block">${error}</div>
</form>
</body>


</html>
