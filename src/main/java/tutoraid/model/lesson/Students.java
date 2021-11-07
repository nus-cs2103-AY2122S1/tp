package tutoraid.model.lesson;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import tutoraid.model.lesson.exceptions.DuplicateStudentInLessonException;
import tutoraid.model.lesson.exceptions.StudentNotFoundInLessonException;
import tutoraid.model.student.Student;

/**
 * Represents a Lesson's students in TutorAid.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Students {

    public final ArrayList<Student> students;

    /**
     * Constructs a {@code Students} object that is empty
     */
    public Students() {
        this.students = new ArrayList<Student>();
    }

    /**
     * Constructs an instance of {@code Students}.
     *
     * @param students Valid arraylist of Student objects.
     */
    public Students(ArrayList<Student> students) {
        this.students = students;
    }

    /**
     * Checks if this arraylist of students contains a student.
     *
     * @param student a Student object to be checked
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student into this arraylist of students.
     *
     * @param student a Student object to be added
     * @throws DuplicateStudentInLessonException if this arraylist of students already contains the student
     */
    public Students addStudent(Student student) {
        requireNonNull(student);
        if (students.contains(student)) {
            throw new DuplicateStudentInLessonException();
        }
        students.add(student);
        return this;
    }

    /**
     * Removes a student from this arraylist of students.
     *
     * @param student a Student object to be removed
     * @throws StudentNotFoundInLessonException if this arraylist of students does not have the student
     */
    public Students removeStudent(Student student) {
        requireNonNull(student);
        if (!students.remove(student)) {
            throw new StudentNotFoundInLessonException();
        }
        return this;
    }

    /**
     * Returns a string Array that contains all the student names in the correct order.
     */
    public ArrayList<String> getAllStudentNamesAsStringArrayList() {
        ArrayList<String> allStudentNamesAsStringArrayList = new ArrayList<>();
        for (Student student : students) {
            String currentLessonName = student.toNameString();
            allStudentNamesAsStringArrayList.add(currentLessonName);
        }
        return allStudentNamesAsStringArrayList;

    }

    /**
     * Returns the number of students in this list.
     */
    public int numberOfStudents() {
        return students.size();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int counter = 1;

        for (Student student : students) {
            str.append("\n").append(counter).append(".  ").append(student.toNameString());
            counter++;
        }

        if (str.toString().equals("")) {
            return "No students";
        } else {
            return str.toString();
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Students // instanceof handles nulls
                && students.equals(((Students) other).students)); // state check
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
