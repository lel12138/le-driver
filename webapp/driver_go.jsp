<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>司机出发</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <script type="text/javascript">
        window._AMapSecurityConfig = {
            securityJsCode:'de47f6b658be11e10177376b566b2982',
        }
    </script>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=2.0&key=130a58a2f04ecf20c278e9d0780a72a3"></script>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/jquery.cookie-1.4.1.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet" type="text/css" href="./css/go.css"/>
    <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
    <script src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
    <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>

    <script type="text/javascript">
        var ID='${ID}';
        var Qidian_lng='${Qidian_lng}';
        var Qidian_lat='${Qidian_lat}';
        var Zhongdian_lng='${Zhongdian_lng}';
        var Zhongdian_lat='${Zhongdian_lat}';
        var Fangan='${Fangan}';
        var lng=$.cookie("lng0");
        var lat=$.cookie("lat0");
        dingshiqi=setInterval(function () {a();},5000);
    </script>
</head>
<body>
<div id="container"></div>
<div id="me">
    <table border="1" bgcolor="white"><!--table为表格标签，border为边框，cellspacing为格子之间间距-->
        <tr>
            <td><a href="/driver_message">我的</a></td>
            <td><a href="/driver_message" id="meme">${driver_name}</a></td>
        </tr>
    </table>
</div>
<div id="go">
    <table border="1" bgcolor="white"><!--table为表格标签，border为边框，cellspacing为格子之间间距-->
        <tr>
            <td ><div id="red">*</div><a href="/select_talking_driver?ID=${ID}">联系用户</a> </td>
        </tr>
        <div style="display:block">${error}</div>
    </table>
</div>
<div id="panel"></div>
<script>
    var map = new AMap.Map("container", {
        resizeEnable: true,
        zoom: 18 //地图显示的缩放级别
    });
    var options = {
        'showButton': true,//是否显示定位按钮
        'position': 'LB',//定位按钮的位置
        /* LT LB RT RB */
        'offset': [10, 20],//定位按钮距离对应角落的距离
        'showMarker': true,//是否显示定位点
        'markerOptions':{//自定义定位点样式，同Marker的Options
            'offset': new AMap.Pixel(-18, -36),
            'content':'<img src="https://a.amap.com/jsapi_demos/static/resource/img/user.png" style="width:36px;height:36px" alt=""/>'
        },
        'showCircle': true,//是否显示定位精度圈
        'circleOptions': {//定位精度圈的样式
            'strokeColor': '#0093FF',
            'noSelect': true,
            'strokeOpacity': 0.5,
            'strokeWeight': 1,
            'fillColor': '#02B0FF',
            'fillOpacity': 0.25,
        }
    };
    var clear=0;
    AMap.plugin(["AMap.Geolocation"], function() {
        var geolocation = new AMap.Geolocation(options);
        map.addControl(geolocation);
        geolocation.getCurrentPosition();
    });
    b(lng,lat,"司机");
    var marker = new AMap.Marker({
        title:"司机模拟定位点",
        'content':'<img src="https://a.amap.com/jsapi_demos/static/resource/img/user.png" style="width:36px;height:36px" alt=""/>',
        anchor: 'bottom-center',
    });

    var marker2 = new AMap.Marker({
        title:"用户",
        content:'<img src="./images/user.png" style="width:30px;height:30px"/>',
        anchor: 'bottom-center',
    });

    map.on('click', function(e) {
        if(clear==0)
        {
            map.clearMap();
            clear=1;
        }
        map.setZoom(25);
        map.add(marker);
        marker.setPosition(e.lnglat);
        map.setCenter(e.lnglat);
        var lng=e.lnglat.getLng();
        var lat=e.lnglat.getLat();
        var type="司机";
        b(lng,lat,type);
    });

    function b(lng,lat,type) {
        $.ajax({
            type: "POST",
            url: "/add_lnglat",
            data: {
                ID: ID,
                type: type,
                lng: lng,
                lat: lat
            },
            dataType: 'text',
            success: function () {
                console.log("success");
            },
            error: function () {

                console.log("error")
            }
        })
    }
    function a() {
        $.ajax({
            type:"POST",
            url:"/select_lnglat",
            data:{
                ID:ID,
                type:"用户"
            },
            dataType:'json',
            success:function (res) {
                if(res[0]=="NO")
                {
                    if(confirm("订单已结束，是否跳转？")==true)
                    {
                        window.location.assign("/driver_finish?ID=${ID}");
                    }
                }
                else {
                    map.add(marker2);
                    marker2.setPosition(res);
                }
                if(res[2]!="0")
                {
                    $("#red").show();
                }
            },
            error:function () {

                console.log("error")
            }
        })
    }

    var now=new AMap.LngLat(lng,lat);
    var qidian=new AMap.LngLat(Number(Qidian_lng),Number(Qidian_lat));
    var zhongdian=new AMap.LngLat(Number(Zhongdian_lng),Number(Zhongdian_lat));
    AMap.plugin('AMap.Driving', function() {
        var driving = new AMap.Driving({
            map: map,
            panel: "panel",
            //policy:AMap.DrivingPolicy.REAL_TRAFFIC//LEAST_DISTANCE最短  LEAST_TIME最快  LEAST_FEE最省  REAL_TRAFFIC考虑实事
        });
        if(Fangan=="最快")
        {
            driving.setPolicy(AMap.DrivingPolicy.LEAST_TIME);
        }
        else if(Fangan=="最短")
        {
            driving.setPolicy(AMap.DrivingPolicy.LEAST_DISTANCE);
        }
        else if(Fangan=="省钱")
        {
            driving.setPolicy(AMap.DrivingPolicy.LEAST_FEE);
        }
        else
        {
            driving.setPolicy(AMap.DrivingPolicy.REAL_TRAFFIC);
        }
        driving.search(now, zhongdian, { waypoints:[qidian]},function (status, result) {
            if (status === 'complete') {
                console.log('绘制路线完成');
            } else {
                console.log('获取驾车数据失败')
            }
        });
    });
</script>


</body>
</html>
