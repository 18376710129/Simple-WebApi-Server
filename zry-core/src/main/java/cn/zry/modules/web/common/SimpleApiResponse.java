package cn.zry.modules.web.common;

import java.io.Serializable;

/**
 * Created by HQ on 2017/1/6.
 */
public class SimpleApiResponse extends ApiResponse implements Serializable {
    private static final long serialVersionUID = 7197759497047405602L;

    private Object data;        //返回数据

    public SimpleApiResponse() {
        super();
    }

    /**
     * 无返回数据时的构造器
     * data为null
     *
     * @param code
     * @param message
     */

    public SimpleApiResponse(String code, String message) {
        super(code, message);
        this.data = "";
    }

    /**
     * 返回数据时的构造器
     *
     * @param code
     * @param message
     * @param data
     */
    public SimpleApiResponse(String code, String message, Object data) {
        super(code, message);
        if (data != null)
            this.data = data;
        else
            this.data = "";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        if (data != null)
            this.data = data;
        else
            this.data = "";
    }
}
