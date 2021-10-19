package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import seedu.address.model.lesson.exceptions.OverlappingLessonsException;

/**
 * Immutable class for a list of lessons that do not overlap
 */
public class NoOverlapLessonList implements Iterable<Lesson> {

    public static final String LESSON_OVERLAP = "One or more lessons overlap";
    public static final String MESSAGE_NO_LESSONS = "No lessons found";

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
     * Factory method that does the copying of lessons list
     * @param lessonsList a list of Lessons
     * @return an instance of NoOverlapLessonList with the specified lessons
     */
    public static NoOverlapLessonList of(List<Lesson> lessonsList) throws OverlappingLessonsException {
        if (lessonsList == null || lessonsList.isEmpty()) {
            return new NoOverlapLessonList();
        }

        ArrayList<Lesson> newList = new ArrayList<>(lessonsList);

        if (doAnyLessonsOverlap(lessonsList)) {
            throw new OverlappingLessonsException();
        }

        return new NoOverlapLessonList(newList);
    }

    /**
     * Check if any lessons in a list overlaps
     * @param toCheck List to check
     * @return true if any lessons in the list overlaps
     */
    public static boolean doAnyLessonsOverlap(List<Lesson> toCheck) {
        for (int i = 0; i < toCheck.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (toCheck.get(i).doLessonsOverlap(toCheck.get(j))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Adds a lesson to the lessons list, however the lesson must not overlap with current lessons
     * @param lesson to add to the list
     * @throws OverlappingLessonsException if lesson overlaps
     */
    public NoOverlapLessonList addLesson(Lesson lesson) throws OverlappingLessonsException {
        requireNonNull(lesson);
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

    /**
     * Get lessons in a readonly format
     * @return lessons in a readonly list
     */
    public List<Lesson> getLessons() {
        return Collections.unmodifiableList(lessonsList);
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
        int index = 1;
        for (Lesson l : lessonsList) {
            builder.append(index).append(". ").append(l.toString()).append(" ");
            index++;
        }
        return builder.toString();
    }

}
