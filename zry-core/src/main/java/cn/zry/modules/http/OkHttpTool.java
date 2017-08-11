package cn.zry.modules.http;

import okhttp3.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Component
public class OkHttpTool implements HttpTool {

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client;

    public OkHttpTool() {
        client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public String doGet(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
        } catch (IOException e) {
            System.out.println("Response get failed: " + e);
        }
        return null;
    }

    public String doPost(String url, String params) {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_JSON, params))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
        } catch (IOException e) {
            System.out.println("Response post failed: " + e);
        }
        return null;
    }

    public InputStream doPostWithStream(String url, String params) {
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        StringEntity entity = null;
        try {
            entity = new StringEntity(params, "utf-8");
            post.setEntity(entity);
            HttpResponse httpResponse = closeableHttpClient.execute(post);
            InputStream input = httpResponse.getEntity().getContent();
            return input;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Response post failed: " + e);
        }
        return null;
    }

}
