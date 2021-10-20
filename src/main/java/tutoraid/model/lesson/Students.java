package tutoraid.model.lesson;

import java.util.ArrayList;

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

    @Override
    public String toString() {
        String str = "";
        int counter = 1;

        for (Student student : students) {
            str += "\n" + counter + ".  " + student.toNameString();
            counter++;
        }

        return str;
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
