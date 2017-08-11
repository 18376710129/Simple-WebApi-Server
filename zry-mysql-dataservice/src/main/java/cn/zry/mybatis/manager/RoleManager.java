package cn.zry.mybatis.manager;

import cn.zry.modules.web.common.IPage;
import cn.zry.modules.web.common.TPage;
import cn.zry.modules.web.curd.StandardManager;
import cn.zry.mybatis.dao.RoleDao;
import cn.zry.mybatis.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lenovo on 2017/8/11.
 */
@Component
public class RoleManager extends StandardManager<Role, String> {

    @Autowired
    private RoleDao roleDao;

    @Override
    public TPage<Role> search(IPage iPage) {
        return null;
    }

    @Override
    public void deleteByIds(List<String> ids) {

    }

    @Override
    public void create(Role role) {

    }

    @Override
    public void update(Role role) {

    }

    @Override
    public Role get(String id) {
        return roleDao.get(id);
    }

    @Override
    public void setTouch(List<String> ids, Integer types) {

    }
}
