package com.arextest.agent.test.service.jwt;

import com.arextest.agent.test.entity.JwtTestResponseBody;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;

/**
 * @author daixq
 * @date 2022/11/11
 */
@Component
public class JwtTestService {

    public JwtTestResponseBody testJwt(String SECRET_NAME) {
        String token = createToken(SECRET_NAME);
        JWTVerifier credentialVerifier = JWT.require(Algorithm.HMAC256(SECRET_NAME)).build();
        DecodedJWT decodedCredential = credentialVerifier.verify(token);
        int userId = decodedCredential.getClaim("userId").asInt();
        String userName = decodedCredential.getClaim("userName").asString();
        String password = decodedCredential.getClaim("password").asString();
        return new JwtTestResponseBody(userId, userName, password);
    }

    private String createToken(String secretName) {
        HashMap<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,600);
        String token = JWT.create()
                .withHeader(map)
                .withClaim("userId",101)
                .withClaim("userName","tester")
                .withClaim("password","12345678")
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(secretName));
        return token;
    }
}
