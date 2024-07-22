package utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public class Utils {

    private static SecureRandom random;

    public static String getRandomString() {
        return UUID.randomUUID().toString();
    }

    public static Long getRandomPositiveLong() {
        try {
            if (random == null) {
                random = SecureRandom.getInstanceStrong();
            }
            long newRandom = random.nextLong();
            return newRandom > 0 ? newRandom : newRandom * -1;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("cant use utility class: %e", ex);
        }
    }
}
