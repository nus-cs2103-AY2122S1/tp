package tutoraid.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate Lessons in Student (Lessons are considered duplicates if they
 * are the same objects).
 */
public class DuplicateLessonForStudentException extends RuntimeException {
    public DuplicateLessonForStudentException() {
        super("Operation would result in duplicate lessons for student");
    }
}
