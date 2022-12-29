package org.example;

import net.jpountz.xxhash.XXHash64;
import net.jpountz.xxhash.XXHashFactory;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.HexFormat;

public class Main {
    public static String MD5(String str) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] passwordDigest = md.digest(str.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : passwordDigest)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    public static String xxHash(String str) {
        XXHashFactory factory = XXHashFactory.fastestInstance();
        byte[] data = str.getBytes(Charset.forName("UTF-8"));
        XXHash64 hash64 = factory.hash64();
        int seed = 123456;
        long hash = hash64.hash(data, 0, data.length, seed);
        return Long.toHexString(hash);
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long cnt = 0;
        int flag = 1;
        while (true) {
            String temp = "12312321409218409218fhkjdsnfkjdsnfkjs";
            try {
                 MD5(temp);
//                xxHash(temp);
                ++cnt;
            } catch (Exception e) {
                System.out.println(e);
            }
            if ((System.currentTimeMillis() - startTime) > 60000 * flag) {
                System.out.println("Total number in " + flag +" min: " + cnt);
                flag +=  1;
            }
            if (flag == 5) {
                break;
            }
        }


    }
}