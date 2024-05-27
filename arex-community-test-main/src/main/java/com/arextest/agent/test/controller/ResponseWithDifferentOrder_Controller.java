package com.arextest.agent.test.controller;

import com.arextest.common.model.response.Response;
import com.arextest.common.model.response.ResponseStatusType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("test")
public class ResponseWithDifferentOrder_Controller {

    @GetMapping("/get")
    @ResponseBody
    public String helloWorld() {
        return "Hello, Owen!";
    }
    @PostMapping("/post")
    @ResponseBody
    public TestResponse test(@RequestBody TestRequest request) {
        List<subClass> list = create_subclassList(request.x, request.y);
        Collections.shuffle(list);
        Body body = new Body();
        body.setClasses(list);
        body.setKey("1");
        body.setName("test");
        TestResponse testResponse=new TestResponse();
        testResponse.setBody(body);
        return testResponse;
    }


    public List<Integer> create_intlist(int y){
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<y ; i++){
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }

    public List<subClass> create_subclassList(int x,int y){
        List<subClass> list = new ArrayList<>();
        for (int i=0; i<x ; i++){
            subClass new_subclass = new subClass();
            new_subclass.setIntlist(create_intlist(y));
            new_subclass.setKey(i);
            new_subclass.setName("No." + i);
            list.add(new_subclass);
        }
        return list;
    }

    @Data
    private static class TestRequest {
        private int x;
        private int y;
    }

    @Data
    private static class TestResponse implements Response{
        private ResponseStatusType responseStatusType;
        private Body  body;
    }
    @Data
    private static class Body{
        private String name;
        private String key;
        private List<subClass> classes;

    }
    @Data
    private static class subClass{
        private String name;
        private int Key;
        private List<Integer> Intlist;
    }
}
