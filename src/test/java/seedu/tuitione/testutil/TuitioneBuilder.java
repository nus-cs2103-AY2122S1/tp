package seedu.tuitione.testutil;

import seedu.tuitione.model.Tuitione;
import seedu.tuitione.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Tuitione ab = new TuitioneBuilder().withStudent("John", "Doe").build();}
 */
public class TuitioneBuilder {

    private Tuitione tuitione;

    public TuitioneBuilder() {
        tuitione = new Tuitione();
    }

    public TuitioneBuilder(Tuitione tuitione) {
        this.tuitione = tuitione;
    }

    /**
     * Adds a new {@code Student} to the {@code Tuitione} that we are building.
     */
    public TuitioneBuilder withStudent(Student student) {
        tuitione.addStudent(student);
        return this;
    }

    public Tuitione build() {
        return tuitione;
    }
}
