package cn.zry.test.webservice;

import cn.zry.modules.http.OkHttpTool;
import cn.zry.modules.mapper.JsonMapper;
import cn.zry.modules.web.common.IPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * Lyf on 2017/8/10.
 */
public class TestArea {
    public static void main(String[] args) {
        String authorization = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MDI0Mzg2NDcsInN1YiI6IntcInNpZFwiOlwiOTZmYWQzZmI1ZjMzNGVjOWE1YjAyM2RkN2FjMDZlMjdcIn0iLCJleHAiOjE1MDI0Mzg3MDd9.gV2NRB6Z45wF8Vpjhm4zxKGE6zrmGinOryc_3hj5XKs";
        String url = "http://127.0.0.1/admin/area/list";

        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
        httpPost.addHeader("authorization", authorization);
        String rtn = null;
        try {
            HttpResponse res = closeableHttpClient.execute(httpPost);
            System.out.println(res.getStatusLine());
            rtn = EntityUtils.toString(res.getEntity());
            System.out.println(rtn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
    }
}
