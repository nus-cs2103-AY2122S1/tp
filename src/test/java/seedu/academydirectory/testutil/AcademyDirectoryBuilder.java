package seedu.academydirectory.testutil;

import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.model.student.Student;

/**
 * A utility class to help with building AcademyDirectory objects.
 * Example usage: <br>
 *     {@code AcademyDirectory ab = new AcademyDirectoryBuilder().withStudent("John", "Doe").build();}
 */
public class AcademyDirectoryBuilder {

    private AcademyDirectory academyDirectory;

    public AcademyDirectoryBuilder() {
        academyDirectory = new AcademyDirectory();
    }

    public AcademyDirectoryBuilder(AcademyDirectory academyDirectory) {
        this.academyDirectory = academyDirectory;
    }

    /**
     * Adds a new {@code Student} to the {@code AcademyDirectory} that we are building.
     */
    public AcademyDirectoryBuilder withStudent(Student student) {
        academyDirectory.addStudent(student);
        return this;
    }

    public AcademyDirectory build() {
        return academyDirectory;
    }
}
