package com.arextest.agent.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author yongwuhe
 */
@SpringBootApplication
@MapperScan(basePackages = "com.arextest.agent.test.mapper")
@EnableCaching
public class ArexAgentTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArexAgentTestApplication.class, args);
	}

}
