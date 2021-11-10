package seedu.address.commons.exceptions;

/**
 * Represents an error during conversion of data from one format to another
 */
public class DataConversionException extends Exception {
    /**
     * This exception is thrown when there is data conversion error.
     *
     * @param cause of the exception.
     */
    public DataConversionException(Exception cause) {
        super(cause);
    }

}
