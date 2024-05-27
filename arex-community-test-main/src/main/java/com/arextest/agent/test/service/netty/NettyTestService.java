package com.arextest.agent.test.service.netty;

import com.arextest.agent.test.entity.netty.NettyTestResponseBody;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author daixq
 * @date 2022/11/09
 */
@Component
@Slf4j
public class NettyTestService {

    private static final String ERR_ILLEGAL_LEN = "IllegalAccessException for FixedLengthFrameDecoder";

    public NettyTestResponseBody nettyTest(String inputStr) {
        String inboundResponse = inboundTest(inputStr);
        String outboundResponse = outboundTest();
        return new NettyTestResponseBody(inboundResponse, outboundResponse);
    }

    /**
     * test in message
     */
    public String inboundTest(String inputStr) {
        ByteBuf buf = Unpooled.copiedBuffer(inputStr, CharsetUtil.UTF_8);
        ByteBuf input = buf.duplicate();

        EmbeddedChannel channel;
        try {
            channel = new EmbeddedChannel(new FixedLengthFrameDecoder(6));
        } catch (IllegalAccessException e) {
            log.error(ERR_ILLEGAL_LEN, e);
            return ERR_ILLEGAL_LEN;
        }
        // write to EmbeddedChannel
        channel.writeInbound(input.retain());
        channel.finish();

        ByteBuf read = channel.readInbound();
        String first = read.toString(CharsetUtil.UTF_8);
        read.release();

        read = channel.readInbound();
        String second = read.toString(CharsetUtil.UTF_8);
        read.release();
        buf.release();
        return first + second;
    }

    /**
     * test out message
     * change negative int to abs
     */
    public String outboundTest() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 1; i <= 5; i++) {
            buf.writeInt(i * -1);
        }
        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        channel.writeOutbound(buf);
        channel.finish();
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            result.append(channel.readOutbound().toString());
        }
        return result.toString();
    }
}
