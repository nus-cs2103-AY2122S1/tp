package tutoraid.model.lesson.exceptions;

/**
 * Signals that the operation will result in duplicate Students in Lesson (Students are considered duplicates if they
 * are the same objects).
 */
public class DuplicateStudentInLessonException extends RuntimeException {
    public DuplicateStudentInLessonException() {
        super("Operation would result in duplicate students in lesson");
    }
}
