/**
 * Created by ziwang on 17/11/17.
 */
/**
 * 轮播图函数
 * @param varNum 图的数目
 * @param varWidth 图的宽度
 */
function wheelPictureFunction(varNum,varWidth) {
    var start = 0;
    var end = 0;//1200 结束px数
    var num = varNum;
    var width = varWidth;
    var maxEnd = num * width;
    var timer = null;
    var currentIndex = 0;
    //缓动运动
    timer = setInterval(function () {
        start = start + (end - start) / 10;
        $('ul').css('left', start * -1);
    }, 70);
    $('.box').mouseenter(function () {
        $('.arrow').css('display','block');
    });
    $('.box').mouseleave(function () {
        $('.arrow').css('display','none');
    });
    $('.circle span').each(function(index){
        var me = this;
        $(this).mouseover(function(){
//          $(me).siblings().removeClass('current');
//          $(me).addClass('current');
            currentIndex = index;
            changeCurrent(index);
            start = end;
            end = index * width;
        });
    });
}

/**
 * 改变小圆点状态
 * @param index 小圆点的index值
 */
function changeCurrent(index) {
    $('.circle span').removeClass('current');
    $($('.circle span')[index]).addClass('current');
}