package seedu.address.testutil;

import seedu.address.model.Classmate;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building ClassMATE objects.
 * Example usage: <br>
 *     {@code Classmate ab = new ClassmateBuilder().withStudent("John", "Doe").build();}
 */
public class ClassmateBuilder {

    private Classmate classmate;

    public ClassmateBuilder() {
        classmate = new Classmate();
    }

    public ClassmateBuilder(Classmate classmate) {
        this.classmate = classmate;
    }

    /**
     * Adds a new {@code Student} to the {@code Classmate} that we are building.
     */
    public ClassmateBuilder withStudent(Student student) {
        classmate.addStudent(student);
        return this;
    }

    public Classmate build() {
        return classmate;
    }
}
