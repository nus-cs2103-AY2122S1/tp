package tutoraid.model.student.exceptions;

/**
 * Signals that the operation is unable to continue as it requires a non-empty progress list.
 */
public class EmptyProgressListException extends RuntimeException {
    public EmptyProgressListException() {
        super("The list of progress entries is empty for this student.");
    }
}
