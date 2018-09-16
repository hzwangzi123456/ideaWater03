/**
 * Created by ziwang on 17/10/31.
 */
function keyLogin() {
    if (event.keyCode==13) {//回车键的键值为13
        $("#submit").trigger("click");
    }
}

$('.inputWrapUsername .name').focus(function () {//账号框焦点事件
    var i = $(this).parent().children('i');
    $(this).css('border-color', '#4CB59B');
    $(i).css('color', '#4CB59B');
});

$('.inputWrapUsername .name').blur(function () {//账号框失去焦点事件
    var i = $(this).parent().children('i');
    $(this).css('border-color', '#cacaca');
    $(i).css('color', '#cacaca');
});

$('.inputWrapPassword .password').focus(function () {//密码框焦点事件
    var i = $(this).parent().children('i');
    $(this).css('border-color', '#4CB59B');
    $(i).css('color', '#4CB59B');
});

$('.inputWrapPassword .password').blur(function () {//账号框失去焦点事件
    var i = $(this).parent().children('i');
    $(this).css('border-color', '#cacaca');
    $(i).css('color', '#cacaca');
});

$("#submit").click(function(){//点击登录按钮
    var name = $('.inputWrapUsername .name');
    var password = $('.inputWrapPassword .password');
    if($(name).val() == "" || $(password).val() == "") {
        alert("账号或者密码不能为空");
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
        window.location.href = "../map/map.html";
    });
});