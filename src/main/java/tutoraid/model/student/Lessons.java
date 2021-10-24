package tutoraid.model.student;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.exceptions.DuplicateLessonForStudentException;
import tutoraid.model.student.exceptions.LessonNotFoundForStudentException;

/**
 * Represents a Student's list of Lessons that he/she is attending in TutorAid.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lessons {

    public final ArrayList<Lesson> lessons;

    /**
     * Constructs a {@code Lessons}.
     *
     * @param lessons Valid arraylist of Lesson objects.
     */
    public Lessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    /**
     * Checks if this arraylist of lessons contains a lesson.
     *
     * @param lesson a Lesson object to be checked
     */
    public boolean containsLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessons.contains(lesson);
    }

    /**
     * Adds a lesson into this arraylist of lessons.
     *
     * @param lesson a Lesson object to be added
     * @throws DuplicateLessonForStudentException if the lesson is already in this arraylist of lessons
     */
    public void addLesson(Lesson lesson) {
        requireNonNull(lesson);
        if (lessons.contains(lesson)) {
            throw new DuplicateLessonForStudentException();
        }
        lessons.add(lesson);
    }

    /**
     * Removes a lesson from this arraylist of lessons.
     *
     * @param lesson a Lesson object to be removed
     * @throws LessonNotFoundForStudentException if this arraylist of lessons does not contain the lesson
     */
    public void removeLesson(Lesson lesson) {
        requireNonNull(lesson);
        if (!lessons.remove(lesson)) {
            throw new LessonNotFoundForStudentException();
        }
    }

    @Override
    public String toString() {
        String str = "";
        int counter = 1;

        for (Lesson lesson : lessons) {
            str += "\n" + counter + ".  " + lesson.toNameString();
            counter++;
        }

        return str;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lessons // instanceof handles nulls
                && lessons.equals(((Lessons) other).lessons)); // state check
    }

    @Override
    public int hashCode() {
        return lessons.hashCode();
    }
}

