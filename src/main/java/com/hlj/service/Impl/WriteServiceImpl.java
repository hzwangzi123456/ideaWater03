package com.hlj.service.Impl;

import com.common.util.JdbcUtil;
import com.hlj.bean.ZjWaterDrinking;
import com.hlj.bean.ZjWaterSurface;
import com.hlj.dao.ZjWaterDrinkingDao;
import com.hlj.dao.ZjWaterSurfaceDao;
import com.hlj.service.WriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.common.util.DateUtil.getCurDateStrY_m_d;

/**
 * @author wangzi
 * @date 19/4/10 上午9:28.
 */
@Transactional(rollbackFor = {Exception.class})
@Service
@Slf4j
public class WriteServiceImpl implements WriteService {
    @Autowired
    private ZjWaterSurfaceDao zjWaterSurfaceDao;

    @Autowired
    private ZjWaterDrinkingDao zjWaterDrinkingDao;
//
//    /**
//     * 将当天的 waterSurface sqlite数据转成model
//     */
//    private List<ZjWaterSurface> waterSurfaceSqlite2model() {
//        log.info("waterSurfaceSqlite2model[]进入方法");
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            // db parameters
//            String url = "jdbc:sqlite:/Users/ziwang/Desktop/test/sqliteFile/zj_water_surface.sqlite3";
//            // create a connection to the database
//            conn = DriverManager.getConnection(url);
//            log.info("waterSurfaceSqlite2model[]连接到SQLite成功!");
//            String curDateStrY_m_d = getCurDateStrY_m_d();
//            //访问zj_water_surface数据库
//            String sql = "select * from zj_water_surface where grab_time LIKE '" + curDateStrY_m_d + "%'";
//            log.info("waterSurfaceSqlite2model[]查询sql语句:{}", "select * from zj_water_surface where grab_time LIKE '" + curDateStrY_m_d + "%'");
//            preparedStatement = conn.prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
//            List<ZjWaterSurface> list = new ArrayList<ZjWaterSurface>();
//            if (resultSet != null) {
//                while (resultSet.next()) {
//                    ZjWaterSurface model = new ZjWaterSurface();
//                    model.setGrabTime(resultSet.getString(1));
//                    model.setMtName(resultSet.getString(2));
//                    model.setMonitorTime(resultSet.getString(3));
//                    model.setPH(resultSet.getString(4));
//                    model.setDO(resultSet.getString(5));
//                    model.setCODmn(resultSet.getString(6));
//                    model.setTP(resultSet.getString(7));
//                    model.setNH3N(resultSet.getString(8));
//                    list.add(model);
//                }
//                return list;
//            }
//        } catch (SQLException e) {
//            log.error("waterSurfaceSqlite2model[]查询数据库出错:{}", e.getMessage());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            log.error("waterSurfaceSqlite2model[]查询数据库出错:{}", e.getMessage());
//        } finally {
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                log.error("waterSurfaceSqlite2model[]关闭连接出错:{}", ex.getMessage());
//            }
//        }
//        log.info("waterSurfaceSqlite2model[]离开方法");
//        return null;
//    }

    @Override
    public Boolean addWaterSurface(String sql) throws RuntimeException {
        log.info("addWaterSurface[]进入方法");
        List<Map<String, Object>> maps = JdbcUtil.queryForList("jdbc:sqlite:/Users/ziwang/Desktop/test/sqliteFile/zj_water_surface.sqlite3", sql);
        List<ZjWaterSurface> list = wsMap2Model(maps);
        log.info("addWaterSurface[]list的长度:{}", list.size());
        if (CollectionUtils.isEmpty(list)) {
            log.error("addWaterSurface[]list为空");
            log.info("addWaterSurface[]离开方法");
            return Boolean.FALSE;
        }
        int i = zjWaterSurfaceDao.insertForeach(list);
        if (i != list.size()) {
            log.error("addWaterSurface[]:数据插入异常,回滚");
            throw new RuntimeException("插入数据错误,数据回滚");
        }
        log.info("addWaterSurface[]离开方法");
        return Boolean.TRUE;
    }

    /**
     * 地表水
     * 将map的list转为model的list
     *
     * @param maps
     * @return
     */
    private static List<ZjWaterSurface> wsMap2Model(List<Map<String, Object>> maps) {
        ArrayList<ZjWaterSurface> list = new ArrayList<>();
        for (Map<String, Object> m : maps) {
            ZjWaterSurface model = new ZjWaterSurface();
            model.setGrabTime((String) m.get("grab_time"));
            model.setMtName((String) m.get("mtName"));
            model.setMonitorTime((String) m.get("monitorTime"));
            model.setPH((String) m.get("pH"));
            model.setDO((String) m.get("DO"));
            model.setCODmn((String) m.get("CODmn"));
            model.setTP((String) m.get("TP"));
            model.setNH3N((String) m.get("NH3N"));
            list.add(model);
        }
        return list;
    }

    @Override
    public Boolean addWaterDrinking(String sql) throws Exception {
        log.info("addWaterDrinking[]进入方法");
        List<Map<String, Object>> maps = JdbcUtil.queryForList("jdbc:sqlite:/Users/ziwang/Desktop/test/sqliteFile/zj_water_drinking.sqlite3", sql);
        List<ZjWaterDrinking> list = wdMap2Model(maps);
        log.info("addWaterDrinking[]list的长度:{}", list.size());
        if (CollectionUtils.isEmpty(list)) {
            log.error("addWaterDrinking[]list为空");
            log.info("addWaterDrinking[]离开方法");
            return Boolean.FALSE;
        }
        int i = zjWaterDrinkingDao.insertForeach(list);
        if (i != list.size()) {
            log.error("addWaterDrinking[]:数据插入异常,回滚");
            throw new RuntimeException("插入数据错误,数据回滚");
        }
        log.info("addWaterDrinking[]离开方法");
        return Boolean.TRUE;
    }

    /**
     * 饮用水maplist转为modellist
     * @param maps
     * @return
     */
    private List<ZjWaterDrinking> wdMap2Model(List<Map<String, Object>> maps) {
        List<ZjWaterDrinking> list = new ArrayList<ZjWaterDrinking>();
        for (Map<String, Object> map:maps) {
            ZjWaterDrinking model = new ZjWaterDrinking();
            model.setGrabTime((String) map.get("grab_time"));
            model.setStationname((String) map.get("stationName"));
            model.setMonitortime((String) map.get("monitorTime"));
            model.setPh((String) map.get("pH"));
            model.setDo((String) map.get("DO"));
            model.setCodmn((String) map.get("CODmn"));
            model.setTp((String) map.get("TP"));
            model.setNh4((String) map.get("NH4"));
            list.add(model);
        }
        return list;
    }

    public static void main(String[] args) {
        String curDateStrY_m_d = getCurDateStrY_m_d();
        List<Map<String, Object>> maps = JdbcUtil.queryForList("jdbc:sqlite:/Users/ziwang/Desktop/test/sqliteFile/zj_water_surface.sqlite3", "select * from zj_water_surface where grab_time LIKE '" + curDateStrY_m_d + "%'");
        wsMap2Model(maps);
    }
}
