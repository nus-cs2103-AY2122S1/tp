package seedu.siasa.model.policy.exceptions;

public class DuplicatePolicyException extends RuntimeException {
    public DuplicatePolicyException() {
        super("Operation would result in duplicate policies.");
    }
}
