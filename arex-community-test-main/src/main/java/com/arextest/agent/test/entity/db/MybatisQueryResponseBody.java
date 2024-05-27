package com.arextest.agent.test.entity.db;

import com.arextest.agent.test.entity.Mealrecomrestaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by rchen9 on 2023/3/21.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MybatisQueryResponseBody {
    List<Mealrecomrestaurant> mealrecomrestaurants;
}
