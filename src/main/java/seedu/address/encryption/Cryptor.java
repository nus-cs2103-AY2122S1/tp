package seedu.address.encryption;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Cryptor implements Cryptable {
    /**
     * File to be decrypted must have the {@code *.enc} extension.
     */
    private static final String LEGAL_FILE_FORMAT_EXTENSION = ".enc";

    private final SecretKey secretKey;
    private final Cipher cipher;

    /**
     * @param secretKey The key used for encryption and decryption.
     * @param transformation The transformation scheme used to generate the encryption algorithm.
     * @throws NoSuchPaddingException If the padding does not exist.
     * @throws NoSuchAlgorithmException If the specified algorithm does not exist.
     */
    public Cryptor(SecretKey secretKey, String transformation)
            throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.secretKey = secretKey;
        this.cipher = Cipher.getInstance(transformation);
    }

    /**
     * Encrypts a string and writes the encrypted string into a file.
     * @param content The String to be encrypted.
     * @param destinationFilePath The destination file path to be written to.
     * @throws InvalidKeyException If the supplied secret key is not valid.
     */
    @Override
    public void encrypt(String content, Path destinationFilePath) throws InvalidKeyException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();

        try (FileOutputStream fileOut = new FileOutputStream(destinationFilePath.toString());
             CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
            fileOut.write(iv);
            cipherOut.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param encryptedSourceFilePath The path to the encrypted source file.
     * @return The decrypted contents of the source file as String.
     * @throws IOException If an {@code IOException} occurs.
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException If the supplied key is invalid.
     */
    @Override
    public String decrypt(Path encryptedSourceFilePath)
            throws IOException, InvalidAlgorithmParameterException, InvalidKeyException {
        if (!isLegalFileFormat(encryptedSourceFilePath)) {
            throw new IOException(); // Guard clause
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

        return sb.toString();
    }

    /**
     * Checks if the file is in the legal format.
     */
    private boolean isLegalFileFormat(Path filePath) {
        return filePath.toString().endsWith(LEGAL_FILE_FORMAT_EXTENSION);
    }
}
