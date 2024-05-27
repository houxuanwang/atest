package com.arextest.agent.test.controller;

import com.arextest.agent.test.util.ApacheHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JCasbinController {

    @GetMapping("/JCasbinHello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }

    @PostMapping("/JCasbinQuery")
    @ResponseBody
    public String query() {
        String result = ApacheHttpUtils.sendPost("http://jsonplaceholder.typicode.com/posts");
        log.info(String.format("ApacheHttp result = %s", result));
        return result;
    }

}
