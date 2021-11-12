package seedu.teachbook.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate students
 * (students are considered duplicates if they have the same {@code Name} and are from the same {@code Class}).
 */
public class DuplicateStudentException extends RuntimeException {
    public DuplicateStudentException() {
        super("Operation would result in duplicate students");
    }
}
