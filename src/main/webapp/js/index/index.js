/**
 * Created by ziwang on 17/11/2.
 */
function showImg(src){
    var screenHeight = parseInt($(window).height());
    var screenWidth = parseInt($(window).width());
//	var $img = $("<img src = ' + src + '/>"); 
    $('.showImg').find('.picture').remove();
    var html = '<img class="picture" src="' + src + '" alt="">';
    $('.showImg').append(html);
    $('.showImg').css('display','block');
    var width = $('.showImg .picture').width();
    var height = $('.showImg .picture').height();
    $('.showImg').css('display','none');
//	$('.showImg .picture').attr('src',str);
//	$('.showImg').css('display','block');
//	$('.showImg').css('display','none');
//	//如果只有一个超，以超的边缩小，如果两个都超，以放大比高的边缩小。
    if(width > screenWidth && height > screenHeight) {
        var ratWidth = width / screenWidth;
        var ratHeight = height / screenHeight;
        if(ratWidth > ratHeight) {
            width = screenWidth;
            height = height / ratWidth;
        }else {
            height = screenHeight;
            width = width / ratHeight;
        }
    }else if(width > screenWidth && height <= screenHeight) {
        var rat = width / screenWidth;
        width = screenWidth;
        height = height / rat;
    }else if(width <= screenWidth && height > screenHeight) {
        var rat = height / screenHeight;
        height = screenHeight;
        width = width / rat;
    }
    var marginLeft = (screenWidth - width) / 2;
    var marginTop = (screenHeight - height) * 0.2;
    $('.showImg .picture').width(width);
    $('.showImg .picture').height(height);
    $('.showImg .picture').css('margin-left',marginLeft);
    $('.showImg .picture').css('margin-top',marginTop);
    $('.showImg').css('display','block');
}


/**
 * 关闭图片具体显示
 */
$('.showImg .close').click(function(){
    $('.showImg').css('display','none');
});
