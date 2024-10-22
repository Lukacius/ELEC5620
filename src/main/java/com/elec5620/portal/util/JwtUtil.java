package com.elec5620.portal.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

/**
 * ClassName: JwtUtil
 * Package: com.elec5620.portal.util
 * Description:
 *
 * @Author Benjamin-Huang
 * @Create 2024/10/22 14:02
 * @Version 1.0
 */
@Component
public class JwtUtil {
    // 使用一个简单的静态字节数组作为密钥（64字节）
    private final byte[] SECRET_KEY = "simple-secret-key-for-testing-jwt-signaturekey-for-testing-jwt-signature!!!".getBytes();

    private final long EXPIRATION_TIME = 86400000;  // 24小时有效

    // 生成JWT Token
    public String generateToken(String email, String userType) {
        String tokenType = getTokenType(userType);  // 根据用户类型设置Token类型

        return Jwts.builder()
                .setSubject(email)
                .claim("userType", userType)
                .claim("tokenType",tokenType)
                .setIssuedAt(new Date())  // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // 过期时间
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)  // 签名
                .compact();
    }

    // 验证JWT Token
    public Claims validateToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)  // 验证密钥
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 根据用户类型返回Token类型
    private String getTokenType(String userType) {
        switch (userType.toLowerCase()) {
            case "admin":
                return "admin-token";
            case "student":
                return "student-token";
            case "teacher":
                return "teacher-token";
            case "tech":
                return "tech-token";
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }
}
