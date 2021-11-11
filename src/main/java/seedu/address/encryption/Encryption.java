package seedu.address.encryption;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public interface Encryption {
    /**
     * Encrypts a file from the supplied file path and writes into an encrypted file.
     * @param sourceFilePath The source file path to be encrypted.
     * @param destinationFilePath The destination file path to be written to.
     * @throws InvalidKeyException If the supplied secret key is not valid.
     * @throws IOException If an {@code IOException} occurs.
     */
    void encrypt(Path sourceFilePath, Path destinationFilePath) throws InvalidKeyException, IOException;

    /**
     * Decrypts a file from the supplied file path and writes into a decrypted file.
     * @param encryptedSourceFilePath The path to the encrypted source file.
     * @param destinationFilePath The destination file path to be written to.
     * @throws IOException If a key mismatch occurs.
     * @throws InvalidAlgorithmParameterException If an invalid encryption algorithm is supplied.
     * @throws InvalidKeyException If the supplied key is invalid.
     */
    void decrypt(Path encryptedSourceFilePath, Path destinationFilePath)
            throws InvalidAlgorithmParameterException, IOException, InvalidKeyException;
}
