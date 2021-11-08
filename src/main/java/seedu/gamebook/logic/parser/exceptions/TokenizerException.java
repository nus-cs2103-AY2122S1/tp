package seedu.gamebook.logic.parser.exceptions;

/**
 * Represents a parse error encountered by the tokenizer.
 */
public class TokenizerException extends IllegalArgumentException {

    public TokenizerException(String message) {
        super(message);
    }

    public TokenizerException(String message, Throwable cause) {
        super(message, cause);
    }
}
