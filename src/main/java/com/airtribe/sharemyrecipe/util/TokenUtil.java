package com.airtribe.sharemyrecipe.util;

import com.airtribe.sharemyrecipe.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {

    public static String generateJwtToken(User user) throws Exception {
        return Jwts.builder().subject(user.getUsername()).claim("userId", user.getUserId())
                   .claim("username", user.getUsername()).claim("email", user.getEmail())
                   .claim("isEnabled", user.getEnabled()).setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000))
                   .signWith(SignatureAlgorithm.HS256, "secretKeyAirtribeTestingTokensecretKeyAirtribeTestingTokensecretKeyAirtribeTestingToken")
                   .compact();
    }

    public static String generateRefreshToken(User user) {
        return Jwts.builder().subject(user.getUsername()).claim("userId", user.getUserId())
                   .claim("username", user.getUsername()).claim("email", user.getEmail())
                   .claim("isEnabled", user.getEnabled()).setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
                   .signWith(SignatureAlgorithm.HS256, "secretKeyAirtribeTestingTokensecretKeyAirtribeTestingTokensecretKeyAirtribeTestingToken")
                   .compact();
    }

    private static Map<String, Object> userToMapWithoutPassword(User user) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", user.getUserId());
        userMap.put("username", user.getUsername());
        userMap.put("email", user.getEmail());
        userMap.put("role", user.getRole());
        userMap.put("enabled", user.getEnabled());
        return userMap;
    }
}
