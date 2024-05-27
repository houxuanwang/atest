package com.arextest.agent.test.controller;

import com.arextest.agent.test.entity.HomeResponseBody;
import com.arextest.common.model.response.Response;
import com.arextest.common.utils.ResponseUtils;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author daixq
 * @date 2022/11/29
 */
@Controller
public class HomeController {

    @RequestMapping
    @ResponseBody
    public Response home() {
        String sVersion = SpringVersion.getVersion();
        String bVersion = SpringBootVersion.getVersion();
        return ResponseUtils.successResponse(new HomeResponseBody(sVersion, bVersion));
    }
}
