<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>司机申请</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $("form").submit(
                function() {
                    if($("#1").val().length==0||$("#2").val().length==0||$("#3")[0].files.length==0||$("#4")[0].files.length==0||$("#5")[0].files.length==0)
                    {
                        alert("信息不完整");
                        return false;
                    }
                }
            )
        });
    </script>
    <link rel="stylesheet" type="text/css" href="./css/denglu.css"/>
</head>
<body>
<form  id="biaoge" action="/be_a_driver2" method="post" enctype="multipart/form-data">
    <!--action为表单提交的服务器地址，method为请求方式，get方式把参数全部挂在网址后面，参数有限制且不安全-->

    <div class="biaodan1">司机申请</div><!--h1到h6为标题-->

    真实姓名：<input id="1" class="biaodan6" type="text" name="name" value="" maxlength="16"/><br/><!--输入框类型为文本，value为默认显示的值-->

    性别：<input type="radio" name="sex" value="man" checked="checked"/>男<input type="radio" name="sex" value="women"/>女<br/>

    联系电话：<input id="2" class="biaodan6"type="text" name="telephone" maxlength="16"/><br/>

    <div class="biaodan6">请上传您的身份证正面图:</div><input id="3" class="biaodan6" type="file" name="shenfenzheng1"/><br/>

    <div class="biaodan6">请上传您的身份证反面图:</div><input id="4" class="biaodan6" type="file" name="shenfenzheng2"/><br/>

    <div class="biaodan6">请上传您的驾驶证内页:</div><input id="5" class="biaodan6" type="file" name="jiashizheng"/><br/>

    <input class="biaodan4" type="submit" value="申请"/><!--确认类型-->
    <div style="display:block">${error}</div>
</form>
在提交申请后，我们会在线下联系您，在经过测试考核，签约加盟，岗前培训后，您将成为正式的乐代驾司机
</body>


</html>
