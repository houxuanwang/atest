package com.arextest.agent.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rchen9 on 2023/3/20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtTestResponseBody {
    private int userId;
    private String userName;
    private String password;
}
