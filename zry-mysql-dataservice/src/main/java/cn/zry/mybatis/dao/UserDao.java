package cn.zry.mybatis.dao;

import cn.zry.modules.data.mysql.StandardDao;
import cn.zry.mybatis.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDao extends StandardDao<User, String> {

    User getUserByUsername(String userName);

}
