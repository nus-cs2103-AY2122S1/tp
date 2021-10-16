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
    private static final String TEST_STRING = "This is a test string\n";

    private static final String ENCRYPTED_FILEPATH_SIMPLE_STRING = "src/test/data/EncryptionTest/testSimpleString.enc";
    private static final String ENCRYPTED_FILEPATH_SIMPLE_STRING_ONE
            = "src/test/data/EncryptionTest/testSimpleStringOne.enc";
    private static final String ENCRYPTED_FILEPATH_SIMPLE_STRING_TWO
            = "src/test/data/EncryptionTest/testSimpleStringTwo.enc";
    private static final String DECRYPTED_FILEPATH_JSON = "src/test/data/EncryptionTest/testJson.json";
    private static final String DECRYPTED_FILEPATH_JSON_OUTPUT = "src/test/data/EncryptionTest/testJsonOutput.json";
    private static final String ENCRYPTED_FILEPATH_JSON = "src/test/data/EncryptionTest/testJson.enc";
    private static final String ILLEGAL_ENCRYPTED_FORMAT = "illegal.txt";

    @Test
    public void whenEncryptingIntoFile_andDecryptingFileAgain_thenOriginalStringIsReturned()
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
            InvalidAlgorithmParameterException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();

        Cryptable cryptor = new Cryptor(secretKey, CIPHER_TRANSFORMATION);
        cryptor.encrypt(TEST_STRING, ENCRYPTED_FILEPATH_SIMPLE_STRING);

        String decryptedContent = cryptor.decrypt(ENCRYPTED_FILEPATH_SIMPLE_STRING);
        assertEquals(TEST_STRING, decryptedContent);

        new File(ENCRYPTED_FILEPATH_SIMPLE_STRING).delete(); // Cleanup
    }

    @Test
    public void whenEncryptingJsonIntoFile_andDecryptingFileAgain_theOriginalContentIsReturned()
            throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        Cryptable cryptor = new Cryptor(secretKey, CIPHER_TRANSFORMATION);

        String content = FileUtil.readFromFile(Paths.get(DECRYPTED_FILEPATH_JSON));

        cryptor.encrypt(content, ENCRYPTED_FILEPATH_JSON);
        String decryptedContent = cryptor.decrypt(ENCRYPTED_FILEPATH_JSON);

        assertEquals(content, decryptedContent);

        new File(ENCRYPTED_FILEPATH_JSON).delete(); // Cleanup
    }

    @Test
    public void ableToEncrypt_Decrypt_WriteJsonToDestination()
            throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        Cryptable cryptor = new Cryptor(secretKey, CIPHER_TRANSFORMATION);

        String content = FileUtil.readFromFile(Paths.get(DECRYPTED_FILEPATH_JSON));

        cryptor.encrypt(content, ENCRYPTED_FILEPATH_JSON);
        String decryptedContent = cryptor.decrypt(ENCRYPTED_FILEPATH_JSON);
        FileUtil.writeToFile(Path.of(DECRYPTED_FILEPATH_JSON_OUTPUT), decryptedContent);

        assertTrue(FileUtil.isFileExists(Path.of(DECRYPTED_FILEPATH_JSON_OUTPUT)));

        String copiedContent = FileUtil.readFromFile(Paths.get(DECRYPTED_FILEPATH_JSON_OUTPUT));
        assertEquals(content, copiedContent);

        new File(DECRYPTED_FILEPATH_JSON_OUTPUT).delete(); // Cleanup
        new File(ENCRYPTED_FILEPATH_JSON).delete(); // Cleanup
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

        new File(ENCRYPTED_FILEPATH_SIMPLE_STRING_ONE).delete(); // Cleanup
        new File(ENCRYPTED_FILEPATH_SIMPLE_STRING_TWO).delete(); // Cleanup
    }

    @Test
    public void failure_whenWrongFileFormatIsSuppliedToDecrypt() throws NoSuchAlgorithmException, NoSuchPaddingException {
        SecretKey secretKey = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM).generateKey();
        Cryptable cryptor = new Cryptor(secretKey, CIPHER_TRANSFORMATION);

        assertThrows(IOException.class, () -> cryptor.decrypt(ILLEGAL_ENCRYPTED_FORMAT));
    }
}
