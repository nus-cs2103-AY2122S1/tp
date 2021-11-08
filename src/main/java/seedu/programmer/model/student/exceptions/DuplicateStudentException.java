package seedu.programmer.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate students (students are considered duplicates if they
 * have the same identity).
 */
public class DuplicateStudentException extends IllegalArgumentException {
    public DuplicateStudentException() {
        super("Operation would result in duplicate students");
    }
}
