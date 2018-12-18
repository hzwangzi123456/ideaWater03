/**
 * Created by ziwang on 17/9/25.
 */
var start = 0;
var end = 0;
var step = 370 * 2;
var currentIndex = 0;//记录当前原点下标
var timer = null;//自动切换图

setInterval(function () {//缓动运动
    start = start + (end - start) / 10;
    $('ul').css('left', start * -1);
}, 70);

function changeCurrent(index) {//改变小圆点状态
    $('.circle span').removeClass('current');
    $($('.circle span')[index]).addClass('current');
    currentIndex = index;
    end = index * step;
}

$(".circle span").each(function (index) {//小圆点事件
    var me = this;
    $(me).mouseover(function () {//小圆点的悬停事件
        changeCurrent(index);
        clearInterval(timer);
    });
    $(me).mouseout(function () {//小圆点失去悬停的事件
        timer = setInterval(function () {
            currentIndex++;
            if (currentIndex == 4) {
                currentIndex = 0;
            }
            changeCurrent(currentIndex);
        }, 6000);
    });
});

$('.content .inputWrap .name').focus(function () {//账号框焦点事件
    var i = $(this).parent().children('i');

    $(this).css('border-color','#4CB59B');
    $(i).css('color','#4CB59B');
});

$('.content .inputWrap .name').blur(function () {//账号框失去焦点事件
    var i = $(this).parent().children('i');

    $(this).css('border-color','#cacaca');
    $(i).css('color','#cacaca');
});

$('.content .inputWrap .password').focus(function () {//密码框焦点事件
    var i = $(this).parent().children('i');

    $(this).css('border-color','#4CB59B');
    $(i).css('color','#4CB59B');
});

$('.content .inputWrap .password').blur(function () {//账号框失去焦点事件
    var i = $(this).parent().children('i');

    $(this).css('border-color','#cacaca');
    $(i).css('color','#cacaca');
});

setInterval(function () {//监听账号框和密码框
    var name = $('.content .inputWrap .name');
    var password = $('.content .inputWrap .password');
    if($(name).val() != "" && $(password).val() != "") {
        $('.submit').css("background-color","#209e85");
        $('.submit').css("color","#f3f6f5");
        $('.submit').css("box-shadow","0 8px 8px 0 rgba(51,51,51,.05), 0 0 8px 0 rgba(51,51,51,.15)");
        $('.submit').css("cursor","pointer");
    }else {
        $('.submit').css("background-color","#eee");
        $('.submit').css("color","#bebebe");
        $('.submit').css("box-shadow","none");
        $('.submit').css("cursor","not-allowed");
    }
},10);

timer = setInterval(function () {//自动播放轮播图
    currentIndex++;
    if (currentIndex == 4) {
        currentIndex = 0;
    }
    changeCurrent(currentIndex);
}, 5000);

$("#submit").click(function(){//点击登录按钮
	var name = $('.content .inputWrap .name');
    var password = $('.content .inputWrap .password');
    if($(name).val() == "" || $(password).val() == "") {
    	return;
    }
	doAjaxSyn(PROJECT_NAME + "/UserController/login.do",{username:$(name).val(),password:$(password).val()},function(data){
		var redata = strToJson(data);
		if(!judgeAjaxData(redata)) {
			$(name).val("");
			$(password).val("");
			alert("账号密码错误");
			return;
		}
		window.location.href = "index.html";
    });
});

$("#pageTitle").click(function(){//大标题点击事件
	window.location.href = "index.html";
});


$(function () {
    $(".header").load("controllerCommon/header.html");
});

