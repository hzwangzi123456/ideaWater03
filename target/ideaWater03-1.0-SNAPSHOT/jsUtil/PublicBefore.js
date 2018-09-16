var PROJECT_NAME = "/ideaWater03/";
var LOGIN_NAME = "html/xinan/login.html";
var INDEX_NAME = "html/index.html";
// var WUZHEN_LOGIN_NAME = "html/wuzhen/login.html";
// var URL_NEW = "http://localhost:8080/watermonitor/";

//var research02_modal = '<div id="research02_modal" style="display:block;position:absolute; top:0 ; right:0;bottom:0;left:0;z-index:99;"><img style="position:absolute;top: 50%;left: 50%;transform:translate(-50%,-50%);" src="../img/timg.gif" alt=""/></div>';

/**
 * 在body内prepend一个转圈
 * @param seconds 转圈盒子seconds秒后消失
 * @returns {*} 转圈盒子dom
 */
function circle_show(seconds) {
    var modal =
        '<div style="display:block;position:absolute; top:0 ; right:0;bottom:0;left:0;z-index:99;">' +
        '<img style="position:absolute;top: 50%;left: 50%;width:40px;height:40px;transform:translate(-50%,-50%);" ' +
        'src="../../img/timg.gif" alt=""/>' +
        '</div>';
    $("body").prepend(modal);
    var firstDivDom = $("body").children("div")[0];
    var timeoutId = setTimeout(function () {
        $(firstDivDom).remove();
    }, seconds * 1000);
    return firstDivDom;
}

/**
 * 手动关闭转圈
 * @param modalId 转圈盒子dom
 */
function circle_hide(dom) {
    $(dom).remove();
}

//重写 alert 不显示ip地址
window.alert = function alertw(name) {
    var iframe = document.createElement("IFRAME");
    iframe.style.display = "none";
    iframe.setAttribute("src", 'data:text/plain,');
    document.documentElement.appendChild(iframe);
    window.frames[0].window.alert(name);
    iframe.parentNode.removeChild(iframe);
}

/**
 * dataType为text格式的异步ajax请求
 * @param urlStr 请求资源地址
 * @param jsonData js对象
 * @param succFun 成功回调方法
 */
function doAjax(urlStr, jsonData, succFun) {//异步
    $.ajax({
        type: "POST",
        dataType: "text",
        url: urlStr,
        traditional: true,
        data: jsonData,
        success: succFun
    });
}

/**
 * dataType为text格式的同步ajax请求
 * @param urlStr 请求资源地址
 * @param jsonData js对象
 * @param succFun 成功回调方法
 */
function doAjaxSyn(urlStr, jsonData, succFun) {//同步
    $.ajax({
        type: "POST",
        dataType: "text",
        url: urlStr,
        async: false,
        traditional: true,
        data: jsonData,
        success: succFun
    });
}

/**
 * dataType为json格式的异步ajax请求
 * @param urlStr 请求资源地址
 * @param jsonData js对象
 * @param succFun 成功回调方法
 */
function doAjaxJson(urlStr, jsonData, succFun) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: urlStr,
        traditional: true,
        data: jsonData,
        success: succFun
    });
}

/**
 * dataType为json格式的同步ajax请求
 * @param urlStr 请求资源地址
 * @param jsonData js对象
 * @param succFun 成功回调方法
 */
function doAjaxSynJson(urlStr, jsonData, succFun) {
    $.ajax({
        type: "POST",
        //response的数据类型
        dataType: "json",
        url: urlStr,
        async: false,
        traditional: true,
        data: jsonData,
        success: succFun
    });
}

/**
 * JSON格式字符串转js对象
 * @param v JSON格式字符串
 * @returns {*} js对象
 */
function strToJson(v) {
    var json_obj = null;
    try {
        json_obj = jQuery.parseJSON(v);
    } catch (e) {
        json_obj = $.parseJSON(v);
    } finally {
        return json_obj;
    }
}

/**
 * 看后台发送了什么ajax响应
 * @param redata ajax响应
 * @returns {boolean} true:发送成功,false:发生了失败的响应或者未发送响应
 */
function judgeAjaxData(redata) {
    if (redata == null) {
        alert("后台未发送ajax响应");
        return false;
    }
    if ("isSucced" in redata == true && redata.isSucced == false) {
        alert(redata.msg);
        return false;
    }
    if ("isSucced" in redata == true && redata.isSucced == true) {
        console.log(redata.msg);
        return true;
    }
    return true
}


/**
 * 日期转换 2017年11月01日 -->  2017-11-01
 * @param dateStringstr 2017年11月01日
 * @returns {string} 2017-11-01
 */
function getDateTime(dateString) {
    var resultDateString = '';
    var year = dateString.substring(0, 4);
    var month = dateString.substring(5, 7);
    var day = dateString.substring(8, 10);
    resultDateString = year + '-' + month + '-' + day;
    return resultDateString;
}
