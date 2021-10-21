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
 * The object that encrypts and decrypts
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
        byte[] iv = cipher.getIV();

        try (FileOutputStream fileOut = new FileOutputStream(destinationFilePath.toString());
             CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
            fileOut.write(iv);
            cipherOut.write(content.getBytes());
            logger.fine("Content encrypted.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void decrypt(Path encryptedSourceFilePath, Path destinationFilePath)
            throws InvalidAlgorithmParameterException, IOException, InvalidKeyException {
        logger.fine("Decrypting content from: " + encryptedSourceFilePath);
        FileUtil.writeToFile(destinationFilePath, decrypt(encryptedSourceFilePath));
    }

    private String decrypt(Path encryptedSourceFilePath)
            throws IOException, InvalidAlgorithmParameterException, InvalidKeyException {
        assert encryptedSourceFilePath != null;

        if (isIllegalFileFormat(encryptedSourceFilePath)) { // Guard clause
            throw new IOException();
        }
        FileInputStream fileIn = new FileInputStream(encryptedSourceFilePath.toString());
        byte[] fileIv = new byte[16];
        fileIn.read(fileIv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));
        CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);

        InputStreamReader inputReader = new InputStreamReader(cipherIn);
        BufferedReader reader = new BufferedReader(inputReader);
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }

        logger.fine("Content decrypted.");

        return sb.toString();
    }

    /**
     * Checks if the file is in an illegal format.
     */
    private boolean isIllegalFileFormat(Path filePath) {
        requireNonNull(filePath);
        return !filePath.toString().endsWith(LEGAL_FILE_FORMAT_EXTENSION);
    }
}
