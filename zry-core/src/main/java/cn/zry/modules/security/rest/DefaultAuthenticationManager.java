package cn.zry.modules.security.rest;

import cn.zry.modules.security.IUser;
import cn.zry.modules.security.IUserManager;
import cn.zry.modules.security.utils.Digests;
import cn.zry.modules.util.Encodes;
import org.apache.commons.lang3.StringUtils;

public class DefaultAuthenticationManager implements IAuthenticationManager {

    private IUserManager<? extends IUser> userManager;

    private boolean needMd5 = false;

    @Override
    public IUser login(String username, String password) {
        String pwd;
        IUser user = userManager.getUserByUserName(username);
        if (user == null)
            return null;

        pwd = encodePassword(user.getPassword());
        if (StringUtils.equals(pwd, password)) {
            return user;
        } else {
            return null;
        }
    }

    private String encodePassword(String plainPass) {
        if (plainPass != null && needMd5) {
            return Encodes.encodeHex(Digests.md5(plainPass.getBytes())).toUpperCase();
        } else {
            return plainPass;
        }
    }

    public void setUserManager(IUserManager<? extends IUser> userManager) {
        this.userManager = userManager;
    }
}
