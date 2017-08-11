package cn.zry.modules.security;

public interface IUserManager<T extends IUser> {

    public T getUserByUserName(String userName);

}
