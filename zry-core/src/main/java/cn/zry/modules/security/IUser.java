package cn.zry.modules.security;

public interface IUser {

    Integer STATUS_USING = 0;                // 启用
    Integer STATUS_UNUSE = -1;               // 禁用

    String getId();

    String getUsername();

    String getRealName();

    String getPassword();

    Integer getStatus();

    String getRoleId();

    String getRoleDomain();
}
