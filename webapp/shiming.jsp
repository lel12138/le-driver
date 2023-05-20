<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>实名验证</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            var a=$("#1");
            var b=$("#2");
            $("form").submit(
                function () {
                    var reg=/^[0-9]*[1-9][0-9]*$/;
                    if(b.val().length!=18||reg.test(b.val())==false)
                    {
                        alert("身份证号不正确");
                        return false;
                    }
                }
            )
        });
    </script>
    <link rel="stylesheet" type="text/css" href="./css/denglu.css"/>
</head>
<body>
<form  id="biaoge" action="/shiming2" method="post">
    <!--action为表单提交的服务器地址，method为请求方式，get方式把参数全部挂在网址后面，参数有限制且不安全-->

    <div class="biaodan1">实名认证</div><!--h1到h6为标题-->

    <input id="1"class="biaodan2" type="text" name="true_name" placeholder="请输入您的真实姓名" maxlength="16"/><br/><!--输入框类型为文本，value为默认显示的值-->

    <input id="2" class="biaodan2" type="text" name="shenfenzhenghao" placeholder="请输入您的身份证号" maxlength="18"/>

    <input class="biaodan4" type="submit" value="认证"/><!--确认类型-->
    <div style="display:block">${error}</div>
</form>
乐代驾会使用各种安全技术和程序来保护您提交的实名认证信息不被未经授权的访问、使用和泄漏。除法律或政府要求或用户同意等原因外，乐代驾不向任何第三方公开、 透露用户信息资源。

</body>


</html>
