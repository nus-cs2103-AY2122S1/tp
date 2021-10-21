package seedu.address.encryption.exceptions;

import seedu.address.encryption.EncryptionKeyGenerator;

/**
 * Represents an error which occurs when generating encryption keys with {@link EncryptionKeyGenerator}.
 */
public class UnsupportedPasswordException extends Exception {
    public UnsupportedPasswordException(String message) {
        super(message);
    }
}
