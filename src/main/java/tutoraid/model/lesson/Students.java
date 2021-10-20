package tutoraid.model.lesson;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
     * Returns true if both sets of students have the same names.
     * This defines a weaker notion of equality between two sets of students.
     */
    public boolean areSameStudents(Students otherStudents) {
        if (otherStudents == this) {
            return true;
        }

        return otherStudents.equals(this);
    }

    @Override
    public String toString() {
        String connector = "\n    ";
        return connector + students.stream().map(Student::toNameString)
                .collect(Collectors.joining(connector));
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
