package tutoraid.testutil;

import tutoraid.model.StudentBook;
import tutoraid.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code StudentBook ab = new StudentBookBuilder().withPerson("John", "Doe").build();}
 */
public class StudentBookBuilder {

    private StudentBook studentBook;

    public StudentBookBuilder() {
        studentBook = new StudentBook();
    }

    public StudentBookBuilder(StudentBook studentBook) {
        this.studentBook = studentBook;
    }

    /**
     * Adds a new {@code Student} to the {@code StudentBook} that we are building.
     */
    public StudentBookBuilder withStudent(Student student) {
        studentBook.addStudent(student);
        return this;
    }

    public StudentBook build() {
        return studentBook;
    }
}
