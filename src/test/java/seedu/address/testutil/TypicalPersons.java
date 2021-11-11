package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOURLYSALARY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOURLYSALARY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOURSWORKED_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOURSWORKED_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OVERTIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRole("Quality Assurance").withLeaves("14").withHourlySalary("15.00")
            .withHoursWorked("70").withOvertime("5").withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com").withPhone("98765432")
            .withRole("Software Developer").withLeaves("20").withHourlySalary("25.00").withHoursWorked("60")
            .withOvertime("0").withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withRole("Accountant").withLeaves("11")
            .withHourlySalary("18.00").withHoursWorked("67").withOvertime("0").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withRole("Legal Assistant")
            .withLeaves("20").withHourlySalary("15.00").withHoursWorked("60").withOvertime("0").withTags("friends")
            .build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withRole("UI/UX Designer")
            .withLeaves("20").withHourlySalary("16.66").withHoursWorked("50").withOvertime("0").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withRole("HR Manager")
            .withLeaves("15").withHourlySalary("16.66").withHoursWorked("40").withOvertime("0").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withRole("Team Manager (Web Design)").withLeaves("8")
            .withHourlySalary("21.33").withHoursWorked("55").withOvertime("0").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withRole("Advertising Team Leader")
            .withLeaves("20").withHourlySalary("29.00").withHoursWorked("20").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withRole("IT Assistant")
            .withLeaves("20").withHourlySalary("17.33").withHoursWorked("60").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withRole(VALID_ROLE_AMY)
            .withLeaves(VALID_LEAVES_AMY).withHourlySalary(VALID_HOURLYSALARY_AMY)
            .withHoursWorked(VALID_HOURSWORKED_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withRole(VALID_ROLE_BOB)
            .withLeaves(VALID_LEAVES_BOB).withHourlySalary(VALID_HOURLYSALARY_BOB)
            .withHoursWorked(VALID_HOURSWORKED_BOB).withOvertime(VALID_OVERTIME_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

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
