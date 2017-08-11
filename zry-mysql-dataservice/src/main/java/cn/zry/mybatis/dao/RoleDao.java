package cn.zry.mybatis.dao;

import cn.zry.modules.data.mysql.StandardDao;
import cn.zry.mybatis.entity.Role;
import org.springframework.stereotype.Component;

@Component
public interface RoleDao extends StandardDao<Role, String> {

}
