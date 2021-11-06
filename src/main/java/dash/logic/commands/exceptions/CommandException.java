package dash.logic.commands.exceptions;

/**
 * Represents an error which occurs during execution of a {@link dash.logic.commands.Command Command}.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     *
     * @param message The error message to be encapsulated.
     * @param cause The previous exception that caused this exception to be constructed.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
