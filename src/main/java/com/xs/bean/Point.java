package com.xs.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzi
 * @date 19/5/7 下午8:28.
 * 采集点类
 */
@Data
public class Point implements Serializable {

    private static final long serialVersionUID = -7152981495289349377L;

    /**
     * 序号
     */
    private int index;

    /**
     * x坐标
     */
    private double x;

    /**
     * y坐标
     */
    private double y;

    /**
     * 值
     */
    private double value;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.index = -1;
        this.value = -1.0;
    }

    public static int[] points = new int[]{0,166,170,147,165,150,140};

    /**
     * TR-插值算法,平均相对误差
     */
    public static void wzIdw() {
        //wzIdw
        double s1  = Math.abs(157.264 - points[1]) / points[1];
        double s2 = Math.abs(158.136 - points[2]) / points[2];
        double s3 = Math.abs(159.530 - points[3]) / points[3];
        double s4 = Math.abs(158.408 - points[4]) / points[4];
        double s5 = Math.abs(159.273 - points[5]) / points[5];
        double s6 = Math.abs(156.165 - points[6]) / points[6];
//        System.out.println("s1:" + s1);
//        System.out.println("s2:" + s2);
//        System.out.println("s3:" + s3);
//        System.out.println("s4:" + s4);
//        System.out.println("s5:" + s5);
//        System.out.println("s6:" + s6);
        System.out.println("wzIdw:" + (s1 + s2 + s3 + s4 + s5 + s6) / 6.0);
    }

    /**
     * IDW算法,平均相对误差
     */
    public static void Idw() {
        //Idw
        double s1  = Math.abs(150.078 - points[1]) / points[1];
        double s2 = Math.abs(157.334 - points[2]) / points[2];
        double s3 = Math.abs(153.707 - points[3]) / points[3];
        double s4 = Math.abs(154.811 - points[4]) / points[4];
        double s5 = Math.abs(159.648 - points[5]) / points[5];
        double s6 = Math.abs(156.547 - points[6]) / points[6];
//        System.out.println("s1:" + s1);
//        System.out.println("s2:" + s2);
//        System.out.println("s3:" + s3);
//        System.out.println("s4:" + s4);
//        System.out.println("s5:" + s5);
//        System.out.println("s6:" + s6);
        System.out.println("Idw:" + (s1 + s2 + s3 + s4 + s5 + s6) / 6.0);
    }

    /**
     * 克里金算法,平均相对误差
     */
    public static void Kri() {
        //Kri
        double s1  = Math.abs(149.666 - points[1]) / points[1];
        double s2 = Math.abs(156.666 - points[2]) / points[2];
        double s3 = Math.abs(154.000 - points[3]) / points[3];
        double s4 = Math.abs(155.333 - points[4]) / points[4];
        double s5 = Math.abs(160.000 - points[5]) / points[5];
        double s6 = Math.abs(155.333 - points[6]) / points[6];
//        System.out.println("s1:" + s1);
//        System.out.println("s2:" + s2);
//        System.out.println("s3:" + s3);
//        System.out.println("s4:" + s4);
//        System.out.println("s5:" + s5);
//        System.out.println("s6:" + s6);
        System.out.println("Kri:" + (s1 + s2 + s3 + s4 + s5 + s6) / 6.0);
    }

    public static void main(String[] args) {
        int a = 0xf40000;//红色
        int b = 0x10f600;//绿色
        int c = 0x000000;//黑色
        int d = 0xffffff;//白色
        System.out.println("A:" + a + "  " + fun(Integer.toHexString(a)));
        System.out.println("B:" + b + "  " + fun(Integer.toHexString(b)));
        System.out.println("C:" + c + "  " + fun(Integer.toHexString(c)));
        System.out.println("D:" + d + "  " + fun(Integer.toHexString(d)));
    }

    /**
     * 将不足6位的16进制数补上前置0
     * @return
     */
    private static String fun(String str) {
        //待补位数
        int temp = 6 - str.length();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        for (int i = 0;i < temp; i ++) {
            sb.insert(0,"0");
        }
        sb.insert(0,"#");
        return sb.toString();
    }
}
