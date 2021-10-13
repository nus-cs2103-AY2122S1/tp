package seedu.address.testutil;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOYMENT_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOYMENT_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_SALARY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_SALARY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPERIENCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPERIENCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_OF_EDUCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_OF_EDUCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
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
            .withPhone("94351253")
            .withEmail("alice@example.com")
            .withRole("Preschool Teacher")
            .withEmploymentType("Full time")
            .withExpectedSalary("2500")
            .withLevelOfEducation("PhD")
            .withExperience("0")
            .withTags("friends").build();

    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withPhone("98765432")
            .withEmail("johnd@example.com")
            .withRole("Doctor")
            .withEmploymentType("Part time")
            .withExpectedSalary("2700")
            .withLevelOfEducation("Masters")
            .withExperience("1")
            .withTags("owesMoney", "friends").build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withRole("Electrical Engineer")
            .withEmploymentType("Temporary")
            .withExpectedSalary("5500")
            .withLevelOfEducation("Elementary")
            .withExperience("2").build();

    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withRole("Sales Assistant")
            .withEmploymentType("Internship")
            .withExpectedSalary("3500")
            .withLevelOfEducation("Middle School")
            .withExperience("3")
            .withTags("friends").build();

    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withRole("Toilet Cleaner")
            .withEmploymentType("Full time")
            .withExpectedSalary("5700")
            .withLevelOfEducation("Bachelors")
            .withExperience("4")
            .withTags("old").build();

    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withRole("NTUC Cashier")
            .withEmploymentType("Part time")
            .withExpectedSalary("1900")
            .withLevelOfEducation("High School")
            .withExperience("5").build();

    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withRole("Waiter")
            .withEmploymentType("Internship")
            .withExpectedSalary("900")
            .withLevelOfEducation("University")
            .withExperience("6").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withRole("Software Engineer")
            .withEmploymentType("Part time")
            .withExpectedSalary("3300")
            .withLevelOfEducation("PhD")
            .withExperience("7").build();

    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withRole("Software Tester")
            .withEmploymentType("Full time")
            .withExpectedSalary("7100")
            .withLevelOfEducation("Masters")
            .withExperience("8").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withRole(VALID_ROLE_AMY)
            .withEmploymentType(VALID_EMPLOYMENT_TYPE_AMY)
            .withExpectedSalary(VALID_EXPECTED_SALARY_AMY)
            .withLevelOfEducation(VALID_LEVEL_OF_EDUCATION_AMY)
            .withExperience(VALID_EXPERIENCE_AMY)
            .withTags(VALID_TAG_FRIEND)
            .build();

    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withRole(VALID_ROLE_BOB)
            .withEmploymentType(VALID_EMPLOYMENT_TYPE_BOB)
            .withExpectedSalary(VALID_EXPECTED_SALARY_BOB)
            .withLevelOfEducation(VALID_LEVEL_OF_EDUCATION_BOB)
            .withExperience(VALID_EXPERIENCE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
