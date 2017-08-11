package cn.zry.modules.security.rest;

import cn.zry.modules.security.IUser;

public interface IAuthenticationManager {

    IUser login(String username, String password);

}
