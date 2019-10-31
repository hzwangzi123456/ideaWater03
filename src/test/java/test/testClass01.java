package test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wangzi
 * @date 18/7/8 下午4:41.
 */
public class testClass01 {
    public static void main(String[] args) {
        new testClass01().test();
    }

    public void test() {
        System.out.println(getClass().getSuperclass().getName());
    }

    @Test
    public void Test() throws Exception {
        Set<Integer> result = new HashSet<Integer>();
        Set<Integer> set1 = new HashSet<Integer>();
        set1.add(3);
        set1.add(1);
        set1.add(5);

        for (Integer integer : set1) {
            System.out.println(integer);
        }

        Set<Integer> set2 = new HashSet<Integer>();
        set2.add(1);
        set2.add(2);
        set2.add(3);
        result.clear();
        result.addAll(set1);
        System.out.println("去重复交集前1：" + set1);
        System.out.println("去重复交集前2：" + set2);
        result.retainAll(set2);
        System.out.println(result);
        System.out.println(set2);
//System.out.println("set1与set2的交集是：" + result);
//
//result.clear();
//result.addAll(set2);
//System.out.println("差集前的1：" + set1);
//System.out.println("差集前的2：" + set2);
//result.removeAll(set1);
//System.out.println("set2与set1的差集是：" + result);
//
//result.clear();
//result.addAll(set1);
//result.addAll(set2);
//
//System.out.print("set1和set2的并集：" + result);
//System.err.print("set1集合并集：是去重复" + "\n");
    }

    @Test
    public void Test11() throws Exception {
        List<Integer> lis = null;
        for (Integer in : lis) {

        }
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
