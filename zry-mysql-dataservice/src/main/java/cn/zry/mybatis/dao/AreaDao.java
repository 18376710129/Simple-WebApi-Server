package cn.zry.mybatis.dao;

import cn.zry.modules.data.mysql.StandardDao;
import cn.zry.mybatis.entity.Area;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AreaDao extends StandardDao<Area, String> {
    List<Area> selectAll();
}
