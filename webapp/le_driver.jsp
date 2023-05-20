<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>乐代驾（司机端）</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <script type="text/javascript">
        window._AMapSecurityConfig = {
            securityJsCode:'de47f6b658be11e10177376b566b2982',
        }
    </script>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=2.0&key=130a58a2f04ecf20c278e9d0780a72a3&plugin=AMap.Geocoder"></script>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/jquery.cookie-1.4.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/three.css"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <script type="text/javascript">
        var lng=0;
        var lat=0;
        var city="";
        var page=0;

        AMap.plugin(["AMap.Geolocation"], function() {
            var geolocation = new AMap.Geolocation();
            geolocation.getCurrentPosition(function(status,result){
                if(status=='complete'){
                    onComplete(result)
                }else{
                    onError(result)
                }
            });

            function onComplete(data) {
                lng=data.position.lng;
                lat=data.position.lat;
                $.cookie("lng0",lng);
                $.cookie("lat0",lat);
                var geocoder = new AMap.Geocoder({
                    // city 指定进行编码查询的城市，支持传入城市名、adcode 和 citycode
                    city: '全国'
                });
                geocoder.getAddress(data.position, function(status, result) {
                    if (status === 'complete' && result.info === 'OK') {
                       city=result.regeocode.addressComponent.city;
                        update();
                    }
                });
            }
            //解析定位错误信息
            function onError(data) {
                console.log("定位失败");
            }
        });


        function update()
        {
            $.ajax({
                type:"POST",
                url:"/le_driver2",
                data:{
                    city:city,
                    lng:lng,
                    lat:lat,
                    page:page
                },
                dataType:'json',
                success:function (res) {
                    var str="";
                    for (var i=0;i<res.length;i++)
                    {
                        str+="<tr><td>"+res[i].qidian+
                            "</td><td>"+res[i].zhongdian+
                            "</td><td>"+res[i].distance+
                            "</td><td>"+res[i].use_time+
                            "</td><td>"+res[i].price+
                            "</td><td><a href=\'/jiedan?ID="+res[i].id+"\'>接单</a></td></tr>";
                    }
                    $("#b").html(str);
                },
                error:function () {
                }
            });
        }
        $(function () {
            $("#a").click(function () {
                page=0;
                update();
            });
            $("#up").click(function () {
                if(page>0)
                {
                    page--;
                }
                update();
            });
            $("#down").click(function () {
                page++;
                update();
            });
        })
    </script>
</head>
<body>

<div id="me">
    <table border="1" bgcolor="white"><!--table为表格标签，border为边框，cellspacing为格子之间间距-->
        <tr>
            <td><a href="/driver_message">我的</a></td>
            <td><a href="/driver_message" id="meme">${driver_name}</a></td>
        </tr>
        <tr>
            <td colspan="2"><button id="a">刷新</button></td>
        </tr>
    </table>
</div>

<table border="1">
    <tr>
        <th>起点</th><th>终点</th><th>里程</th><th>预计用时</th><th>预计价格</th><th></th>
        <tbody id="b"></tbody>
    </tr>
    <div style="display:block">${error}</div>
</table>
<button id="up">上一页</button>
<button id="down">下一页</button>
</body>
</html>