/**
 * Created by ziwang on 17/8/5.
 */
$(function(){
	doAjax(PROJECT_NAME + "PhotoController/findPhotoByPojo.do",{
	},function(data){
		var resdata = strToJson(data);
		if(resdata == null) {
			console.log("未找到数据");
			return;
		}
		if("isSucced" in resdata == true) {
			console.log(resdata.msg);
			return;
		}
		
		$('.content .w').html('');
		var html = '';
		var preDate = (new Date(resdata[0].dateTime)).Format("yyyy-MM-dd");
		for(var i = 0;i < resdata.length;i ++) {
			if(i == 0) {
				html += '<div class="divimg" id="' + (new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") + '"><p>' + (new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") + '</p>';
			}
			if((new Date(resdata[i].dateTime)).Format("yyyy-MM-dd") != preDate) {
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
		
		$('.imgunit').mouseenter(function () {
		    if ($(this).find('.check').length != 0) {
		        return;
		    }
		    $(this).find('.introduce').css('display','block');
		    var html = '<div class="opt">';
		    html += '<div class="del fl" onclick="deleteFun(this)">';
		    html += '删除';
		    html += '</div>';
		    html += '<div class="send fr" onclick="sendFun(this)">';
		    html += '发送';
		    html += '</div>';
		    html += '<div class="cb"></div>';
		    html += '</div>';
		    $(this).find('.opt').remove();
		    $(this).append(html);
		});

		$('.imgunit').mouseleave(function () {
			$(this).find('.introduce').css('display','none');
		    $(this).find('.opt').remove();
		});
	});
});

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

function deleteFun(me) {
    var html = '<div class="modal" style="position: absolute;top:0px;left:0px;width:250px;height: 250px;background-color: #666666">' +
        '<div style="position: absolute;top: 125px;left: 90px;width: 80px;height: 30px;font-size: 20px;color: white;cursor:pointer;" onclick="cancelDeleteFun(this)">撤销删除</div>' +
        '</div>';
    $(me).closest('.imgunit').append(html);
}

function sendFun(me) {
    var html = '<div class="modal" style="position: absolute;top:0px;left:0px;width:250px;height: 250px;background-color: #666666">' +
        '<div style="position: absolute;top: 125px;left: 90px;width: 80px;height: 30px;font-size: 20px;color:white;">已发送</div>' +
        '</div>';
    $(me).closest('.imgunit').append(html);
}

function cancelDeleteFun(me) {
    $(me).closest('.imgunit').find('.modal').remove();
}


$('.fixed img').click(function () {
    var str = $('.imgunit').css('border-color');
    if (str == 'rgb(255, 255, 255)') {//开启编辑模式
        $('.imgunit').css("border", "4px solid red");
        var html = '<img class="check" style="position:absolute;top:10px;right:10px;width:30px;height: 30px;background-color: white;border: 1px solid white;" src="' + '../img/check.png' + '" alt="">';
        $('.imgunit').prepend(html);
        $('.imgunit .check').css('cursor', 'pointer');
        $('.imgunit .check').click(function () {
            if ($(this).attr('src') == '../img/checked05.png') {
                $(this).attr('src', '../img/check.png');
            } else {
                $(this).attr('src', '../img/checked05.png');
            }
        });
    } else if (str == 'rgb(255, 0, 0)') {
        $('.imgunit').css('border', '4px solid white');
        $('.imgunit').find('.check').remove();
    }
});

$('.fixed .del').click(function () {
    var checkArray = $('.imgunit .check');
    var num = 0;
    for (var i = 0; i < checkArray.length; i++) {
        if ($(checkArray[i]).attr('src') == '../img/checked05.png') {
            num++;
        }
    }
    num += $('.imgunit .modal').length;
    $('#span01').text(num);
    $('.confirm').css('display','block');
});

$('.dialog .result .yes').click(function(){
	var idArray = new Array();
    var checkArray = $('.imgunit .check');
    for(var i = 0;i < checkArray.length;i ++) {
        if ($(checkArray[i]).attr('src') == '../img/checked05.png') {
            $(checkArray[i]).parent('.imgunit').remove();
            idArray.push($(checkArray[i]).siblings('a').find('img').attr('alt'));
        }
    }
    var modalArray = $('.imgunit .modal');
    for(var i = 0;i < modalArray.length;i ++) {
    	idArray.push($(modalArray[i]).siblings('a').find('img').attr('alt'));
    }
    $('.imgunit .modal').parent('.imgunit').remove();
    $('.confirm').css('display','none');
    if($('.imgunit').css('border-color') == 'rgb(255, 0, 0)') {
        $('.imgunit').css('border', '4px solid white');
        $('.imgunit').find('.check').remove();
    }
    
    doAjax(PROJECT_NAME + "PhotoController/deletePhotoByKey.do",
    		{idArray:JSON.stringify(idArray)},
    		function(data){
    			var resdata = strToJson(data);
    			if("isSucced" in resdata == true) {
    				console.log(resdata.msg);
    				return;
    			}
    });
});

$('.dialog .result .cancel').click(function(){
    $('.confirm').css('display','none');
    if($('.imgunit').css('border-color') == 'rgb(255, 0, 0)') {
        $('.imgunit').css('border', '4px solid white');
        $('.imgunit').find('.check').remove();
    }
});

$('.showImg .close').click(function(){
	$('.showImg').css('display','none');
});
