package cn.zry.mybatis.manager;

import cn.zry.modules.util.Identities;
import cn.zry.modules.web.common.IPage;
import cn.zry.modules.web.common.TPage;
import cn.zry.modules.web.curd.StandardManager;
import cn.zry.mybatis.dao.AreaDao;
import cn.zry.mybatis.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lyf on 2017/8/10.
 */
@Component
public class AreaManager extends StandardManager<Area, String> {

    @Autowired
    private AreaDao areaDao;

    @Override
    public TPage<Area> search(IPage iPage) {
        if (iPage == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("limitStart", iPage.getLimitStart());
        map.put("pageSize", iPage.getPageSize());
        if (iPage.getParam().get("orderField") != null && iPage.getParam().get("orderRule") != null) {
            map.put("orderField", iPage.getParam().get("orderField"));
            map.put("orderRule", iPage.getParam().get("orderRule"));
        }
        return new TPage<>(areaDao.count(), areaDao.searchPage(map));
    }

    @Override
    public void deleteByIds(List<String> ids) {
        if (ids != null || ids.size() > 0) {
            areaDao.deleteByIds(ids);
        }
    }

    @Override
    public void create(Area area) {
        if (area != null) {
            if (area.getId() == null || area.getId().trim().equals("")) {
                area.setId(Identities.uuid2());
            }
            areaDao.save(area);
        }
    }

    @Override
    public void update(Area area) {
        if (area != null && area.getId() != null) {
            areaDao.update(area);
        }
    }

    @Override
    public Area get(String id) {
        return areaDao.get(id);
    }

    @Override
    public void setTouch(List<String> ids, Integer types) {

    }
}
