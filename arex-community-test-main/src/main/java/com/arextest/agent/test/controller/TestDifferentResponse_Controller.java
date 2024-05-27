package com.arextest.agent.test.controller;

import com.arextest.common.model.response.Response;
import com.arextest.common.model.response.ResponseStatusType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


@Slf4j
@RestController
@RequestMapping("test1")
public class TestDifferentResponse_Controller {

    @GetMapping("/get1")
    @ResponseBody
    public Body helloWorld() {
        int upperbound = 10;
        Random rand = new Random();
        Body body = new Body();
        int rand_num = rand.nextInt(upperbound);
        body.setKey(rand_num);
        body.setList(create_different_list(rand_num));
        body.setName("test");
        return body;
    }
    public List<Integer> create_different_list(int x){
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<x ; i++){
            list.add(i);
        }
        return list;
    }
    @Data
    private static class Body {
        String name;
        int key;
        List<Integer> list;
    }
}