package com.arextest.agent.test.controller;

import com.arextest.agent.test.entity.Request;
import com.arextest.agent.test.service.MybatisTestService;
import com.arextest.agent.test.service.hibernate.HibernateTestService;
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
 * @author yongwuhe
 * @date 2022/11/05
 * to use mysql or h2, configure it in application.properties. by default, use mysql.
 */
@Controller
@RequestMapping(value = "/dbTest")
public class DbTestController {
    @Autowired
    MybatisTestService mybatisTestService;

    @Autowired
    HibernateTestService hibernateTestService;

    @RequestMapping(value = "/mybatis/query")
    @ResponseBody
    public Response mybatisQueryTest(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "ravioli" : request.getInput();
        return ResponseUtils.successResponse(
                mybatisTestService.testMybatisQuery(param)
        );
    }

    @RequestMapping(value = "/mybatis/insert")
    @ResponseBody
    public Response mybatisInsertTest() {
        return ResponseUtils.successResponse(
                mybatisTestService.testMybatisInsert()
        );
    }

    @RequestMapping(value = "/mybatis/delete")
    @ResponseBody
    public Response mybatisDeleteTest() {
        return ResponseUtils.successResponse(
                mybatisTestService.testMybatisDelete()
        );
    }

    @RequestMapping(value = "/mybatis/update")
    @ResponseBody
    public Response mybatisUpdateTest() {
        return ResponseUtils.successResponse(
                mybatisTestService.testMybatisUpdate()
        );
    }

    @RequestMapping(value = "/mybatis/foreachUpdate")
    @ResponseBody
    public Response mybatisForeachUpdateTest() {
        return ResponseUtils.successResponse(
                mybatisTestService.testMybatisForeachUpdate()
        );
    }

    @RequestMapping(value = "/mybatis/batchInsert")
    @ResponseBody
    public Response mybatisBatchInsertTest() {
        return ResponseUtils.successResponse(
                mybatisTestService.testMybatisBatchInsert()
        );
    }

    @RequestMapping(value = "/mybatis/batchQuery")
    @ResponseBody
    public Response mybatisBatchQueryTest() {
        return ResponseUtils.successResponse(
                mybatisTestService.testMybatisBatchQuery()
        );
    }

    @RequestMapping(value = "/mybatis/batchUpdate")
    @ResponseBody
    public Response mybatisBatchUpdateTest() {
        return ResponseUtils.successResponse(
                mybatisTestService.testMybatisBatchUpdate()
        );
    }

    @RequestMapping(value = "/hibernate/save")
    @ResponseBody
    public Response hibernateSaveTest() {
        return ResponseUtils.successResponse(
                hibernateTestService.testHibernateSave()
        );
    }

    @RequestMapping(value = "/hibernate/saveAll")
    @ResponseBody
    public Response hibernateSaveAllTest() {
        return ResponseUtils.successResponse(
                hibernateTestService.testHibernateSaveAll()
        );
    }

    @RequestMapping(value = "/hibernate/delete")
    @ResponseBody
    public Response hibernateDeleteTest() {
        return ResponseUtils.successResponse(
                hibernateTestService.testHibernateDelete()
        );
    }

    @RequestMapping(value = "/hibernate/findAll")
    @ResponseBody
    public Response hibernateFindAllTest() {
        return ResponseUtils.successResponse(
                hibernateTestService.testHibernateFindAll()
        );
    }

    @RequestMapping(value = "/hibernate/findAllWithExample")
    @ResponseBody
    public Response hibernateFindAllWithExampleTest() {
        return ResponseUtils.successResponse(
                hibernateTestService.testHibernateFindAllWithExample()
        );
    }

    @RequestMapping(value = "/hibernate/findById")
    @ResponseBody
    public Response hibernateFindByIdTest(@Nullable @RequestBody Request request) {
        String param = (request == null || StringUtil.isBlank(request.getInput())) ? "0" : request.getInput();
        return ResponseUtils.successResponse(
                hibernateTestService.testHibernateFindById(Integer.parseInt(param))
        );
    }
}
