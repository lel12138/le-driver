<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <script type="text/javascript" src="./js/jquery.js"></script>

    <script type="text/javascript">
    $(function () {
        var a=$("#1");
        var b=$("#2");
        var c=$("#3");
        c.click(function () {
            var telephone1=a.val();
            var reg=/^[0-9]*[1-9][0-9]*$/;
            if(telephone1.length!=11||reg.test(telephone1)==false)
            {
                alert("手机号不正确");
                return false;
            }
            else
            {
                $.ajax({
                    type:"POST",
                    url:"/is_user",
                    data:{
                        telephone:telephone1,
                        gan:"change_password"
                    },
                    dataType:'text',
                    success:function (res) {
                        if(res=="NO")
                        {
                            b.val(parseInt(Math.random()*8000+1000));
                        }
                        else
                        {
                            alert("此手机号已经注册");
                        }
                    },
                    error:function () {
                        console.log("error")
                    }
                })
            }
        });
        $("form").submit(
            function () {
                var reg=/^[0-9]*[1-9][0-9]*$/;
                if(a.val().length!=11||reg.test(a.val())==false)
                {
                    alert("手机号不正确");
                    return false;
                }
                if(b.val().length!=4)
                {
                    alert("验证码不正确");
                    return false;
                }
            }
        )
    });


    </script>
    <link rel="stylesheet" type="text/css" href="./css/denglu.css"/>
</head>
<body>
<form  id="biaoge" action="/zhuce2" method="post">
    <!--action为表单提交的服务器地址，method为请求方式，get方式把参数全部挂在网址后面，参数有限制且不安全-->

    <div class="biaodan1">新用户注册</div><!--h1到h6为标题-->

    <input id="1"class="biaodan2" type="text" name="telephone" placeholder="请输入手机号" maxlength="16"/><br/><!--输入框类型为文本，value为默认显示的值-->

    <input id="2" class="biaodan3" type="text" name="yanzhengma" placeholder="请输入验证码" maxlength="4"/>

    <input id="3" class="biaodan5" type="button" value="获取验证码"><br/>

    <input class="biaodan4" type="submit" value="下一步"/><!--确认类型-->
    <div style="display:block">${error}</div>
</form>
</body>


</html>
