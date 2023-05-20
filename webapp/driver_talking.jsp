<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>联系用户</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/denglu.css"/>
    <script type="text/javascript">
        var ID='${ID}';
        dingshiqi=setInterval(function () {a();},5000);
        a();
        function a() {
            $.ajax({
                type:"POST",
                url:"/select_talking2",
                data:{
                    ID:ID,
                    typee:"司机"
                },
                dataType:'json',
                success:function (res) {
                    var str="";
                    for (var i=0;i<res.length;i++)
                    {
                        if(res[i].typee=="司机发言")
                        {
                            str+="<i id=\"little\">"+res[i].timee+"<br>"+"</i>"+
                                "<i id=\"shiji\">"+"我："+res[i].valuee+"<br>"+"</i>";
                        }
                        else {
                            str+="<i id=\"little\">"+res[i].timee+"<br>"+"</i>"+
                                "<i id=\"wo\">"+"用户："+res[i].valuee+"<br>"+"</i>";
                        }

                    }
                    $("#b").html(str);
                },
                error:function () {

                    console.log("error")
                }
            })
        }
        $(function () {
            $("#say").click(function () {
                var valuee=$("#valuee").val();
                $.ajax({
                    type:"POST",
                    url:"/say",
                    data:{
                        ID:ID,
                        typee:"司机发言",
                        valuee:valuee
                    },
                    dataType:'text',
                    success:function (res) {
                        console.log("success");
                        $("#valuee").val("");
                    },
                    error:function () {

                        console.log("error")
                    }
                })
            })
        })
    </script>
</head>
<body>
<h3>联系用户中</h3>
<i id="b"></i>
<input type="text" name="valuee" id="valuee" placeholder="文明交谈，和谐社会"/>
    <input type="button" value="发送" id="say"/>

</body>
</html>