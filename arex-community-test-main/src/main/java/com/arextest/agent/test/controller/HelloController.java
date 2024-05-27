package com.arextest.agent.test.controller;

import com.arextest.agent.test.entity.ReqAndResResponseBody;
import com.arextest.agent.test.entity.Request;
import com.arextest.common.model.response.Response;
import com.arextest.common.utils.ResponseUtils;
import jodd.util.StringUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.util.annotation.Nullable;

/**
 * @author daixq
 * @date 2023/01/12
 */
@RestController
public class HelloController {
    @RequestMapping(value = "/admin/hello")
    public Response adminPost(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "admin" : request.getInput();
        return ResponseUtils.successResponse(new ReqAndResResponseBody("hello", param));
    }

    @RequestMapping(value = "/user/hello")
    public Response userPost(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "user" : request.getInput();
        return ResponseUtils.successResponse(new ReqAndResResponseBody("hello", param));
    }

    @RequestMapping(value = "/db/hello")
    public Response dbaPost(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "dba" : request.getInput();
        return ResponseUtils.successResponse(new ReqAndResResponseBody("hello", param));
    }

    @RequestMapping(value = "/hello")
    public Response helloPost(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "welcome" : request.getInput();
        return ResponseUtils.successResponse(new ReqAndResResponseBody("hello", param));
    }
}
