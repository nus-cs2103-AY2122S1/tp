package seedu.address.encryption;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FileUtil;
import seedu.address.encryption.exceptions.UnsupportedPasswordException;

public class EncryptionTest {
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String PASSWORD_ONE = "password_one";
    private static final String PASSWORD_TWO = "password_two";
    private static final String TOO_LONG_PASSWORD = "1111111111111111111111111111111111";

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "EncryptionTest");
    private static final Path DECRYPTED_FILEPATH_JSON = TEST_DATA_FOLDER.resolve("testJson.json");
    private static final Path DECRYPTED_FILEPATH_JSON_OUTPUT = TEST_DATA_FOLDER.resolve("testJsonOutput.json");
    private static final Path ENCRYPTED_FILEPATH_JSON = TEST_DATA_FOLDER.resolve("testJson.enc");
    private static final Path ENCRYPTED_FILEPATH_JSON_ONE = TEST_DATA_FOLDER.resolve("testJsonOne.enc");
    private static final Path ENCRYPTED_FILEPATH_JSON_TWO = TEST_DATA_FOLDER.resolve("testJsonTwo.enc");
    private static final Path ILLEGAL_ENCRYPTED_FORMAT = TEST_DATA_FOLDER.resolve("illegal.txt");

    @Test
    @Disabled
    public void whenEncryptingJsonIntoFile_andDecryptingFileAgain_theOriginalContentIsReturned()
            throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        Encryption token = new EncryptionManager(secretKey, CIPHER_TRANSFORMATION);

        String content = FileUtil.readFromFile(DECRYPTED_FILEPATH_JSON);
        token.encrypt(DECRYPTED_FILEPATH_JSON, ENCRYPTED_FILEPATH_JSON);

        token.decrypt(ENCRYPTED_FILEPATH_JSON, DECRYPTED_FILEPATH_JSON);
        String decryptedContent = FileUtil.readFromFile(DECRYPTED_FILEPATH_JSON);

        assertEquals(content, decryptedContent);

        FileUtil.deleteFile(ENCRYPTED_FILEPATH_JSON); // Cleanup
    }

    @Test
    @Disabled
    public void ableToEncrypt_decrypt_writeJsonToDestination()
            throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        Encryption token = new EncryptionManager(secretKey, CIPHER_TRANSFORMATION);

        String content = FileUtil.readFromFile(DECRYPTED_FILEPATH_JSON);
        token.encrypt(DECRYPTED_FILEPATH_JSON, ENCRYPTED_FILEPATH_JSON);
        token.decrypt(ENCRYPTED_FILEPATH_JSON, DECRYPTED_FILEPATH_JSON_OUTPUT);

        assertTrue(FileUtil.isFileExists(DECRYPTED_FILEPATH_JSON_OUTPUT));

        String copiedContent = FileUtil.readFromFile(DECRYPTED_FILEPATH_JSON_OUTPUT);
        assertEquals(content, copiedContent);

        FileUtil.deleteFile(DECRYPTED_FILEPATH_JSON_OUTPUT); // Cleanup
        FileUtil.deleteFile(ENCRYPTED_FILEPATH_JSON); // Cleanup
    }

    @Test
    public void keyGenerator_producesIdenticalKeys_usingSameString() throws UnsupportedPasswordException {
        SecretKey key1 = EncryptionKeyGenerator.generateKey(PASSWORD_ONE);
        SecretKey key2 = EncryptionKeyGenerator.generateKey(PASSWORD_TWO);
        assertNotEquals(key1, key2);

        SecretKey duplicate1 = EncryptionKeyGenerator.generateKey(PASSWORD_ONE);
        SecretKey duplicate2 = EncryptionKeyGenerator.generateKey(PASSWORD_TWO);
        assertEquals(key1, duplicate1);
        assertEquals(key2, duplicate2);
    }

    @Test
    public void failure_whenWrongKeyIsSuppliedToDecrypt()
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        SecretKey secretKey1 = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        SecretKey secretKey2 = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();

        Encryption token1 = new EncryptionManager(secretKey1, CIPHER_TRANSFORMATION);
        Encryption token2 = new EncryptionManager(secretKey2, CIPHER_TRANSFORMATION);

        token1.encrypt(DECRYPTED_FILEPATH_JSON, ENCRYPTED_FILEPATH_JSON_ONE);
        token2.encrypt(DECRYPTED_FILEPATH_JSON, ENCRYPTED_FILEPATH_JSON_TWO);

        assertThrows(IOException.class, () ->
                token2.decrypt(ENCRYPTED_FILEPATH_JSON_ONE, DECRYPTED_FILEPATH_JSON));
        assertThrows(IOException.class, () ->
                token1.decrypt(ENCRYPTED_FILEPATH_JSON_TWO, DECRYPTED_FILEPATH_JSON));

        FileUtil.deleteFile(ENCRYPTED_FILEPATH_JSON_ONE); // Cleanup
        FileUtil.deleteFile(ENCRYPTED_FILEPATH_JSON_TWO); // Cleanup
    }

    @Test
    public void failure_whenWrongFileFormatIsSuppliedToDecrypt()
            throws NoSuchAlgorithmException, NoSuchPaddingException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        Encryption token = new EncryptionManager(secretKey, CIPHER_TRANSFORMATION);

        assertThrows(IOException.class, () -> token.decrypt(ILLEGAL_ENCRYPTED_FORMAT, DECRYPTED_FILEPATH_JSON));
    }

    @Test
    public void failure_whenWrongKeyIsSuppliedToDecrypt_usingKeyGenerator()
            throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeyException,
            UnsupportedPasswordException {
        SecretKey key1 = EncryptionKeyGenerator.generateKey(PASSWORD_ONE);
        SecretKey key2 = EncryptionKeyGenerator.generateKey(PASSWORD_TWO);

        Encryption token1 = new EncryptionManager(key1, CIPHER_TRANSFORMATION);
        Encryption token2 = new EncryptionManager(key2, CIPHER_TRANSFORMATION);

        token1.encrypt(DECRYPTED_FILEPATH_JSON, ENCRYPTED_FILEPATH_JSON_ONE);
        token2.encrypt(DECRYPTED_FILEPATH_JSON, ENCRYPTED_FILEPATH_JSON_TWO);

        assertThrows(IOException.class, () -> token2.decrypt(ENCRYPTED_FILEPATH_JSON_ONE, DECRYPTED_FILEPATH_JSON));
        assertThrows(IOException.class, () -> token1.decrypt(ENCRYPTED_FILEPATH_JSON_TWO, DECRYPTED_FILEPATH_JSON));

        FileUtil.deleteFile(ENCRYPTED_FILEPATH_JSON_ONE); // Cleanup
        FileUtil.deleteFile(ENCRYPTED_FILEPATH_JSON_TWO); // Cleanup
    }

    @Test
    public void failure_whenTooLongPasswordIsSupplied() {
        assertThrows(UnsupportedPasswordException.class, () -> EncryptionKeyGenerator.generateKey(TOO_LONG_PASSWORD));
        assertDoesNotThrow(() -> EncryptionKeyGenerator.generateKey(PASSWORD_ONE));
    }
}
