package cn.zry.mybatis.manager;

import cn.zry.modules.web.common.IPage;
import cn.zry.modules.web.common.TPage;
import cn.zry.modules.web.curd.StandardManager;
import cn.zry.mybatis.dao.UserDao;
import cn.zry.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lenovo on 2017/8/10.
 */
@Component
public class UserManager extends StandardManager<User, String> {

    @Autowired
    private UserDao userDao;

    public User getUserByUsername(String userName) {
        return userDao.getUserByUsername(userName);
    }

    @Override
    public TPage<User> search(IPage ip) {
        return null;
    }

    @Override
    public void deleteByIds(List<String> ids) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public void setTouch(List<String> ids, Integer types) {

    }
}
