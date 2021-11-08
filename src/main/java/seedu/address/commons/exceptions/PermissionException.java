package seedu.address.commons.exceptions;

/**
 * Represents an error during insufficient permission.
 */
public class PermissionException extends Exception {

    /**
     * @param message should contain relevant information on the failed constraint(s).
     */
    public PermissionException(String message) {
        super(message);
    }

}
