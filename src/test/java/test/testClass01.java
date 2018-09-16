package test;

import org.junit.Test;

import java.util.Date;

/**
 * @author wangzi
 * @date 18/7/8 下午4:41.
 */
public class testClass01 extends Date {
    public static void main(String[] args) {
        new testClass01().test();
    }

    public void test() {
        System.out.println(getClass().getSuperclass().getName());
    }

    @Test
    public void test01() {
        fun02();
    }

    public static int fun01() {
        int value = 0;
        try {
            System.out.println("try……");
            int a = 1 / value;
            return -1;

        } catch (Exception e) {
            System.out.println("catch……");
        } finally {
            System.out.println("finally……");
            value++;
//            return value;
        }
        return -3;
    }

    public static void fun02() {
        try {
            int value = 50;
            if (value < 40)
                throw new Exception("value is too small");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Continue after the catch block");
    }
}
