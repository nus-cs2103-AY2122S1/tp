package manageme.model.link.exceptions;

/**
 * Represents an error during conversion of data from one format to another
 */
public class LinkNotOpenException extends RuntimeException {
    public LinkNotOpenException() {
        super("Cannot open Link.");
    }
}
