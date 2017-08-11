package cn.zry.server.webapi;

import cn.zry.modules.web.curd.StandardManager;
import cn.zry.modules.web.curd.VueControllerSupport;
import cn.zry.mybatis.entity.Area;
import cn.zry.mybatis.manager.AreaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Lyf on 2017/8/10.
 */
@Controller
@RequestMapping(value = "admin/area")
public class AreaController extends VueControllerSupport<Area,String>{

    @Autowired
    private AreaManager areaManager;

    @Override
    protected StandardManager<Area, String> getStandardManager() {
        return areaManager;
    }
}
