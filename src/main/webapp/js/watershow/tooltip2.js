/**
 * Created by ziwang on 17/11/18.
 */
var xPos;
var yPos;
var num = 10;
var nowAQI = 155;

//init();
//坐标构造函数
function point(x, y) {
    this.x = x;
    this.y = y;
}

//距离结构体
function distant(dis, col) {
    this.dis = dis;
    this.col = col;
}

//站点信息,采集点
var Zhan = new Array();
//采集点的数据
var ZhanCol = new Array();
Zhan[0] = new point(381, 115);
ZhanCol[0] = 0;//0
Zhan[1] = new point(581, 147);
ZhanCol[1] = 16;//16
Zhan[2] = new point(732, 136);
ZhanCol[2] = 90;//90
Zhan[3] = new point(704, 271);
ZhanCol[3] = 5;//5
Zhan[4] = new point(613, 353);
ZhanCol[4] = 25;//25
Zhan[5] = new point(495, 471);
ZhanCol[5] = 69;//69
Zhan[6] = new point(367, 392);
ZhanCol[6] = 52;//52
Zhan[7] = new point(257, 250);
ZhanCol[7] = 14;//14
//边框的点
var i, j, ymin, ymax, m;
//湖一圈的点
var _Data = new Array();
_Data[0] = new point(380, 114);
_Data[1] = new point(401, 115);
_Data[2] = new point(432, 121);
_Data[3] = new point(458, 128);
_Data[4] = new point(469, 129);
_Data[5] = new point(476, 134);
_Data[6] = new point(489, 134);
_Data[7] = new point(519, 142);
_Data[8] = new point(557, 152);
_Data[9] = new point(569, 153);
_Data[10] = new point(600, 145);
_Data[11] = new point(619, 136);
_Data[12] = new point(633, 125);
_Data[13] = new point(647, 110);
_Data[14] = new point(663, 92);
_Data[15] = new point(673, 84);
_Data[16] = new point(697, 79);
_Data[17] = new point(713, 81);
_Data[18] = new point(725, 90);
_Data[19] = new point(734, 107);
_Data[20] = new point(740, 126);
_Data[21] = new point(740, 135);
_Data[22] = new point(733, 143);
_Data[23] = new point(734, 153);
_Data[24] = new point(740, 160);
_Data[25] = new point(738, 176);
_Data[26] = new point(730, 193);
_Data[27] = new point(723, 219);
_Data[28] = new point(722, 234);
_Data[29] = new point(712, 257);
_Data[30] = new point(706, 272);
_Data[31] = new point(698, 307);
_Data[32] = new point(667, 322);
_Data[33] = new point(659, 330);
_Data[34] = new point(653, 348);
_Data[35] = new point(647, 355);
_Data[36] = new point(612, 366);
_Data[37] = new point(610, 357);
_Data[38] = new point(603, 350);
_Data[39] = new point(597, 358);
_Data[40] = new point(595, 367);
_Data[41] = new point(596, 375);
_Data[42] = new point(589, 377);
_Data[43] = new point(587, 371);
_Data[44] = new point(581, 365);
_Data[45] = new point(576, 363);
_Data[46] = new point(574, 370);
_Data[47] = new point(575, 377);
_Data[48] = new point(577, 383);
_Data[49] = new point(564, 391);
_Data[50] = new point(559, 382);
_Data[51] = new point(550, 378);
_Data[52] = new point(548, 387);
_Data[53] = new point(549, 394);
_Data[54] = new point(553, 401);
_Data[55] = new point(545, 408);
_Data[56] = new point(540, 403);
_Data[57] = new point(535, 400);
_Data[58] = new point(529, 400);
_Data[59] = new point(529, 409);
_Data[60] = new point(532, 414);
_Data[61] = new point(534, 419);
_Data[62] = new point(527, 431);
_Data[63] = new point(522, 429);
_Data[64] = new point(516, 426);
_Data[65] = new point(513, 426);
_Data[66] = new point(513, 434);
_Data[67] = new point(515, 440);
_Data[68] = new point(519, 445);
_Data[69] = new point(512, 459);
_Data[70] = new point(508, 470);
_Data[71] = new point(507, 479);
_Data[72] = new point(470, 480);
_Data[73] = new point(456, 459);
_Data[74] = new point(441, 444);
_Data[75] = new point(420, 425);
_Data[76] = new point(393, 408);
_Data[77] = new point(367, 397);
_Data[78] = new point(336, 391);
_Data[79] = new point(305, 377);
_Data[80] = new point(275, 361);
_Data[81] = new point(269, 340);
_Data[82] = new point(266, 313);
_Data[83] = new point(261, 287);
_Data[84] = new point(255, 260);
_Data[85] = new point(249, 245);
_Data[86] = new point(236, 234);
_Data[87] = new point(224, 220);
_Data[88] = new point(215, 206);
_Data[89] = new point(211, 192);
_Data[90] = new point(214, 187);
_Data[91] = new point(221, 193);
_Data[92] = new point(228, 200);
_Data[93] = new point(239, 214);
_Data[94] = new point(254, 222);
_Data[95] = new point(264, 225);
_Data[96] = new point(278, 224);
_Data[97] = new point(296, 217);
_Data[98] = new point(310, 206);
_Data[99] = new point(327, 194);
_Data[100] = new point(340, 178);
_Data[101] = new point(349, 161);
_Data[102] = new point(355, 145);
_Data[103] = new point(366, 131);
_Data[104] = new point(376, 115);
_Data[105] = new point(380, 114);
_Data[106] = new point(401, 115);
ymin = ymax = _Data[0].y
for (i = 1; i <= 104; i++) {
    if (_Data[i].y > ymax)
        ymax = _Data[i].y;
    if (_Data[i].y < ymin)
        ymin = _Data[i].y;
}
//X轴扫描记录数组
//湖与y横线的交点的x坐标
var pp = new Array(); //先声明一维,pp[3][2]=5,y=5情况下,第3个交点的x坐标为5
for (i = 0; i < 500; i++) { //一维长度
    pp[i] = new Array(); //在声明二维
    for (j = 0; j < 10; j++) { //二维长度为5
        pp[i][j] = 0;
    }
}
//X轴扫描交点数
var kk = new Array();
for (i = 0; i < 500; i++) {
    kk[i] = 0;
}

//X轴扫描求焦点
for (i = ymin; i <= ymax; i++) {
    kk[i] = 0;
    for (j = 0; j < 105; j++) {
        if ((_Data[j].y < i && _Data[j + 1].y > i)
            || (_Data[j].y > i && _Data[j + 1].y < i)) {
            x = (i - _Data[j].y) * (_Data[j + 1].x - _Data[j].x)
                / (_Data[j + 1].y - _Data[j].y) + _Data[j].x;
            pp[i][kk[i]++] = x;
        }
        if (_Data[j + 1].y == i) {//尖尖头和y横线重合
            x = _Data[j + 1].x;
            if (i < _Data[j].y && i < _Data[j + 2].y) {
                pp[i][kk[i]++] = x;
                pp[i][kk[i]++] = x;
            } else if (i >= _Data[j].y && i >= _Data[j + 2].y) {
            } else
                pp[i][kk[i]++] = x;
        }
    }

}

//每行交点排序
var t;
for (m = ymin; m <= ymax; m++) {
    for (i = 0; i < kk[m] - 1; i++) {//每一行的所有交点进行冒泡排序
        for (j = i + 1; j < kk[m]; j++)
            if (pp[m][i] > pp[m][j]) {
                t = pp[m][i];
                pp[m][i] = pp[m][j];
                pp[m][j] = t;
            }

    }
}
//不重要
var wColor = new Array();
wColor[0] = new point(0, 15);
wColor[1] = new point(0, 31);
wColor[2] = new point(0, 47);
wColor[3] = new point(0, 63);
wColor[4] = new point(0, 79);
wColor[5] = new point(0, 95);
wColor[6] = new point(0, 111);
wColor[7] = new point(0, 127);
wColor[8] = new point(0, 143);
wColor[9] = new point(0, 159);
wColor[10] = new point(0, 175);
wColor[11] = new point(0, 191);
wColor[12] = new point(0, 207);
wColor[13] = new point(0, 223);
wColor[14] = new point(0, 239);
wColor[15] = new point(0, 255);
wColor[16] = new point(16, 255);
wColor[17] = new point(32, 255);
wColor[18] = new point(48, 255);
wColor[19] = new point(64, 255);
wColor[20] = new point(80, 255);
wColor[21] = new point(96, 255);
wColor[22] = new point(112, 255);
wColor[23] = new point(128, 255);
wColor[24] = new point(144, 255);
wColor[25] = new point(160, 255);
wColor[26] = new point(176, 255);
wColor[27] = new point(192, 255);
wColor[28] = new point(208, 255);
wColor[29] = new point(224, 255);
wColor[30] = new point(240, 255);

//var p1= new point ; var p2= new point ;
//var PA= new point ; var PB= new point ; var P3= new point ;

paint();
//	var wwdis=new distant;

function paint() {
    var c = document.getElementById("myCanvas");
    var context = c.getContext("2d");
    //context.beginPath();
    //context.strokeStyle ="blue";

    /* context.moveTo(_Data[0].x,_Data[0].y);
     for(i=1;i<=105;i++)  context.lineTo(_Data[i].x,_Data[i].y);
     context.closePath();context.stroke();
     context.fillStyle = "rgb(100,149,237)";
     context.fill();      */
    var m1, m2, num, wi, wj, wm;
    var lp = new point;
    var dis, col;
    var _Juli = new Array();
    var tt;//= new distant;

    for (wi = ymin; wi <= ymax; wi++) {// +2
        for (wj = 0; wj < kk[wi]; wj = wj + 2) {//必定是偶数,两个两个进行匹配
            for (wm = pp[wi][wj]; wm <= pp[wi][wj + 1]; wm = wm + 2) {//第一个交点到第二个交点之间的像素点2px

                num = 0;
                for (m1 = 0; m1 < 8 - 1; m1++) {
                    for (m2 = m1 + 1; m2 < 8; m2++) {
                        //var lp = new point(wm,wi);
                        //lp就是要求的某个像素点,湖内部的点
                        lp.x = wm;
                        lp.y = wi;

                        //dis=GetNearestDistance(Zhan[m1],Zhan[m2],lp);
                        // col=(ZhanCol[m1]+ZhanCol[m2])/2;
                        // _Juli[num++]=new distant(dis,col);

                        //Juli数组就是放该像素点到所有线段(站点之间的连线)的距离和颜色
                        _Juli[num] = new distant;
                        _Juli[num++] = GetNearestDistance(Zhan[m1],
                            Zhan[m2], lp, m1, m2);
                        //  _Juli[num++]=new distant(wwdis.dis,wwdis.col);
                    }
                }
                //冒泡取了最短的12条,这里可以调,那个效果好
                var wk = 12;
                for (m1 = 0; m1 < wk; m1++) {
                    for (m2 = m1 + 1; m2 < num; m2++) {
                        if (_Juli[m1].dis > _Juli[m2].dis) {
                            tt = _Juli[m1].dis;
                            _Juli[m1].dis = _Juli[m2].dis;
                            _Juli[m2].dis = tt;
                            tt = _Juli[m1].col;
                            _Juli[m1].col = _Juli[m2].col;
                            _Juli[m2].col = tt;

                        }
                    }
                }
                //将前12个取平均值
                col = 0;
                for (m1 = 0; m1 < wk; m1++)
                    col = col + _Juli[m1].col;
                col = col / wk;
                // col=_Juli[0].col;
                context.beginPath();
                // context.fillStyle="rgb(0,255,255)";
                //画颜色
                if (col <= 50) {
                    col = Math.floor(255 * 2 * col / 100);
                    context.fillStyle = "rgb(0,0," + col + ")";
                } else {
                    col = col - 50;
                    col = Math.floor(255 * 2 * col / 100);
                    context.fillStyle = "rgb(0," + col + ",255)";
                }

                /*  if(col>30) col=30;
                 if(col<1) col=0;
                 context.fillStyle="rgb(0,"+wColor[col].x+","+wColor[col].y+")"; */
                //开始画这个lp的颜色,lp就是一个像素点
                //可以存下来,颜色,x,y
                context.fillRect(wm, wi, 2, 2);
            }

        }
    }
}

function GetPointDistance(p1, p2) {
    return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y)
        * (p1.y - p2.y));
}

/**
 * 求p3点到线的距离和颜色
 * @param PA 一个站点
 * @param PB 另一个站点
 * @param P3 像素点(待求)
 * @param wm1 PA站点的序号
 * @param wm2 PB站点的序号
 * @returns {distant}
 * @constructor
 */
function GetNearestDistance(PA, PB, P3, wm1, wm2) {
    var wwdis = new distant;
    var wwcol;
    //----------图2--------------------
    var a, b, c;
    a = GetPointDistance(PB, P3);
    if (a <= 0.00001) {  //p3在PB站点附近就默认是PB的站点的颜色为像素点的颜色
        wwcol = ZhanCol[wm2];
        wwdis.dis = 0.0;
        wwdis.col = wwcol;
        return wwdis;
    }

    b = GetPointDistance(PA, P3);
    if (b <= 0.00001) { //p3在PA站点附近就默认是PA的站点的颜色为像素点的颜色
        wwcol = ZhanCol[wm1];
        wwdis.dis = 0.0;
        wwdis.col = wwcol;
        return wwdis;
    }

    //一般来说站点不会很接近
    c = GetPointDistance(PA, PB);
    if (c <= 0.00001) { //PA和PB很接近的话,就是a或者b的距离
        wwcol = ZhanCol[wm2];
        wwdis.dis = a;
        wwdis.col = wwcol;
        return wwdis;
    }
    //如果PA和PB坐标相同，则退出函数，并返回距离
    //------------------------------

    //如果是钝角返回b
    if (a * a >= b * b + c * c) {//延长线外的高无法判断,就取了b为距离
        wwcol = ZhanCol[wm1];
        wwdis.dis = b;
        wwdis.col = wwcol;
        return wwdis;
    }

    //如果是钝角返回a
    if (b * b >= a * a + c * c) {//延长线外的高无法判断,就取了a为距离
        wwcol = ZhanCol[wm1];
        wwdis.dis = a;
        wwdis.col = wwcol;
        return wwdis;
    }

    //正常情况下,求高
    var l = (a + b + c) / 2; //周长的一半
    var s = Math.sqrt(l * (l - a) * (l - b) * (l - c)); //海伦公式求面积，也可以用矢量求
    var d = 2 * s / c;
    var dd = Math.sqrt(b * b - d * d);
    //wwcol=ZhanCol[wm1]*dd/c+ZhanCol[wm2]*(1-dd/c);
    wwcol = ZhanCol[wm1] + (ZhanCol[wm2] - ZhanCol[wm1]) * (dd / c);
    wwdis.dis = d;
    wwdis.col = wwcol;
    return wwdis;
}

function showToolTip(title, msg, evt) {
    if (evt) {
        var url = evt.target;
    }
    else {
        evt = window.event;
        var url = evt.srcElement;
    }
    xPos = evt.clientX;
    yPos = evt.clientY;

    var toolTip = document.getElementById("toolTip");
    toolTip.innerHTML = "<h1>" + title + "</h1>"
        + "<button onClick='showhidediv(one)' >内容一 </button>"
        + "<button onClick='showhidediv(two)' >内容二 </button>"
        + "<br>"
        + "<div id='one' style='display: ' >"
        + "氧气浓度值<br>"
        + "<table  border='2'>"
        + "<tr><td width='50px'>前天</td><td width='50px'>昨天</td><td width='50px'>今天</td></tr>"
        + "<tr><td>30</td><td>40</td><td>25</td></tr>"
        + "</table>"


        + "</div>"
        + "<div id='two' style='display:none ' >"
        + "今日水质： " + msg
        + "</div>"

        + "<button onclick='Tip()' >近24小时 </button>";
    toolTip.style.top = parseInt(yPos) + 2 + "px";
    toolTip.style.left = parseInt(xPos) + 2 + "px";
    toolTip.style.visibility = "visible";

}

function showhidediv(id) {
    var ONE = document.getElementById("one");
    var TWO = document.getElementById("two");
    if (id == one) {
        if (ONE.style.display == 'none') {
            ONE.style.display = 'block';
            TWO.style.display = 'none';
        }
    }
    else {
        if (TWO.style.display == 'none') {
            TWO.style.display = 'block';
            ONE.style.display = 'none';
        }
    }

}


function Tip(){
    // hideToolTip();
    if (num >= 1) {
        num--;
        // console.log('函数执行了' + num + '次');

        //pic=3;
        ZhanCol[1] = num * 10;
//	nowAQI_2=70+num*10;
//	nowAQI_3=0+num*10;
        //showToolTip('站点1','水质良好','3',event);
        paint();
        setTimeout('Tip()', 500); // 2秒后重复执行
    }
    else
        num = 10;

}

/**
 * 隐藏提示框
 */
function hideToolTip() {
    var toolTip = document.getElementById("toolTip");
    toolTip.style.visibility = "hidden";
}

$(function () {
    //第一次渲染ph变化图
    Tip();
    //每隔7秒渲染一次ph变化图
    setInterval(function () {
        Tip();
    }, 7000);
});