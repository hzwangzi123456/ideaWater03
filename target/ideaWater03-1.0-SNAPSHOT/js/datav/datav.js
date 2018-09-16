/**
 * Created by ziwang on 18/7/31.
 */
var c = document.getElementById("myCanvas");
var ctx = c.getContext("2d");
ctx.setLineDash([6, 6]);
ctx.strokeStyle = '#2090ff';
ctx.lineTo(200, 180);
ctx.lineTo(250, 170);
ctx.lineTo(300, 170);
ctx.lineTo(350, 200);
ctx.lineTo(400, 200);
ctx.lineTo(430, 230);
ctx.lineTo(430, 280);
ctx.lineTo(480, 330);
ctx.lineTo(530, 360);
ctx.lineTo(500, 390);
ctx.lineTo(450, 430);
ctx.lineTo(400, 430);
ctx.lineTo(350, 400);
ctx.lineTo(300, 400);
ctx.lineTo(250, 430);
ctx.lineTo(150, 380);
ctx.lineTo(180, 330);
ctx.lineTo(190, 280);
ctx.lineTo(195, 230);
ctx.lineTo(200, 180);
ctx.stroke();

var theme = "dark";
var browser = {
    versions: function () {
        var u = navigator.userAgent,
            app = navigator.appVersion;
        return {
            mobile: !!u.match(/AppleWebKit.*Mobile.*/),
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
            android: u.indexOf("Android") > -1 || u.indexOf("Linux") > -1,
            iPhone: u.indexOf("iPhone") > -1,
            iPad: u.indexOf("iPad") > -1
        };
    }(),
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
};

var scjgChart;
var yysrfxChart;
var jgqsChart;
var summaryChart;
var monitoringChart;

$("#shipPicture").click(function () {
    var src = "url('../../img/datav/eastLake.png')";
    $('#dituChart').css("background-image", src);
    $('#dituChart').css("background-size", "");
    $('#dituChart').css("background-repeat", "no-repeat");
    $('#dituChart').css("background-position", "center");
    $("#myCanvas").show();
    $("#ship").show();
    $("#plane").show();
    $(".smallCircle").show();
});

/**
 * 点击飞机动图
 */
$("#planeCircle").click(function(){
    var src = "url('../../img/datav/eastLake03.jpg')";
    $('#dituChart').css("background-image", src);
    $('#dituChart').css("background-size", "100% 80%");
    $("#myCanvas").hide();
    $("#ship").hide();
    $("#plane").hide();
    $(".smallCircle").hide();
});

/**
 * 点击船的动图
 */
$("#ship").click(function () {
    var div = $(this).parent();
    var state = $("#shipCircle").css("animation-play-state");
    var ph = (6.5 + Math.random() * 1).toFixed(1);
    var waterTemperature = (23 + Math.random() * 3).toFixed(1);
    var conductivity = Math.floor(Math.random() * 8000);
    if (state == "running") {
        $("#layer02").modal("toggle");
        $("#dialog-content").text("ph:" + ph + " ,水温:" + waterTemperature + " ,导电性:" + conductivity);
        $(div).css("animation-play-state", "paused");
    } else if (state == "paused") {
        $(div).css("animation-play-state", "running");
    }
});

$(function () {
    $('#layer02').on('hide.bs.modal', function () {
        $("#shipCircle").css("animation-play-state", "running");
    })
});

/**
 * 无人机数据分析图
 */
$("#uavPicture").click(function () {
    var src = "url('../../img/datav/eastLake03.jpg')";
    $('#dituChart').css("background-image", src);
    $('#dituChart').css("background-size", "100% 80%");
    $("#myCanvas").hide();
    $("#ship").hide();
    $("#plane").hide();
    $(".smallCircle").hide();
//    var screenHeight = parseInt($(window).height());
//    var screenWidth = parseInt($(window).width());
//    $('.showImg').find('.picture').remove();
//    var html = '<img class="picture" src="' + src + '" alt="">';
//    $('.showImg').append(html);
//    $('.showImg').css('display', 'block');
//    var width = $('.showImg .picture').width();
//    var height = $('.showImg .picture').height();
//    $('.showImg').css('display', 'none');
////	//如果只有一个超，以超的边缩小，如果两个都超，以放大比高的边缩小。
//    if (width > screenWidth && height > screenHeight) {
//        var ratWidth = width / screenWidth;
//        var ratHeight = height / screenHeight;
//        if (ratWidth > ratHeight) {
//            width = screenWidth;
//            height = height / ratWidth;
//        } else {
//            height = screenHeight;
//            width = width / ratHeight;
//        }
//    } else if (width > screenWidth && height <= screenHeight) {
//        var rat = width / screenWidth;
//        width = screenWidth;
//        height = height / rat;
//    } else if (width <= screenWidth && height > screenHeight) {
//        var rat = height / screenHeight;
//        height = screenHeight;
//        width = width / rat;
//    }
//    var marginLeft = (screenWidth - width) / 2;
//    var marginTop = (screenHeight - height) * 0.2;
//    $('.showImg .picture').width(width);
//    $('.showImg .picture').height(height);
//    $('.showImg .picture').css('margin-left', marginLeft);
//    $('.showImg .picture').css('margin-top', marginTop);
//    $('.showImg').css('display', 'block');
});

$(function () {
    var summarySlideVis = 4;
//    if (browser.versions.mobile) {
//        $("#summary-slide").insertAfter($(".title-row"));
//        summarySlideVis = 3;
//    }
//    else { //自适应
//        var screenHeight = $(window).height();
//
//        if (screenHeight >= 600) {
//            var mainTitleHeight = $(".title-row").height();
//            var titleHeight = $("#cptj-box h3").height();
//            var cptjHeight = $("#cptj-box").height();
//            var summaryHeight = $("#summary-slide").height();
//
//            var leftChartHeight = (screenHeight - mainTitleHeight - titleHeight * 2 - 95 - cptjHeight) / 2;
//            $("#scjgChart").height(leftChartHeight);
//            $("#jgqsChart").height(leftChartHeight);
//
//            var rightChartHeight = (screenHeight - mainTitleHeight - titleHeight * 2 - 85) / 2;
//
//            $("#yysrdbChart").height(rightChartHeight);
//            $("#yysrfxChart").height(rightChartHeight);
//
//            var centerHeight = screenHeight - mainTitleHeight - summaryHeight - 15;
//            $("#dituChart").height(centerHeight);
//        }
//    }

    jQuery("#summary-slide").slide({
        mainCell: ".summary ul",
        autoPage: true,
        effect: "left",
        autoPlay: true,
        vis: summarySlideVis
    });

    //时间范围切换
    $(".time-range a").click(function () {
        var $this = $(this);
        window.location.href = "/datav?type=" + ($this.attr("rel"));
    });

    scjgChart = echarts.init(document.getElementById('scjgChart'), theme);
//    yysrfxChart = echarts.init(document.getElementById('yysrfxChart'), theme);
    jgqsChart = echarts.init(document.getElementById('jgqsChart'), theme);
    summaryChart = echarts.init(document.getElementById('summaryChart'), theme);
    monitoringChart = echarts.init(document.getElementById('monitoringChart'), theme);
    //食材结构
//    initScjgChart(1);

//    //营养摄入对比
//    $.ajax({
//        url: '/datav/statNutritionCompareList?type=default',
//        type: "GET",
//        success: function (reply) {
//            initYysrdbChart(reply);
//        }
//    });
//
//    //价格趋势
//    $.ajax({
//        url: '/datav/statMaterialPriceList?type=default',
//        type: "GET",
//        success: function (reply) {
//            initJgqsChart(reply);
//        }
//    });
//
//    //配送地图
//    $.ajax({
//        url: PROJECT_NAME + '/CustomerController/shippingMap.do',
//        type: "GET",
//        success: function (reply) {
////            console.log(reply);
////            console.log(reply.geoCoordList);
////            initPeisongMap(reply);
//        }
//    });
    initJgqsChart();
    initYysrdbChart();
    initScjgChart02();
    initSummaryChart();
    getmonitoringChart(1);
//    initYysrfxChart02();
//    function initJgqsChart(reply) {

    /**初始化后台地图数据*/
//    function initPeisongMap(reply) {
//        var dituChart = echarts.init(document.getElementById('dituChart'));
//
//        var geoArray = [];
//        $.each(reply.geoCoordList, function () {
//            var obj = $(this)[0];
//            var coordArray = [];
//            coordArray.push(obj.xaxis, obj.yaxis);
//            if (obj.name == '配送站') {
//                geoArray.push({
//                    name: '',
//                    value: [obj.xaxis, obj.yaxis],
//                    symbolSize: 3 + obj.value / 10,
//                    itemStyle: {"normal": {"color": "#FC6D2C"}}
//                });
//            } else {
//                geoArray.push({
//                    name: obj.name,
//                    value: [obj.xaxis, obj.yaxis],
//                    symbolSize: 3 + obj.value / 10
//                });
//            }
//        });
//
//        var peiSongData = [];
//        $.each(reply.shippingLinesList, function () {
//            var obj = $(this)[0];
//            var fromArray = [obj.fromX, obj.fromY];
//            var destArray = [obj.destX, obj.destY];
//            peiSongData.push({
//                fromName: obj.fromName,
//                toName: obj.destName,
//                coords: [fromArray, destArray]
//            });
//        });
//
//        var series = [];
//        var list = [];
//        list.push(['配送站', peiSongData]);
//        var colors = ['#2af131', '#fc6d2c', '#f8cc31'];
//        list.forEach(function (item, i) {
//            series.push(
//                    {
//                        name: item[1],
//                        type: 'effectScatter',
//                        coordinateSystem: 'bmap',
//                        zlevel: 2,
//                        rippleEffect: {
//                            brushType: 'stroke'
//                        },
//                        label: {
//                            normal: {
//                                show: true,
//                                position: 'right',
//                                formatter: '{b}',
//                                textStyle: {
//                                    fontSize: 14
//                                }
//                            }
//                        },
//                        tooltip: {
//                            trigger: 'item',
//                            formatter: function (obj) {
//                                var shtml = '';
//                                shtml += '<div style="font-size: 12px; height: 26px;">';
//                                shtml += obj.data.name;
//                                shtml += '</div>';
//                                return shtml;
//                            }
//                        },
//                        itemStyle: {
//                            normal: {
////                                color: '#46bee9',
//                                color: '#11283d',
//                                textStyle: {
//                                    fontSize: 1
//                                }
//                            }
//                        },
//                        data: geoArray
//                    },
//                    {
//                        name: item[2],
//                        type: 'lines',
//                        zlevel: 2,
//                        coordinateSystem: 'bmap',
//                        effect: {
//                            show: true,
//                            period: 3, //箭头指向速度，值越小速度越快
//                            trailLength: 0.05, //特效尾迹长度[0,1]值越大，尾迹越长重
//                            symbol: 'arrow', //箭头图标
//                            symbolSize: 5 //图标大小
//                        },
//                        tooltip: {
//                            trigger: 'item',
//                            formatter: function (obj) {
//                                var shtml = '';
//                                shtml += '<div style="solid rgba(255,255,255,.3); font-size: 13px;">';
//                                shtml += obj.data.fromName + '  至  ' + obj.data.toName;
//                                shtml += '</div>';
//                                return shtml;
//                            }
//                        },
//                        lineStyle: {
//                            normal: {
//                                color: '#F8CC31',
//                                width: 3, //尾迹线条宽度
//                                opacity: 0.001, //尾迹线条透明度
//                                curveness: 0 //尾迹线条曲直度,
//                            }
//                        },
//                        data: peiSongData
//                    }
//            );
//        });
//
//        var dituOption = {
//            backgroundColor: 'transparent',
//            title: {
//                left: 'center',
//                textStyle: {
//                    color: 'white'
//                }
//            },
//            tooltip: {
//                trigger: 'item'
//            },
//            //此属性是作用是定义地图的样式
//            bmap: {
//                center: [119.736695, 30.261491], //配送站的经纬坐标
//                zoom: 18,
//                roam: false, //缩放，平移控制
////                mapStyle: {
////                    'styleJson': [
////                        {
////                            'featureType': 'water',  //调整河流颜色
////                            'elementType': 'all',
////                            'stylers': {
////                                'color': '#12273c'
////                            }
////                        },
////                        {
////                            'featureType': 'land',  //调整土地颜色
////                            'elementType': 'geometry',
////                            'stylers': {
////                                'color': '#12273c'
////                            }
////                        },
////                        {
////                            'featureType': 'highway',
////                            'elementType': 'all',
////                            'stylers': {
////                                'visibility': 'off'
////                            }
////                        },
////                        {
////                            'featureType': 'arterial', //调整一些干道颜色
////                            'elementType': 'geometry.fill',
////                            'stylers': {
////                                'color': '#2f4459'
////                            }
////                        },
////                        {
////                            'featureType': 'arterial',
////                            'elementType': 'geometry.stroke',
////                            'stylers': {
////                                'color': '#0b3d51'
////                            }
////                        },
////                        {
////                            'featureType': 'local',
////                            'elementType': 'geometry',
////                            'stylers': {
////                                'color': '#000000'
////                            }
////                        },
////                        {
////                            'featureType': 'railway',
////                            'elementType': 'geometry.fill',
////                            'stylers': {
////                                'color': '#000000'
////                            }
////                        },
////                        {
////                            'featureType': 'railway',
////                            'elementType': 'geometry.stroke',
////                            'stylers': {
////                                'color': '#08304b'
////                            }
////                        },
////                        {
////                            'featureType': 'subway',
////                            'elementType': 'geometry',
////                            'stylers': {
////                                'lightness': -70
////                            }
////                        },
////                        {
////                            'featureType': 'building',     //调整建筑物颜色
////                            'elementType': 'geometry.fill',
////                            'stylers': {
////                                'color': '#000000'
////                            }
////                        },
////                        {
////                            'featureType': 'all',  //调整所有标签的填充颜色
////                            'elementType': 'labels.text.fill',
////                            'stylers': {
////                                'color': '#857f7f'
////                            }
////                        },
////                        {
////                            'featureType': 'all',   //调整所有的标签的边缘颜色
////                            'elementType': 'labels.text.stroke',
////                            'stylers': {
////                                'color': '#000000',
////                                'visibility': 'off'
////                            }
////                        },
////                        {
////                            'featureType': 'building',  //调整建筑物
////                            'elementType': 'geometry',
////                            'stylers': {
////                                'color': '#022338'
////                            }
////                        },
////                        {
////                            'featureType': 'green',
////                            'elementType': 'geometry',
////                            'stylers': {
////                                'color': '#062032'
////                            }
////                        },
////                        {
////                            'featureType': 'boundary', //边界
////                            'elementType': 'all',
////                            'stylers': {
////                                'color': '#12273c'
////                            }
////                        },
////                        {
////                            'featureType': 'manmade',
////                            'elementType': 'all',
////                            'stylers': {
////                                'color': '#12273c'
////                            }
////                        },
////                        {
////                            'featureType': 'label',
////                            'elementType': 'all',
////                            'stylers': {
////                                'visibility': 'off'
////                            }
////                        }
////                    ]
////                }
//            },
//            series: series
//        };
//        dituChart.setOption(dituOption, true);
//        //标注节点点击事件
//        dituChart.on('click', function (params) {
//            var schoolName = params.name;
//            //营养摄入对比
//            $.ajax({
//                url: '/datav/statNutritionCompareList?type=default&search_EQ_customerName=' + schoolName,
//                type: "GET",
//                success: function (reply) {
//                    initYysrdbChart(reply);
//                }
//            });
//
//            //菜谱推荐异步载入
//            $("#recommendMenu_title").text("今日订单");
//            $("#recommendMenu_table").load('/datav/statRecommendedMenuList?type=today&search_EQ_customerName=' + schoolName);
//        });
//
//        var bmap = dituChart.getModel().getComponent('bmap').getBMap();
//
//
//        ////隐藏多余的地图切换器 添加控件和比例尺
//        var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
////        var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
//        bmap.addControl(top_left_navigation);
////        bmap.addControl(top_right_navigation);
//    }
});

$(".smallCircle").click(function () {
    //传入监测点的编号
    getmonitoringChart($(this).text());
});

/**
 * 监测点图表生成
 * 参数:num 第几个监测点
 */
function getmonitoringChart(num) {
    var monitoringOption = {
        title: {
            text: '标题文本',
            textStyle: {
                //文字颜色
                color: '#ccc',
                //字体风格,'normal','italic','oblique'
                fontStyle: 'normal',
                //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                fontWeight: 'bold',
                //字体系列
                fontFamily: 'sans-serif',
                //字体大小
                fontSize: 18,
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#283b56'
                }
            }
        },
        legend: {
            data: ['ph', '水温'],
        },
        grid: {
            x: 40,
            y: 40,
        },
        toolbox: {
            show: false,
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        dataZoom: {
            show: false,
            start: 0,
            end: 100
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                data: (function () {
                    var now = new Date();
                    var res = [];
                    var len = 1;
                    while (len--) {
                        res.unshift(now.toLocaleTimeString().replace(/^\D*/, ''));
                        now = new Date(now - 2000);
                    }
                    return res;
                })()
            },
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                max: 30,
                min: 0,
                boundaryGap: [0.2, 0.2]
            }
        ],
        series: [
            {
                name: '水温',
                type: 'bar',
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: (function () {
                    var res = [];
                    var len = 1;
                    while (len--) {
                        res.push((23 + Math.random() * 3).toFixed(1));
                    }
                    return res;
                })()
            },
            {
                name: 'ph',
                type: 'bar',
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: (function () {
                    var res = [];
                    var len = 1;
                    while (len--) {
                        res.push((6 + Math.random() * 1).toFixed(1));
                    }
                    return res;
                })()
            },
        ]
    };

    if (num == 1) {
        monitoringOption.title.text = "1号监测点";
    } else if (num == 2) {
        monitoringOption.title.text = "2号监测点";
    } else if (num == 3) {
        monitoringOption.title.text = "3号监测点";
    }

    if (monitoringOption && typeof monitoringOption === "object") {
        monitoringChart.setOption(monitoringOption, true);
    }
}

/**
 * 初始化综合数据图表
 */
function initSummaryChart() {
    var summaryOption = {
//        tooltip: {
//            trigger: 'axis',
//            axisPointer: {
//                type: 'cross',
//                label: {
//                    backgroundColor: '#283b56'
//                }
//            }
//        },
//        legend: {
//            data: ['ph', '水温'],
//        },
//        grid: {
//            x: 40,
//            y: 40,
//        },
//        toolbox: {
//            show: false,
//            feature: {
//                dataView: {readOnly: false},
//                restore: {},
//                saveAsImage: {}
//            }
//        },
        title: {
//            text: "无人机监测数据"
        },
        tooltip: {
            trigger: "axis"
        },
        legend: {
            data: ["ph", "水温", "溶解氧"]
        },
        toolbox: {
            show: true,
            feature: {
                mark: {
                    show: true
                },
                dataView: {
                    show: true,
                    readOnly: true
                },
                magicType: {
                    show: false,
                    type: ["line", "bar", "stack", "tiled"]
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
//        dataZoom: {
//            show: false,
//            start: 0,
//            end: 100
//        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                data: (function () {
                    var now = new Date();
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.unshift(now.toLocaleTimeString().replace(/^\D*/, ''));
                        now = new Date(now - 2000);
                    }
                    return res;
                })()
            },
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                max: 30,
                min: 0,
                boundaryGap: [0.2, 0.2]
            }
        ],
        series: [
            {
                name: '水温',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: "default"
                        }
                    }
                },
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: (function () {
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.push((23 + Math.random() * 3).toFixed(1));
                    }
                    return res;
                })()
            },
            {
                name: 'ph',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: "default"
                        }
                    }
                },
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: (function () {
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.push((6 + Math.random() * 1).toFixed(1));
                    }
                    return res;
                })()
            },
            {
                name: '溶解氧',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: "default"
                        }
                    }
                },
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: (function () {
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.push((6 + Math.random() * 1).toFixed(1));
                    }
                    return res;
                })()
            },
        ]
    };

    setInterval(function () {
        axisData = (new Date()).toLocaleTimeString().replace(/^\D*/, '');

        var data0 = summaryOption.series[0].data;
        var data1 = summaryOption.series[1].data;
        var data2 = summaryOption.series[2].data;

        data0.shift();
        data0.push((23 + Math.random() * 3).toFixed(1));
        data1.shift();
        data1.push((6 + Math.random() * 1.5).toFixed(1));
        data2.shift();
        data2.push((6 + Math.random() * 1.5).toFixed(1));
        summaryOption.xAxis[0].data.shift();
        summaryOption.xAxis[0].data.push(axisData);

//        data1.shift();
//        data1.push((23 + Math.random() * 3).toFixed(1));

        summaryChart.setOption(summaryOption);
    }, 2100);

    if (summaryOption && typeof summaryOption === "object") {
        summaryChart.setOption(summaryOption, true);
    }
}

//价格趋势
function initJgqsChart() {
    var jgqsOption = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#283b56'
                }
            }
        },
        legend: {
            data: ['无人船水温', '无人机水温'],
//            data: ['无人机水温']
        },
        grid: {
            x: 40,
            y: 40,
        },
        toolbox: {
            show: false,
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        dataZoom: {
            show: false,
            start: 0,
            end: 100
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                data: (function () {
                    var now = new Date();
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.unshift(now.toLocaleTimeString().replace(/^\D*/, ''));
                        now = new Date(now - 2000);
                    }
                    return res;
                })()
            },
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                name: '水温',
                max: 30,
                min: 0,
                boundaryGap: [0.2, 0.2]
            }
        ],
        series: [
            {
                name: '无人船水温',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: "default"
                        }
                    }
                },
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: (function () {
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.push((23 + Math.random() * 3).toFixed(1));
                    }
                    return res;
                })()
            },
//            {
//                name: '无人机水温',
//                type: 'line',
//                color: '#febe3c',
//                xAxisIndex: 0,
//                yAxisIndex: 0,
//                data: (function () {
//                    var res = [];
//                    var len = 10;
//                    while (len--) {
//                        res.push((23 + Math.random() * 3).toFixed(1));
//                    }
//                    return res;
//                })()
//            }
        ]
    };

    setInterval(function () {
        axisData = (new Date()).toLocaleTimeString().replace(/^\D*/, '');

        var data0 = jgqsOption.series[0].data;
//        var data1 = jgqsOption.series[1].data;
        data0.shift();
        data0.push((23 + Math.random() * 3).toFixed(1));

        jgqsOption.xAxis[0].data.shift();
        jgqsOption.xAxis[0].data.push(axisData);

//        data1.shift();
//        data1.push((23 + Math.random() * 3).toFixed(1));

        jgqsChart.setOption(jgqsOption);
    }, 2100);

    if (jgqsOption && typeof jgqsOption === "object") {
        jgqsChart.setOption(jgqsOption, true);
    }
}

//营养摄入对比
function initYysrdbChart() {
    var yysrdbChart = echarts.init(document.getElementById('yysrdbChart'), theme);
    var yysrdbOption = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
//                    backgroundColor: '#283b56'
                    backgroundColor: '#f6c044'
                }
            }
        },
        legend: {
            data: ['无人船导电性', '无人机导电性']
        },
        grid: {
            x: 40,
            y: 60,
        },
        toolbox: {
            show: false,
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        dataZoom: {
            show: false,
            start: 0,
            end: 100
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                data: (function () {
                    var now = new Date();
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.unshift(now.toLocaleTimeString().replace(/^\D*/, ''));
                        now = new Date(now - 2000);
                    }
                    return res;
                })()
            },
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                name: '导电性',
                max: 8000,
                min: 0,
//                boundaryGap: [0.2, 0.2]
            }
        ],
        series: [
            {
                name: '无人船导电性',
                type: 'bar',
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: (function () {
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.push(Math.floor(Math.random() * 8000));
                    }
                    return res;
                })()
            },
//            {
//                name: '无人机导电性',
//                type: 'bar',
//                color: '#febe3c',
//                xAxisIndex: 0,
//                yAxisIndex: 0,
//                data: (function () {
//                    var res = [];
//                    var len = 10;
//                    while (len--) {
//                        res.push(Math.floor(Math.random() * 8000));
//                    }
//                    return res;
//                })()
//            }
        ]
    };

    setInterval(function () {
        axisData = (new Date()).toLocaleTimeString().replace(/^\D*/, '');

        var data0 = yysrdbOption.series[0].data;
//        var data1 = yysrdbOption.series[1].data;

        data0.shift();
        data0.push(Math.floor(Math.random() * 8000));

//        data1.shift();
//        data1.push(Math.floor(Math.random() * 8000));

        yysrdbOption.xAxis[0].data.shift();
        yysrdbOption.xAxis[0].data.push(axisData);
        yysrdbChart.setOption(yysrdbOption);
    }, 2100);

    if (yysrdbOption && typeof yysrdbOption === "object") {
        yysrdbChart.setOption(yysrdbOption, true);
    }
}

//食材结构
function initScjgChart02() {
    var scjgOption = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#283b56'
                }
            }
        },
        legend: {
            data: ['无人船ph', '无人机ph']
        },
        toolbox: {
            show: false,
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        dataZoom: {
            show: false,
            start: 0,
            end: 100
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                data: (function () {
                    var now = new Date();
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.unshift(now.toLocaleTimeString().replace(/^\D*/, ''));
                        now = new Date(now - 2000);
                    }
                    return res;
                })()
            },
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                name: 'ph',
                max: 10,
                min: 0,
                boundaryGap: [0.2, 0.2]
            }
        ],
        series: [
            {
                name: '无人船ph',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: "default"
                        }
                    }
                },
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: (function () {
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.push((6 + Math.random() * 1).toFixed(1));
                    }
                    return res;
                })()
            },
//            {
//                name: '无人机ph',
//                type: 'line',
//                color: '#febe3c',
//                xAxisIndex: 0,
//                yAxisIndex: 0,
//                data: (function () {
//                    var res = [];
//                    var len = 10;
//                    while (len--) {
//                        res.push((6 + Math.random() * 1).toFixed(1));
//                    }
//                    return res;
//                })()
//            }
        ]
    };

    setInterval(function () {
        axisData = (new Date()).toLocaleTimeString().replace(/^\D*/, '');

        var data0 = scjgOption.series[0].data;
//        var data1 = scjgOption.series[1].data;

        data0.shift();
        data0.push((6 + Math.random() * 2).toFixed(1));

//        data1.shift();
//        data1.push((6 + Math.random() * 2).toFixed(1));

        scjgOption.xAxis[0].data.shift();
        scjgOption.xAxis[0].data.push(axisData);
        scjgChart.setOption(scjgOption);
    }, 2100);

    if (scjgOption && typeof scjgOption === "object") {
        scjgChart.setOption(scjgOption, true);
    }
}


//
////营养摄入分析
//function initYysrfxChart02() {
//    var customerArray = ['东湖', '乌镇', '新安'];
//    //ph
//    var proteinArray = [6.9, 7.8, 6.3];
//    //氨氮
//    var fatArray = [157, 156, 147];
//    //导电性
//    var sugarArray = [2225, 3730, 2143];
//    //溶解氧
//    var dietaryFiberArray = [6.4, 6.3, 6.5];
//    //水温
//    var traceElementArray = [23.1, 24, 24.6];
//
//    var yysrfxOption = {
//        title: {
//            text: '营养摄入分析',
//            left: 'center',
//            textStyle: {},
//            show: false
//        },
//        tooltip: {
//            trigger: 'axis',
//            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
//                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
//            }
//        },
//        legend: {
//            y: 'bottom',
//            itemWidth: 10,
//            itemHeight: 10,
//            itemGap: 10,
//            data: ['ph', '氨氮', '导电性', '溶解氧', '水温']
//        },
//        grid: {
//            left: '3%',
//            right: '8%',
//            bottom: '10%',
//            top: '3%',
//            containLabel: true
//        },
//        xAxis: {
//            type: 'value',
////            name: '单位（毫克）',
//            nameGap: 5,
//            nameRotate: 270,
//            axisLabel: {
//                interval: 0,//横轴信息全部显示
//                rotate: 30//倾斜显示
//            }
//        },
//        yAxis: {
//            type: 'category',
//            data: customerArray
//        },
//        series: [
//            {
//                name: 'ph',
//                type: 'bar',
//                stack: '总量',
//                data: proteinArray,
//                barWidth: 20
//            },
//            {
//                name: '氨氮',
//                type: 'bar',
//                stack: '总量',
//                data: fatArray
//            },
//            {
//                name: '导电性',
//                type: 'bar',
//                stack: '总量',
//                data: sugarArray
//            },
//            {
//                name: '溶解氧',
//                type: 'bar',
//                stack: '总量',
//                data: dietaryFiberArray
//            },
//            {
//                name: '水温',
//                type: 'bar',
//                stack: '总量',
//                data: traceElementArray
//            }
//        ]
//    };
//    yysrfxChart.setOption(yysrfxOption);
//}
/*]]>*/