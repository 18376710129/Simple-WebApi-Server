package cn.zry.modules.web.common;

/**
 * lyf on 2017/8/10.
 */
public class ITouch<K> {

    private K[] ids;        //批量的ID
    private Integer type;   //需要修改的状态

    public ITouch() {
    }

    public K[] getIds() {
        return ids;
    }

    public void setIds(K[] ids) {
        this.ids = ids;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
