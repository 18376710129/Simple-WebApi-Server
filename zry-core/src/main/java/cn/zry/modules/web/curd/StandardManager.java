package cn.zry.modules.web.curd;

import cn.zry.modules.web.common.IPage;
import cn.zry.modules.web.common.TPage;

import java.util.List;

public abstract class StandardManager<T, K> {

    public abstract TPage<T> search(IPage iPage);

    public abstract void deleteByIds(List<K> ids);

    //~~~~~~~~~~~ Optional method ~~~~~~~~~~~

    public abstract void create(T t);

    public abstract void update(T t);

    public abstract T get(K id);

    public abstract void setTouch(List<K> ids, Integer types);

}
