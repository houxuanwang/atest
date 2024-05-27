package com.arextest.agent.test.controller;

import com.arextest.agent.test.entity.Request;
import com.arextest.agent.test.service.netty.NettyTestService;
import com.arextest.common.model.response.Response;
import com.arextest.common.utils.ResponseUtils;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.util.annotation.Nullable;

/**
 * @author daixq
 * @date 2022/11/09
 */
@Controller
@RequestMapping(value = "/nettyTest")
public class NettyTestController {

    @Autowired
    private NettyTestService nettyTestService;

    @RequestMapping(value = "/nettyTest")
    @ResponseBody
    public Response nettyTest(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "Hello World!" : request.getInput();
        return ResponseUtils.successResponse(nettyTestService.nettyTest(param));
    }
}
