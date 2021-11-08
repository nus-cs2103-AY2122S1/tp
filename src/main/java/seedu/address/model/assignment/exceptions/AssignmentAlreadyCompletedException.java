package seedu.address.model.assignment.exceptions;

public class AssignmentAlreadyCompletedException extends RuntimeException {
    public AssignmentAlreadyCompletedException() {
        super("This assignment is already marked!");
    }
}
