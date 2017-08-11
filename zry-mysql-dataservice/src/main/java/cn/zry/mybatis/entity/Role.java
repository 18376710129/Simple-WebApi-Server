package cn.zry.mybatis.entity;

import java.io.Serializable;

/**
 * 角色表
 */
public class Role implements Serializable {

    private static final long serialVersionUID = -4249924572156885176L;

    private String id;
    private String name;            //角色名称
    private String domain;          //当前角色可登陆的域名服务

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
