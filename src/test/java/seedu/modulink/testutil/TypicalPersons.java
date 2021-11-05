package seedu.modulink.testutil;

import static seedu.modulink.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_GITHUB_USERNAME_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_GITHUB_USERNAME_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_TAG_CS2100;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_TAG_CS2103T;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.modulink.model.AddressBook;
import seedu.modulink.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withStudentId("A1234567A")
            .withEmail("alice@example.com")
            .withGitHubUsername("alicepauline")
            .withTelegramHandle("@alicepauline_99")
            .withPhone("94351253")
            .withFavourite(true)
            .withTags("CS2100").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withStudentId("A1234567B")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withFavourite(true)
            .withTags("CS2103T", "CS2100").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withStudentId("A1234567C")
            .withEmail("heinz@example.com").withGitHubUsername("carlkurz")
            .withFavourite(true).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withStudentId("A1234567D").withEmail("cornelia@example.com").withTelegramHandle("@danielmeierr")
            .withFavourite(false).withTags("CS2103T").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withStudentId("A1234567E").withFavourite(false).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withGitHubUsername("fiona123")
            .withStudentId("A1234567F").withFavourite(false).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withStudentId("A1234567G").withFavourite(false).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withStudentId("A1234567H")
            .withPhone("8482424")
            .withEmail("stefan@example.com").withFavourite(false).build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withStudentId("A1234567I")
            .withPhone("8482131")
            .withEmail("hans@example.com").withFavourite(false).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withStudentId(VALID_ID_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withGitHubUsername(VALID_GITHUB_USERNAME_AMY)
            .withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY).withTags(VALID_TAG_CS2100).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withStudentId(VALID_ID_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withGitHubUsername(VALID_GITHUB_USERNAME_BOB)
            .withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB).withTags(VALID_TAG_CS2103T, VALID_TAG_CS2100)
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
