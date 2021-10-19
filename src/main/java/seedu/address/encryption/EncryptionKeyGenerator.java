package seedu.address.encryption;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionKeyGenerator {
    private static final String ENCRYPTION_ALGORITHM = "AES";

    public static SecretKey generateKey(String password) {
        return new SecretKeySpec(appendPadding(password).getBytes(StandardCharsets.UTF_8), ENCRYPTION_ALGORITHM);
    }

    private static String appendPadding(String password) {
        StringBuilder sb = new StringBuilder(password);
        sb.append("/".repeat(Math.max(0, 32 - password.length())));
        return sb.toString();
    }
}
