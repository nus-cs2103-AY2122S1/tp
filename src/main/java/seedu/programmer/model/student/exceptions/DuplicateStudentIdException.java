package seedu.programmer.model.student.exceptions;

public class DuplicateStudentIdException extends IllegalArgumentException {
    public DuplicateStudentIdException() {
        super("Operation would result in students with similar Student Id");
    }
}