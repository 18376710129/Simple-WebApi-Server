package cn.zry.modules.web.common;

import java.util.HashMap;
import java.util.Map;

public class IPage {

    public IPage() {
    }

    public IPage(int pageSize, int pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    private int pageSize = 10;                              //每页数目
    private int pageNumber = 1;                             //当前页
    private Map<String, String> param = new HashMap<>();    //传递参数

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageSize(int pageSize) {
        if (pageSize == 0)
            return;
        this.pageSize = pageSize;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber == 0)
            return;
        this.pageNumber = pageNumber;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public Map<String, String> getParam() {
        return param;
    }

    //for mybatis
    public int getLimitStart() {
        return (pageNumber - 1) * pageSize;
    }
}
