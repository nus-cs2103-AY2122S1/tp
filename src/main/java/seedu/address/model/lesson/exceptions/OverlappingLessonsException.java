package seedu.address.model.lesson.exceptions;

public class OverlappingLessonsException extends RuntimeException {
    public static final String MESSAGE = "New lesson overlaps with existing lessons";

    public OverlappingLessonsException() {
        super(MESSAGE);
    }
}
