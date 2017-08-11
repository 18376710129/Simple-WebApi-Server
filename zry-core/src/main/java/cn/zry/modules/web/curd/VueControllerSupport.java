package cn.zry.modules.web.curd;

import cn.zry.modules.web.BaseController;
import cn.zry.modules.web.common.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

public abstract class VueControllerSupport<T, K> extends BaseController {

    //该方法如果在泛型参数中将Manager的实现类型传递进来的话，可以通过反射直接实现。
    //但是这样在实现Action的时候可能会带来灵活性的减弱，因此不如采用这种简洁的形式
    protected abstract StandardManager<T, K> getStandardManager();

    /**
     * 新增数据
     */
    @ResponseBody
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object save(@RequestBody T obj) {
        getStandardManager().create(obj);
        return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "返回成功");
    }

    /**
     * 更新数据
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object update(@RequestBody T obj) {
        getStandardManager().update(obj);
        return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "返回成功");
    }

    /**
     * 更新状态
     */
    @ResponseBody
    @RequestMapping(value = "touch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object setTouch(@RequestBody ITouch<K> iTouch) {
        K[] sids = iTouch.getIds();
        Integer type = iTouch.getType();
        if (sids == null || sids.length == 0 || type == null)
            return new SimpleApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数错误");
        List<K> ids = Arrays.asList(sids);
        if (ids.size() > 0)
            getStandardManager().setTouch(ids, type);
        return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "返回成功");
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object delete(@RequestBody IDeleteIds<K> iDeleteIds) {
        K[] sids = iDeleteIds.getIds();
        if (sids == null || sids.length == 0)
            return new SimpleApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数错误");
        List<K> ids = Arrays.asList(sids);
        if (ids.size() > 0)
            getStandardManager().deleteByIds(ids);
        return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "返回成功");
    }

    /**
     * 根据ID查询info
     */
    @ResponseBody
    @RequestMapping(value = "get/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getById(@PathVariable("id") K id) {
        if (id == null || id.equals(""))
            return new SimpleApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数错误");
        return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "返回成功", getStandardManager().get(id));
    }


    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object list(@RequestBody IPage iPage) {
        return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "返回成功", getStandardManager().search(iPage));
    }
}
