// 请求数据
var resdata = null;
var username = "";
// 所属省份加上改变事件
$('#select01')
    .change(
        function () {
            doAjaxSyn(
                PROJECT_NAME
                + "/AuthorityAreasController/findAuthorityAreasByPojo.do",
                {
                    province: $('#select01').val(),
                    username: username
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

//根据设备Id,起始日期，结束日期,返回结果数组
function getChartDatas(instrumentId, start, end) {
    doAjaxSyn(PROJECT_NAME + "YascmfDatasController/findYascmfDatasByTime.do", {
        instrumentId: instrumentId,
        start: start,
        end: end
    }, function (data) {
        redata = strToJson(data);
        if (redata == null) {
            console.log("后台未发送ajax响应");
            return;
        }
        if ("isSucced" in redata == true && redata.isSucced == false) {
            alert(redata.msg);
            return;
        }
        circle_hide("research02_modal");
    });
    return redata;
}

// 点击加载
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
    echarts.dispose(document.getElementById("placeholder"));
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
    init(resdata, 'placeholder');
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

// 单双图表切换
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

// 根据请求到的数据初始化echart
function init(tempdata, id) {
    var myChart = echarts.init(document.getElementById(id));
    option.xAxis[0].data = getDateTime(tempdata);
    option.series[0].data = getPh(tempdata);
    option.series[1].data = getWaterTemperature(tempdata);
    option.series[2].data = getAmmoniaNitrogen(tempdata);
    option.series[3].data = getP(tempdata);
    option.series[4].data = getConductivity(tempdata);
    option.series[5].data = getDissolvedOxygen(tempdata);
    option.series[6].data = getNTU(tempdata);
    myChart.setOption(option);
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
        P[i] = obj[i].P;
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

// 得到时间数组
function getDateTime(obj) {
    var date_time = new Array();
    for (var i = 0; i < obj.length; i++) {
        var date = new Date(obj[i].dateTime);
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = addZero(date.getDate());
        var hour = addZero(date.getHours());
        var minute = addZero(date.getMinutes());
        var second = date.getSeconds();
        date_time[i] = month + '月' + day + '日' + hour + ':' + minute;
    }
    return date_time;
}

//传入一个数字,如果数字>=0&& <=9,加前导0
function addZero(num) {
    if(num >= 0 && num <= 9) {
        return "0" + num;
    }
    return num;
}

$(function () {
    doAjaxSyn(PROJECT_NAME + "/UserController/findSessionByLogin.do", {}, function (data) {
        var redata = strToJson(data);
        if (redata == null) {
            console.log("后台未发送ajax响应");
            return;
        }
        if ("isSucced" in redata == true && redata.isSucced == false) {
            console.log(redata.msg);
            return;
        }
        username = redata.username;
    });

    doAjaxSyn(PROJECT_NAME + "/AuthorityAreasController/findAuthorityAreasProvinceByPojo.do", {username: username}, function (data) {
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

//	doAjaxSyn(
//			PROJECT_NAME + "/YascmfAreasController/findYascmfAreasCity.do",
//			{},
//			function(data) {
//				var redata = strToJson(data);
//				if (redata == null) {
//					console.log("后台未发送ajax响应");
//					return;
//				}
//				if ("isSucced" in redata == true && redata.isSucced == false) {
//					console.log(redata.msg);
//					alert("未找到数据");
//					return;
//				}
//				$('#select01').html('');
//				var html = '<option value="" class="" selected="selected">请选择</option>';
//				for ( var i = 0; i < redata.length; i++) {
//					html += '<option label="' + redata[i] + '" value="'
//							+ redata[i] + '">' + redata[i] + '</option>';
//				}
//				$('#select01').append(html);
//			});
});
