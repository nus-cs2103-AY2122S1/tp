package seedu.address.testutil;

import seedu.address.model.CsBook;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building Csbook objects.
 * Example usage: <br>
 *     {@code CsBook ab = new CsBookBuilder().withStudent("John", "Doe").build();}
 */
public class CsBookBuilder {

    private CsBook csBook;

    public CsBookBuilder() {
        csBook = new CsBook();
    }

    public CsBookBuilder(CsBook csBook) {
        this.csBook = csBook;
    }

    /**
     * Adds a new {@code Student} to the {@code CsBook} that we are building.
     */
    public CsBookBuilder withStudent(Student student) {
        csBook.addStudent(student);
        return this;
    }

    public CsBook build() {
        return csBook;
    }
}
