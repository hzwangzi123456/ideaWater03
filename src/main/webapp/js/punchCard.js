/**
 * Created by ziwang on 17/8/17.
 */
var DAY = [0,31,28,31,30,31,30,31,31,30,31,30,31];
var LEAPDAY = [0,31,29,31,30,31,30,31,31,30,31,30,31];
var Total = 0;// 总记录数
var MAP = {};// 日期 -> status
var MAPworkHour = {};// 日期 -> workHour
var MAPoffHour = {};// 日期 -> offHour
var pageObject = {
    current:1,
    last:1000,
    init:function(last){
        this.last = last;
        this.current = 1;
    },
    show:function(current){// 返回显示数组，-1表示...
        var butArray = new Array();
        var last = this.last;
        var current = current;
        butArray.push(1);
        if(current - 1 >= 7) {// 需要加入前...
            butArray.push(-1);
            var n = 9;
            current -= 4;
            while(n--) {
                if(current > last) {
                    break;
                }
                butArray.push(current++);
            }
            if(last - current >= 2) {// 需要加入后...
                butArray.push(-1);
                butArray.push(last);
            } else {// 不需要加入后..
                for(var i = current;i <= last;i ++) {
                    butArray.push(i);
                }
            }
        } else {// 不需要加入前...
            var n = 7;
            var value = 2;
            while(n--) {
                if(value > last) {
                    break;
                }
                butArray.push(value++);
            }
            var lastofbutArray = butArray[butArray.length - 1];
            if(lastofbutArray < last) {
                butArray.push(-1);
                butArray.push(last);
            }
        }
        return butArray;
    },
    next:function(){
        var current = this.current;
        var last = this.last;
        current ++;
        if(current > last) {
            return null;
        }
        this.current = current;
        return this.show(current);
    },
    prev:function(){
        var current = this.current;
        current -- ;
        if(current == 0) {
            return null;
        }
        this.current = current;
        return this.show(current);
    },
    chartshow:function () {
        $('.paging').html('');
        var butArray = pageObject.show(this.current);
        var html = '<ul>';
        html += '<li onclick="pageObject.clickprev()" class="btpre btlist">' + '<' + '</li>';
        for(var i = 0;i < butArray.length;i ++) {
            if(butArray[i] == this.current) {
                html += '<li onclick="pageObject.clickshow(this)" class="btlist btactive">' + butArray[i] + '</li>';
                continue;
            }
            if(butArray[i] == -1) {
                html += '<li class="btlist">' + '...' + '</li>';
            } else {
                html += '<li onclick="pageObject.clickshow(this)" class="btlist">' + butArray[i] + '</li>';
            }
        }
        html += '<li onclick="pageObject.clicknext()" class="btlist">' + '>' + '</li>';
        html += '</ul>';
        $('.paging').append(html);
        
        $('.recordcontent').html('');
        doAjax(PROJECT_NAME + "RecordController/findRecordByPage.do",{
        	page : this.current,
			rows : 6,
			total: Total,
			sort : 'dateTime',
			order : 'ASC'
        },function(data){
        	var resdata = strToJson(data);
        	if(resdata == null) {
        		console.log("后台没有发送数据");
        		return;
        	}
        	if(typeof resdata.msg != 'undefined') {
        		console.log(resdata.msg);
        	}
        	var unitArray = resdata.rows;
        	var html = '';
        	for(var i = 0;i < unitArray.length;i ++) {
        		html += '<div class="unit">';
        		html += '<img class="fl" src="../img/head.png" alt="">';
        		html += '<div class="info fl">';
        		html += '<p><span>' + unitArray[i].username + '</span>的第<span style="color: #209e85;font-size:18px;">' + unitArray[i].recordDay + '</span>天打卡:</span>';
        		html += '<p>上班时间: <span>' + unitArray[i].workHour + '</span>,下班时间: <span>' + unitArray[i].offHour + '</span>';
        		if(unitArray[i].status == 1) {
        			html += ' 状态：<span style="color:#4DC3A9">正常</span></p>';
        		}else if(unitArray[i].status == 2) {
        			html += ' 状态：<span style="color:#FF0000">迟到</span></p>';
        		}else if(unitArray[i].status == 3){
        			html += ' 状态：<span style="color:#EEDC82">早退</span></p>';
        		}
        		html += '<p>' + unitArray[i].dateTime + '</p>';
        		html += '</div>';
        		html += '<div class="cb"></div>';
        		html += '</div>';
        	}
        	$('.recordcontent').append(html);
        });
    },
    clickshow:function (me) {
        this.current = $(me).text();
        this.chartshow();
    },
    clicknext:function () {
        var current = this.current;
        var last = this.last;
        current ++;
        if(current > last) {
            this.current = last;
        }
        this.current = current;
        this.chartshow();
    },
    clickprev:function () {
        var current = this.current;
        current --;
        if(current == 0) {
            current = 1;
        }
        this.current = current;
        this.chartshow();
    }
};

// $(tdArray[i]).append('<div class="tdUndefined">' + preTot + '</div>');
function tdAppendDiv($td,year,month,day){
	var status = MAP[transform(year, month, day)];
	$td.html('');
	if(status == 'undefined' || status == undefined) {// 未打卡
		$td.append('<div class="tdUndefined">' + day + '</div>');
		return;
	}
	if(status == 1) {// 正常
		$td.append('<div class="tdGreen">' + day + '</div>');
	} else if(status == 2) {// 迟到
		$td.append('<div class="tdRed">' + day + '</div>');
	} else if(status == 3) {// 早退
		$td.append('<div class="tdYellow">' + day + '</div>');
	}
	return;
}

// 2015-4-5 -> 2015-04-05
function transform(year,month,day){
	var year = parseFloat(year);
	var month = parseFloat(month);
	var day = parseFloat(day);
	if(month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if(day >= 1 && day <= 9) {
		day = "0" + day;
	}
	return year + "-" + month + "-" + day;
}

function judgeLEAP(year) { // 闰年返回true，否则返回false
    if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {// 是闰年
        return true;
    }
    return false;
}

// 返回某年某月有几天
function lookDAY(year,month) {
    year = parseFloat(year);
    month = parseFloat(month);
    if(judgeLEAP(year)) {
        return LEAPDAY[month];
    }
    return DAY[month];
}

// 根据年份和月份返回第一天是星期几（1，7）
function lookfirstDay(year,month) {
    var date = new Date(year + '/' + month + '/01');
    if(date.getDay() == 0) {
        return date.getDay() + 7;
    }
    return date.getDay();
}

$('.pre').click(function () {
    $(this).css('background-color','#209E85');
    $(this).css('color','#FFFFFF');
    var me = this;
    setTimeout(
        function () {
            $(me).css('background-color','#E4E4E4');
            $(me).css('color','#666');
        },300
    );
    var spanArray = $(this).next('.title').find('span');
    var month = $(spanArray[1]).text();
    var year = $(spanArray[0]).text();
    month -- ;
    if(month == 0) {
        month = 12;
        year -- ;
    }
    if(month >= 1 && month <= 9) {
        month = "0" + month;
    }
    $(spanArray[1]).text(month + " ");
    $(spanArray[0]).text(year + " ");
    chart(year,month);
});

// 点击下一个月
$('.next').click(function () {
    $(this).css('background-color','#209E85');
    $(this).css('color','#FFFFFF');
    var me = this;
    setTimeout(
        function () {
            $(me).css('background-color','#E4E4E4');
            $(me).css('color','#666');
        },300
    );
    var spanArray = $(this).prev('.title').find('span');
    var month = $(spanArray[1]).text();
    var year = $(spanArray[0]).text();
    month ++ ;
    if(month == 13) {
        month = 1;
        year ++ ;
    }
    if(month >= 1 && month <= 9) {
        month = "0" + month;
    }
    $(spanArray[1]).text(month + " ");
    $(spanArray[0]).text(year + " ");
    chart(year,month);
});

// 写出当年当月的日历
function chart(year,month){
    var currentYear = parseFloat(year);
    var currentMonth = parseFloat(month);
    var currentTot = lookDAY(currentYear,currentMonth);// 该月总天数
    var preYear = currentYear;
    var preMonth = currentMonth - 1;
    if(preMonth == 0) {
        preMonth = 12;
        preYear -- ;
    }
    var preTot = lookDAY(preYear,preMonth);// 前月总天数
    var firstDay = lookfirstDay(currentYear,currentMonth);// 得到该月第一天的星期(0,6)
    firstDay -- ;// 星期一对应的下标是0
    var tdArray = $('tbody td');
    $('tbody td').css('color','#333').css('cursor', 'pointer');
    for(var i = firstDay - 1;i >= 0;i --) {
    	$(tdArray[i]).text(preTot).css('color','#bbb').css('cursor','not-allowed');
        preTot --;
    }
    var nextDay = 42 - currentTot + firstDay;// 下个月需要显示的天数
    var num = 1;
    while(currentTot --) {
        tdAppendDiv($(tdArray[firstDay]), currentYear, currentMonth, num);
        firstDay ++;
        num ++;
    }
    num = 1;
    while(nextDay --) {
        $(tdArray[firstDay]).text(num).css('color','#bbb').css('cursor','not-allowed');
        firstDay ++;
        num ++;
    }
}

$('.content .nav li').click(function () {// 导航条切换 css切换
    $('.content .nav li').css("text-indent",0).css("color","#555");
    $(this).css("text-indent","50px").css("color","#5F9EA0");
});

$('#navcalendar').click(function () {
    $('.calendar').css('display','block');
    $('.detail').css('display','block');
    $('.record').css('display','none');
});

$('#navrecorder').click(function () {
    $('.calendar').css('display','none');
    $('.detail').css('display','none');
    $('.record').css('display','block');
});

$('.calendar tbody td').click(function(){// 日历上每个点都可以点击
    var res = $(this).find('div');
    if(res.length == 0) {
    	return;
    }
    var spanArray = $('.calendar table .title').find('span');
    var date = transform($(spanArray[0]).text(), $(spanArray[1]).text(), $(this).text());
    var pArray = $('.detail p');
    var spanArray02 = $(pArray[1]).find('span');
    $(spanArray02[0]).text($(spanArray[0]).text());
    $(spanArray02[1]).text($(spanArray[1]).text());
    $(spanArray02[2]).text($(this).text());
    
    if(MAP[date] == undefined || MAP[date] == "undefined") {
    	$($(pArray[4]).find('span')[0]).text('未打卡').css('color','#00BFFF');	
//    	$($(pArray[4]).find('span')[0]);
    	$($(pArray[2]).find('span')[0]).text("00:00");
        $($(pArray[3]).find('span')[0]).text("00:00");
        return;
    }
    if(MAP[date] == 1) {
    	$($(pArray[4]).find('span')[0]).text('正常').css('color','#4dc3a9');
//    	$($(pArray[4]).find('span')[0]);
    	$($(pArray[2]).find('span')[0]).text(MAPworkHour[date]);
    	$($(pArray[3]).find('span')[0]).text(MAPoffHour[date]);
    	return;
    }
    if(MAP[date] == 2) {
    	$($(pArray[4]).find('span')[0]).text('迟到').css('color','#ff0000');
//    	$($(pArray[4]).find('span')[0]);
    	$($(pArray[2]).find('span')[0]).text(MAPworkHour[date]);
    	$($(pArray[3]).find('span')[0]).text(MAPoffHour[date]);
    	return;
    }
    if(MAP[date] == 3) {
    	$($(pArray[4]).find('span')[0]).text('早退').css('color','#eede8e');
//    	$($(pArray[4]).find('span')[0]);
    	$($(pArray[2]).find('span')[0]).text(MAPworkHour[date]);
    	$($(pArray[3]).find('span')[0]).text(MAPoffHour[date]);
    	return;
    }
});

$(function(){
	Total = 0;
	doAjax(PROJECT_NAME + "RecordController/findRecordByPojo.do", {},function(data){
		var resdata = strToJson(data);
		if (resdata == null) {
			console.log("后台未发送response");
			return;
		}
		if(typeof resdata.msg != 'undefined') {
			console.log(resdata.msg);
		}
		resdata = resdata.rows;
		if(resdata.length % 6 != 0) {
			 pageObject.init(Math.ceil(resdata.length / 6 ));
		} else {
			pageObject.init(resdata.length / 6);
		}
		pageObject.chartshow();
		Total = resdata.length;
		$('.calendar .days span').text(Total);
		MAP = {};
		MAPworkHour = {};
		MAPoffHour = {};
		for(var i = 0;i < resdata.length;i ++) {
			MAP[resdata[i].dateTime] = resdata[i].status;
			MAPworkHour[resdata[i].dateTime] = resdata[i].workHour;
			MAPoffHour[resdata[i].dateTime] = resdata[i].offHour;
		}
	});
});

$(function () {
    var date = new Date();
    chart(date.getFullYear(),date.getMonth() + 1);
    var spanArray = $('.calendar .title').find('span');
    $(spanArray[0]).text(date.getFullYear() + " ");
    var month = date.getMonth() + 1;
    if(month >= 1 && month <= 9) {
        month = "0" + month;
    }
    $(spanArray[1]).text(month + " ");

    $('.content .nav li').css("text-indent",0).css("color","#555");
    $($('.content .nav li')[0]).css("text-indent","50px").css("color","#5F9EA0");
});


