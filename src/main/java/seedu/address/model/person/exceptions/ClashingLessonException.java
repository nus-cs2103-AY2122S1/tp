package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in clashing lessons (Lessons are considered clashing if they have overlapping
 * time range).
 */
public class ClashingLessonException extends RuntimeException {
    public ClashingLessonException() {
        super("Operation would result in clashing lessons");
    }
}
