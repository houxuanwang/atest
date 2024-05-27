package com.arextest.agent.test.entity.db;

import com.arextest.agent.test.entity.TestUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rchen9 on 2023/3/21.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HibernateDeleteResponseBody {
    private int beforeSize;
    private int afterSize;
    private TestUser deleteUser;
}
