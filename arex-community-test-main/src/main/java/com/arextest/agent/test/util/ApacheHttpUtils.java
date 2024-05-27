package com.arextest.agent.test.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ApacheHttpUtils {
    public static String sendPost(String url) {

        String result = "";
        HttpPost post = new HttpPost(url);

        // add request parameters or form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username", "abc"));
        urlParameters.add(new BasicNameValuePair("password", "123"));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)) {

                result = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            log.error("ApacheHttp exception", e);
            e.printStackTrace();
        }

        return result;
    }

}
