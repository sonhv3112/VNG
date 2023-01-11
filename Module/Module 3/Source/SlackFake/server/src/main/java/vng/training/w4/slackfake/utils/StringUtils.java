package vng.training.w4.slackfake.utils;

import io.jsonwebtoken.*;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

public final class StringUtils {
    private static final String jwt_key = "1234"; //UUID.randomUUID().toString();

    public static String MD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passwordDigest = md.digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : passwordDigest)
                sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String generateRandomString(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        String randomString = new String(array, Charset.forName("UTF-8"));
        return randomString;
    }

    public static String generateAccessTokenWithUserId(String userId) {
        String jwt = Jwts.builder()
                .setSubject("user1")
                .claim("username", userId)
                .signWith(SignatureAlgorithm.HS256, jwt_key)
                .compact();
        return jwt;
    }

    public static String decodeUsernameFromAccessToken(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(jwt_key)
                    .parseClaimsJws(jwt);
            String username = claims.getBody().get("username", String.class);
            return username;
        } catch (SignatureException e) {
            e.printStackTrace();
            return null;
        }
    }
}
