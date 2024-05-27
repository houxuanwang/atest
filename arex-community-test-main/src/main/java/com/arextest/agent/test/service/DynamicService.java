package com.arextest.agent.test.service;

import com.arextest.common.annotation.ArexMock;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import com.arextest.agent.test.entity.dynamic.CurrentTimeResponseBody;
import com.arextest.agent.test.entity.Mealrecomrestaurant;
import com.arextest.agent.test.entity.dynamic.OptionTestResponseBody;
import com.arextest.agent.test.entity.dynamic.RandomIntResponseBody;
import com.arextest.agent.test.entity.dynamic.UuidResponseBody;
import com.arextest.agent.test.mapper.MybatisPlusMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
@ArexMock
public class DynamicService<T> {

    public DynamicService(){
        getRandomInt();
        getUuid();
        getCurrentTime();
    }

    @Autowired
    MybatisPlusMapper plusMapper;

    public String readFile(String fileName) {
        File file = new File(fileName);
        throw new RuntimeException(fileName + "FileNotFoundException 2");
    }

    public CompletableFuture<List<Mealrecomrestaurant>> getRestaurants() {
        return CompletableFuture.supplyAsync(
                () -> {
                    QueryWrapper<Mealrecomrestaurant> queryWrapper = new QueryWrapper<>();
                    queryWrapper.lambda().eq(Mealrecomrestaurant::getRestaurantName, "FOOD");
                    return plusMapper.selectList(queryWrapper);
                });
    }

    public Future<List<Mealrecomrestaurant>> getRestaurantsAsFuture() {
        return java.util.concurrent.Executors.newSingleThreadExecutor().submit(
                () -> {
                    QueryWrapper<Mealrecomrestaurant> queryWrapper = new QueryWrapper<>();
                    queryWrapper.lambda().eq(Mealrecomrestaurant::getRestaurantName, "FOOD");
                    return plusMapper.selectList(queryWrapper);
                });
    }

    public ListenableFuture<List<Mealrecomrestaurant>> getRestaurantsAsListenableFuture() {
        return  MoreExecutors.listeningDecorator(java.util.concurrent.Executors.newCachedThreadPool()).submit(
                () -> {
                    QueryWrapper<Mealrecomrestaurant> queryWrapper = new QueryWrapper<>();
                    queryWrapper.lambda().eq(Mealrecomrestaurant::getRestaurantName, "FOOD");
                    return plusMapper.selectList(queryWrapper);
                });
        }

    public RandomIntResponseBody getRandomInt() {
        Random rm = new Random();
        int n = rm.nextInt();
        return new RandomIntResponseBody(n);
    }

    public UuidResponseBody getUuid() {
        String uuid = UUID.randomUUID().toString();
        return new UuidResponseBody(uuid);
    }

    public CurrentTimeResponseBody getCurrentTime() {
        return new CurrentTimeResponseBody(java.lang.System.currentTimeMillis());
    }

    public OptionTestResponseBody optionTest(T t){
        return new OptionTestResponseBody<T>(t);
    }

    @Cacheable(value = "testCache")
    public List<Mealrecomrestaurant> testCacheableWithoutParameter() {
      QueryWrapper<Mealrecomrestaurant> queryWrapper = new QueryWrapper<>();
      queryWrapper.lambda().eq(Mealrecomrestaurant::getRestaurantName,"FOOD");
      return plusMapper.selectList(queryWrapper);
    }

    @Cacheable(value = "testCache", key = "'testCacheableWithReturnVoid'")
    public void testCacheableWithReturnVoid() {
        System.out.println("testCacheableWithReturnVoid");
    }

    @Cacheable(value = "testCache", key = "")
    public List<Mealrecomrestaurant> testCacheableWithEmptyKey(String name) {
        QueryWrapper<Mealrecomrestaurant> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Mealrecomrestaurant::getRestaurantName, name);
        return plusMapper.selectList(queryWrapper);
    }

    @Cacheable(value = "testCache", key = "#name + #name2")
    public List<Mealrecomrestaurant> testCacheableWithKey(String name, String name2) {
        QueryWrapper<Mealrecomrestaurant> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Mealrecomrestaurant::getRestaurantName, name);
        return plusMapper.selectList(queryWrapper);
    }

    @ArexMock
    public List<Mealrecomrestaurant> testArexMockWithoutParameter() {
        QueryWrapper<Mealrecomrestaurant> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Mealrecomrestaurant::getRestaurantName,"FOOD");
        return plusMapper.selectList(queryWrapper);
    }

    @ArexMock(key = "'testCacheableWithReturnVoid'")
    public void testArexMockWithReturnVoid() {
        System.out.println("testArexMockWithReturnVoid");
    }

    @ArexMock(key = "")
    public List<Mealrecomrestaurant> testArexMockWithEmptyKey(String name) {
        QueryWrapper<Mealrecomrestaurant> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Mealrecomrestaurant::getRestaurantName, name);
        return plusMapper.selectList(queryWrapper);
    }

    @ArexMock(key = "#name + #name2")
    public List<Mealrecomrestaurant> testArexMockWithKey(String name, String name2) {
        QueryWrapper<Mealrecomrestaurant> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Mealrecomrestaurant::getRestaurantName, name);
        return plusMapper.selectList(queryWrapper);
    }


}
