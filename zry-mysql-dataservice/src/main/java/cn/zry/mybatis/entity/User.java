package cn.zry.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 */
public class User implements Serializable {

    private static final long serialVersionUID = -2146374097534481544L;

    //启用状态
    public static final Integer STATUS_USING = 0;               //账号启用
    public static final Integer STATUS_UNUSE = -1;              //账号禁用

    private String id;
    private String username;            //用户名
    private String realName;            //姓名
    private String password;            //密码
    private String openId;              //微信OPENID
    private Date createDate;            //创建时间
    private Integer status;             //账号状态
    private String roleId;              //角色ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

}
