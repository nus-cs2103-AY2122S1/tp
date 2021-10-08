package seedu.address.model.lesson.exceptions;

public class OverlappingLessonsException extends RuntimeException {
    public OverlappingLessonsException() {
        super("New lesson overlaps with existing lessons");
    }
}
