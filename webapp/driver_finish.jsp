<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>订单完成</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/denglu.css"/>
    <script type="text/javascript">

    </script>
</head>
<body>
<form  id="biaoge" action="/driver_pingjia?ID=${ID}" method="post">
    <!--action为表单提交的服务器地址，method为请求方式，get方式把参数全部挂在网址后面，参数有限制且不安全-->

    <div class="biaodan1">司机评价</div><!--h1到h6为标题-->

    您对本次服务的评价：
    <br>
    <input type="checkbox" name="pingjia" value="态度良好，服务到位" checked="checked"/>态度良好，服务到位<br>
    <input type="checkbox" name="pingjia" value="为乘客提供额外帮助" />为乘客提供额外帮助<br>
    <input type="checkbox" name="pingjia" value="天气或交通状况恶劣" />天气或交通状况恶劣<br>
    <input type="checkbox" name="pingjia" value="乘客态度恶劣，干扰行驶" />乘客态度恶劣，干扰行驶<br>


    <input class="biaodan4" type="submit" value="提交"/><!--确认类型-->
    <div style="display:block">${error}</div>
</form>
您的评价最终会被系统智能评估，并影响最终的服务价格<br>
不实的评价会影响您在未来订单议价过程的权重
</body>
</html>