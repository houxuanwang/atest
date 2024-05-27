package com.arextest.agent.test.controller.redis;

import com.arextest.agent.test.entity.Request;
import com.arextest.agent.test.service.redis.LettuceTestService;
import com.arextest.common.model.response.Response;
import com.arextest.common.utils.ResponseUtils;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.util.annotation.Nullable;

@Controller
@RequestMapping(value = "/lettuceTest")
public class LettuceTestController {
    @Autowired
    LettuceTestService lettuceTestService;


    @RequestMapping(value = "/renameException")
    @ResponseBody
    public Response testRenameException(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "hkey1" : request.getInput();
        return ResponseUtils.successResponse(
                lettuceTestService.testRenameException(param)
        );
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public String testGet(@Nullable @RequestBody Request request) {
        String key = (request == null || StringUtil.isBlank(request.getInput())) ? "key1" : request.getInput();
        return lettuceTestService.testGet(key);
    }
}
