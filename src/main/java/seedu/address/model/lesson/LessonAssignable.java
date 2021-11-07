package seedu.address.model.lesson;

import java.util.List;

import seedu.address.model.lesson.exceptions.CannotAssignException;

public interface LessonAssignable {

    /**
     * Check if the assignable can be assigned the lesson
     *
     * @param lesson lesson to check
     * @return true if lesson can be assigned
     */
    boolean canAssignLesson(Lesson lesson);

    /**
     * Immutable way of adding a lesson
     * @param lesson to add
     * @return LessonAssignable with added lesson
     * @throws CannotAssignException if person is unable to attend lesson
     */
    LessonAssignable assignLesson(Lesson lesson) throws CannotAssignException;

    /**
     * Checks if the index is valid, meaning that it exists.
     * @param index of lesson to check.
     * @return true if index is a valid lesson.
     */
    boolean isValidLessonIndex(int index);

    /**
     * Immutable way of removing a lesson
     * @param index of lesson to remove
     * @return LessonAssignable with removed lesson
     * @throws IndexOutOfBoundsException if index specified is out of bounds
     */
    LessonAssignable unassignLesson(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the lessons assigned
     * @return lessons list
     */
    List<Lesson> getLessons();

    /**
     * Change the lessons of this lesson assignable person to the specified lesson list
     *
     * @param lessons to set with
     * @return LessonAssignable with all lessons
     * @throws CannotAssignException if any of the lessons cannot be assigned
     */
    LessonAssignable setLessons(List<Lesson> lessons) throws CannotAssignException;
}
