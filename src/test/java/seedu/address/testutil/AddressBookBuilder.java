package seedu.address.testutil;

import seedu.address.model.StudentBook;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code StudentBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private StudentBook studentBook;

    public AddressBookBuilder() {
        studentBook = new StudentBook();
    }

    public AddressBookBuilder(StudentBook studentBook) {
        this.studentBook = studentBook;
    }

    /**
     * Adds a new {@code Student} to the {@code StudentBook} that we are building.
     */
    public AddressBookBuilder withPerson(Student student) {
        studentBook.addPerson(student);
        return this;
    }

    public StudentBook build() {
        return studentBook;
    }
}
