<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>下单</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/denglu.css"/>
    <script type="text/javascript">
        var i=0;
        var ID='${ID}';
        $(function () {
            dingshiqi=setInterval(function () {a();},1000);
            $("#gogogo").click(function () {
                clearInterval(dingshiqi);
                window.location.assign("/go?ID="+ID);
            })
        });
        function a() {
            i++;
            $("#time").val(i);
            if(i%5==0)
            {
                $.ajax({
                    type:"POST",
                    url:"/shifoujiedan",
                    data:{
                        ID:ID
                    },
                    dataType:'json',
                    success:function (res) {
                        if(res[0]=="YES")
                        {
                            $("#i").show();
                            $("#driver_telephone").val(res[1]);
                        }
                    },
                    error:function () {
                    }
                });
            }

        }
    </script>
</head>
<body>
<i id="j">正在等待司机接单，已经等待<input type="text" id="time" >秒<br></i>
<i id="i">已有司机<input type="text" id="driver_telephone" style="width: 90px">接单，<input type="button" id="gogogo" value="现在出发" ></i>

</body>
</html>