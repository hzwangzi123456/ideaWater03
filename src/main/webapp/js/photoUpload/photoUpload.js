/**
 * Created by ziwang on 17/11/30.
 */

/**
 * 点击加载
 */
$("#reload").click(function () {
    var start = $("#start").val().trim();
    var end = $("#end").val().trim();
    start = getDateTime(start);
    start += ' 00:00:00';
    end = getDateTime(end);
    end += ' 23:59:59';
    if (start > end) {
        alert('起始时间不能大于结束时间');
        $("#start").val('');
        $("#end").val('');
        return;
    }
    $('.content .w').html('');
    //显示转圈盒子100秒
    var circleBoxDom = circle_show(100);
    doAjax(PROJECT_NAME + "PhotoController/findPhotoByTimeMap.do", {
        'startDateTime': start,
        'endDateTime': end
    }, function (data) {
        var resdata = strToJson(data);
        if (!judgeAjaxData(resdata)) {
            return;
        }
        resdata = resdata.rows;
        var html = '';
        var preDate = (new Date(resdata[0].dateTime)).Format("yyyy-MM-dd");
        for (var i = 0; i < resdata.length; i++) {
            if (i == 0) {
                html += '<div class="divimg" id="' + (new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") + '"><p>' + (new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") + '</p>';
            }
            if ((new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") != preDate) {
                preDate = (new Date(resdata[i].dateTime)).Format("yyyy-MM-dd");
                html += '<div class="cb"></div></div>';
                html += '<div class="divimg" id="' + (new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") + '"><p>' + (new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") + '</p>';
            }
            html += '<div class="imgunit fl">';
            html += '<div class="introduce">' + resdata[i].introduce + '</div>';
            html += '<a href="javascript:showImg(\'' + resdata[i].base64String + '\')" target="_blank">';
            html += '<img src="' + resdata[i].base64String + '" alt="' + resdata[i].id + '">';
            html += '</a>';
            html += '</div>';
        }
        html += '<div class="cb"></div></div>';
        $('.content .w').append(html);
        //手动关闭转圈盒子
        circle_hide(circleBoxDom);
    });
});

/**
 * 显示图片放大图
 * @param src img的src
 */
function showImg(src) {
    var screenHeight = parseInt($(window).height());
    var screenWidth = parseInt($(window).width());
//	var $img = $("<img src = ' + src + '/>");
    $('.showImg').find('.picture').remove();
    var html = '<img class="picture" src="' + src + '" alt="">';
    $('.showImg').append(html);
    $('.showImg').css('display', 'block');
    var width = $('.showImg .picture').width();
    var height = $('.showImg .picture').height();
    $('.showImg').css('display', 'none');
//	$('.showImg .picture').attr('src',str);
//	$('.showImg').css('display','block');
//	$('.showImg').css('display','none');
//	//如果只有一个超，以超的边缩小，如果两个都超，以放大比高的边缩小。
    if (width > screenWidth && height > screenHeight) {
        var ratWidth = width / screenWidth;
        var ratHeight = height / screenHeight;
        if (ratWidth > ratHeight) {
            width = screenWidth;
            height = height / ratWidth;
        } else {
            height = screenHeight;
            width = width / ratHeight;
        }
    } else if (width > screenWidth && height <= screenHeight) {
        var rat = width / screenWidth;
        width = screenWidth;
        height = height / rat;
    } else if (width <= screenWidth && height > screenHeight) {
        var rat = height / screenHeight;
        height = screenHeight;
        width = width / rat;
    }
    var marginLeft = (screenWidth - width) / 2;
    var marginTop = (screenHeight - height) * 0.2;
    $('.showImg .picture').width(width);
    $('.showImg .picture').height(height);
    $('.showImg .picture').css('margin-left', marginLeft);
    $('.showImg .picture').css('margin-top', marginTop);
    $('.showImg').css('display', 'block');
}

/**
 * 点击大图关闭按钮
 */
$('.showImg .close').click(function () {
    $('.showImg').css('display', 'none');
});


$(function () {
    //向后台发送ajax请求
    $(".header").load("../controllerCommon/header.html");
    $(".nav").load("../controllerCommon/nav.html");

    //未登录用户返回首页
    doAjaxSyn(PROJECT_NAME + "/UserController/findSessionByLogin.do", {}, function (data) {
        var redata = strToJson(data);
        if (!judgeAjaxData(redata)) {
            window.location.href = PROJECT_NAME + INDEX_NAME;
            return;
        }
        user = redata;
    });
    /*
     doAjax(PROJECT_NAME + "PhotoController/findPhotoByPojo.do", {}, function (data) {
     var resdata = strToJson(data);
     if (!judgeAjaxData(resdata)) {
     return;
     }

     $('.content .w').html('');
     var html = '';
     var preDate = (new Date(resdata[0].dateTime)).Format("yyyy-MM-dd");
     for (var i = 0; i < resdata.length; i++) {
     if (i == 0) {
     html += '<div class="divimg" id="' + (new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") + '"><p>' + (new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") + '</p>';
     }
     if ((new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") != preDate) {
     preDate = (new Date(resdata[i].dateTime)).Format("yyyy-MM-dd");
     html += '<div class="cb"></div></div>';
     html += '<div class="divimg" id="' + (new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") + '"><p>' + (new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") + '</p>';
     }
     html += '<div class="imgunit fl">';
     html += '<div class="introduce">' + resdata[i].introduce + '</div>';
     html += '<a href="javascript:showImg(\'' + resdata[i].base64String + '\')" target="_blank">';
     html += '<img src="' + resdata[i].base64String + '" alt="' + resdata[i].id + '">';
     html += '</a>';
     html += '</div>';
     }
     html += '<div class="cb"></div></div>';
     $('.content .w').append(html);
     });
     */
});