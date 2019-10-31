package test;

import com.constant.InversionEnum;
import com.constant.IsDeleteEnum;
import com.constant.StatusEnum;
import com.jt.dao.mybatis.EquipmentDOMapper;
import com.jt.entity.EquipmentDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class EquipmentTest {

    @Autowired
    private EquipmentDOMapper equipmentDao;

    @Test
    public void addTest() throws Exception {

    }

    @Test
    public void Test() throws Exception {
    }

    @Test
    public void Tes1t() throws Exception {
        List<Integer> integers = null;
//        for(Integer et:integers) {
//            System.out.println(et);
//        }

        integers.forEach(e -> {
//            System.out.println(e);
        });

    }
}
