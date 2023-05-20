<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>准备下单</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <script type="text/javascript">
        window._AMapSecurityConfig = {
            securityJsCode:'de47f6b658be11e10177376b566b2982',
        }
    </script>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=2.0&key=130a58a2f04ecf20c278e9d0780a72a3&plugin=AMap.Driving"></script>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/jquery.cookie-1.4.1.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet" type="text/css" href="./css/xiadan.css"/>
    <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
    <script src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
    <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body>
<div id="container"></div>
<div id="me">
    <table border="1" bgcolor="white"><!--table为表格标签，border为边框，cellspacing为格子之间间距-->
        <tr>
            <td><a href="/my_message">我的</a></td>
            <td><a href="/my_message" id="meme">${name}</a></td>
        </tr>
        <tr>
            <td><input id="zuikuai" class="xuanfangan" type="button" value="最快路线"></td>
            <td><input id="zuiduan" class="xuanfangan" type="button" value="最短路线"></td>
        </tr>

        <tr>
            <td><input id="shengqian" class="xuanfangan" type="button" value="省钱路线"></td>
            <td><input id="shikuang" class="xuanfangan" type="button" value="实况路线"></td>
        </tr>

    </table>
</div>
<div id="panel"></div>
<div id="road">
    <table border="1">
        <tr>
            <td>
                <input id="qi_zhong" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td>
                <input id="road_message" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td>
                <input id="xiadan" type="button" value="下单">
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    //基本地图加载
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
            'fillOpacity': 0.25
        }
    };
    AMap.plugin(["AMap.Geolocation"], function() {
        var geolocation = new AMap.Geolocation(options);
        map.addControl(geolocation);
        geolocation.getCurrentPosition();
    });

    //构造路线导航类
    var driving = new AMap.Driving({
        map: map,
        //panel: "panel",
        //policy:AMap.DrivingPolicy.REAL_TRAFFIC//LEAST_DISTANCE最短  LEAST_TIME最快  LEAST_FEE最省  REAL_TRAFFIC考虑实事
    });
    // 根据起终点经纬度规划驾车导航路线
    var qidian=new AMap.LngLat($.cookie('lng'),$.cookie('lat'));
    var zhongdian=new AMap.LngLat($.cookie('lng2'),$.cookie('lat2'));

    var fangan="最快";
    var city="中国";
    var distance="";
    var time="";
    var price="";

    autosearch("最快");
    $("#qi_zhong").val($.cookie('qidian')+" -> "+$.cookie('zhongdian'));

    function autosearch(i){
        driving.search(qidian, zhongdian, function(status, result) {
            // result 即是对应的驾车导航信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_DrivingResult
            if (status === 'complete') {
                log.success('绘制'+i+'路线完成');
                var distance1=(result.routes[0].distance/1000).toFixed(2);
                var time1=Math.floor(result.routes[0].time/60);
                var price1=30;
                if(distance1>8)
                {
                    price1=30+Math.ceil((distance1-8)*4);
                }
                distance=distance1+"公里";
                price=price1+"元";
                if(time1>60)
                {
                    var hour=Math.floor(time1/60);
                    var minute=Math.floor(time1-hour*60);
                    time=hour+"小时"+minute+"分钟";
                }
                else {
                    time=time1+"分钟";
                }
                $("#road_message").val("距离:"+distance+" 用时:"+time+" 预估"+price);
            } else {
                log.error('获取驾车数据失败：' + result)
            }
        })
    }



</script>
<script type="text/javascript">
    $(function () {
        var zuikuai=$("#zuikuai"); //#号不能丢，按id获取对象
        zuikuai.click(function () {//直接就有一个click函数
            driving.setPolicy(AMap.DrivingPolicy.LEAST_TIME);
            autosearch("最快");
            fangan="最快";
        });

        var zuiduan=$("#zuiduan"); //#号不能丢，按id获取对象
        zuiduan.click(function () {//直接就有一个click函数
            driving.setPolicy(AMap.DrivingPolicy.LEAST_DISTANCE);
            autosearch("最短");
            fangan="最短";
        });

        var shengqian=$("#shengqian"); //#号不能丢，按id获取对象
        shengqian.click(function () {//直接就有一个click函数
            driving.setPolicy(AMap.DrivingPolicy.LEAST_FEE);
            autosearch("省钱");
            fangan="省钱";
        });

        var shikuang=$("#shikuang"); //#号不能丢，按id获取对象
        shikuang.click(function () {//直接就有一个click函数
            driving.setPolicy(AMap.DrivingPolicy.REAL_TRAFFIC);
            autosearch("实况");
            fangan="实况";
        });

        $("#xiadan").click(function () {
            console.log("OK");
            $.ajax({
                type:"POST",
                url:"/xiadan",
                data:{
                    fangan:fangan,
                    distance:distance,
                    time:time,
                    price:price
                    },
                dataType:'text',
                success:function (res) {
                    window.location.assign("/xiadan2?ID="+res)
                },
                error:function () {

                    console.log("error")
                }
            })
        })
    });
</script>

</body>
</html>