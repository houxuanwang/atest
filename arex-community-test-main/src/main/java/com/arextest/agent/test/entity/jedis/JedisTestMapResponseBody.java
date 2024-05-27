package com.arextest.agent.test.entity.jedis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by rchen9 on 2023/3/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JedisTestMapResponseBody {
    private Map<String, String> stringMap;
    private Map<String, String> byteMap;
}
