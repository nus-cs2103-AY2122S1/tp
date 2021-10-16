package seedu.address.encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FileUtil;

public class EncryptionTest {
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String TEST_STRING = "This is a test string" + System.lineSeparator();

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "EncryptionTest");
    private static final Path ENCRYPTED_FILEPATH_SIMPLE_STRING = TEST_DATA_FOLDER.resolve("testSimpleString.enc");
    private static final Path ENCRYPTED_FILEPATH_SIMPLE_STRING_ONE =
            TEST_DATA_FOLDER.resolve("testSimpleStringOne.enc");
    private static final Path ENCRYPTED_FILEPATH_SIMPLE_STRING_TWO =
            TEST_DATA_FOLDER.resolve("testSimpleStringTwo.enc");
    private static final Path DECRYPTED_FILEPATH_JSON = TEST_DATA_FOLDER.resolve("testJson.json");
    private static final Path DECRYPTED_FILEPATH_JSON_OUTPUT = TEST_DATA_FOLDER.resolve("testJsonOutput.json");
    private static final Path ENCRYPTED_FILEPATH_JSON = TEST_DATA_FOLDER.resolve("testJson.enc");
    private static final Path ILLEGAL_ENCRYPTED_FORMAT = TEST_DATA_FOLDER.resolve("illegal.txt");

    @Test
    public void whenEncryptingIntoFile_andDecryptingFileAgain_thenOriginalStringIsReturned()
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
            InvalidAlgorithmParameterException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();

        Cryptable cryptor = new Cryptor(secretKey, CIPHER_TRANSFORMATION);
        cryptor.encrypt(TEST_STRING, ENCRYPTED_FILEPATH_SIMPLE_STRING);

        String decryptedContent = cryptor.decrypt(ENCRYPTED_FILEPATH_SIMPLE_STRING);
        assertEquals(TEST_STRING, decryptedContent);

        new File(ENCRYPTED_FILEPATH_SIMPLE_STRING.toString()).delete(); // Cleanup
    }

    @Test
    public void whenEncryptingJsonIntoFile_andDecryptingFileAgain_theOriginalContentIsReturned()
            throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        Cryptable cryptor = new Cryptor(secretKey, CIPHER_TRANSFORMATION);

        String content = FileUtil.readFromFile(DECRYPTED_FILEPATH_JSON);

        cryptor.encrypt(content, ENCRYPTED_FILEPATH_JSON);
        String decryptedContent = cryptor.decrypt(ENCRYPTED_FILEPATH_JSON);

        assertEquals(content, decryptedContent);

        new File(ENCRYPTED_FILEPATH_JSON.toString()).delete(); // Cleanup
    }

    @Test
    public void ableToEncrypt_decrypt_writeJsonToDestination()
            throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        Cryptable cryptor = new Cryptor(secretKey, CIPHER_TRANSFORMATION);

        String content = FileUtil.readFromFile(DECRYPTED_FILEPATH_JSON);

        cryptor.encrypt(content, ENCRYPTED_FILEPATH_JSON);
        String decryptedContent = cryptor.decrypt(ENCRYPTED_FILEPATH_JSON);
        FileUtil.writeToFile(DECRYPTED_FILEPATH_JSON_OUTPUT, decryptedContent);

        assertTrue(FileUtil.isFileExists(DECRYPTED_FILEPATH_JSON_OUTPUT));

        String copiedContent = FileUtil.readFromFile(DECRYPTED_FILEPATH_JSON_OUTPUT);
        assertEquals(content, copiedContent);

        new File(DECRYPTED_FILEPATH_JSON_OUTPUT.toString()).delete(); // Cleanup
        new File(ENCRYPTED_FILEPATH_JSON.toString()).delete(); // Cleanup
    }

    @Test
    public void failure_whenWrongKeyIsSuppliedToDecrypt()
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        SecretKey secretKey1 = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        SecretKey secretKey2 = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();

        Cryptable cryptor1 = new Cryptor(secretKey1, CIPHER_TRANSFORMATION);
        Cryptable cryptor2 = new Cryptor(secretKey2, CIPHER_TRANSFORMATION);

        cryptor1.encrypt(TEST_STRING, ENCRYPTED_FILEPATH_SIMPLE_STRING_ONE);
        cryptor2.encrypt(TEST_STRING, ENCRYPTED_FILEPATH_SIMPLE_STRING_TWO);

        assertThrows(IOException.class, () -> cryptor2.decrypt(ENCRYPTED_FILEPATH_SIMPLE_STRING_ONE));
        assertThrows(IOException.class, () -> cryptor1.decrypt(ENCRYPTED_FILEPATH_SIMPLE_STRING_TWO));

        new File(ENCRYPTED_FILEPATH_SIMPLE_STRING_ONE.toString()).delete(); // Cleanup
        new File(ENCRYPTED_FILEPATH_SIMPLE_STRING_TWO.toString()).delete(); // Cleanup
    }

    @Test
    public void failure_whenWrongFileFormatIsSuppliedToDecrypt()
            throws NoSuchAlgorithmException, NoSuchPaddingException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        Cryptable cryptor = new Cryptor(secretKey, CIPHER_TRANSFORMATION);

        assertThrows(IOException.class, () -> cryptor.decrypt(ILLEGAL_ENCRYPTED_FORMAT));
    }
}
