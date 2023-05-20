<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>管理员登录</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/admin.css"/>
</head>
<body class="all">
<div class="top">
    <div class="pingpai">
        <a>乐代驾管理</a>
    </div>
</div>
<form  id="biaoge" action="/admin_denglu" method="post">
    <!--action为表单提交的服务器地址，method为请求方式，get方式把参数全部挂在网址后面，参数有限制且不安全-->

    <div class="biaodan1">管理员登录</div><!--h1到h6为标题-->

    <input class="biaodan2" type="text" name="telephone" placeholder="请输入管理员账号" maxlength="16"/><br/><!--输入框类型为文本，value为默认显示的值-->

    <input class="biaodan2" type="password" name="password" placeholder="请输入密码" maxlength="16"/><br/><!--输入框类型为密码，最大长度为6-->

    <input class="biaodan4" type="submit" value="登录"/><!--确认类型-->
    <div style="display:block">${error}</div>
</form>
</body>
</html>
