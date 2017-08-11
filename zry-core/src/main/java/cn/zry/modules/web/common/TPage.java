package cn.zry.modules.web.common;

import java.util.List;

/**
 * lyf on 2017/8/10.
 */
public class TPage<T> {
    private Long total;      //总数
    private List<T> list;       //当前页数据

    public TPage() {
    }

    public TPage(Long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
