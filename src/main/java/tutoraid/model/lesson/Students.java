package tutoraid.model.lesson;

import tutoraid.model.student.Student;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a Lesson's students in TutorAid.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudents(String)}
 */
public class Students {

    public final ArrayList<Student> value;

    /**
     * Constructs a {@code Students}.
     *
     * @param students Valid students' indexes.
     */
    public Students(ArrayList<Student> students) {
        value = students;
    }

    /**
     * Returns true if both sets of students have the same names.
     * This defines a weaker notion of equality between two sets of students.
     */
    public boolean areSameStudents(Students otherStudents) {
        if (otherStudents == this) {
            return true;
        }

        return otherStudents != null
                && otherStudents.equals(this);
    }

    @Override
    public String toString() {
        return value.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Students // instanceof handles nulls
                && value.equals(((Students) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
