package tutoraid.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate LessonName objects in Student (LessonName objects are considered
 * duplicates if they are the equal).
 */
public class DuplicateStudentLessonException extends RuntimeException {
    public DuplicateStudentLessonException() {
        super("Operation would result in duplicate lessons in student");
    }
}
