package com.arextest.agent.test.entity.jedis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by rchen9 on 2023/3/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JedisTestHKeysResponseBody {
    private Set<String> stringHKeys;
    private Set<String> byteToStringSet;
}
