;!function(){
    //laydate.skin('molv');
    laydate({
        elem: '#demo01'
    });
    laydate({
        elem: '#demo02'
    })
}();
var arr;//数据数组

$(function(){
    var mtree = $('ul.mtree');
    mtree.addClass('jet');

    $("#colorful-elliptic").colorfulTab({
        theme: "elliptic"
    });

    doAjaxSyn(PROJECT_NAME + "YascmfInstrumentsController/findYascmfInstrumentsByPojo.do",{},function(data){
        redata = strToJson(data);
        if(redata == null) {
            console.log("未找到数据");
            return;
        }
        if("isSucced" in redata == true && redata.isSucced == false) {
            console.log(redata.msg);
            return;
        }

        $("#select01").html('');
        var html = '';
        for(var i = 0;i < redata.length;i ++) {
            html += '<option>' + redata[i].instrumentId + '</option>';
        }
        $("#select01").append(html);
    });
// 	    arr = getCartDatas("D01");
// 		init(arr,$("#ph"),"","D01",0.1,5);
});

function search(){
    var select = $("#select01").val();
    var demo01 = $("#demo01").val().trim();
    var demo02 = $("#demo02").val().trim();
    if(select == '' || select == null) {
        alert("设备不能为空");
        return;
    }
    if(demo01 == '' || demo01 == null) {
        alert("起始时间不能为空");
        return;
    }
    if(demo02 == '' || demo02 == null) {
        alert("结束时间不能为空");
        return;
    }
    if(demo01 >= demo02) {
        alert("起始时间不能大于等于结束时间");
        return;
    }
    arr = getChartDatas(select,demo01,demo02);
    fun01($('#li01'));
// 		console.log(arr);
// 		console.log(arr.length);
}

function fun01(me){//点击a标签跳转函数
    if(arr == null || arr.length == 0){
        alert("请先搜索");
        return;
    }
    initColor(arr,$($(me).find('a').attr("href")),"");
}

//根据设备Id,起始日期，结束日期,返回结果数组
function getChartDatas(instrumentId,start,end){
    var redata;
    doAjaxSyn(PROJECT_NAME + "YascmfDatasController/findYascmfDatasByTime.do",
        {instrumentId:instrumentId,start:start,end:end},function(data){
            redata = strToJson(data);
            if(redata == null) {
                console.log("后台未发送ajax响应");
                return;
            }
            if("isSucced" in redata == true && redata.isSucced == false) {
                console.log(redata.msg);
                return;
            }
        });
    return redata;
}

//根据设备ID创建Chart
//    function getCartDatas(instrumentId) {
//        var redata;
//        doAjaxSyn(PROJECT_NAME + "YascmfDatasController/findYascmfDatasByPojo.do",{instrumentId:instrumentId},function(data){
//            redata = strToJson(data);
//            if(redata == null) {
//                console.log("后台未发送ajax响应");
//                return;
//            }
//            if("isSucced" in redata == true && redata.isSucced == false) {
//                console.log(redata.msg);
//                return;
//            }
//        });
//        return redata;
//    }

function initColor(arr,even,unit){
    var id = even[0];
    echarts.dispose(even[0]);
    var mychart = echarts.init(even[0]);
//      option.xAxis[0].data = getDateTime(arr);
    option02.xAxis[0].data = getDateTime(arr);
    if(even.attr('id') == "ph"){
        option02.legend = {data:['PH']};
        option02.series = [ {
            name : 'PH',
            type : 'line',
            stack : 'ph值',
            data : new Array()
        } ];
        option02.series[0].data = getPh(arr);
    }else if(even.attr('id') == "temperature"){
        option02.legend = {data:['水温']};
        option02.series = [ {
            name: '水温',
            type: 'line',
            stack: 'T(℃)',
            data: new Array()
        }];
        option02.series[0].data = getWaterTemperature(arr);
    }else if(even.attr('id') == "conductivity"){
        option02.legend = {data:['电导率']};
        option02.series = [ {
            name : '电导率',
            type : 'line',
            stack : 'CDT(us/cm)',
            data : new Array()
        } ];
        option02.series[0].data = getConductivity(arr);
    }else if(even.attr('id') == "nitrogen"){
        option02.legend = {data:['氨氮']};
        option02.series = [ {
            name: '氨氮',
            type: 'line',
            stack: 'N(mg/L)',
            data: new Array()
        }];
        option02.series[0].data = getAmmoniaNitrogen(arr);
    }else if(even.attr('id') == "dissolved_oxygen"){
        option02.legend = {data:['溶解氧']};
        option02.series = [ {
            name: '溶解氧',
            type: 'line',
            stack: 'DO(mg/L)',
            data: new Array()
        }];
        option02.series[0].data = getDissolvedOxygen(arr);
    }else if(even.attr('id') == "ntu"){
        option02.legend = {data:['浊度']};
        option02.series = [ {
            name: '浊度',
            type: 'line',
            stack: 'NTU',
            data: new Array()
        }];
        option02.series[0].data = getNTU(arr);
    }else if(even.attr('id') == "P"){
        option02.legend = {data:['总磷']};
        option02.series = [ {
            name: '总磷',
            type: 'line',
            stack: 'P(mg/L)',
            data: new Array()
        }];
        option02.series[0].data = getP(arr);
    }
    if(unit != "" && unit != null){
        option.series[0].name += "(" + unit + ")";
    }
//        mychart.setOption(option);
    mychart.setOption(option02);
    return mychart;
}

function getPh(obj) {
    var ph = new Array();
    for(var i = 0; i < obj.length; i++) ph[i] = obj[i].ph;
    return ph;
}

function getConductivity(obj) {
    var conductivity = new Array();
    for(var i = 0; i < obj.length; i++) conductivity[i] = obj[i].conductivity;
    return conductivity;
}

function getWaterTemperature(obj) {
    var water_temperature = new Array();
    for(var i = 0; i < obj.length; i++) water_temperature[i] = obj[i].waterTemperature;
    return water_temperature;
}

function getAmmoniaNitrogen(obj) {
    var ammonia_nitrogen = new Array();
    for(var i = 0; i < obj.length; i++) ammonia_nitrogen[i] = obj[i].ammoniaNitrogen;
    return ammonia_nitrogen;
}

function getDissolvedOxygen(obj) {
    var dissolved_oxygen = new Array();
    for(var i = 0; i < obj.length; i++) dissolved_oxygen[i] = obj[i].dissolvedOxygen;
    return dissolved_oxygen;
}

function getNTU(obj) {
    var ntu = new Array();
    for(var i = 0; i < obj.length; i++) ntu[i] = obj[i].ntu;
    return ntu;
}

function getP(obj) {
    var P = new Array();
    for(var i = 0; i < obj.length; i++) P[i] = obj[i].p;
    return P;
}

function getDateTime(obj) {
    var date_time = new Array();
    for(var i = 0; i < obj.length; i++ ){
        var date = new Date(obj[i].dateTime);
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDate();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();
        date_time[i] = month + '月' + day + '日' + hour + ':' + minute ;
    }
    return date_time;
}