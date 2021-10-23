package seedu.address.encryption;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FileUtil;

/**
 * The object that encrypts and decrypts files.
 * Adapted and modified from <a href="https://www.baeldung.com/java-cipher-input-output-stream">
 * Encrypting and Decrypting Files in Java</a> by Baeldung.
 */
public class EncryptionManager implements Encryption {
    /**
     * File to be decrypted must have the {@code *.enc} extension.
     */
    private static final String LEGAL_FILE_FORMAT_EXTENSION = ".enc";
    private static final Logger logger = LogsCenter.getLogger(EncryptionManager.class);

    private final SecretKey secretKey;
    private final Cipher cipher;

    /**
     * @param secretKey The key used for encryption and decryption.
     * @param transformation The transformation scheme used to generate the encryption algorithm.
     * @throws NoSuchPaddingException If the padding does not exist.
     * @throws NoSuchAlgorithmException If the specified algorithm does not exist.
     */
    public EncryptionManager(SecretKey secretKey, String transformation)
            throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.secretKey = secretKey;
        cipher = Cipher.getInstance(transformation);
    }

    @Override
    public void encrypt(Path sourceFilePath, Path destinationFilePath) throws InvalidKeyException, IOException {
        requireNonNull(sourceFilePath);
        requireNonNull(destinationFilePath);
        if (isIllegalFileFormat(destinationFilePath)) { // Guard clause
            throw new IOException();
        }

        logger.fine("Encrypting content to: " + destinationFilePath);
        String content = FileUtil.readFromFile(sourceFilePath);
        encrypt(content, destinationFilePath);
    }

    /**
     * Encrypts a string into an encrypted file.
     * @param content The content to be encrypted.
     * @param destinationFilePath The destination file path to be written to.
     * @throws InvalidKeyException If the supplied secret key is not valid.
     */
    private void encrypt(String content, Path destinationFilePath) throws InvalidKeyException {
        requireNonNull(content);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] initializationVector = cipher.getIV();

        try (FileOutputStream fileOut = new FileOutputStream(destinationFilePath.toString());
             CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
            fileOut.write(initializationVector);
            cipherOut.write(content.getBytes());
            logger.fine("Content encrypted.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void decrypt(Path encryptedSourceFilePath, Path destinationFilePath)
            throws InvalidAlgorithmParameterException, IOException, InvalidKeyException {
        requireNonNull(encryptedSourceFilePath);
        if (isIllegalFileFormat(encryptedSourceFilePath)) { // Guard clause
            throw new IOException();
        }
        logger.fine("Decrypting content from: " + encryptedSourceFilePath);

        FileInputStream fileIn = new FileInputStream(encryptedSourceFilePath.toString());
        byte[] fileInitializationVector = new byte[16];
        fileIn.read(fileInitializationVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileInitializationVector));

        FileUtil.writeToFile(destinationFilePath, decrypt(fileIn, cipher));

        logger.fine("Contents decrypted");
    }

    /**
     * @param fileIn The encrypted file as an input stream
     * @param cipher The matching cipher
     * @return A decrypted string from the encrypted file
     * @throws IOException If an IOException occurs while reading the file
     */
    private String decrypt(FileInputStream fileIn, Cipher cipher) throws IOException {
        // Try with resources handles auto closing readers and input streams.
        // The catch block can be omitted as exceptions are handled by the caller.
        try (CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
             InputStreamReader inputReader = new InputStreamReader(cipherIn);
             BufferedReader reader = new BufferedReader(inputReader)) {

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }

            return sb.toString();
        }
    }

    /**
     * Checks if the file is in an illegal format.
     */
    private boolean isIllegalFileFormat(Path filePath) {
        requireNonNull(filePath);
        return !filePath.toString().endsWith(LEGAL_FILE_FORMAT_EXTENSION);
    }
}
