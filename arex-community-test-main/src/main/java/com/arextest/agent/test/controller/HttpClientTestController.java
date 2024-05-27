package com.arextest.agent.test.controller;

import com.arextest.agent.test.entity.Request;
import com.arextest.agent.test.service.httpclient.ApacheHttpClientTestService;
import com.arextest.agent.test.service.httpclient.CommonClientTestService;
import com.arextest.agent.test.service.httpclient.OkHttpTestService;
import com.arextest.agent.test.service.httpclient.RestTemplateTestService;
import com.arextest.agent.test.service.httpclient.WebClientTestService;
import com.arextest.common.model.response.Response;
import com.arextest.common.utils.ResponseUtils;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.util.annotation.Nullable;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author yongwuhe
 * @date 2022/11/01
 */
@Controller
@RequestMapping(value = "/httpClientTest")
public class HttpClientTestController {

    @Autowired
    CommonClientTestService commonClientTestService;

    @Autowired
    RestTemplateTestService restTemplateTestService;

    @Autowired
    OkHttpTestService okHttpTestService;

    @Autowired
    ApacheHttpClientTestService apacheHttpClientTestService;

    @Autowired
    WebClientTestService webClientTestService;

    @RequestMapping(value = "/commonClient")
    @ResponseBody
    public Response commonClientTest(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "{\"userId\": 3, \"title\": \"Programmer\", \"body\":\"C++\"}" : request.getInput();
        return ResponseUtils.successResponse(commonClientTestService.commonClientTest(param, false));
    }

    @RequestMapping(value = "/commonClientGzip")
    @ResponseBody
    public Response commonClientGzipTest(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "{\"userId\": 3, \"title\": \"Programmer\", \"body\":\"C++\"}" : request.getInput();
        return ResponseUtils.successResponse(commonClientTestService.commonClientTest(param, true));
    }

    @RequestMapping(value = "/restTemplate")
    @ResponseBody
    public Response restTemplate(@Nullable @RequestBody Request request) throws InterruptedException {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "Hi_Friend" : request.getInput();
        return ResponseUtils.successResponse(restTemplateTestService.restTemplateTest(param));
    }

    @RequestMapping(value = "/okHttp")
    @ResponseBody
    public Response okHttp(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "Hi_Friend" : request.getInput();
        return ResponseUtils.successResponse(okHttpTestService.okHttpTest(param));
    }

    @RequestMapping(value = "/apacheClient")
    @ResponseBody
    public Response apacheClient(@Nullable @RequestBody Request request) throws IOException, ExecutionException, InterruptedException {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "Hi_Friend" : request.getInput();
        return param.equals("Gzip") ?
                ResponseUtils.successResponse(apacheHttpClientTestService.testGzip()) :
                ResponseUtils.successResponse(apacheHttpClientTestService.test(param));
    }

    @RequestMapping(value = "/webClient")
    @ResponseBody
    public Response webClient(@Nullable @RequestBody Request request) throws InterruptedException {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "Hi_Friend" : request.getInput();
        return ResponseUtils.successResponse(webClientTestService.asyncWebClientTest(param));
    }
}
