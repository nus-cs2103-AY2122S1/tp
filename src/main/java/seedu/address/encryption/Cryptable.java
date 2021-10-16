package seedu.address.encryption;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public interface Cryptable {
    void encrypt(String content, Path fileName) throws InvalidKeyException;
    String decrypt(Path fileName) throws IOException, InvalidAlgorithmParameterException, InvalidKeyException;
}
