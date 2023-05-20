<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>搜索终点</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/le.ico" />
    <script type="text/javascript">
        window._AMapSecurityConfig = {
            securityJsCode:'de47f6b658be11e10177376b566b2982',
        }
    </script>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=2.0&key=130a58a2f04ecf20c278e9d0780a72a3&plugin=AMap.DistrictSearch,AMap.AutoComplete"></script>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/jquery.cookie-1.4.1.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet" type="text/css" href="./css/qidian.css"/>
</head>
<body>
<div id="container"></div>
<div id="me">
    <table border="1" bgcolor="white"><!--table为表格标签，border为边框，cellspacing为格子之间间距-->
        <tr>
            <td><a href="/my_message">我的</a></td>
            <td><a href="/my_message" id="meme">${name}</a></td>
        </tr>
    </table>
</div>
<div id="myPageTop">
    <table border="1">
        <tr>
            <td colspan="2">
                <div id="shuoming">切换地级市可帮助搜索</div>
            </td>
        </tr>
        <tr>
            <td>
                <select id='province' onchange='search(this)'>
            </select>
            </td>
            <td>
                <select id='city' onchange='search(this)'>
            </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input id="tipinput" placeholder="搜索终点："/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="/zhunbeixiadan">准备下单</a>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    var opts = {
        subdistrict: 1,   //返回下一级行政区
        showbiz:false
    };
    var city=$.cookie("city");
    district = new AMap.DistrictSearch(opts);//注意：需要使用插件同步下发功能才能这样直接使用
    district.search('中国', function(status, result) {
        if(status=='complete'){
            getData(result.districtList[0]);
        }

    });
    function getData(data) {
        var curlevel = data.districtList[0].level;
        if (curlevel == 'province')
        {
            $("#city").innerHTML = '';
            var contentSub = new Option("请选择省份");
        }
        else
        {
            $("#city").innerHTML = '';
            var contentSub = new Option("请选择地级市");
        }
        var curList =  document.querySelector('#' + curlevel);
        curList.innerHTML='';
        curList.add(contentSub);
        for (var i = 0, l = data.districtList.length; i < l; i++) {
            var name = data.districtList[i].name;

            contentSub = new Option(name);

            contentSub.adcode = data.districtList[i].adcode;
            curList.add(contentSub);
        }
    }

    function search(obj) {
        var option = obj[obj.options.selectedIndex];
        var adcode = option.adcode;
        district.search(adcode, function(status, result) {
            if(status === 'complete'){
                if(result.districtList[0].level!=="city")
                {
                    getData(result.districtList[0]);
                }
                city=result.districtList[0].name;
                auto.setCity(city);
            }
        });
    }

    //地图加载
    var map = new AMap.Map("container", {
        resizeEnable: true,
        zoom:18
    });

    var options = {
        'showButton': true,//是否显示定位按钮
        'position': 'RB',//定位按钮的位置
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
    }
    AMap.plugin(["AMap.Geolocation"], function() {
        var geolocation = new AMap.Geolocation(options);
        map.addControl(geolocation);
        geolocation.getCurrentPosition()
    });

    var autoOptions = {
        input: "tipinput",
        city:city,
        citylimit:true
    };
    var auto = new AMap.AutoComplete(autoOptions);

    AMap.plugin(['AMap.PlaceSearch','AMap.AutoComplete'], function(){
        var placeSearch = new AMap.PlaceSearch({
            map: map,
            pageSize:10
        });  //构造地点查询类
        auto.on("select", select);//注册监听，当选中某条记录时会触发
        function select(e) {
            placeSearch.setCity(e.poi.adcode);
            //placeSearch.search(e.poi.name);  //关键字查询查询
            placeSearch.search(e.poi.name,function (status,result) {
                if(status=='complete'){
                    onComplete(result)
                }else{
                    onError(result);
                }
            });
            function onComplete(data) {
                //console.log(data.poiList.pois[0].location.lng+" "+data.poiList.pois[0].location.lat);
                $.cookie('zhongdian',data.poiList.pois[0].name);
                $.cookie('lng2',data.poiList.pois[0].location.lng);
                $.cookie('lat2',data.poiList.pois[0].location.lat);
            }
            function onError(data) {
                console.log("NO");
            }
        }

        placeSearch.on("markerClick",markerClick);
        function markerClick(e){
            //console.log(e.data.location.lng+" "+e.data.location.lat);
            $("#tipinput").val("前往"+e.data.name);
            $.cookie('zhongdian',e.data.name);
            $.cookie('lng2',e.data.location.lng);
            $.cookie('lat2',e.data.location.lat);
        }
    });

    var marker = new AMap.Marker({
        title:"终点",
        content:'<img src="./images/zhong.png" style="width:25px;height:25px"/>',
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
                    $("#tipinput").val("前往"+address1);
                    $.cookie('zhongdian',address1);
                    $.cookie('lng2',e.lnglat.getLng());
                    $.cookie('lat2',e.lnglat.getLat());
                }
            });
        });
    });

</script>
</body>
</html>