package cn.zry.modules.security.rest;


import cn.zry.modules.security.IUser;
import cn.zry.modules.util.Identities;

import java.util.Date;

public class Session implements java.io.Serializable {

    private static final long serialVersionUID = 2187539611259212536L;

    private String sid;
    private String uid;
    private String username;
    private String realName;
    private String password;
    private Integer status;
    private String roleId;
    private String roleUrl;
    private Date since = new Date();

    public Session() {
        this(null);
    }

    public Session(IUser user) {
        this.sid = Identities.uuid2();
        if (user != null) {
            this.uid = user.getId();
            this.username = user.getUsername();
            this.realName = user.getRealName();
            this.password = user.getPassword();
            this.status = user.getStatus();
            this.roleId = user.getRoleId();
        }
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleUrl() {
        return roleUrl;
    }

    public void setRoleUrl(String roleUrl) {
        this.roleUrl = roleUrl;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }
}
