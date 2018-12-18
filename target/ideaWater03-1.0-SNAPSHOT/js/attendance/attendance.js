/**
 * Created by ziwang on 17/11/29.
 */
//记录总条数
// var Total = 0;
var pageObject = {
    // //记录总条数
    // Total: 1000,
    //每页记录条数
    rows: 6,
    //当前页页码
    current: 1,
    //尾页页码
    last: 1000,
    //起始时间
    startDateTime: '2010-02-02',
    //终止时间
    endDateTime: '2020-01-01',
    /**
     * 初始化对象
     * @param startDateTime 起始时间
     * @param endDateTime 终止时间
     */
    init: function (startDateTime, endDateTime, rows) {
        this.current = 1;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.rows = rows;
    },
    /**
     * 根据当前页码,获得分页按钮数字数组
     * @param current 当前页码
     * @returns {Array} 分页按钮数字数组
     */
    show: function (current) {// 返回显示数组，-1表示...
        var butArray = new Array();
        var last = this.last;
        var current = current;
        butArray.push(1);
        if (current - 1 >= 7) {// 需要加入前...
            butArray.push(-1);
            var n = 9;
            current -= 4;
            while (n--) {
                if (current > last) {
                    break;
                }
                butArray.push(current++);
            }
            if (last - current >= 2) {// 需要加入后...
                butArray.push(-1);
                butArray.push(last);
            } else {// 不需要加入后..
                for (var i = current; i <= last; i++) {
                    butArray.push(i);
                }
            }
        } else {// 不需要加入前...
            var n = 7;
            var value = 2;
            while (n--) {
                if (value > last) {
                    break;
                }
                butArray.push(value++);
            }
            var lastofbutArray = butArray[butArray.length - 1];
            if (lastofbutArray < last) {
                butArray.push(-1);
                butArray.push(last);
            }
        }
        return butArray;
    },
    // next: function () {
    //     var current = this.current;
    //     var last = this.last;
    //     current++;
    //     if (current > last) {
    //         return null;
    //     }
    //     this.current = current;
    //     return this.show(current);
    // },
    // prev: function () {
    //     var current = this.current;
    //     current--;
    //     if (current == 0) {
    //         return null;
    //     }
    //     this.current = current;
    //     return this.show(current);
    // },
    /**
     * 第一次显示考勤列表
     */
    firstchartshow: function () {
        var me = this;
        doAjaxSyn(PROJECT_NAME + "RecordController/findRecordByTimeMap.do", {
            startDateTime: this.startDateTime,
            endDateTime: this.endDateTime
        }, function (data) {
            var resdata = strToJson(data);
            if (!judgeAjaxData(resdata)) {
                return;
            }
            resdata = resdata.rows;
            if (resdata.length % 6 == 0) {
                me.last = resdata.length / 6;
            } else {
                me.last = Math.ceil(resdata.length / 6);
            }
        });
    },
    /**
     * 显示分页查询考勤列表
     */
    chartshow: function () {
        var me = this;
        //加载考勤信息列表
        $('.recordcontent').html('');
        doAjax(PROJECT_NAME + "RecordController/findRecordByTimePageMap.do", {
            page: this.current,
            rows: this.rows,
            // total: this.Total,
            sort: 'dateTime',
            order: 'ASC',
            startDateTime: this.startDateTime,
            endDateTime: this.endDateTime
        }, function (data) {
            var resdata = strToJson(data);
            if (!judgeAjaxData(resdata)) {
                return;
            }
            var unitArray = resdata.rows;
            var html = '';
            for (var i = 0; i < unitArray.length; i++) {
                html += '<div class="record-unit">';
                html += '<img class="fl" src="../../img/head.png" alt="">';
                html += '<div class="info fl">';
                html += '<p><span>' + unitArray[i].username + '</span>的第<span style="color: #209e85;font-size:18px;">' + unitArray[i].recordDay + '</span>天打卡:</span>';
                html += '<p>上班时间: <span>' + unitArray[i].workHour + '</span>,下班时间: <span>' + unitArray[i].offHour + '</span>';
                if (unitArray[i].status == 1) {
                    html += ' 状态：<span style="color:#4DC3A9">正常</span></p>';
                } else if (unitArray[i].status == 2) {
                    html += ' 状态：<span style="color:#FF0000">迟到</span></p>';
                } else if (unitArray[i].status == 3) {
                    html += ' 状态：<span style="color:#EEDC82">早退</span></p>';
                }
                html += '<p>' + unitArray[i].dateTime + '</p>';
                html += '</div>';
                html += '<div class="cb"></div>';
                html += '</div>';
            }
            $('.recordcontent').append(html);

            //加载分页按钮
            $('.paging').html('');
            var butArray = pageObject.show(me.current);
            var html = '<ul>';
            html += '<li onclick="pageObject.clickprev()" class="btpre btlist">' + '<' + '</li>';
            for (var i = 0; i < butArray.length; i++) {
                if (butArray[i] == me.current) {
                    html += '<li onclick="pageObject.clickshow(this)" class="btlist btactive">' + butArray[i] + '</li>';
                    continue;
                }
                if (butArray[i] == -1) {
                    html += '<li class="btlist">' + '...' + '</li>';
                } else {
                    html += '<li onclick="pageObject.clickshow(this)" class="btlist">' + butArray[i] + '</li>';
                }
            }
            html += '<li onclick="pageObject.clicknext()" class="btlist">' + '>' + '</li>';
            html += '</ul>';
            $('.paging').append(html);
        });


    },
    /**
     * 点击分页按钮
     * @param me 被点击分页按钮的this
     */
    clickshow: function (me) {
        this.current = $(me).text();
        this.chartshow();
    },
    /**
     * 点击分页按钮下一页按钮
     */
    clicknext: function () {
        var current = this.current;
        var last = this.last;
        current++;
        if (current > last) {
            this.current = last;
            alert("当前页是尾页");
            return;
        }
        this.current = current;
        this.chartshow();
    },
    /**
     * 点击分页按钮上一页按钮
     */
    clickprev: function () {
        var current = this.current;
        current--;
        if (current == 0) {
            alert("当前页是首页");
            current = 1;
            return;
        }
        this.current = current;
        this.chartshow();
    }
};

/**
 * 点击加载
 */
$("#reload").click(function () {
    var start = $("#start").val().trim();
    var end = $("#end").val().trim();
    start = getDateTime(start);
    end = getDateTime(end);
    if (start > end) {
        alert('起始时间不能大于结束时间');
        $("#start").val('');
        $("#end").val('');
        return;
    }
    pageObject.init(start, end, 6);
    pageObject.firstchartshow();
    pageObject.chartshow();
});

$(function () {
    // pageObject.init('2017-02-01', '2017-04-10', 6);
    // pageObject.firstchartshow();
    // pageObject.chartshow();
    // Total = 0;
    // doAjax(PROJECT_NAME + "RecordController/findRecordByPojo.do", {}, function (data) {
    //     var resdata = strToJson(data);
    //     console.log(resdata);
    //     if(!judgeAjaxData(resdata)) {
    //         alert("未发送或者发生了失败的响应");
    //         return ;
    //     }
    //     //获得响应数据
    //     resdata = resdata.rows;
    //     //获得总页数
    //     if (resdata.length % 6 != 0) {
    //         pageObject.init(Math.ceil(resdata.length / 6),'2017-02-01','2017-02-02');
    //     } else {
    //         pageObject.init(resdata.length / 6,'2017-02-01','2020-02-02');
    //     }
    //     pageObject.chartshow();
    //     Total = resdata.length;
    // $('.calendar .days span').text(Total);
    // MAP = {};
    // MAPworkHour = {};
    // MAPoffHour = {};
    // for (var i = 0; i < resdata.length; i++) {
    //     MAP[resdata[i].dateTime] = resdata[i].status;
    //     MAPworkHour[resdata[i].dateTime] = resdata[i].workHour;
    //     MAPoffHour[resdata[i].dateTime] = resdata[i].offHour;
    // }
    // });

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
});