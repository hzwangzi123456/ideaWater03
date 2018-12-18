/**
 * Created by ziwang on 17/10/27.
 */
//设备的信息
var points = null;
//用户信息
var user = null;
//地区信息
var address = null;

/**
 * 初始化infowindow
 * @param index points的下标
 */
function initInfoWindow(index) {
    if (points == null || points.length == 0) {
        return;
    }
    $(".infowindow-instrument").text(points[index].instrumentId);
    $(".province-value").text(points[index].province);
    $(".city-value").text(points[index].city);
    $(".country-value").text(points[index].county);
    $(".address-value").text(points[index].extra);
}
/**
 * 初始化百度地图
 * @param x 经度
 * @param y 维度
 * @param rank 地图级别
 */
function initializeByparam(x, y, rank) {
    // 编写自定义函数，创建标注
    function addMarker(point) {
        //var myIcon = new BMap.Icon("http://api.map.baidu.com/images/marker_red_sprite.png", new BMap.Size(50, 50));
        // 创建标注
        var marker = new BMap.Marker(point);
        var infoWindow = new BMap.InfoWindow($("#infowindow").html()); // 创建信息窗口对象
        //var infoWindow = new BMap.InfoWindow("djsalfjlkdas", opts);
        marker.addEventListener("mouseover", function () {
            marker.openInfoWindow(infoWindow);
        });
        marker.addEventListener("mouseout", function () {
            // marker.closeInfoWindow();
        });
        marker.addEventListener("click", function () {
            // console.log("bg show");
            // window.location.href = "data.html";
//						var ui = document.getElementById("bg");
//						ui.style.display = "block";
//						var chart = document.getElementById("chart");
//						chart.style.display = "block";
        });
        map.addOverlay(marker); // 将标注添加到地图中
    }

    // 百度地图API功能
    /* 创建地图并添加控件begin */
    var map = new BMap.Map("container");
    map.centerAndZoom(new BMap.Point(119.736214, 30.261479), 18);
    map.addControl(new BMap.NavigationControl());
    map.addControl(new BMap.ScaleControl());
    map.addControl(new BMap.OverviewMapControl());
    map.addControl(new BMap.MapTypeControl());
    map.setCurrentCity("杭州");

    var points = [[119.736695, 30.261491],
        [119.736771, 30.26162],
        [119.736911, 30.261811],
        [119.736911, 30.261811],
        [119.736911, 30.261811],
        [119.736911, 30.261811],
        [119.736479, 30.26061],
        [119.735931, 30.260181],
        [119.735931, 30.260181],//
        [119.73564, 30.259849],
        [119.734858, 30.26022],
        [119.734283, 30.260505],
        [119.734234, 30.260781],
        [119.734045, 30.261089],
        [119.734508, 30.261214],
        [119.734997, 30.26146],
        [119.735518, 30.261577],
        [119.736214, 30.261479],
        [119.736695, 30.261491]];
    showPolyLine(points, "green", map);
};

function showPolyLine(points, color, map) {
    /* points[][]数据结构为二维数组，这里转换为Marker，再将多个marker点放入pline，组成一条线  begin */
    var pLine = [];
    for (var i = 0; i < points.length; i++) {
        pLine.push(new BMap.Point(points[i][0], points[i][1]));
        if (i == 0 || i == points.length - 1) {//起点终点图标
            map.addOverlay(new BMap.Marker(new BMap.Point(points[i][0], points[i][1])));
        }
    }
    /* points[][]数据结构为二维数组，这里转换为Marker，再将多个marker点放入pline，组成一条线  end */


    /* 添加轨迹接口begin */
    var polyline = new BMap.Polyline(pLine, {
        strokeColor: color,
        strokeWeight: 6,
        strokeOpacity: 0.5
    });
    map.addOverlay(polyline);
    /* 添加轨迹接口begin */
};

// map.centerAndZoom(new BMap.Point(x, y), rank); // 初始化地图,设置中心点坐标和地图级别
// map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
// map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
// map.setMapType(BMAP_SATELLITE_MAP);
// addMarker(new BMap.Point(x, y));

/**
 * 点击地区名的点击事件
 */
function linkNameClick() {
    var $i = $(this).children("i");
    //如果i是右箭头
    if ($i.hasClass("fa-caret-right")) {
        $i.removeClass("fa-caret-right");
        $i.addClass("fa-caret-down");
        $(this).parents(".instruments-nav-link").siblings(".instruments-nav-link").children(".link-content").hide();
        $(this).parents(".instruments-nav-link").siblings(".instruments-nav-link").children(".link-name").css("background-color", "#eaedf1");
        $(".link-content li").css("background-color", "#eaedf1");
        $(this).css("background-color", "#F1F4F6");
        $(this).parents(".instruments-nav-link").siblings(".instruments-nav-link").children(".link-name").children("i").removeClass("fa-caret-down");
        $(this).parents(".instruments-nav-link").siblings(".instruments-nav-link").children(".link-name").children("i").addClass("fa-caret-right");
        //打开设备id列表
        $(this).siblings("ul").show();
    } else {
        $i.removeClass("fa-caret-down");
        $i.addClass("fa-caret-right");
        $(this).css("background-color", "#eaedf1");
        //关闭设备id列表
        $(this).siblings("ul").hide();
    }
}

/**
 * 加入地区前缀html和第一个设备id
 * @param html 需要加入的html
 * @param i 第一个设备的下标
 * @return 添加设备后的html
 */
function prefixNewAddress(html, i) {
    if (points == null || points.length <= i || i < 0) {
        return html;
    }
    html += '<div class="instruments-nav-link">';
    html += '<div class="link-name">';
    html += '<i class="fa fa-caret-right" aria-hidden="true"></i>';
    html += points[i].extra;
    html += '</div>';
    html += '<ul class="link-content" style="display: none;">';
    html += '<li>' + points[i].instrumentId + '</li>';
    return html;
}

/**
 * 添加地区后缀到html
 * @param html 需要添加的html
 * @return 添加完后缀的html
 */
function suffixNewAddress(html) {
    html += '</ul></div>';
    return html;
}

/**
 * 添加地区的第二个及以上设备
 * @param html 原html
 * @param i 设备下标
 * @returns 新html
 */
function addOtherInstruments(html, i) {
    if (points == null || points.length <= i || i < 0) {
        return html;
    }
    html += '<li>' + points[i].instrumentId + '</li>';
    return html;
}

$(function () {
    //向后台发送ajax请求
    $(".header").load("../controllerCommon/header.html");
    $(".nav").load("../controllerCommon/nav.html");

    doAjaxSyn(PROJECT_NAME + "/UserController/findSessionByLogin.do", {}, function (data) {
        var redata = strToJson(data);
        if (!judgeAjaxData(redata)) {
            window.location.href = PROJECT_NAME + INDEX_NAME;
            return;
        }
        user = redata;
    });

    doAjaxSyn(PROJECT_NAME + "/AuthorityAreasController/findAuthorityAreasByPojo.do", {username: user.username}, function (data) {
        var redata = strToJson(data);
        if (!judgeAjaxData(redata)) {
            alert("该用户没有数据");
            return;
        }
        address = redata;
    });

    if (address != null) {
        points = new Array();
        for (var i = 0; i < address.length; i++) {
            doAjaxSyn(PROJECT_NAME + "/AreasInstrumentsController/findAreasInstrumentsByPojo.do", {prefix: address[i].prefix}, function (data) {
                var redata = strToJson(data);
                if (!judgeAjaxData(redata)) {
                    return;
                }
                for (var j = 0; j < redata.length; j++) {
                    points.push(redata[j]);
                }
            });
        }
    }

    if (points == null || points.length == 0) {
        return;
    }

    $(".instruments-nav-link").remove();
    var html = "";
    html = prefixNewAddress(html, 0);
    for (var i = 1; i < points.length; i++) {
        if (points[i].extra == points[i - 1].extra) {
            //说明要添加第二及以上设备
            html = addOtherInstruments(html, i);
        } else {
            //要添加新地区设备
            html = suffixNewAddress(html);
            html = prefixNewAddress(html, i);
        }
    }
    html = suffixNewAddress(html);
    $(".instruments-nav").append(html);
    $(".link-name").click(linkNameClick);
    $(".link-content li").each(function (index) {
        var me = this;
        $(this).click(function () {
            $(".link-content li").css("background-color", "#eaedf1");
            if (points == null) {
                alert("未找到数据");
                return;
            }
            $(this).css("background-color", "#F1F4F6");
            if ("coordinate" in points[index] == false) {
                alert(points[index].instrumentId + "设备没有坐标");
                return;
            }
            initInfoWindow(index);
            if (points == null) {
                alert("未找到数据");
                return;
            }

            if ("coordinate" in points[index] == false) {
                alert(points[index].instrumentId + "设备没有坐标");
                return;
            }
            console.log(points[0].coordinate.split(",")[0].trim() + " " + points[0].coordinate.split(",")[1].trim());
            initializeByparam(points[index].coordinate.split(",")[0].trim(), points[index].coordinate.split(",")[1].trim(), 19);
        });
    });
    //点击第一个设备
    $($(".link-name")[0]).trigger("click");
    $($(".link-content li")[0]).trigger("click");
});