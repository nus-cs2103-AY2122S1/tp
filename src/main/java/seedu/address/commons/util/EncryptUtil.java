package seedu.address.commons.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A class for accessing the Account File.
 */
public class EncryptUtil {

    private static final String HASH_ALGORITHM = "MD5";
    private static final String HASH_FORMAT = "%032x";
    private static final int SIGNUM = 1;

    /**
     * Returns hashed text from plaintext.
     *
     * @throws NoSuchAlgorithmException if the algorithm does not exist.
     */
    public static String hash(String plaintext) throws NoSuchAlgorithmException {
        byte[] bytesOfPassword = plaintext.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        messageDigest.reset();
        messageDigest.update(bytesOfPassword);
        byte[] digest = messageDigest.digest();
        return String.format(HASH_FORMAT, new BigInteger(SIGNUM, digest));
    }
}
