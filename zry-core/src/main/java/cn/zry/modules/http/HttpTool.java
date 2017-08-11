package cn.zry.modules.http;

import java.io.InputStream;

/**
 * Created by lenovo on 2017/5/26.
 */
public interface HttpTool {

    String doGet(String url);

    String doPost(String url, String params);

    InputStream doPostWithStream(String url, String params);
}
