package test;

import com.common.util.DateUtil;
import com.yasi.enums.RecordType;
import com.yasi.model.AttendanceRecord02;
import com.yasi.service.AttendanceRecord02Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;

/**
 * @author wangzi
 * @date 18/9/25 下午5:01.
 */
@RunWith(SpringJUnit4ClassRunner.class) //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class AttendanceRecord02ServiceTest {

    @Autowired
    private AttendanceRecord02Service attendanceRecord02Service;

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