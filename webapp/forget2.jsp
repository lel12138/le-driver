<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>设置新密码</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <script type="text/javascript" src="./js/jquery.js"></script>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet" type="text/css" href="./css/denglu.css"/>
</head>
<body>

<form  id="biaoge" action="/forget3" method="post" >
    <!--action为表单提交的服务器地址，method为请求方式，get方式把参数全部挂在网址后面，参数有限制且不安全-->

    <div class="biaodan1">设置新密码</div><!--h1到h6为标题-->

    <input class="biaodan2" type="password" name="password" placeholder="请输入您的新密码" maxlength="16"/><br/>

    <input class="biaodan2" type="password" name="password2" placeholder="请再次确认您的新密码" maxlength="16"/><br/>

    <input class="biaodan4" type="submit" value="修改"/><!--确认类型-->
    <div style="display:block">${error}</div>
</form>
</body>


</html>
