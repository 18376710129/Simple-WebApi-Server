package cn.zry.mybatis.manager;


import cn.zry.modules.security.IUser;
import cn.zry.modules.security.IUserManager;
import cn.zry.mybatis.dao.RoleDao;
import cn.zry.mybatis.entity.Role;
import cn.zry.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthUserManager implements IUserManager<IUser> {

    @Autowired
    private UserManager userManager;
    @Autowired
    private RoleManager roleManager;

    @Override
    public IUser getUserByUserName(String userName) {
        final User curUser = userManager.getUserByUsername(userName);
        if (curUser == null) return null;
        final Role curRole = roleManager.get(curUser.getRoleId());
        IUser iuser = new IUser() {
            @Override
            public String getId() {
                return curUser.getId();
            }

            @Override
            public String getUsername() {
                return curUser.getUsername();
            }

            @Override
            public String getRealName() {
                return curUser.getRealName();
            }

            @Override
            public String getPassword() {
                return curUser.getPassword();
            }

            @Override
            public Integer getStatus() {
                return curUser.getStatus();
            }

            @Override
            public String getRoleId() {
                return curUser.getRoleId();
            }

            @Override
            public String getRoleDomain() {
                return curRole != null ? (curRole.getDomain() != null ? curRole.getDomain() : "") : "";
            }
        };
        return iuser;
    }
}