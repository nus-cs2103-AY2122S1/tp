package seedu.academydirectory.testutil;

import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AcademyDirectoryBuilder().withPerson("John", "Doe").build();}
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
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AcademyDirectoryBuilder withPerson(Person person) {
        academyDirectory.addPerson(person);
        return this;
    }

    public AcademyDirectory build() {
        return academyDirectory;
    }
}
