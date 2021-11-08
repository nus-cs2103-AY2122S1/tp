package seedu.smartnus.logic.commands.exceptions;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class DeleteCommandException extends CommandException {
    public DeleteCommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code DeleteCommandException} with the specified detail {@code message} and {@code cause}.
     */
    public DeleteCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
