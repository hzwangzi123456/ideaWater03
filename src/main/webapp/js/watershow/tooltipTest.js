/**
 * Created by ziwang on 17/11/18.
 */

var pointList = null;



function paint() {
    var c = document.getElementById("myCanvas");
    var context = c.getContext("2d");
    for (var i = 0;i < pointList.length;i ++) {
        context.fillStyle = pointList[i].value;
        context.fillRect(pointList[i].x, pointList[i].y, 2, 2);
    }

    //context.beginPath();
    //context.strokeStyle ="blue";

    /* context.moveTo(_Data[0].x,_Data[0].y);
     for(i=1;i<=105;i++)  context.lineTo(_Data[i].x,_Data[i].y);
     context.closePath();context.stroke();
     context.fillStyle = "rgb(100,149,237)";
     context.fill();      */
    // var m1, m2, num, wi, wj, wm;
    // var lp = new point;
    // var dis, col;
    // var _Juli = new Array();
    // var tt;//= new distant;
    //
    // for (wi = ymin; wi <= ymax; wi++) {
    //     for (wj = 0; wj < kk[wi]; wj = wj + 2) {
    //         for (wm = pp[wi][wj]; wm <= pp[wi][wj + 1]; wm = wm + 2) {
    //
    //             num = 0;
    //             for (m1 = 0; m1 < 8 - 1; m1++) {
    //                 for (m2 = m1 + 1; m2 < 8; m2++) {
    //                     //var lp = new point(wm,wi);
    //                     lp.x = wm;
    //                     lp.y = wi;
    //
    //                     //dis=GetNearestDistance(Zhan[m1],Zhan[m2],lp);
    //                     // col=(ZhanCol[m1]+ZhanCol[m2])/2;
    //                     // _Juli[num++]=new distant(dis,col);
    //                     _Juli[num] = new distant;
    //                     _Juli[num++] = GetNearestDistance(Zhan[m1],
    //                         Zhan[m2], lp, m1, m2);
    //                     //  _Juli[num++]=new distant(wwdis.dis,wwdis.col);
    //                 }
    //             }
    //             var wk = 12;
    //             for (m1 = 0; m1 < wk; m1++) {
    //                 for (m2 = m1 + 1; m2 < num; m2++) {
    //                     if (_Juli[m1].dis > _Juli[m2].dis) {
    //                         tt = _Juli[m1].dis;
    //                         _Juli[m1].dis = _Juli[m2].dis;
    //                         _Juli[m2].dis = tt;
    //                         tt = _Juli[m1].col;
    //                         _Juli[m1].col = _Juli[m2].col;
    //                         _Juli[m2].col = tt;
    //
    //                     }
    //                 }
    //             }
    //             col = 0;
    //             for (m1 = 0; m1 < wk; m1++)
    //                 col = col + _Juli[m1].col;
    //             col = col / wk;
    //             // col=_Juli[0].col;
    //             context.beginPath();
    //             // context.fillStyle="rgb(0,255,255)";
    //             if (col <= 50) {
    //                 col = Math.floor(255 * 2 * col / 100);
    //                 context.fillStyle = "rgb(0,0," + col + ")";
    //             } else {
    //                 col = col - 50;
    //                 col = Math.floor(255 * 2 * col / 100);
    //                 context.fillStyle = "rgb(0," + col + ",255)";
    //             }
    //
    //             /*  if(col>30) col=30;
    //              if(col<1) col=0;
    //              context.fillStyle="rgb(0,"+wColor[col].x+","+wColor[col].y+")"; */
    //             context.fillRect(wm, wi, 2, 2);
    //         }
    //
    //     }
    // }
}


$(function () {
    doAjaxSyn(PROJECT_NAME + "/TR/getPoint.do", {}, function (data) {
        var redata = strToJson(data);
        if (!judgeAjaxData(redata)) {
            window.location.href = PROJECT_NAME + INDEX_NAME;
            return;
        }
        pointList = redata;
        paint();
    });
});