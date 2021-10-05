package seedu.address.model.lesson;

import java.util.ArrayList;
import java.util.Iterator;

import seedu.address.model.lesson.exceptions.OverlappingLessonsException;

/**
 * Immutable class for a list of lessons that do not overlap
 */
public class NoOverlapLessonList implements Iterable<Lesson> {

    private static final String MESSAGE_NO_LESSONS = "No lessons found";

    private final ArrayList<Lesson> lessonsList;

    public NoOverlapLessonList() {
        lessonsList = new ArrayList<>();
    }

    /**
     * Private constructor to enforce immutability
     */
    private NoOverlapLessonList(ArrayList<Lesson> lessonsList) {
        this.lessonsList = lessonsList;
    }

    /**
     * Adds a lesson to the lessons list, however the lesson must not overlap with current lessons
     * @param lesson to add to the list
     * @throws OverlappingLessonsException if lesson overlaps
     */
    public NoOverlapLessonList addLesson(Lesson lesson) throws OverlappingLessonsException {
        if (doesLessonOverlap(lesson)) {
            throw new OverlappingLessonsException();
        }
        ArrayList<Lesson> newList = new ArrayList<>(lessonsList);
        newList.add(lesson);
        return new NoOverlapLessonList(newList);
    }

    /**
     * Removes the lesson at index
     * @param index to remove
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public NoOverlapLessonList removeLesson(int index) throws IndexOutOfBoundsException {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        ArrayList<Lesson> newList = new ArrayList<>(lessonsList);
        newList.remove(index);
        return new NoOverlapLessonList(newList);
    }

    /**
     * Checks if given index for accessing the list is valid
     * @param index to check
     * @return true if the index is valid, false otherwise
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < lessonsList.size();
    }

    /**
     * Checks if lesson overlaps with any lesson in the list
     * @param lesson to check with
     * @return true if lesson overlaps, false otherwise
     */
    public boolean doesLessonOverlap(Lesson lesson) {
        for (Lesson l : lessonsList) {
            if (l.doLessonsOverlap(lesson)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Lesson> iterator() {
        return lessonsList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof NoOverlapLessonList)) {
            return false;
        }

        NoOverlapLessonList otherList = (NoOverlapLessonList) other;
        return lessonsList.equals(otherList.lessonsList);
    }

    @Override
    public int hashCode() {
        return lessonsList.hashCode();
    }

    @Override
    public String toString() {
        if (lessonsList.size() == 0) {
            return MESSAGE_NO_LESSONS;
        }
        final StringBuilder builder = new StringBuilder();
        for (Lesson l : lessonsList) {
            builder.append(l.toString()).append(" ");
        }
        return builder.toString();
    }

}
