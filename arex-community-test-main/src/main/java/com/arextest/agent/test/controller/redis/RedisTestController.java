package com.arextest.agent.test.controller.redis;

import com.arextest.agent.test.config.RedisConfig;
import com.arextest.agent.test.entity.Request;
import com.arextest.agent.test.service.redis.JedisTestService;
import com.arextest.common.model.response.Response;
import com.arextest.common.utils.ResponseUtils;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.util.annotation.Nullable;

import javax.annotation.PostConstruct;

/**
 * @author yongwuhe
 * @date 2022/10/20
 * the redis configuration can be found in application.properties
 */
@Controller
@RequestMapping(value = "/redisTest")
public class RedisTestController {
    private JedisTestService jedisTestService;

    @Autowired
    RedisConfig redisConfig;

    @PostConstruct
    public void init() {
        jedisTestService = JedisTestService.getInstance(redisConfig.getHost(), redisConfig.getPort());
    }

    @RequestMapping(value = "/jedis/set")
    @ResponseBody
    public Response testRedisSet(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "" : request.getInput();
        return ResponseUtils.successResponse(
                jedisTestService.testSet(param)
        );
    }

    @RequestMapping(value = "/jedis/setWithParams")
    @ResponseBody
    public Response setWithSetParams() {
        return ResponseUtils.successResponse(
                jedisTestService.testSetWithParams()
        );
    }

    @RequestMapping(value = "/jedis/get")
    @ResponseBody
    public Response testRedisGet() {
        return ResponseUtils.successResponse(
                jedisTestService.testGet()
        );
    }

    @RequestMapping(value = "/jedis/getEx")
    @ResponseBody
    public Response testRedisGetEx() {
        return ResponseUtils.successResponse(
                jedisTestService.testGetEx()
        );
    }

    @RequestMapping(value = "/jedis/getDel")
    @ResponseBody
    public Response testRedisGetDel() {
        return ResponseUtils.successResponse(
                jedisTestService.testGetDel()
        );
    }

    @RequestMapping(value = "/jedis/copy")
    @ResponseBody
    public Response testRedisCopy() {
        return ResponseUtils.successResponse(
                jedisTestService.testCopy()
        );
    }

    @RequestMapping(value = "/jedis/del")
    @ResponseBody
    public Response testRedisDel() {
        return ResponseUtils.successResponse(
                jedisTestService.testDel()
        );
    }

    @RequestMapping(value = "/jedis/delMultiKeys")
    @ResponseBody
    public Response testRedisDel2() {
        return ResponseUtils.successResponse(
                jedisTestService.testDelMultiKeys()
        );
    }

    @RequestMapping(value = "/jedis/ping")
    @ResponseBody
    public Response testRedisPing() {
        return ResponseUtils.successResponse(
                jedisTestService.testPing()
        );
    }

    @RequestMapping(value = "/jedis/pingWithParam")
    @ResponseBody
    public Response testRedisPingWithParam() {
        return ResponseUtils.successResponse(
                jedisTestService.testPingWithParameter()
        );
    }

    @RequestMapping(value = "/jedis/exists")
    @ResponseBody
    public Response testExists() {
        return ResponseUtils.successResponse(
                jedisTestService.testExists()
        );
    }

    @RequestMapping(value = "/jedis/unlink")
    @ResponseBody
    public Response testUnlink() {
        return ResponseUtils.successResponse(
                jedisTestService.testUnlink()
        );
    }

    @RequestMapping(value = "/jedis/unlinkMultiKeys")
    @ResponseBody
    public Response testUnlinkMultiKeys() {
        return ResponseUtils.successResponse(
                jedisTestService.testUnlinkMultiKeys()
        );
    }

    @RequestMapping(value = "/jedis/type")
    @ResponseBody
    public Response testType() {
        return ResponseUtils.successResponse(
                jedisTestService.testType()
        );
    }

    @RequestMapping(value = "/jedis/rename")
    @ResponseBody
    public Response testRename() {
        return ResponseUtils.successResponse(
                jedisTestService.testRename()
        );
    }

    @RequestMapping(value = "/jedis/renameException")
    @ResponseBody
    public Response testRenameException() {
        try {
            return ResponseUtils.successResponse(
                    jedisTestService.testRenameException()
            );
        } catch (Exception e) {
            return ResponseUtils.exceptionResponse(
                    e.getMessage()
            );
        }
    }

    @RequestMapping(value = "/jedis/renameNx")
    @ResponseBody
    public Response testRenameNx() {
        return ResponseUtils.successResponse(
                jedisTestService.testRenameNx()
        );
    }

    @RequestMapping(value = "/jedis/expire")
    @ResponseBody
    public Response testExpire() {
        return ResponseUtils.successResponse(
                jedisTestService.testExpire()
        );
    }

    @RequestMapping(value = "/jedis/expireTime")
    @ResponseBody
    public Response testExpireTime() {
        return ResponseUtils.successResponse(
                jedisTestService.testExpireTime()
        );
    }

    @RequestMapping(value = "/jedis/append")
    @ResponseBody
    public Response testAppend() {
        return ResponseUtils.successResponse(
                jedisTestService.testAppend()
        );
    }

    @RequestMapping(value = "/jedis/substr")
    @ResponseBody
    public Response testSubStr() {
        return ResponseUtils.successResponse(
                jedisTestService.testSubStr()
        );
    }

    @RequestMapping(value = "/jedis/hset")
    @ResponseBody
    public Response testHSet() {
        return ResponseUtils.successResponse(
                jedisTestService.testHSet()
        );
    }

    @RequestMapping(value = "/jedis/hget")
    @ResponseBody
    public Response testHGet() {
        return ResponseUtils.successResponse(
                jedisTestService.testHGet()
        );
    }

    @RequestMapping(value = "/jedis/hsetnx")
    @ResponseBody
    public Response testHSetNx() {
        return ResponseUtils.successResponse(
                jedisTestService.testHSetNx()
        );
    }

    @RequestMapping(value = "/jedis/hmset")
    @ResponseBody
    public Response testHMSet() {
        return ResponseUtils.successResponse(
                jedisTestService.testHMSet()
        );
    }

    @RequestMapping(value = "/jedis/hmget")
    @ResponseBody
    public Response testHMGet() {
        return ResponseUtils.successResponse(
                jedisTestService.testHMGet()
        );
    }

    @RequestMapping(value = "/jedis/hexists")
    @ResponseBody
    public Response testHExists() {
        return ResponseUtils.successResponse(
                jedisTestService.testHExists()
        );
    }

    @RequestMapping(value = "/jedis/hdel")
    @ResponseBody
    public Response testHDel() {
        return ResponseUtils.successResponse(
                jedisTestService.testHDel()
        );
    }

    @RequestMapping(value = "/jedis/hlen")
    @ResponseBody
    public Response testHlen() {
        return ResponseUtils.successResponse(
                jedisTestService.testHLen()
        );
    }

    @RequestMapping(value = "/jedis/hkeys")
    @ResponseBody
    public Response testHKeys() {
        return ResponseUtils.successResponse(
                jedisTestService.testHKeys()
        );
    }

    @RequestMapping(value = "/jedis/hvals")
    @ResponseBody
    public Response testHVals() {
        return ResponseUtils.successResponse(
                jedisTestService.testHVals()
        );
    }

    @RequestMapping(value = "/jedis/hgetAll")
    @ResponseBody
    public Response testHGetAll() {
        return ResponseUtils.successResponse(
                jedisTestService.testHGetAll()
        );
    }

    @RequestMapping(value = "/jedis/hrandfield")
    @ResponseBody
    public Response testHRandField() {
        return ResponseUtils.successResponse(
                jedisTestService.testHRandField()
        );
    }
}
