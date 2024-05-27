package com.arextest.agent.test.mapper;

import com.arextest.agent.test.entity.Mealrecomrestaurant;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MybatisMapper {

  @Insert("insert into mealrecomrestaurant(restaurantName, dishName, flavorId, price, score) values(#{mealrecomrestaurant.restaurantName}, #{mealrecomrestaurant.dishName}, #{mealrecomrestaurant.flavorId}, #{mealrecomrestaurant.price}, #{mealrecomrestaurant.score})")
  @Options(useGeneratedKeys = true, keyProperty = "mealrecomrestaurant.id")
  Integer insert(@Param("mealrecomrestaurant") Mealrecomrestaurant mealrecomrestaurant);

  @Update("update mealrecomrestaurant set flavorId=#{flavorId} where id = #{id}")
  void update(Mealrecomrestaurant mealrecomrestaurant);

  @Select("select * from mealrecomrestaurant where dishName = #{dishName} limit 10")
  List<Mealrecomrestaurant> findByDishName(@Param("dishName") String dishName);

}
