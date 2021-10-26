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
     * Constructs a {@code Students}.
     *
     * @param students Valid arraylist of Student objects.
     */
    public Students(ArrayList<Student> students) {
        this.students = students;
    }

    /**
     * Returns true if student is in the set of students.
     */
    public boolean hasStudent(Student otherStudent) {
        return students.contains(otherStudent);
    }

    /**
     * Checks if this arraylist of students contains a student.
     *
     * @param student a Student object to be checked
     */
    public boolean containsStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student into this arraylist of students.
     *
     * @param student a Student object to be added
     * @throws DuplicateStudentInLessonException if this arraylist of students already contains the student
     */
    public void addStudent(Student student) {
        requireNonNull(student);
        if (students.contains(student)) {
            throw new DuplicateStudentInLessonException();
        }
        students.add(student);
    }

    /**
     * Removes a student from this arraylist of students.
     *
     * @param student a Student object to be removed
     * @throws StudentNotFoundInLessonException if this arraylist of students does not have the student
     */
    public void removeStudent(Student student) {
        requireNonNull(student);
        if (!students.remove(student)) {
            throw new StudentNotFoundInLessonException();
        }
    }

    /**
     * Returns a string Array that contains all the student names in the correct order.
     */
    public ArrayList<String> getAllStudentNamesAsStringArrayList() {
        ArrayList<String> allStudentNamesAsStringArrayList = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            String currentLessonName = students.get(i).toNameString();
            allStudentNamesAsStringArrayList.add(currentLessonName);
        }
        return allStudentNamesAsStringArrayList;
    }

    @Override
    public String toString() {
        String str = "";
        int counter = 1;

        for (Student student : students) {
            str += "\n" + counter + ".  " + student.toNameString();
            counter++;
        }

        if (str.equals("")) {
            return "No students";
        } else {
            return str;
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
