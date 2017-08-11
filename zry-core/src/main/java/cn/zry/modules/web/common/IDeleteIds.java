package cn.zry.modules.web.common;

/**
 * lyf on 2017/8/10.
 */
public class IDeleteIds<K> {

    private K[] ids;    //ID数组

    public IDeleteIds() {
    }

    public K[] getIds() {
        return ids;
    }

    public void setIds(K[] ids) {
        this.ids = ids;
    }
}
