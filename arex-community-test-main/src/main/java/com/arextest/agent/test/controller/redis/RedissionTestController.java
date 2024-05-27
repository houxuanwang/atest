package com.arextest.agent.test.controller.redis;

import com.arextest.agent.test.entity.Request;
import com.arextest.agent.test.service.redis.RedissionTestService;
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
 * @date 2022/12/07
 */
@Controller
@RequestMapping(value = "/redissionTest")
public class RedissionTestController {
    @Autowired
    RedissionTestService redissionTestService;

    @RequestMapping(value = "/getList")
    @ResponseBody
    public Response testGetList(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "my-list" : request.getInput();
        return ResponseUtils.successResponse(
                redissionTestService.testGetList(param)
        );
    }

    @RequestMapping(value = "/getLock")
    @ResponseBody
    public Response testGetLock(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "my-lock" : request.getInput();
        return ResponseUtils.successResponse(
                redissionTestService.testGetLock(param)
        );
    }

    @RequestMapping(value = "/getId")
    @ResponseBody
    public Response testGetId() {
        return ResponseUtils.successResponse(
                redissionTestService.testGetId()
        );
    }

    @RequestMapping(value = "/isShuttingDown")
    @ResponseBody
    public Response testIsShuttingDown() {
        return ResponseUtils.successResponse(
                redissionTestService.testIsShuttingDown()
        );
    }

    @RequestMapping(value = "/isShutdown")
    @ResponseBody
    public Response testIsShutdown() {
        return ResponseUtils.successResponse(
                redissionTestService.testIsShutdown()
        );
    }

    @RequestMapping(value = "/getConfig")
    @ResponseBody
    public Response testGetConfig() {
        return ResponseUtils.successResponse(
                redissionTestService.testGetConfig()
        );
    }

    @RequestMapping(value = "/getAtomicLong")
    @ResponseBody
    public Response testGetAtomicLong() {
        return ResponseUtils.successResponse(
                redissionTestService.testGetAtomicLong()
        );
    }

    @RequestMapping(value = "/getAtomicDouble")
    @ResponseBody
    public Response testGetAtomicDouble() {
        return ResponseUtils.successResponse(
                redissionTestService.testGetAtomicDouble()
        );
    }

    @RequestMapping(value = "/getLongAdder")
    @ResponseBody
    public Response testGetLongAdder() {
        return ResponseUtils.successResponse(
                redissionTestService.testGetLongAdder()
        );
    }


    @RequestMapping(value = "/getDoubleAdder")
    @ResponseBody
    public Response testGetDoubleAdder() {
        return ResponseUtils.successResponse(
                redissionTestService.testGetDoubleAdder()
        );
    }


    @RequestMapping(value = "/renameException")
    @ResponseBody
    public Response testRenameException() {
        try {
            return ResponseUtils.successResponse(
                    redissionTestService.testRenameException()
            );
        } catch (Exception e) {
            return ResponseUtils.exceptionResponse(e.getMessage());
        }

    }

}
