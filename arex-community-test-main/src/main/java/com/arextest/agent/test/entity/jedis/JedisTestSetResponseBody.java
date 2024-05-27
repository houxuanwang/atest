package com.arextest.agent.test.entity.jedis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rchen9 on 2023/3/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JedisTestSetResponseBody {
    private String stringSet;
    private String byteSet;
}
