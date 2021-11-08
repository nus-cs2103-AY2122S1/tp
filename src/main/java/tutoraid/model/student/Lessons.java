package tutoraid.model.student;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.exceptions.DuplicateStudentLessonsException;
import tutoraid.model.student.exceptions.LessonNotFoundInStudentException;

/**
 * Represents a student's list of lessons in TutorAid.
 */
public class Lessons {

    public static final String MESSAGE_CONSTRAINTS =
            "Lessons constructor either takes in no argument "
                    + "or takes in a String ArrayList";

    public final ArrayList<Lesson> lessons;

    /**
     * Constructs a {@code Lessons}.
     */
    public Lessons() {
        lessons = new ArrayList<>();
    }

    /**
     * Adds a lesson to this list.
     *
     * @param lesson the lesson to be added
     * @throws DuplicateStudentLessonsException if the lesson is already in the list
     */
    public void addLesson(Lesson lesson) {
        requireNonNull(lesson);
        if (lessons.contains(lesson)) {
            throw new DuplicateStudentLessonsException();
        }
        lessons.add(lesson);
    }

    /**
     * Removes a lesson from this list.
     *
     * @param lesson the lesson to be deleted
     * @throws LessonNotFoundInStudentException if the lesson is not in the list
     */
    public void deleteLesson(Lesson lesson) {
        requireNonNull(lesson);
        if (!lessons.contains(lesson)) {
            throw new LessonNotFoundInStudentException();
        }
        lessons.remove(lesson);
    }

    /**
     * Checks if this list has a lesson.
     */
    public boolean hasLesson(Lesson lesson) {
        return lessons.contains(lesson);
    }

    /**
     * Returns a String Array that contains all the lesson names of the lessons in this list in the correct order.
     */
    public ArrayList<String> getAllLessonNamesAsStringArrayList() {
        ArrayList<String> allLessonNamesAsStringArrayList = new ArrayList<>();
        for (Lesson lesson : lessons) {
            String currentLessonName = lesson.toNameString();
            allLessonNamesAsStringArrayList.add(currentLessonName);
        }
        return allLessonNamesAsStringArrayList;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int counter = 1;

        for (String lessonName : getAllLessonNamesAsStringArrayList()) {
            str.append("\n").append(counter).append(".  ").append(lessonName);
            counter++;
        }

        if (str.toString().equals("")) {
            return "No Lessons";
        } else {
            return str.toString();
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lessons // instanceof handles nulls
                && this.lessons.equals(((Lessons) other).lessons)); // state check
    }

    @Override
    public int hashCode() {
        return lessons.hashCode();
    }
}
