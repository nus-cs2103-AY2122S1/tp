package seedu.address.model.member.exceptions;

/**
 * Signals that the operation will result in duplicate Members (Members are considered duplicates if they have the same
 * identity).
 */
public class DuplicateMemberException extends RuntimeException {
    /**
     * Constructs a {@code DuplicateMemberException} without input.
     */
    public DuplicateMemberException() {
        super("Operation would result in duplicate members");
    }
}
