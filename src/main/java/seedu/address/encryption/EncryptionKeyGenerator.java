package seedu.address.encryption;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import seedu.address.encryption.exceptions.UnsupportedPasswordException;

/**
 * Utility class to generate encryption keys.
 */
public class EncryptionKeyGenerator {
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final int AES_NUM_CHAR = 32; // 32 chars = 256 bits

    /**
     * @param password The supplied password
     * @return A SecretKey generated with the given password
     */
    public static SecretKey generateKey(String password) throws UnsupportedPasswordException {
        return new SecretKeySpec(appendPadding(password).getBytes(StandardCharsets.UTF_8), ENCRYPTION_ALGORITHM);
    }

    /**
     * Pads the give password string to 32 characters.
     * @param password The supplied password
     * @return A string containing 32 characters
     * @throws UnsupportedPasswordException If the supplied password exceeds 32 characters in length
     */
    private static String appendPadding(String password) throws UnsupportedPasswordException {
        assert password != null;
        if (password.length() > AES_NUM_CHAR) {
            throw new UnsupportedPasswordException("The supplied password exceeded 32 character limit");
        }
        return password + "/".repeat(Math.max(0, AES_NUM_CHAR - password.length()));
    }
}
