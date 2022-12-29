package BenchMarkHash;

import java.security.MessageDigest;
import net.jpountz.xxhash.StreamingXXHash32;
import net.jpountz.xxhash.XXHash32;
import net.jpountz.xxhash.XXHashFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {
    public static String MD5(String str) throws Exception { 
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] passwordDigest = md.digest(str.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : passwordDigest)
            sb.append(String.format("%02x", b));
        return sb.toString(); 
    }

    public static int xxhash(String str) throws Exception { 
        XXHashFactory factory = XXHashFactory.fastestInstance();

        byte[] data = str.getBytes("UTF-8");
        ByteArrayInputStream in = new ByteArrayInputStream(data);

        int seed = 0x9747b28c; 
        StreamingXXHash32 hash32 = factory.newStreamingHash32(seed);
        byte[] buf = new byte[8]; 
            for (;;) {
            int read = in.read(buf);
            if (read == -1) {
                break;
            }
            hash32.update(buf, 0, read);
        }
        int hash = hash32.getValue();
        return hash;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int cnt = 0; 
        while (true) { 
            String temp = "12312321409218409218fhkjdsnfkjdsnfkjs";
            try { 
                // System.out.print(++cnt + ": ");
                System.out.println(xxhash(temp)); 
                // MD5(temp);
                // xxhash(temp);
                ++cnt;
            } catch (Exception e) { 
                System.out.println(e);
            }
            if ((System.currentTimeMillis() - startTime) > 60000) 
                break; 
        }       
        System.out.println("Total number in 1 min: " + cnt);
    }
}
