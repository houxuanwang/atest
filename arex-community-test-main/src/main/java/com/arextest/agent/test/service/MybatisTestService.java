package com.arextest.agent.test.service;

import com.arextest.agent.test.entity.db.DbOperationResponseBody;
import com.arextest.agent.test.entity.Mealrecomrestaurant;
import com.arextest.agent.test.entity.db.MybatisBatchInsertResponseBody;
import com.arextest.agent.test.entity.db.MybatisQueryResponseBody;
import com.arextest.agent.test.mapper.MybatisMapper;
import com.arextest.agent.test.mapper.MybatisPlusMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yongwuhe
 * @date 2022/11/04
 */
@Component
public class MybatisTestService {

    @Autowired
    MybatisMapper mapper;

    @Autowired
    MybatisPlusMapper plusMapper;

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public MybatisQueryResponseBody testMybatisQuery(String parameterData) {
        try {
            QueryWrapper<Mealrecomrestaurant> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(Mealrecomrestaurant::getDishName, parameterData);
            List<Mealrecomrestaurant> mealrecomrestaurants = plusMapper.selectList(queryWrapper);
            return new MybatisQueryResponseBody(mealrecomrestaurants);
        } catch (Throwable ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public DbOperationResponseBody testMybatisInsert() {
        Mealrecomrestaurant mealrecomrestaurant = new Mealrecomrestaurant();
        mealrecomrestaurant.setRestaurantName("testMybatisPlus");
        mealrecomrestaurant.setDishName("testInsert");
        mealrecomrestaurant.setFlavorId(1);
        mealrecomrestaurant.setPrice(BigDecimal.valueOf(20));
        mealrecomrestaurant.setScore(BigDecimal.valueOf(4));
        int insert = plusMapper.insert(mealrecomrestaurant);
        return new DbOperationResponseBody(insert);
    }

    public DbOperationResponseBody testMybatisDelete() {
        prepareTestItems();
        QueryWrapper<Mealrecomrestaurant> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Mealrecomrestaurant::getRestaurantName, "FOOD");
        int delete = plusMapper.delete(queryWrapper);
        return new DbOperationResponseBody(delete);
    }

    public DbOperationResponseBody testMybatisUpdate() {
        prepareTestItems();
        LambdaUpdateWrapper<Mealrecomrestaurant> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Mealrecomrestaurant::getDishName, "rice").set(Mealrecomrestaurant::getScore, 10);
        int update = plusMapper.update(null, updateWrapper);
        return new DbOperationResponseBody(update);
    }

    public DbOperationResponseBody testMybatisForeachUpdate() {
        prepareTestItems();
        LambdaUpdateWrapper<Mealrecomrestaurant> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Mealrecomrestaurant::getRestaurantName, "FOOD").set(Mealrecomrestaurant::getScore, 10);
        int update = plusMapper.update(null, updateWrapper);
        return new DbOperationResponseBody(update);
    }

    public MybatisBatchInsertResponseBody testMybatisBatchInsert() {
        try (SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH)) {
            MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
            Mealrecomrestaurant mealrecomrestaurant_one = new Mealrecomrestaurant();
            mealrecomrestaurant_one.setRestaurantName("testMybatisPlus");
            mealrecomrestaurant_one.setDishName("testBatchInsert");
            mealrecomrestaurant_one.setFlavorId(1);
            mealrecomrestaurant_one.setPrice(BigDecimal.valueOf(20));
            mealrecomrestaurant_one.setScore(BigDecimal.valueOf(4));
            int id_one = mapper.insert(mealrecomrestaurant_one);

            Mealrecomrestaurant mealrecomrestaurant_two = new Mealrecomrestaurant();
            mealrecomrestaurant_two.setRestaurantName("testMybatisPlus");
            mealrecomrestaurant_two.setDishName("testBatchInsert");
            mealrecomrestaurant_two.setFlavorId(1);
            mealrecomrestaurant_two.setPrice(BigDecimal.valueOf(20));
            mealrecomrestaurant_two.setScore(BigDecimal.valueOf(4));
            int id_two = mapper.insert(mealrecomrestaurant_two);

            List<BatchResult> batchResults = sqlSession.flushStatements();
            sqlSession.commit();
            return new MybatisBatchInsertResponseBody(mealrecomrestaurant_one.getId(), mealrecomrestaurant_two.getId());
        }
    }

    public DbOperationResponseBody testMybatisBatchUpdate() {
        try (SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH)) {
            MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);

            List<Mealrecomrestaurant> result = mapper.findByDishName("testBatchInsert");
            for (Mealrecomrestaurant mealrecomrestaurant : result) {
                mealrecomrestaurant.setFlavorId(2);
                mapper.update(mealrecomrestaurant);
            }

            List<BatchResult> batchResults = sqlSession.flushStatements();
            sqlSession.commit();
            return new DbOperationResponseBody(batchResults.size());
        }
    }

    public DbOperationResponseBody testMybatisBatchQuery() {
        try (SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH)) {
            MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);

            List<Mealrecomrestaurant> result_one = mapper.findByDishName("testBatchInsert");
            List<Mealrecomrestaurant> result_two = mapper.findByDishName("testBatchInsert");

            List<BatchResult> batchResults = sqlSession.flushStatements();
            sqlSession.commit();
            return new DbOperationResponseBody(batchResults.size());
        }
    }


    private void prepareTestItems() {
        List<Mealrecomrestaurant> items = queryTempTestItems();
        if (items.size() < 1) {
            insertTempTestItems();
        }
    }

    private List<Mealrecomrestaurant> queryTempTestItems() {
        QueryWrapper<Mealrecomrestaurant> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Mealrecomrestaurant::getRestaurantName, "FOOD");
        List<Mealrecomrestaurant> items = plusMapper.selectList(queryWrapper);
        return items;
    }

    private void insertTempTestItems() {
        Mealrecomrestaurant mealrecomrestaurant = new Mealrecomrestaurant("FOOD", "rice", 1, BigDecimal.valueOf(15), BigDecimal.valueOf(9));
        insertItem(mealrecomrestaurant);
        mealrecomrestaurant = new Mealrecomrestaurant("FOOD", "noodles", 2, BigDecimal.valueOf(35), BigDecimal.valueOf(8));
        insertItem(mealrecomrestaurant);
    }

    private int insertItem(Mealrecomrestaurant mealrecomrestaurant) {
        return plusMapper.insert(mealrecomrestaurant);
    }

    /**
     * create test data
     *
     * @return
     */
    public List<Mealrecomrestaurant> getTempTestItems() {
        List<Mealrecomrestaurant> items = queryTempTestItems();
        if (items.size() < 1) {
            insertTempTestItems();
            return queryTempTestItems();
        }
        return items;
    }
}
