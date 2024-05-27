package com.arextest.agent.test.service.hibernate;

import com.arextest.agent.test.entity.db.HibernateDeleteResponseBody;
import com.arextest.agent.test.entity.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author daixq
 * @date 2022/11/07
 */
@Component
public class HibernateTestService {
    @Autowired
    HibernateRepository hibernateRepository;

    public TestUser testHibernateSave() {
        TestUser testUser = new TestUser();
        testUser.setName("Lily");
        testUser.setAge(18);
        TestUser result = hibernateRepository.save(testUser);
        return result;
    }

    public HibernateDeleteResponseBody testHibernateDelete() {
        List<TestUser> testUsers = prepareTestItems();
        int beforeDel = testUsers.size();
        TestUser testUser = testUsers.get(0);
        hibernateRepository.delete(testUser);
        return new HibernateDeleteResponseBody(beforeDel, beforeDel - 1, testUser);
    }

    public TestUser testHibernateFindById(Integer userId) {
        List<TestUser> testUsers = prepareTestItems();
        Optional<TestUser> user = hibernateRepository.findById(testUsers.get(userId).getId());
        return user.get();
    }

    public List<TestUser> testHibernateSaveAll() {
        List<TestUser> testUsers = getTestItems();
        List<TestUser> result = hibernateRepository.saveAll(testUsers);
        return result;
    }

    public List<TestUser> testHibernateFindAll() {
        prepareTestItems();
        List<TestUser> result = hibernateRepository.findAll();
        return result;
    }

    public List<TestUser> testHibernateFindAllWithExample() {
        prepareTestItems();
        TestUser testUser = new TestUser();
        testUser.setName("Jack");
        testUser.setAge(20);
        List<TestUser> result = hibernateRepository.findAll(Example.of(testUser));
        return result;
    }

    private List<TestUser> prepareTestItems() {
        List<TestUser> testUsers = getTestItems();
        return hibernateRepository.saveAll(testUsers);
    }

    public List<TestUser> getTestItems() {
        List<TestUser> testUsers = new ArrayList<>();
        TestUser testUser = new TestUser();
        testUser.setName("Kite");
        testUser.setAge(19);
        testUsers.add(testUser);
        TestUser testUser2 = new TestUser();
        testUser2.setName("Jack");
        testUser2.setAge(20);
        testUsers.add(testUser2);
        return testUsers;
    }
}
