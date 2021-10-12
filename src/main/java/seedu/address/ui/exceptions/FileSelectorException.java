package seedu.address.ui.exceptions;

/**
 * Represents an error encountered by a file selector.
 */
public class FileSelectorException extends Exception {
    public FileSelectorException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code FileSelectorException} with the specified detail {@code message} and {@code cause}.
     */
    public FileSelectorException(String message, Throwable cause) {
        super(message, cause);
    }
}
