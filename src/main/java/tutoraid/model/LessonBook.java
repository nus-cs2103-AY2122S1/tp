package tutoraid.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.UniqueLessonList;

/**
 * Wraps all data at the lesson-book level
 * Duplicates are not allowed (by .isSameLesson comparison)
 */
public class LessonBook implements ReadOnlyLessonBook {

    private final UniqueLessonList lessons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        lessons = new UniqueLessonList();
    }

    public LessonBook() {}

    /**
     * Creates a LessonBook using the Lessons in the {@code toBeCopied}
     */
    public LessonBook(ReadOnlyLessonBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the lesson list with {@code lessons}.
     * {@code lessons} must not contain duplicate lessons.
     */
    public void setLessons(List<Lesson> lessons) {
        this.lessons.setLessons(lessons);
    }

    /**
     * Resets the existing data of this {@code StudentBook} with {@code newData}.
     */
    public void resetData(ReadOnlyLessonBook newData) {
        requireNonNull(newData);
        setLessons(newData.getLessonList());
    }

    //// lesson-level operations

    /**
     * Returns true if a lesson with the same identity as {@code lesson} exists in the lesson book.
     */
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessons.contains(lesson);
    }

    /**
     * Adds a lesson to the lesson book.
     * The lesson must not already exist in the lesson book.
     */
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    /**
     * Replaces the given lesson {@code target} in the list with {@code editedLesson}.
     * {@code target} must exist in the lesson book.
     * The lesson identity of {@code editedLesson} must not be the same as another existing lesson in the lesson book.
     */
    public void setLesson(Lesson target, Lesson editedLesson) {
        requireNonNull(editedLesson);

        lessons.setLesson(target, editedLesson);
    }

    /**
     * Removes {@code key} from this {@code LessonBook}.
     * {@code key} must exist in the lesson book.
     */
    public void removeLesson(Lesson key) {
        lessons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return lessons.asUnmodifiableObservableList().size() + " lessons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Lesson> getLessonList() {
        return lessons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonBook // instanceof handles nulls
                && lessons.equals(((LessonBook) other).lessons));
    }

    @Override
    public int hashCode() {
        return lessons.hashCode();
    }
}
