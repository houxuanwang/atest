package com.arextest.agent.test.service.httpclient;

import com.arextest.agent.test.entity.httpclient.OkHttpTestResponseBody;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author daixq
 * @date 2022/11/07
 */
@Component
@Slf4j
public class OkHttpTestService extends HttpClientTestServiceBase {
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
//        .readTimeout(1, TimeUnit.MILLISECONDS)
            .build();
    ;

    public OkHttpTestResponseBody okHttpTest(String parameterData) {
        String asyncGetResponse = asyncGet();
        String asyncPostResponse = asyncPost(parameterData);
        String syncGetResponse = syncGet();
        String syncPostResponse = syncPost(parameterData);
        return new OkHttpTestResponseBody(asyncGetResponse, asyncPostResponse, syncGetResponse, syncPostResponse);
    }

    private String asyncGet() {
        Request request = new Request.Builder().get().url(GET_URL).build();
        Call call = okHttpClient.newCall(request);
        CompletableFuture<String> future = new CompletableFuture<>();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() == null) {
                    future.complete(String.format("asyncGet url: %s, response empty", GET_URL));
                } else {
                    future.complete("asyncGet response: " + Objects.requireNonNull(response.body()).string());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                future.complete(String.format("asyncGet url: %s, exception: %s", GET_URL, e.getMessage()));
            }
        });

        return future.join();
    }

    private String asyncPost(String parameterData) {
        RequestBody body = new FormBody.Builder()
                .add("userId", "5")
                .add("title", "Go")
                .add("body", "Advance")
                .add("input", parameterData)
                .build();
        Request request = new Request.Builder().post(body).url(POST_URL).build();
        Call call = okHttpClient.newCall(request);
        CompletableFuture<String> future = new CompletableFuture<>();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() == null) {
                    future.complete(String.format("asyncPost url: %s, response empty", POST_URL));
                } else {
                    future.complete("asyncPost response: " + Objects.requireNonNull(response.body()).string());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                future.complete(String.format("asyncPost url: %s, exception: %s", POST_URL, e.getMessage()));
            }
        });

        return future.join();
    }

    private String syncGet() {
        Request request = new Request.Builder().get().url(GET_URL).build();
        try {

            try (Response response = okHttpClient.newCall(request).execute()) {

                if (response.body() == null) {
                    return String.format("syncGet url: %s, response empty", GET_URL);
                }

                return "syncGet response: " + Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            return String.format("syncGet url: %s, exception: %s", GET_URL, e.getMessage());
        }
    }

    private String syncPost(String parameterData) {
        RequestBody body = new FormBody.Builder()
                .add("userId", "5")
                .add("title", "Go")
                .add("body", "Advance")
                .add("input", parameterData)
                .build();
        Request request = new Request.Builder().post(body).url(POST_URL).build();
        try {
            try (Response response = okHttpClient.newCall(request).execute()) {

                if (response.body() == null) {
                    return String.format("syncPost url: %s, response empty", POST_URL);
                }

                return "syncPost response: " + Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            return String.format("syncPost url: %s, exception: %s", POST_URL, e.getMessage());
        }
    }
}
