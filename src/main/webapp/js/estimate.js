$(function () {
    var arr = $('.cage-name').children();
    for(var i = 0;i < arr.length;i ++) {
        $(arr[i]).find('ul').css('display','none');
        $(arr[i]).find('a').click(function () {
            $(this).parent().siblings().find('ul').css('display','none');
            $(this).next().css('display','block');
        });
    }
    var a_arr = $('.lv2');
    console.log(a_arr);
    for(var i = 0;i < a_arr.length;i ++) {
        // var text = $(a_arr[i]).text();
        $(a_arr[i]).click(function () {
            $('.post-content').html('');
            var iframe = '<iframe id="iframe01" class="login-dialog" src=' + 'text/' + $(this).attr('id') + '.html' + ' width="100%" height="100%"></iframe>';
            $('.post-content').append(iframe);
        });
    }
    $('.post-content').html('');
    $('.post-content').append($(a_arr[0]).text());
});