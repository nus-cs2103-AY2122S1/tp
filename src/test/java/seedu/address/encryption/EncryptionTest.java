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

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "EncryptionTest");
    private static final Path DECRYPTED_FILEPATH_JSON = TEST_DATA_FOLDER.resolve("testJson.json");
    private static final Path DECRYPTED_FILEPATH_JSON_OUTPUT = TEST_DATA_FOLDER.resolve("testJsonOutput.json");
    private static final Path ENCRYPTED_FILEPATH_JSON = TEST_DATA_FOLDER.resolve("testJson.enc");
    private static final Path ENCRYPTED_FILEPATH_JSON_ONE = TEST_DATA_FOLDER.resolve("testJsonOne.enc");
    private static final Path ENCRYPTED_FILEPATH_JSON_TWO = TEST_DATA_FOLDER.resolve("testJsonTwo.enc");
    private static final Path ILLEGAL_ENCRYPTED_FORMAT = TEST_DATA_FOLDER.resolve("illegal.txt");

    @Test
    public void whenEncryptingJsonIntoFile_andDecryptingFileAgain_theOriginalContentIsReturned()
            throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        Cryptable cryptor = new Cryptor(secretKey, CIPHER_TRANSFORMATION);

        String content = FileUtil.readFromFile(DECRYPTED_FILEPATH_JSON);
        cryptor.encrypt(DECRYPTED_FILEPATH_JSON, ENCRYPTED_FILEPATH_JSON);
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
        cryptor.encrypt(DECRYPTED_FILEPATH_JSON, ENCRYPTED_FILEPATH_JSON);
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
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        SecretKey secretKey1 = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        SecretKey secretKey2 = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();

        Cryptable cryptor1 = new Cryptor(secretKey1, CIPHER_TRANSFORMATION);
        Cryptable cryptor2 = new Cryptor(secretKey2, CIPHER_TRANSFORMATION);

        cryptor1.encrypt(DECRYPTED_FILEPATH_JSON, ENCRYPTED_FILEPATH_JSON_ONE);
        cryptor2.encrypt(DECRYPTED_FILEPATH_JSON, ENCRYPTED_FILEPATH_JSON_TWO);

        assertThrows(IOException.class, () -> cryptor2.decrypt(ENCRYPTED_FILEPATH_JSON_ONE));
        assertThrows(IOException.class, () -> cryptor1.decrypt(ENCRYPTED_FILEPATH_JSON_TWO));

        new File(ENCRYPTED_FILEPATH_JSON_ONE.toString()).delete(); // Cleanup
        new File(ENCRYPTED_FILEPATH_JSON_TWO.toString()).delete(); // Cleanup
    }

    @Test
    public void failure_whenWrongFileFormatIsSuppliedToDecrypt()
            throws NoSuchAlgorithmException, NoSuchPaddingException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        Cryptable cryptor = new Cryptor(secretKey, CIPHER_TRANSFORMATION);

        assertThrows(IOException.class, () -> cryptor.decrypt(ILLEGAL_ENCRYPTED_FORMAT));
    }
}
