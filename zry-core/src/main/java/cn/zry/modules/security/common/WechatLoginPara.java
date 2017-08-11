package cn.zry.modules.security.common;

/**
 * author jbp
 * date ${date}
 * Created by Administrator on 2017/5/15.
 */
public class WechatLoginPara {

    private String id;

    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

