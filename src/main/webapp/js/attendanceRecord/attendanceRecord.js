/**
 * Created by ziwang on 18/1/16.
 */

/**
 * 计算并修改table宽度
 */
function computerTableWidth() {
    //th的个数
    var num = 0;
    num = $($(".main-table").children("thead").children("tr")[0]).children("th").length;
    //修改table的宽度
    $(".main-table").width(num * 120);
}

/**
 * 点击加载
 */
$("#reload").click(function () {
    var start = $("#start").val().trim();
    var end = $("#end").val().trim();
    //查询时间不能为空
    if (start == null || start == '' || end == null || end == '') {
        alert("查询时间不能为空");
        return;
    }
    start = getDateTime(start);
    end = getDateTime(end);

    if (start > end) {
        alert('起始时间不能大于结束时间');
        $("#start").val('');
        $("#end").val('');
        return;
    }

    doAjaxSynJson(PROJECT_NAME + "EmployeeController/findEmployeeRecordByVo.do", {
        startTimeTIMESTAMP: start, endTimeTIMESTAMP: end
    }, function (data) {
        if (data == null || data.length == 0) {
            alert("未找到数据");
            return;
        }

        //冒泡排序当员工从小到大排序
        for(var i = 0;i < data.length - 1;i ++) {
            for(var j = 0;j < data.length - 1 - i;j ++) {
                if(data[j].workNumber > data[j + 1].workNumber) {
                    var temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
        showEmployeeRecord(data);
    });
});

/**
 * 渲染河长考勤信息
 * @param data
 */
function showEmployeeRecord(data) {
    //河长考勤记录天数
    var dateLength = data[0].recordList.length;
    var recordList = data[0].recordList;
    $(".main-table").html('');
    //生成thead
    var html = '<thead><tr><th><div> </div></th><th><div>部门</div></th><th><div>工号</div></th><th><div>出勤天数</div></th><th><div>旷工天数</div></th>';
    for (var i = 0; i < recordList.length; i++) {
        var date = recordList[i].date.substring(8, 10);
        var day = recordList[i].day;
        if (day.trim() == '六' || day.trim() == '日') {
            html += '<th><div><div class="main-table-day" style="color: rgb(120, 192, 110);">' + day + '</div><div class="main-table-date" style="color: rgb(120, 192, 110);">' + date + '</div></div></th>';
        } else {
            html += '<th><div><div class="main-table-day">' + day + '</div><div class="main-table-date">' + date + '</div></div></th>';
        }
    }
    //thead闭元素
    html += '</tr></thead>';

    //生成tbody
    html += '<tbody>';
    for (var i = 0; i < data.length; i++) {
        //得到一个员工的信息
        var employee = data[i];
        var tempRecordList = employee.recordList;
        //出勤天数
        var attendDay = 0;
        //缺勤天数
        var absenceDay = 0;
        for (var j = 0; j < tempRecordList.length; j++) {
            var recordList = tempRecordList[j];
            //出勤
            if (recordList.state == 1) {
                attendDay++;
            } else if (recordList.state == 2) {
                absenceDay++;
            }
        }
        //生成某个员工基本信息
        html += '<tr><td><div>' + employee.name + '</div></td>';
        html += '<td><div>' + employee.department + '</div></td>';
        html += '<td><div>' + employee.workNumber + '</div></td>';
        html += '<td><div>' + attendDay + '</div></td>';
        html += '<td><div>' + absenceDay + '</div></td>';
        //生成某个员工的每日的考勤信息
        for (var j = 0; j < tempRecordList.length; j++) {
            var recordList = tempRecordList[j];
            //出勤
            if (recordList.state == 1) {
                html += '<td><div style="color:#ABCDEF;">' + '出勤' + '</div></td>';
                //缺勤
            } else if (recordList.state == 2) {
                html += '<td><div style="color:rgb(247, 181, 94);">' + '缺勤' + '</div></td>';
                //休息
            } else {
                html += '<td><div style="color: rgb(120, 192, 110);">' + '休息' + '</div></td>';
            }
        }
        //一个员工生成完毕
        html += '</tr>';
    }


    //tbody闭元素
    html += '</tbdoy>';

    $(".main-table").append(html);
    //计算table的宽度
    computerTableWidth();
}

$(function () {
    //向后台发送ajax请求
    $(".header").load("../controllerCommon/header.html");
    $(".nav").load("../controllerCommon/nav.html");
    computerTableWidth();
});