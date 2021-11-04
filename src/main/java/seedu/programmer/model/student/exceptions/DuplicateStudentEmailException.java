package seedu.programmer.model.student.exceptions;

public class DuplicateStudentEmailException extends IllegalArgumentException {
    public DuplicateStudentEmailException() {
        super("Operation would result in students with similar Email");
    }
}