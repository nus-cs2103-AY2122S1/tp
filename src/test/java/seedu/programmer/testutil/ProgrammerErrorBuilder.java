package seedu.programmer.testutil;

import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.student.Student;

/**
 * A utility class to help with building ProgrammerError objects.
 * Example usage: <br>
 *     {@code ProgrammerError ab = new ProgrammerErrorBuilder().withStudent("John", "Doe").build();}
 */
public class ProgrammerErrorBuilder {

    private ProgrammerError programmerError;

    public ProgrammerErrorBuilder() {
        programmerError = new ProgrammerError();
    }

    public ProgrammerErrorBuilder(ProgrammerError programmerError) {
        this.programmerError = programmerError;
    }

    /**
     * Adds a new {@code Student} to the {@code ProgrammerError} that we are building.
     */
    public ProgrammerErrorBuilder withStudent(Student student) {
        programmerError.addStudent(student);
        return this;
    }

    public ProgrammerError build() {
        return programmerError;
    }
}
