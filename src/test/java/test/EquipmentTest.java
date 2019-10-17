package test;

import com.constant.InversionEnum;
import com.constant.IsDeleteEnum;
import com.constant.StatusEnum;
import com.jt.dao.EquipmentDao;
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
    private EquipmentDao equipmentDao;

    @Test
    public void addTest() throws Exception {
        EquipmentDO equipmentDO2 = new EquipmentDO();

        equipmentDO2.setEquipmentId(2l);
        equipmentDO2.setUploadPeriod(1800l);
        equipmentDO2.setInversionSwitch(InversionEnum.NO.getCode());
        equipmentDO2.setPicDir("C:\\home\\jt\\picture\\2\\");
        equipmentDO2.setStatus(StatusEnum.VALID.getCode());
        equipmentDO2.setIsDelete(IsDeleteEnum.NO.getCode());

        int i = equipmentDao.insertSelective(equipmentDO2);

    }

    @Test
    public void Test() throws Exception {
        List<EquipmentDO> equipmentDOS = equipmentDao.selectByExample(null);
        System.out.println(equipmentDOS);
    }
}
