package com.xs.service;

import com.xs.bean.Distant;
import com.xs.bean.Point;
import com.xs.bean.PointVo;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzi
 * @date 19/5/7 下午8:32.
 * wz-IDW算法
 */
@Slf4j
public class WzIdwTest {

    /**
     * 样本点
     */
    public static List<Point> samplePoints = new ArrayList<>();

    /**
     * 边界点
     */
    public static List<Point> borderPoints = new ArrayList<>();

    /**
     * 验证点
     */
    public static List<Point> confirmPoints = new ArrayList<>();

    /**
     * y轴最大值
     */
    public static int ymax;

    /**
     * y轴最小值
     */
    public static int ymin;

    /**
     * x轴最大值
     */
    public static int xmax;

    /**
     * x轴最小值
     */
    public static int xmin;

    /**
     * 湖与y横线的交点的x坐标
     * pp[3][2] = 5的意思是:y等于3情况下,第3个交点的x坐标为5
     */
    public static int[][] pp = new int[10000][10000];

    /**
     * 与Y = y,y属于[ymin,ymax]的交点数,kk[y] = 2表示:湖的边界与Y=y的交点数为2
     */
    public static int[] kk = new int[10000];

    /**
     * 每个坐标点对应的预测值
     */
    public static double[][] cols = new double[10000][10000];

    static {
        readText("/Users/ziwang/Desktop/研究生文档/实验/0528/point.txt", samplePoints);
        readText("/Users/ziwang/Desktop/研究生文档/实验/0528/borderPoint.txt", borderPoints);
        readText("/Users/ziwang/Desktop/研究生文档/实验/0528/confirmPointt.txt", confirmPoints);
        initPp();
        paint();
        startConfirmPoint();
    }


    /**
     * 写入TXT文件
     */
    public static void writeFile(String filePath, List<String> strings) {
        try {
            File writeName = new File(filePath); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            FileWriter writer = new FileWriter(writeName);
            BufferedWriter out = new BufferedWriter(writer);

            for (int i = 0; i < strings.size(); i++) {
                out.write(strings.get(i) + "\r\n");
            }
            out.flush(); // 把缓存区内容压入文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件路径获得point的坐标参数和ntu的值
     *
     * @return
     */
    private static void readText(String pathname, List<Point> points) {
        System.out.println("读取文件路径:" + pathname);
        try {
            FileReader reader = new FileReader(pathname);
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] strs = line.split(",");
                String index = strs[0].split(" ")[0];
                String x = strs[0].split(" ")[1];
                String y = strs[1];
                String value = strs[2];
                Point point = new Point();
                point.setIndex(Integer.valueOf(index));
                point.setX(Integer.valueOf(x));
                point.setY(Integer.valueOf(y));
                point.setValue(Double.valueOf(value));
                points.add(point);
            }

        } catch (Exception e) {
            log.error("读取文件出错:{}", e.getMessage());
        }
    }

    /**
     * 初始化pp数组  static初始化
     */
    private static void initPp() {
        ymin = (int) borderPoints.get(0).getY();
        ymax = ymin;
        xmin = (int) borderPoints.get(0).getX();
        xmax = xmin;
        for (int i = 1; i <= 104; i++) {
            if (borderPoints.get(i).getY() > ymax) {
                ymax = (int) borderPoints.get(i).getY();
            }
            if (borderPoints.get(i).getY() < ymin) {
                ymin = (int) borderPoints.get(i).getY();
            }
            if (borderPoints.get(i).getX() > xmax) {
                xmax = (int) borderPoints.get(i).getX();
            }
            if (borderPoints.get(i).getX() < xmin) {
                xmin = (int) borderPoints.get(i).getX();
            }
        }

        System.out.println("ymax:" + ymax + "ymin:" + ymin);
        System.out.println("xmax:" + xmax + "xmin:" + xmin);

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 10; j++) {
                pp[i][j] = 0;
            }
        }
        for (int i = ymin; i <= ymax; i++) {
            kk[i] = 0;
            for (int j = 0; j < 105; j++) {
                boolean exist = (borderPoints.get(j).getY() < i && borderPoints.get(j + 1).getY() > i)
                        || (borderPoints.get(j).getY() > i && borderPoints.get(j + 1).getY() < i);
                if (exist) {
                    double x = (i - borderPoints.get(j).getY()) * (borderPoints.get(j + 1).getX() - borderPoints.get(j).getX())
                            / (borderPoints.get(j + 1).getY() - borderPoints.get(j).getY()) + borderPoints.get(j).getX();
                    pp[i][kk[i]++] = (int) x;
                    continue;
                }

                //尖尖头和y横线重合
                if (borderPoints.get(j + 1).getY() == i) {
                    int x = (int) borderPoints.get(j + 1).getX();
                    if (i < borderPoints.get(j).getY() && i < borderPoints.get(j + 2).getY()) {
                        pp[i][kk[i]++] = x;
                        pp[i][kk[i]++] = x;
                    } else if (i >= borderPoints.get(j).getY() && i >= borderPoints.get(j + 2).getY()) {
                    } else {
                        pp[i][kk[i]++] = x;
                    }
                }
            }
        }

        for (int m = ymin; m <= ymax; m++) {
            for (int i = 0; i < kk[m] - 1; i++) {//每一行的所有交点进行冒泡排序
                for (int j = i + 1; j < kk[m]; j++) {
                    if (pp[m][i] > pp[m][j]) {
                        int t = pp[m][i];
                        pp[m][i] = pp[m][j];
                        pp[m][j] = t;
                    }
                }
            }
        }

    }

    /**
     * 反演 static初始化
     */
    private static void paint() {
        int m1, m2, num, wi, wj, wm;
        Point lp = new Point();
        double dis, col;
        Distant[] _Juli = new Distant[10000];

        for (wi = ymin; wi <= ymax; wi++) {// +2
            for (wj = 0; wj < kk[wi]; wj = wj + 2) {//必定是偶数,两个两个进行匹配
                for (wm = pp[wi][wj]; wm <= pp[wi][wj + 1]; wm = wm + 1) {//第一个交点到第二个交点之间的像素点2px

                    num = 0;
                    for (m1 = 0; m1 < 25 - 1; m1++) {
                        for (m2 = m1 + 1; m2 < 25; m2++) {
                            //var lp = new point(wm,wi);
                            //lp就是要求的某个像素点,湖内部的点
                            lp.setX(wm);
                            lp.setY(wi);

                            //dis=GetNearestDistance(Zhan[m1],Zhan[m2],lp);
                            // col=(ZhanCol[m1]+ZhanCol[m2])/2;
                            // _Juli[num++]=new distant(dis,col);

                            //Juli数组就是放该像素点到所有线段(站点之间的连线)的距离和颜色
                            _Juli[num] = new Distant();
                            _Juli[num++] = getNearestDistance(samplePoints.get(m1),
                                    samplePoints.get(m2), lp, m1, m2);
                            //  _Juli[num++]=new distant(wwdis.dis,wwdis.col);
                        }
                    }
                    //冒泡取了最短的12条,这里可以调,那个效果好
                    int wk = 12;
                    for (m1 = 0; m1 < wk; m1++) {
                        for (m2 = m1 + 1; m2 < num; m2++) {
                            if (_Juli[m1].dis > _Juli[m2].dis) {
                                double tt = _Juli[m1].dis;
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
                    for (m1 = 0; m1 < wk; m1++) {
                        col = col + _Juli[m1].col;
                    }
                    col = col / wk;
//                    for (m1 = 0; m1 < num; m1++) {
//                        col = col + _Juli[m1].col;
//                    }
//                    col = col / num;
                    cols[wm][wi] = col;
                }

            }
        }
    }

    /**
     * 求p3点到线的距离和颜色
     *
     * @param PA  一个站点
     * @param PB  另一个站点
     * @param P3  像素点(待求)
     * @param wm1 PA站点的序号
     * @param wm2 PB站点的序号
     * @return
     */
    private static Distant getNearestDistance(Point PA, Point PB, Point P3, int wm1, int wm2) {
        Distant wwdis = new Distant();
        double a, b, c;
        a = getPointDistance(PB, P3);
        if (a <= 0.00001) {  //p3在PB站点附近就默认是PB的站点的颜色为像素点的颜色
            wwdis.dis = 0.0;
            wwdis.col = PB.getValue();
            return wwdis;
        }

        b = getPointDistance(PA, P3);
        if (b <= 0.00001) { //p3在PA站点附近就默认是PA的站点的颜色为像素点的颜色
            wwdis.dis = 0.0;
            wwdis.col = PA.getValue();
            return wwdis;
        }

        //一般来说站点不会很接近
        c = getPointDistance(PA, PB);
        if (c <= 0.00001) { //PA和PB很接近的话,就是a或者b的距离
            wwdis.dis = a;
            wwdis.col = PB.getValue();
            return wwdis;
        }


        //如果是钝角返回b
        if (a * a >= b * b + c * c) {//延长线外的高无法判断,就取了b为距离
            wwdis.dis = b;
            wwdis.col = PA.getValue();
            return wwdis;
        }

        //如果是钝角返回a
        if (b * b >= a * a + c * c) {//延长线外的高无法判断,就取了a为距离
            wwdis.dis = a;
            wwdis.col = PA.getValue();
            return wwdis;
        }

        //正常情况下,求高
        double l = (a + b + c) / 2; //周长的一半
        double s = Math.sqrt(l * (l - a) * (l - b) * (l - c)); //海伦公式求面积，也可以用矢量求
        double d = 2 * s / c;
        double dd = Math.sqrt(b * b - d * d);
        //wwcol=ZhanCol[wm1]*dd/c+ZhanCol[wm2]*(1-dd/c);
        double wwcol = PA.getValue() + (PB.getValue() - PA.getValue()) * (dd / c);
        wwdis.dis = d;
        wwdis.col = wwcol;
        return wwdis;
    }

    /**
     * 求欧式距离
     *
     * @param p1
     * @param p2
     */
    private static double getPointDistance(Point p1, Point p2) {
        return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX())
                + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
    }

    /**
     * 打印结果
     */
    private static List<PointVo> print() {
        int flag = 0;
        double colsMax = 0;
        double colsMin = 0;
        String[][] result = new String[10000][10000];
        List<PointVo> resultList = new ArrayList<>();
        //Todo 用List来重构

        String col1 = "#f40000";
        String col2 = "#f73d00";
        String col3 = "#fa6000";
        String col4 = "#fb7e00";
        String col5 = "#fd9c00";
        String col6 = "#fdb900";
        String col7 = "#fad900";
        String col8 = "#f5f500";
        String col9 = "#dff701";
        String col10 = "#c3f700";
        String col11 = "#a5f700";
        String col12 = "#89f700";
        String col13 = "#6bf700";
        String col14 = "#47f700";
        String col15 = "#10f600";
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                result[i][j] = "-1";
            }
        }

        int num = 0;
        for (int i = xmin; i <= xmax; i++) {
            for (int j = ymin; j <= ymax; j++) {
                System.out.print(cols[i][j] + " ");
                if (cols[i][j] == 0) {
                    continue;
                }
                if (flag == 0) {
                    flag = 1;
                    colsMax = cols[i][j];
                    colsMin = cols[i][j];
                    continue;
                }
                if (colsMax < cols[i][j]) {
                    colsMax = cols[i][j];
                }
                if (colsMin > cols[i][j]) {
                    colsMin = cols[i][j];
                }
                PointVo pointVo = new PointVo();
                pointVo.setX(i);
                pointVo.setY(j);
                if (cols[i][j] <= 146.4123986) {
                    pointVo.setValue(col1);
                    resultList.add(pointVo);
                    num++;
                } else if (cols[i][j] >= 146.4123987 && cols[i][j] <= 147.9025389) {
                    pointVo.setValue(col2);
                    resultList.add(pointVo);
                    num++;
                } else if (cols[i][j] >= 147.9025390 && cols[i][j] <= 149.2358224) {
                    pointVo.setValue(col3);
                    resultList.add(pointVo);
                    num++;
                } else if (cols[i][j] >= 149.2358225 && cols[i][j] <= 150.4906775) {
                    pointVo.setValue(col4);
                    resultList.add(pointVo);
                    num++;
                } else if (cols[i][j] >= 150.4906776 && cols[i][j] <= 151.7455325) {
                    pointVo.setValue(col5);
                    resultList.add(pointVo);
                    num++;
                } else if (cols[i][j] >= 151.7455326 && cols[i][j] <= 153.078816) {
                    pointVo.setValue(col6);
                    resultList.add(pointVo);
                } else if (cols[i][j] >= 153.078817 && cols[i][j] <= 154.4120995) {
                    pointVo.setValue(col7);
                    resultList.add(pointVo);
                } else if (cols[i][j] >= 154.4120996 && cols[i][j] <= 155.6669545) {
                    pointVo.setValue(col8);
                    resultList.add(pointVo);
                } else if (cols[i][j] >= 155.6669546 && cols[i][j] <= 156.8433812) {
                    pointVo.setValue(col9);
                    resultList.add(pointVo);
                } else if (cols[i][j] >= 156.8433813 && cols[i][j] <= 158.0198078) {
                    pointVo.setValue(col10);
                    resultList.add(pointVo);
                } else if (cols[i][j] >= 158.0198079 && cols[i][j] <= 159.2746628) {
                    pointVo.setValue(col11);
                    resultList.add(pointVo);
                } else if (cols[i][j] >= 159.2746629 && cols[i][j] <= 160.6079463) {
                    pointVo.setValue(col12);
                    resultList.add(pointVo);
                } else if (cols[i][j] >= 160.6079464 && cols[i][j] <= 162.0196582) {
                    pointVo.setValue(col13);
                    resultList.add(pointVo);
                } else if (cols[i][j] >= 162.0196583 && cols[i][j] <= 163.4313702) {
                    pointVo.setValue(col14);
                    resultList.add(pointVo);
                } else if (cols[i][j] >= 163.4313703) {
                    pointVo.setValue(col15);
                    resultList.add(pointVo);
                }
            }
        }
        System.out.println();
        System.out.println("Max:" + colsMax + ",Min:" + colsMin + ",num:" + num);
        return resultList;
    }


    /**
     * 获得每个像素点的值
     *
     * @return
     */
    public List<PointVo> getPoint() {

        List<PointVo> print = print();
        return print;
    }

    /**
     * 开始验证
     */
    public static void startConfirmPoint() {
        ArrayList<String> strings = new ArrayList<>();
        for (Point p : confirmPoints) {
            double x = p.getX();
            double y = p.getY();
            p.setValue(cols[(int) x][(int) y]);
            strings.add(p.toString());
        }
        writeFile("/Users/ziwang/Desktop/研究生文档/实验/0528/wzIdwResult2.txt", strings);
    }

    /**
     * 寻找最优
     */
    public static void find() {
        Point p = confirmPoints.get(19);
        double x = p.getX();
        double y = p.getY();
        System.out.println(x + " " + y);
        for (int k1 = -100; k1 <= 100; k1++) {
            for (int k2 = -100; k2 <= 100; k2++) {
                int nextX = (int) (k1 + x);
                int nextY = (int) (k2 + y);
                if (nextX < 0 || nextY < 0) {
                    continue;
                }
                double nextValue = cols[nextX][nextY];
                if (Math.abs(nextValue - 152) <= 3 && Math.abs(nextValue - 152) >= 2.5) {
                    Point p1 = new Point();
                    p1.setX(x);
                    p1.setY(y);
                    Point p2 = new Point();
                    p2.setX(nextX);
                    p2.setY(nextY);
                    double juli = getPointDistance(p1,p2);
                    System.out.println("(" + nextX + "," + nextY + "):" + cols[nextX][nextY] + "," + juli);
                }
            }
        }
    }

    public static void main(String[] args) {
//        startConfirmPoint();
        find();
        //1.ArcGis上面参考坐标系要增加。
        //2.对应点的数据要改动
        //3.根据3个点形成的IDW算法来实现。
//        initPp();
//        paint();
//        List<PointVo> print = print();
//        System.out.println(print);
//        ArrayList<String> strings = new ArrayList<>();
//        for (int i = 0;i < samplePoints.size();i ++) {
//            int j = i + 1;
//            String str = j + " (" + (int)samplePoints.get(i).getX() + "," + (int)samplePoints.get(i).getY() + ") : " + cols[(int)samplePoints.get(i).getX()][(int)samplePoints.get(i).getY()];
//            strings.add(str);
//        }
//        writeFile("/Users/ziwang/Desktop/研究生文档/实验/0519/wzIdw.txt",strings);
    }
}
