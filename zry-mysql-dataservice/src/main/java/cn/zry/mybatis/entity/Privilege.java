package cn.zry.mybatis.entity;

import java.io.Serializable;

/**
 * 权限表
 */
public class Privilege implements Serializable {

    private static final long serialVersionUID = -24038715404955464L;

    private static final Integer ISMENU_TRUE = 1;           //是菜单
    private static final Integer ISMENU_FALSE = 0;          //不是菜单

    private String id;
    private String name;                //权限名称
    private String address;             //地址
    private String remark;              //说明
    private String pid;                 //父id
    private Integer isMenu;             //是否为菜单

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }
}
