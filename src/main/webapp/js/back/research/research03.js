var resdata;//数据数组
var user = null;

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


function fun01(me) {//点击a标签跳转函数
    if (resdata == null || resdata.length == 0) {
        alert("请先搜索");
        return;
    }
    initColor(resdata, $($(me).find('a').attr("href")), "");
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
    /*销毁之前的echarts实例*/
    // echarts.dispose(document.getElementById("placeholder"));
    echarts.dispose(document.getElementById("phplaceholder"));
    echarts.dispose(document.getElementById("templaceholder"));
    echarts.dispose(document.getElementById("Nplaceholder"));
    echarts.dispose(document.getElementById("Pplaceholder"));
    echarts.dispose(document.getElementById("condplaceholder"));
    echarts.dispose(document.getElementById("OXplaceholder"));
    echarts.dispose(document.getElementById("turplaceholder"));
    // 请求数据
    resdata = getChartDatas(select03, start, end);
    if (resdata == null || ("isSucced" in resdata == true && resdata.isSucced == false)) {
        return;
    }
    //显示单图表和多图表
    $("#singlebox").removeClass("boxhide");
    $("#multiplebox").removeClass("boxhide");
    //初始化echarts实例
    fun01($('#li01'));
    initPh('phplaceholder');
    initWaterTemperature('templaceholder');
    initAmmoniaNitrogen('Nplaceholder');
    initP('Pplaceholder');
    initConductivity('condplaceholder');
    initDissolvedOxygen('OXplaceholder');
    initNTU('turplaceholder');
    //隐藏多图表
    $("#multiplebox").addClass("boxhide");
});

// 单多图表切换
$("#single").click(function () {
    if (resdata == null || ("isSucced" in resdata == true && resdata.isSucced == false)) {
        alert("未找到数据");
        return;
    }
    $("#multiplebox").addClass("boxhide");
    $("#singlebox").removeClass("boxhide");
});

$("#multiple").click(function () {
    if (resdata == null || ("isSucced" in resdata == true && resdata.isSucced == false)) {
        alert("未找到数据");
        return;
    }
    $("#singlebox").addClass("boxhide");
    $("#multiplebox").removeClass("boxhide");
});


//根据设备Id,起始日期，结束日期,返回结果数组
function getChartDatas(instrumentId, start, end) {
    var redata;
    doAjaxSyn(PROJECT_NAME + "YascmfDatasController/findYascmfDatasByTime.do",
        {instrumentId: instrumentId, start: start, end: end}, function (data) {
            redata = strToJson(data);
            if (redata == null) {
                console.log("后台未发送ajax响应");
                return;
            }
            if ("isSucced" in redata == true && redata.isSucced == false) {
                console.log(redata.msg);
                return;
            }
        });
    return redata;
}

function initColor(arr, even, unit) {
    var id = even[0];
    echarts.dispose(even[0]);
    var mychart = echarts.init(even[0]);
    option02.xAxis[0].data = getDateTime(arr);
    if (even.attr('id') == "ph") {
        option02.legend = {data: ['PH']};
        option02.series = [{
            name: 'PH',
            type: 'line',
            stack: 'ph值',
            data: new Array()
        }];
        option02.series[0].data = getPh(arr);
    } else if (even.attr('id') == "temperature") {
        option02.legend = {data: ['水温']};
        option02.series = [{
            name: '水温',
            type: 'line',
            stack: 'T(℃)',
            data: new Array()
        }];
        option02.series[0].data = getWaterTemperature(arr);
    } else if (even.attr('id') == "conductivity") {
        option02.legend = {data: ['电导率']};
        option02.series = [{
            name: '电导率',
            type: 'line',
            stack: 'CDT(us/cm)',
            data: new Array()
        }];
        option02.series[0].data = getConductivity(arr);
    } else if (even.attr('id') == "nitrogen") {
        option02.legend = {data: ['氨氮']};
        option02.series = [{
            name: '氨氮',
            type: 'line',
            stack: 'N(mg/L)',
            data: new Array()
        }];
        option02.series[0].data = getAmmoniaNitrogen(arr);
    } else if (even.attr('id') == "dissolved_oxygen") {
        option02.legend = {data: ['溶解氧']};
        option02.series = [{
            name: '溶解氧',
            type: 'line',
            stack: 'DO(mg/L)',
            data: new Array()
        }];
        option02.series[0].data = getDissolvedOxygen(arr);
    } else if (even.attr('id') == "ntu") {
        option02.legend = {data: ['浊度']};
        option02.series = [{
            name: '浊度',
            type: 'line',
            stack: 'NTU',
            data: new Array()
        }];
        option02.series[0].data = getNTU(arr);
    } else if (even.attr('id') == "P") {
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

// 得到Ph数组
function getPh(obj) {
    var ph = new Array();
    for (var i = 0; i < obj.length; i++)
        ph[i] = obj[i].ph;
    return ph;
}

// 设置Ph图
function initPh(id) {
    option02.legend = {data: ['PH']};
    option02.series = [{
        name: 'PH',
        type: 'line',
        stack: 'ph值',
        data: new Array()
    }];
    option02.series[0].data = getPh(resdata);
    option02.xAxis[0].data = getDateTime(resdata);
    var myChart = echarts.init(document.getElementById(id));
    myChart.setOption(option02);
}

// 得到电导率数组
function getConductivity(obj) {
    var conductivity = new Array();
    for (var i = 0; i < obj.length; i++)
        conductivity[i] = obj[i].conductivity;
    return conductivity;
}

// 设置电导率图
function initConductivity(id) {
    option02.legend = {data: ['电导率']};
    option02.series = [{
        name: '电导率',
        type: 'line',
        stack: 'CDT(us/cm)',
        data: new Array()
    }];
    option02.series[0].data = getConductivity(resdata);
    option02.xAxis[0].data = getDateTime(resdata);
    var myChart = echarts.init(document.getElementById(id));
    myChart.setOption(option02);
}

// 得到水温数组
function getWaterTemperature(obj) {
    var water_temperature = new Array();
    for (var i = 0; i < obj.length; i++)
        water_temperature[i] = obj[i].waterTemperature;
    return water_temperature;
}

// 设置水温图
function initWaterTemperature(id) {
    option02.legend = {data: ['水温']};
    option02.series = [{
        name: '水温',
        type: 'line',
        stack: 'T(℃)',
        data: new Array()
    }];
    option02.series[0].data = getWaterTemperature(resdata);
    option02.xAxis[0].data = getDateTime(resdata);
    var myChart = echarts.init(document.getElementById(id));
    myChart.setOption(option02);
}

// 得到氨氮数组
function getAmmoniaNitrogen(obj) {
    var ammonia_nitrogen = new Array();
    for (var i = 0; i < obj.length; i++)
        ammonia_nitrogen[i] = obj[i].ammoniaNitrogen;
    return ammonia_nitrogen;
}

//设置氨氮图
function initAmmoniaNitrogen(id) {
    option02.legend = {data: ['氨氮']};
    option02.series = [{
        name: '氨氮',
        type: 'line',
        stack: 'N(mg/L)',
        data: new Array()
    }];
    option02.series[0].data = getAmmoniaNitrogen(resdata);
    option02.xAxis[0].data = getDateTime(resdata);
    var myChart = echarts.init(document.getElementById(id));
    myChart.setOption(option02);
}

// 得到溶解氧数组
function getDissolvedOxygen(obj) {
    var dissolved_oxygen = new Array();
    for (var i = 0; i < obj.length; i++)
        dissolved_oxygen[i] = obj[i].dissolvedOxygen;
    return dissolved_oxygen;
}

//设置溶解氧图
function initDissolvedOxygen(id) {
    option02.legend = {data: ['溶解氧']};
    option02.series = [{
        name: '溶解氧',
        type: 'line',
        stack: 'DO(mg/L)',
        data: new Array()
    }];
    option02.series[0].data = getDissolvedOxygen(resdata);
    option02.xAxis[0].data = getDateTime(resdata);
    var myChart = echarts.init(document.getElementById(id));
    myChart.setOption(option02);
}

// 得到浊度数组
function getNTU(obj) {
    var ntu = new Array();
    for (var i = 0; i < obj.length; i++)
        ntu[i] = obj[i].ntu;
    return ntu;
}

//设置浊度图
function initNTU(id) {
    option02.legend = {data: ['浊度']};
    option02.series = [{
        name: '浊度',
        type: 'line',
        stack: 'NTU',
        data: new Array()
    }];
    option02.series[0].data = getNTU(resdata);
    option02.xAxis[0].data = getDateTime(resdata);
    var myChart = echarts.init(document.getElementById(id));
    myChart.setOption(option02);
}

// 得到总磷数组
function getP(obj) {
    var P = new Array();
    for (var i = 0; i < obj.length; i++)
        P[i] = obj[i].p;
    return P;
}

//设置总磷图
function initP(id) {
    option02.legend = {data: ['总磷']};
    option02.series = [{
        name: '总磷',
        type: 'line',
        stack: 'P(mg/L)',
        data: new Array()
    }];
    option02.series[0].data = getP(resdata);
    option02.xAxis[0].data = getDateTime(resdata);
    var myChart = echarts.init(document.getElementById(id));
    myChart.setOption(option02);
}

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

//传入一个数字,如果数字>=0&& <=9,加前导0
// function addZero(num) {
//     if (num >= 0 && num <= 9) {
//         return "0" + num;
//     }
//     return num;
// }

$(function () {
    var mtree = $('ul.mtree');
    mtree.addClass('jet');
    $("#colorful-elliptic").colorfulTab({
        theme: "elliptic"
    });

    doAjaxSyn(PROJECT_NAME + "/UserController/findSessionByLogin.do", {}, function (data) {
        var redata = strToJson(data);
        if (!judgeAjaxData(redata)) {
            return;
        }
        user = redata;
        if (user.hasAmmonianitrogen == 0) {
            $($("ul.colorful-tab-menu").children('li')[2]).hide();
            $($("#multiplebox").children('div')[2]).hide();
        }
        if (user.hasPh == 0) {
            $($("ul.colorful-tab-menu").children('li')[0]).hide();
            $($("#multiplebox").children('div')[0]).hide();
        }
        if (user.hasWaterTemperature == 0) {
            $($("ul.colorful-tab-menu").children('li')[1]).hide();
            $($("#multiplebox").children('div')[1]).hide();
        }
        if (user.hasConductivity == 0) {
            $($("ul.colorful-tab-menu").children('li')[4]).hide();
            $($("#multiplebox").children('div')[4]).hide();
        }
        if (user.hasDissolvedOxygen == 0) {
            $($("ul.colorful-tab-menu").children('li')[5]).hide();
            $($("#multiplebox").children('div')[5]).hide();
        }
        if (user.hasNtu == 0) {
            $($("ul.colorful-tab-menu").children('li')[6]).hide();
            $($("#multiplebox").children('div')[6]).hide();
        }
        if (user.hasP == 0) {
            $($("ul.colorful-tab-menu").children('li')[3]).hide();
            $($("#multiplebox").children('div')[3]).hide();
        }
    });

    doAjaxSyn(PROJECT_NAME + "/AuthorityAreasController/findAuthorityAreasProvinceByPojo.do", {username: user.username}, function (data) {
        var redata = strToJson(data);
        if (!judgeAjaxData(redata)) {
            return;
        }
        $('#select01').html('');
        var html = '<option value="" class="" selected="selected">请选择</option>';
        for (var i = 0; i < redata.length; i++) {
            html += '<option label="' + redata[i] + '" value="'
                + redata[i] + '">' + redata[i] + '</option>';
        }
        $('#select01').append(html);
    });
});