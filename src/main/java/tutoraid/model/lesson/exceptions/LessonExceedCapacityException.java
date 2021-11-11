package tutoraid.model.lesson.exceptions;

/**
 * Signals that the lesson has more students than its capacity.
 */
public class LessonExceedCapacityException extends RuntimeException {
    public LessonExceedCapacityException() {
        super("Operation is rejected as lesson exceeds its capacity.");
    }
}
