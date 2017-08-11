package cn.zry.mybatis.entity;

import java.io.Serializable;

/**
 * 角色权限关联表
 */
public class RolePrivilege implements Serializable {

    private static final long serialVersionUID = 2004558118250056479L;

    private String id;
    private String pid;     //权限ID
    private String rid;     //角色ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
