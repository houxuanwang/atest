package com.arextest.agent.test.entity.netty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rchen9 on 2023/3/20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NettyTestResponseBody {
    private String inboundResponse;
    private String outboundResponse;
}
