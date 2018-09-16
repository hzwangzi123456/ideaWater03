/**
 * Created by ziwang on 17/10/26.
 */
var resdata = null;//数据数组
var user = null;
var prefix = null;

//所属省份绑定改变事件
$('#select01')
    .change(
        function () {
            doAjaxSyn(
                PROJECT_NAME
                + "/AuthorityAreasController/findAuthorityAreasByPojo.do",
                {
                    province: $('#select01').val(),
                    username: user.username
                },
                function (data) {
                    var redata = strToJson(data);
                    if (redata == null) {
                        console.log("后台未发送ajax响应");
                        return;
                    }
                    if ("isSucced" in redata == true
                        && redata.isSucced == false) {
                        console.log(redata.msg);
                        return;
                    }
                    $('#select02').html('');
                    var html = '<option value="" class="" selected="selected">请选择</option>';
                    for (var i = 0; i < redata.length; i++) {
                        html += '<option label="' + redata[i].extra
                            + '" value="' + redata[i].prefix
                            + '">' + redata[i].extra
                            + '</option>';
                    }
                    $('#select02').append(html);
                });
        });

// 所属地区绑定改变事件
$('#select02')
    .change(
        function () {
            doAjaxSyn(
                PROJECT_NAME
                + "/AreasInstrumentsController/findAreasInstrumentsByPojo.do",
                {
                    prefix: $('#select02').val()
                },
                function (data) {
                    var redata = strToJson(data);
                    if (redata == null) {
                        console.log("后台未发送ajax响应");
                        return;
                    }
                    if ("isSucced" in redata == true
                        && redata.isSucced == false) {
                        console.log(redata.msg);
                        return;
                    }
                    $('#select03').html('');
                    var html = '<option value="" class="" selected="selected">请选择</option>';
                    for (var i = 0; i < redata.length; i++) {
                        html += '<option label="'
                            + redata[i].instrumentId
                            + '" value="'
                            + redata[i].instrumentId + '">'
                            + redata[i].instrumentId
                            + '</option>';
                    }
                    $('#select03').append(html);
                });
        });

//根据设备id,开始时间,结束时间请求后台数据
function getChartDatas(instrumentId, start, end) {
    var redata = null;
    doAjaxSyn(PROJECT_NAME + "YascmfDatasController/findYascmfDatasByTime.do",
        {instrumentId: instrumentId, start: start, end: end}, function (data) {
            redata = strToJson(data);
        });
    if (!judgeAjaxData(redata)) {
        return null;
    }
    return redata;
}

$('#reload').click(function () {
    var select01 = $('#select01').val();
    if (select01 == null || select01 == '') {
        alert('请选择所属省份');
        return;
    }
    var select02 = $('#select02').val();
    if (select02 == null || select02 == '') {
        alert('请选择所属地区');
        return;
    }
    var select03 = $('#select03').val();// 设备编号
    if (select03 == null || select03 == '') {
        alert('请选择设备编号');
        return;
    }
    var start = $('#start').val();
    if (start == null || start == '') {
        alert('请选择起始时间');
        return;
    }
    start = start.replace('年', '-');
    start = start.replace('月', '-');
    start = start.replace('日', '');
    var end = $('#end').val();
    if (end == null || end == '') {
        alert('请选择结束时间');
        return;
    }
    end = end.replace('年', '-');
    end = end.replace('月', '-');
    end = end.replace('日', '');
    if (start >= end) {
        alert('起始时间不能大于结束时间');
        return;
    }
    resdata = null;
    resdata = getChartDatas(select03, start, end);
    if (resdata == null) {
        alert("未找到数据");
        return;
    }
    //点击单图按钮
    $($(".single-button")[0]).trigger("click");
    //single显现
    $(".singlebox").show();
    //multip显示,不然echarts.init会获取不到dom的宽和高
    $(".mutiplebox").show();
    disposeFun();
    initFun();
    //multiple隐藏
    $(".mutiplebox").hide();
});

//销毁所有echarts实例
function disposeFun() {
    $(".colorful-tab-content").each(function () {
        echarts.dispose(this);
    });
}

//初始化echarts实例
function initFun() {
    if (resdata == null) {
        alert("未找到数据");
        return;
    }
    $(".colorful-tab-content").each(function () {
        initColor(resdata, this);
    });
}

//初始化图表(数据,dom,单位)
function initColor(arr, dom, unit) {
    var $object = $(dom);
    var mychart = echarts.init(dom);
    option02.xAxis[0].data = getDateTime(arr);
    if ($object.attr('id') == "ph" || $object.attr('id').trim() == "phchart-ph") {
        //y轴的名字
        option02.yAxis.name = '';
        option02.legend = {data: ['PH']};
        option02.series = [{
            name: 'PH',
            type: 'line',
            stack: 'ph值',
            data: new Array()
        }];
        option02.series[0].data = getPh(arr);
    } else if ($object.attr('id') == "temperature" || $object.attr('id').trim() == "temperaturechart-temperature") {
        //y轴的名字
        option02.yAxis.name = 'T(℃)';
        option02.legend = {data: ['水温']};
        option02.series = [{
            name: '水温',
            type: 'line',
            stack: 'T(℃)',
            data: new Array()
        }];
        option02.series[0].data = getWaterTemperature(arr);
    } else if ($object.attr('id') == "conductivity" || $object.attr('id').trim() == "conductivitychart-conductivity") {
        //y轴的名字
        option02.yAxis.name = 'us/cm';
        option02.legend = {data: ['电导率']};
        option02.series = [{
            name: '电导率',
            type: 'line',
            stack: 'CDT(us/cm)',
            data: new Array()
        }];
        option02.series[0].data = getConductivity(arr);
    } else if ($object.attr('id') == "nitrogen" || $object.attr('id').trim() == "nitrogenchart-nitrogen") {
        //y轴的名字
        option02.yAxis.name = 'mg/L';
        option02.legend = {data: ['氨氮']};
        option02.series = [{
            name: '氨氮',
            type: 'line',
            stack: 'N(mg/L)',
            data: new Array()
        }];
        option02.series[0].data = getAmmoniaNitrogen(arr);
    } else if ($object.attr('id') == "dissolved_oxygen" || $object.attr('id').trim() == "dissolved_oxygenchart-dissolved_oxygen") {
        //y轴的名字
        option02.yAxis.name = 'mg/L';
        option02.legend = {data: ['溶解氧']};
        option02.series = [{
            name: '溶解氧',
            type: 'line',
            stack: 'DO(mg/L)',
            data: new Array()
        }];
        option02.series[0].data = getDissolvedOxygen(arr);
    } else if ($object.attr('id') == "ntu" || $object.attr('id').trim() == "ntuchart-ntu") {
        //y轴的名字
        option02.yAxis.name = '';
        option02.legend = {data: ['浊度']};
        option02.series = [{
            name: '浊度',
            type: 'line',
            stack: 'NTU',
            data: new Array()
        }];
        option02.series[0].data = getNTU(arr);
    } else if ($object.attr('id') == "P" || $object.attr('id').trim() == "Pchart-P") {
        //y轴的名字
        option02.yAxis.name = 'mg/L';
        option02.legend = {data: ['总磷']};
        option02.series = [{
            name: '总磷',
            type: 'line',
            stack: 'P(mg/L)',
            data: new Array()
        }];
        option02.series[0].data = getP(arr);
    }
    if (unit != "" && unit != null) {
        option.series[0].name += "(" + unit + ")";
    }
    mychart.setOption(option02);
    return mychart;
}


//向上按钮绑定点击事件
$(".top").click(function () {
    var tot = $(document).scrollTop();
    var timer = setInterval(function () {
        tot -= 50;
        if (tot <= 0) {
            $(document).scrollTop(0);
            clearTimeout(timer);
            return;
        }
        $(document).scrollTop(tot);
    }, 15);
});

//切换单和多图
$(".single-button").click(function () {
    $(this).siblings(".single-button").removeClass("button-green");
    $(this).addClass("button-green");
    var text = $(this).text();
    if (text.trim() == "单图") {
        $(".singlebox").show();
        $(".mutiplebox").hide();
    } else {
        $(".singlebox").hide();
        $(".mutiplebox").show();
    }
});

// 得到Ph数组
function getPh(obj) {
    var ph = new Array();
    for (var i = 0; i < obj.length; i++)
        ph[i] = obj[i].ph;
    return ph;
}

// 得到电导率数组
function getConductivity(obj) {
    var conductivity = new Array();
    for (var i = 0; i < obj.length; i++)
        conductivity[i] = obj[i].conductivity;
    return conductivity;
}

// 得到水温数组
function getWaterTemperature(obj) {
    var water_temperature = new Array();
    for (var i = 0; i < obj.length; i++)
        water_temperature[i] = obj[i].waterTemperature;
    return water_temperature;
}

// 得到氨氮数组
function getAmmoniaNitrogen(obj) {
    var ammonia_nitrogen = new Array();
    for (var i = 0; i < obj.length; i++)
        ammonia_nitrogen[i] = obj[i].ammoniaNitrogen;
    return ammonia_nitrogen;
}

// 得到溶解氧数组
function getDissolvedOxygen(obj) {
    var dissolved_oxygen = new Array();
    for (var i = 0; i < obj.length; i++)
        dissolved_oxygen[i] = obj[i].dissolvedOxygen;
    return dissolved_oxygen;
}

// 得到浊度数组
function getNTU(obj) {
    var ntu = new Array();
    for (var i = 0; i < obj.length; i++)
        ntu[i] = obj[i].ntu;
    return ntu;
}

// 得到总磷数组
function getP(obj) {
    var P = new Array();
    for (var i = 0; i < obj.length; i++)
        P[i] = obj[i].p;
    return P;
}

//得到日期
function getDateTime(obj) {
    var date_time = new Array();
    for (var i = 0; i < obj.length; i++) {
        var dateString = obj[i].dateTime.substring(0, obj[i].dateTime.length - 2);
        var year = dateString.substring(0, 4);
        var month = dateString.substring(5, 7);
        var day = dateString.substring(8, 10);
        var hour = dateString.substring(11, 13);
        var minute = dateString.substring(14, 16);
        var second = dateString.substring(17, 19);
        date_time[i] = month + '月' + day + '日' + hour + ':' + minute;
    }
    return date_time;
}

$(function () {
    doAjaxSyn(PROJECT_NAME + "/UserController/findSessionByLogin.do", {}, function (data) {
        var redata = strToJson(data);
        if (!judgeAjaxData(redata)) {
            window.location.href = PROJECT_NAME + INDEX_NAME;
            return;
        }
        user = redata;
        if (user.hasPh == 0) {
            $($("ul.colorful-tab-menu").children('li')[0]).hide();
            $("#phchart").hide();
        }
        if (user.hasWaterTemperature == 0) {
            $($("ul.colorful-tab-menu").children('li')[1]).hide();
            $("#temperaturechart").hide();
        }
        if (user.hasAmmonianitrogen == 0) {
            $($("ul.colorful-tab-menu").children('li')[2]).hide();
            $("#nitrogenchart").hide();
        }
        if (user.hasP == 0) {
            $($("ul.colorful-tab-menu").children('li')[3]).hide();
            $("#Pchart").hide();
        }
        if (user.hasConductivity == 0) {
            $($("ul.colorful-tab-menu").children('li')[4]).hide();
            $("#conductivitychart").hide();
        }
        if (user.hasDissolvedOxygen == 0) {
            $($("ul.colorful-tab-menu").children('li')[5]).hide();
            $("#dissolved_oxygenchart").hide();
        }
        if (user.hasNtu == 0) {
            $($("ul.colorful-tab-menu").children('li')[6]).hide();
            $("#ntuchart").hide();
        }
    });
    var mtree = $('ul.mtree');
    mtree.addClass('jet');
    $("#colorful-elliptic").colorfulTab({
        theme: "elliptic"
    });
    $("#colorful-elliptic-ph").colorfulTab({
        theme: "elliptic"
    });
    $("#colorful-elliptic-temperature").colorfulTab({
        theme: "elliptic"
    });
    $("#colorful-elliptic-nitrogen").colorfulTab({
        theme: "elliptic"
    });
    $("#colorful-elliptic-P").colorfulTab({
        theme: "elliptic"
    });
    $("#colorful-elliptic-conductivity").colorfulTab({
        theme: "elliptic"
    });
    $("#colorful-elliptic-dissolved_oxygen").colorfulTab({
        theme: "elliptic"
    });
    $("#colorful-elliptic-ntu").colorfulTab({
        theme: "elliptic"
    });
    //向后台发送ajax请求
    $(".header").load("../controllerCommon/header.html");
    $(".nav").load("../controllerCommon/nav.html");

    if (user != null) {
        //根据username去查对应的prefix
        doAjaxSyn(PROJECT_NAME + "/AuthorityController/findAuthorityByPojo.do", {username: user.username}, function (data) {
            var redata = strToJson(data);
            if (!judgeAjaxData(redata)) {
                return;
            }
            prefix = redata[0].prefix;
        });

        if (prefix != null) {
            doAjaxSyn(PROJECT_NAME + "AreasInstrumentsController/findAreasInstrumentsByPojo.do", {prefix: prefix}, function (data) {
                var redata = strToJson(data);
                if (!judgeAjaxData(redata)) {
                    return;
                }

                $('#select01').html('');
                var html01 = '';
                for (var i = 0; i < redata.length; i++) {
                    html01 += '<option label="' + redata[i].province
                        + '" value="' + redata[i].province
                        + '">' + redata[i].province
                        + '</option>';
                }
                $('#select01').append(html01);

                $('#select02').html('');
                var html02 = '';
                for (var i = 0; i < redata.length; i++) {
                    html02 += '<option label="' + redata[i].extra
                        + '" value="' + redata[i].extra
                        + '">' + redata[i].extra
                        + '</option>';
                }
                $('#select02').append(html02);

                $('#select03').html('');
                var html03 = '';
                for (var i = 0; i < redata.length; i++) {
                    html03 += '<option label="' + redata[i].instrumentId
                        + '" value="' + redata[i].instrumentId
                        + '">' + redata[i].instrumentId
                        + '</option>';
                }
                $('#select03').append(html03);
            });
        }
    }

});