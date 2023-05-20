<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>乐代驾</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <script type="text/javascript">
        window._AMapSecurityConfig = {
            securityJsCode:'de47f6b658be11e10177376b566b2982',
        }
    </script>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=2.0&key=130a58a2f04ecf20c278e9d0780a72a3"></script>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/jquery.cookie-1.4.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/kaishi.css"/>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
    <title>地图显示</title>
    <style>
        html,
        body,
        #container {
            width: 100%;
            height: 100%;
        }
    </style>
</head>


<body>
<div id="container"></div>
<script>
    var map = new AMap.Map('container', {
        resizeEnable: true,
        zoom:18
    });

    var options = {
        'showButton': true,//是否显示定位按钮
        'buttonPosition': 'RB',//定位按钮的位置

        /* LT LB RT RB */
        'buttonOffset': new AMap.Pixel(10, 20),//定位按钮距离对应角落的距离
        'showMarker': true,//是否显示定位点
        'markerOptions':{//自定义定位点样式，同Marker的Options
            'offset': new AMap.Pixel(-18, -36),
            'content':'<img src="https://a.amap.com/jsapi_demos/static/resource/img/user.png" style="width:36px;height:36px"/>'
        },

        'showCircle': true,//是否显示定位精度圈
        'circleOptions': {//定位精度圈的样式
            'strokeColor': '#0093FF',
            'noSelect': true,
            'strokeOpacity': 0.5,
            'strokeWeight': 1,
            'fillColor': '#02B0FF',
            'fillOpacity': 0.25
        },
        'enableHighAccuracy':true,
        'maximumAge':5000
    };

    AMap.plugin(["AMap.Geolocation"], function() {

        var geolocation = new AMap.Geolocation(options);
        map.addControl(geolocation);
        geolocation.getCurrentPosition(function(status,result){
            if(status=='complete'){
                onComplete(result)
            }else{
                onError(result)
            }
        });

        function onComplete(data) {
            var geocoder = new AMap.Geocoder({
                // city 指定进行编码查询的城市，支持传入城市名、adcode 和 citycode
                city: '全国'
            });
            geocoder.getAddress(data.position, function(status, result) {
                if (status === 'complete' && result.info === 'OK') {
                    var address=result.regeocode.formattedAddress;//这个是全地址
                    var province=result.regeocode.addressComponent.province;
                    var city=result.regeocode.addressComponent.city;
                    var district=result.regeocode.addressComponent.district;
                    var township=result.regeocode.addressComponent.township;
                    var address1=address.replace(province,'').replace(city,'').replace(district,'').replace(township,'');
                    $("#address").val("从"+address1+"出发");
                    $.cookie('province',province);
                    $.cookie('qidian',address1);
                    $.cookie('city',city);
                    $.cookie('lng',data.position.getLng());
                    $.cookie('lat',data.position.getLat());
                }
            })
        }
        //解析定位错误信息
        function onError(data) {
            $("#address").val("定位失败");
        }

    });

    var marker = new AMap.Marker({
        title:"起点",
        content:'<img src="./images/qi.png" style="width:25px;height:25px"/>',
        anchor: 'bottom-center',
    });

    AMap.plugin('AMap.Geocoder', function() {
        var geocoder = new AMap.Geocoder({
            // city 指定进行编码查询的城市，支持传入城市名、adcode 和 citycode
            city: '全国'
        });

        map.on('click', function(e) {
            map.add(marker);
            marker.setPosition(e.lnglat);

            geocoder.getAddress(e.lnglat, function(status, result) {
                if (status === 'complete' && result.info === 'OK') {

                    var address=result.regeocode.formattedAddress;//这个是全地址
                    var province=result.regeocode.addressComponent.province;
                    var city=result.regeocode.addressComponent.city;
                    var district=result.regeocode.addressComponent.district;
                    var township=result.regeocode.addressComponent.township;
                    var address1=address.replace(province,'').replace(city,'').replace(district,'').replace(township,'');
                    $("#address").val("从"+address1+"出发");
                    $.cookie('province',province);
                    $.cookie('qidian',address1);
                    $.cookie('city',city);
                    $.cookie('lng',e.lnglat.getLng());
                    $.cookie('lat',e.lnglat.getLat());
                }
            });
        });
    });

</script>

<div id="me">
    <table border="1" bgcolor="white"><!--table为表格标签，border为边框，cellspacing为格子之间间距-->
        <tr>
            <td><a href="/my_message">我的</a></td>
            <td><a href="/my_message" id="meme">${name}</a></td>
        </tr>
    </table>
</div>

<div id="go">
    <table border="1" ><!--table为表格标签，border为边框，cellspacing为格子之间间距-->
        <tr>
            <td colspan="2">
                <div id="shuoming">点击地图即可更改起点，或点击放大镜搜索起点</div>
            </td>
        </tr>
        <tr>
            <td ><input type="text"  readonly="true" id="address"></td>
            <td ><a href="/qidian" id="find"><img src="./images/find.png"></a></td>
        </tr>
        <tr>
            <td colspan="2"><a href="/zhongdian" >到哪儿去</a></td>
        </tr>
        <div style="display:block">${error}</div>
    </table>
</div>

</body>
</html>
