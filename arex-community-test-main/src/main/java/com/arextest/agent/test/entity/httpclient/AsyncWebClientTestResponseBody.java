package com.arextest.agent.test.entity.httpclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rchen9 on 2023/3/21.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsyncWebClientTestResponseBody {
    private String AsyncGetResponse;
    private String AsyncPostResponse;
}
