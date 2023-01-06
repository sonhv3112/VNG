package vng.training.w4.slackfake.utils;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.Charset;

public final class PasswordUtils {

    public static String generateSalt() {
        return RandomStringUtils.random(32, true, true);
    }

    private static String shuffle(String rawPassword, String salt) {
        return new StringBuilder(salt).insert(16, rawPassword).toString();
    }

    public static String hashing(String rawPassword, String salt) {
        String password = shuffle(rawPassword, salt);
        return Hashing.sha512().hashString(password, Charset.defaultCharset()).toString();
    }

    public static boolean passwordCompare(String password, String rawPassword, String salt) {
        String hashedPassword = hashing(rawPassword, salt);
        return hashedPassword.equals(password);
    }

}
