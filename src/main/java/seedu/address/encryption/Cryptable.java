package seedu.address.encryption;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public interface Cryptable {
    /**
     * Encrypts a file from the supplied file path into an encrypted file.
     * @param sourceFilePath The source file path to be encrypted.
     * @param destinationFilePath The destination file path to be written to.
     * @throws InvalidKeyException If the supplied secret key is not valid.
     */
    void encrypt(Path sourceFilePath, Path destinationFilePath) throws InvalidKeyException, IOException;

    /**
     * @param encryptedSourceFilePath The path to the encrypted source file.
     * @return The decrypted contents of the source file as String.
     * @throws IOException If an {@code IOException} occurs.
     * @throws InvalidAlgorithmParameterException If an invalid encryption algorithm is supplied.
     * @throws InvalidKeyException If the supplied key is invalid.
     */
    String decrypt(Path encryptedSourceFilePath)
            throws IOException, InvalidAlgorithmParameterException, InvalidKeyException;
}
