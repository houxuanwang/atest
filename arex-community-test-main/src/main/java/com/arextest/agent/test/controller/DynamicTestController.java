package com.arextest.agent.test.controller;

import com.arextest.agent.test.entity.dynamic.FutureResponseBody;
import com.arextest.agent.test.entity.Mealrecomrestaurant;
import com.arextest.agent.test.entity.Request;
import com.arextest.agent.test.mapper.MybatisPlusMapper;
import com.arextest.agent.test.service.DynamicService;
import com.arextest.common.model.response.Response;
import com.arextest.common.utils.ResponseUtils;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.util.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Controller
@Slf4j
@RequestMapping(value = "/dynamicTest")
public class DynamicTestController {

    @Autowired
    MybatisPlusMapper plusMapper;
    @Autowired
    DynamicService<Integer> dynamicService;

    @RequestMapping(value = "/testException")
    @ResponseBody
    public String testException(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "D:/test.txt" : request.getInput();
        try {
            return dynamicService.readFile(param);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/testRandomInt")
    @ResponseBody
    public Response testRandomInt(@Nullable @RequestBody Request request) {
        try {
            return ResponseUtils.successResponse(dynamicService.getRandomInt());
        } catch (Exception e) {
            return ResponseUtils.exceptionResponse(e.getMessage());
        }
    }

    @RequestMapping(value = "/testUuid")
    @ResponseBody
    public Response testUuid(@Nullable @RequestBody Request request) {
        try {
            return ResponseUtils.successResponse(dynamicService.getUuid());
        } catch (Exception e) {
            return ResponseUtils.exceptionResponse(e.getMessage());
        }
    }

    @RequestMapping(value = "/testCurrentTime")
    @ResponseBody
    public Response testCurrentTime(@Nullable @RequestBody Request request) {
        try {
            return ResponseUtils.successResponse(dynamicService.getCurrentTime());
        } catch (Exception e) {
            return ResponseUtils.exceptionResponse(e.getMessage());
        }
    }

    @RequestMapping(value = "/testOptionClass")
    @ResponseBody
    public Response testOptionClass(@Nullable @RequestBody Request request) {
        try {
            Integer value = 300;
            return ResponseUtils.successResponse(dynamicService.optionTest(value));
        } catch (Exception e) {
            return ResponseUtils.exceptionResponse(e.getMessage());
        }
    }

    @RequestMapping(value = "/testReturnCompletableFuture")
    @ResponseBody
    public Response testReturnCompletableFuture() {
        CompletableFuture<List<Mealrecomrestaurant>> future = dynamicService.getRestaurants();
        future.whenComplete(((restaurants, throwable) -> {
            if (throwable != null) {
                log.error("testReturnCompletableFuture", throwable);
            }
        }));
        String restaurantNumber = "query failed";
        try {
            restaurantNumber = String.valueOf(future.get().size());
        } catch (Exception ex) {
            log.error("future.get", ex);
        }
        return ResponseUtils.successResponse(
                new FutureResponseBody(restaurantNumber)
        );
    }

    @RequestMapping(value = "/testReturnFuture")
    @ResponseBody
    public Response testReturnFuture() {
        Future<List<Mealrecomrestaurant>> future = dynamicService.getRestaurantsAsFuture();

        String restaurantNumber = "query failed";

        try {
            restaurantNumber = String.valueOf(future.get().size());
        } catch (Exception ex) {
            log.error("future.get", ex);
        }
        return ResponseUtils.successResponse(
                new FutureResponseBody(restaurantNumber)
        );
    }

    @RequestMapping(value = "/testReturnListenableFuture")
    @ResponseBody
    public Response testReturnListenableFuture() {
        com.google.common.util.concurrent.ListenableFuture<List<Mealrecomrestaurant>> listenableFuture
                = dynamicService.getRestaurantsAsListenableFuture();

        String restaurantNumber = "query failed";

        try {
            restaurantNumber = String.valueOf(listenableFuture.get().size());
        } catch (InterruptedException e) {
            log.error("future.get InterruptedException", e);
        } catch (ExecutionException e) {
            log.error("future.get ExecutionException", e);
        }
        return ResponseUtils.successResponse(new FutureResponseBody(restaurantNumber));
    }

    @RequestMapping(value = "/testSpringCache")
    @ResponseBody
    public Response testSpringCache() {
        return ResponseUtils.successResponse(dynamicService.testCacheableWithoutParameter());
    }

    @RequestMapping(value = "/testCacheableWithReturnVoid")
    @ResponseBody
    public Response testCacheableWithReturnVoid() {
        dynamicService.testCacheableWithReturnVoid();
        return ResponseUtils.successResponse("testCacheableWithReturnVoid");
    }

    @RequestMapping(value = "/testCacheableWithEmptyKey")
    @ResponseBody
    public Response testCacheableWithEmptyKey() {
        return ResponseUtils.successResponse(dynamicService.testCacheableWithEmptyKey("FOOD"));
    }

    @RequestMapping(value = "/testCacheableWithKey")
    @ResponseBody
    public Response testCacheableWithKey() {
        return ResponseUtils.successResponse(dynamicService.testCacheableWithKey("FOOD", "FOOD2"));
    }

    @RequestMapping(value = "/testArexMockWithoutParameter")
    @ResponseBody
    public Response testArexMockWithoutParameter() {
        return ResponseUtils.successResponse(dynamicService.testArexMockWithoutParameter());
    }

    @RequestMapping(value = "/testArexMockWithReturnVoid")
    @ResponseBody
    public Response testArexMockWithReturnVoid() {
        dynamicService.testArexMockWithReturnVoid();
        return ResponseUtils.successResponse("testArexMockWithReturnVoid");
    }

    @RequestMapping(value = "/testArexMockWithEmptyKey")
    @ResponseBody
    public Response testArexMockWithEmptyKey() {
        return ResponseUtils.successResponse(dynamicService.testArexMockWithEmptyKey("FOOD"));
    }

    @RequestMapping(value = "/testArexMockWithKey")
    @ResponseBody
    public Response testArexMockWithKey() {
        return ResponseUtils.successResponse(dynamicService.testArexMockWithKey("FOOD", "FOOD2"));
    }

}
