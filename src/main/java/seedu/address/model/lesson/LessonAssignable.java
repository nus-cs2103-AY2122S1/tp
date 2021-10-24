package seedu.address.model.lesson;

import java.util.List;

import seedu.address.model.lesson.exceptions.CannotAssignException;

public interface LessonAssignable {

    /**
     * Immutable way of adding a lesson
     * @param lesson to add
     * @return Person with added lesson
     * @throws CannotAssignException if person is unable to attend lesson
     */
    LessonAssignable assignLesson(Lesson lesson) throws CannotAssignException;

    /**
     * Immutable way of removing a lesson
     * @param index of lesson to remove
     * @return Person with removed lesson
     * @throws IndexOutOfBoundsException if index specified is out of bounds
     */
    LessonAssignable unassignLesson(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the lessons assigned together with the attendees for the lesson
     * @return lesson and attendees;
     */
    List<LessonWithAttendees> getLessonsWithAttendees();
}
