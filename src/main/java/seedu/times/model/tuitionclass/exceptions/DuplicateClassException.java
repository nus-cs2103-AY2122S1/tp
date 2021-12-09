package seedu.times.model.tuitionclass.exceptions;

/**
 * Exception thrown when user attempts at adding to unique class list a tuition class  that has same timing
 * as an already existing tuition class
 */
public class DuplicateClassException extends RuntimeException {
    public static final String DUPLICATE_CLASS_ERROR_MESSAGE = "Operation would result in classes with same timing!";
    public DuplicateClassException() {
        super(DUPLICATE_CLASS_ERROR_MESSAGE);
    }
}
