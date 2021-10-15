package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CLASSMATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 *
 * Data here should match data in typicalPersonsAddressBook.json
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withFaculty("computing")
            .withMajor("computer science")
            .withSkills("frontend")
            .withLanguages("javascript", "html", "css")
            .withFrameworks("reactJS")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withFaculty("computing")
            .withMajor("business analytics")
            .withSkills("backend")
            .withLanguages("sql")
            .withFrameworks("psql")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withEmail("heinz@example.com")
            .withFaculty("computing")
            .withMajor("business analytics")
            .withSkills("UIUX").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withEmail("cornelia@example.com")
            .withFaculty("computing")
            .withMajor("computer science")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withEmail("werner@example.com")
            .withFaculty("computing")
            .withMajor("business analytics").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withEmail("lydia@example.com")
            .withFaculty("fass")
            .withMajor("economics")
            .withSkills("marketing").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withEmail("anna@example.com")
            .withFaculty("computing")
            .withMajor("business analytics")
            .withLanguages("python").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withEmail("stefan@example.com")
            .withFaculty("computing")
            .withMajor("business analytics").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withEmail("hans@example.com")
            .withFaculty("computing")
            .withMajor("information systems").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withName(VALID_NAME_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withFaculty(VALID_FACULTY_AMY)
            .withMajor(VALID_MAJOR_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withFaculty(VALID_FACULTY_BOB)
            .withMajor(VALID_MAJOR_BOB)
            .withTags(VALID_TAG_CLASSMATE, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
