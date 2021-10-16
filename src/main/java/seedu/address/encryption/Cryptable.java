package seedu.address.encryption;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public interface Cryptable {
    void encrypt(String content, String fileName) throws InvalidKeyException;
    String decrypt(String fileName) throws IOException, InvalidAlgorithmParameterException, InvalidKeyException;
}
