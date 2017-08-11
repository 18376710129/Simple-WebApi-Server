package cn.zry.test.webservice;

import cn.zry.modules.util.Encodes;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * Created by QL on 2015/12/16.
 */
public class TestLogin {
    public static void main(String[] args) {

        long s = System.currentTimeMillis();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1/login");
        //设置head头部信息
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
        String username = "15623885110";
        String password = "123456";
        String domain = "admin.test.cn";
        String auth = Encodes.encodeBase64((username + ":" + password + ":" + domain).getBytes());
        httpPost.addHeader("authorization", "Basic " + auth);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");

        try {
            HttpResponse res = httpClient.execute(httpPost);
            System.out.println(res.getStatusLine());
            String rtn = EntityUtils.toString(res.getEntity());
            System.out.println(rtn);

            System.out.println((System.currentTimeMillis() - s) + "ms");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
    }
}
