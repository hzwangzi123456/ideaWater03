package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.common.util.CollectionUtil;
import com.common.util.DateUtil;
import com.hlj.dto.DownLoadDto;
import com.hlj.dto.Student;
import com.hlj.dto.TestDto;
import com.hlj.service.ReadService;
import com.yasi.enums.RecordType;
import com.yasi.model.AttendanceRecord02;
import com.yasi.service.AttendanceRecord02Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.collections.Lists;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

/**
 * @author wangzi
 * @date 18/9/25 下午5:01.
 */
@RunWith(SpringJUnit4ClassRunner.class) //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class AttendanceRecord02ServiceTest {

    @Autowired
    private AttendanceRecord02Service attendanceRecord02Service;

    @Autowired
    private ReadService readService;

    @Test
    public void test3() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<Student> studentClass = Student.class;

        Method[] methods = studentClass.getDeclaredMethods();
        if ( CollectionUtil.isNotEmpty( methods ) ) {
            for ( Method a : methods ) {
                if ( a.getName().equals( "sout" ) ) {
                    System.out.println("找到了");
                    a.setAccessible(true);
                    Object invoke = a.invoke(studentClass.newInstance());
                    System.out.println(invoke);
                }
            }
        }
    }

    @Test
    public void test2() {
        TestDto downLoadDto = new TestDto();
        downLoadDto.setId(1);
        downLoadDto.setName("wz");
        downLoadDto.setCreateTime( new Timestamp( System.currentTimeMillis() ) );

        List<TestDto> testDtos = Lists.newArrayList();
        testDtos.add( downLoadDto );

        String s = JSON.toJSONStringWithDateFormat(testDtos, "yyyy-MM-dd HH:mm:ss" ,  SerializerFeature.PrettyFormat , SerializerFeature.WriteDateUseDateFormat);
        System.out.println( s );

        testDtos = JSON.parseArray(s, TestDto.class);
        System.out.println( testDtos.toString() );

        System.out.println( testDtos.get( 0 ).getCreateTime().getTime() );
    }


    @Test
    public void test() {
        TestDto downLoadDto = new TestDto();
        downLoadDto.setId(1);
        downLoadDto.setName(null);
        downLoadDto.setCreateTime( new Timestamp( System.currentTimeMillis() ) );

        readService.test( downLoadDto );
    }

    @Test
    public void createTest() throws ParseException {
        AttendanceRecord02 attendanceRecord02 = new AttendanceRecord02();
        attendanceRecord02.setWorkNumber(2);
        attendanceRecord02.setStartPosition("浙农林");
        attendanceRecord02.setEndPosition("杭电");

        attendanceRecord02.setEndTime("2018-09-26 18:46:29");
        attendanceRecord02.setStartTime("2018-09-26 07:16:29");
        attendanceRecord02.setType(RecordType.PUNCHCARD.getValue());
        attendanceRecord02Service.create(attendanceRecord02);

//        attendanceRecord02Service.create();
    }

    @Test
    public void loadTest() throws Exception {
        AttendanceRecord02 load = attendanceRecord02Service.load(1);
        System.out.println(load);
    }

    @Test
    public void test01() throws ParseException {
        long startLong = DateUtil.date2TimeStamp("2018-09-25 19:46:29");
        long endLong = DateUtil.date2TimeStamp("2018-09-26 09:46:29");
        double hour = (endLong - startLong) * 1.0 / DateUtil.SecondsPerHour;
        System.out.println(startLong);
        System.out.println(endLong);

        System.out.println("小时数:" + hour);
    }
}