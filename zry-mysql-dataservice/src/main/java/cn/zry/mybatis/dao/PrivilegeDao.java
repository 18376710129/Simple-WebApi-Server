package cn.zry.mybatis.dao;

import cn.zry.modules.data.mysql.StandardDao;
import cn.zry.mybatis.entity.Privilege;
import org.springframework.stereotype.Component;

@Component
public interface PrivilegeDao extends StandardDao<Privilege, String> {

}
