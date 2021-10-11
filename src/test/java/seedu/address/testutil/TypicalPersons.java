package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUS_NETWORK_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUS_NETWORK_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;

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
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("e0000000@u.nus.edu")
            .withPhone("94351253")
            .withTags("friends")
            .withGitHubId("alice-pauline")
            .withNusNetworkId("e0000000")
            .withType("student")
            .withStudentId("A0000000X")
            .withTutorialId("00")
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("e0000001@u.nus.edu")
            .withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withGitHubId("benson-meier")
            .withNusNetworkId("e0000001")
            .withType("student")
            .withStudentId("A0000001X")
            .withTutorialId("01")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("e0000002@u.nus.edu")
            .withAddress("wall street")
            .withGitHubId("carl-kurz")
            .withNusNetworkId("e0000002")
            .withType("student")
            .withStudentId("A0000002X")
            .withTutorialId("02")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("e0000003@u.nus.edu")
            .withAddress("10th street")
            .withTags("friends")
            .withGitHubId("daniel-meier")
            .withNusNetworkId("e0000003")
            .withType("student")
            .withStudentId("A0000003X")
            .withTutorialId("03")
            .build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("e0000004@u.nus.edu")
            .withAddress("michegan ave")
            .withGitHubId("elle-meyer")
            .withNusNetworkId("e0000004")
            .withType("student")
            .withStudentId("A0000004X")
            .withTutorialId("04")
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("e0000005@u.nus.edu")
            .withAddress("little tokyo")
            .withGitHubId("fiona-kunz")
            .withNusNetworkId("e0000005")
            .withType("student")
            .withStudentId("A0000005X")
            .withTutorialId("05")
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withPhone("9482442")
            .withEmail("e0000006@u.nus.edu")
            .withAddress("4th street")
            .withGitHubId("george-best")
            .withNusNetworkId("e0000006")
            .withType("student")
            .withStudentId("A0000006X")
            .withTutorialId("06")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("e0000007@u.nus.edu")
            .withAddress("little india")
            .withGitHubId("hoon-meier")
            .withNusNetworkId("e0000007")
            .withType("student")
            .withStudentId("A0000007X")
            .withTutorialId("07")
            .build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("e0000008@u.nus.edu")
            .withAddress("chicago ave")
            .withGitHubId("ida-mueller")
            .withNusNetworkId("e0000008")
            .withType("student")
            .withStudentId("A0000008X")
            .withTutorialId("08")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND)
            .withGitHubId(VALID_GITHUB_ID_AMY)
            .withNusNetworkId(VALID_NUS_NETWORK_ID_AMY)
            .withType(VALID_TYPE_AMY)
            .withStudentId(VALID_STUDENT_ID_AMY)
            .withTutorialId(VALID_TUTORIAL_ID_AMY)
            .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withGitHubId(VALID_GITHUB_ID_BOB)
            .withNusNetworkId(VALID_NUS_NETWORK_ID_BOB)
            .withType(VALID_TYPE_BOB)
            .withStudentId(VALID_STUDENT_ID_BOB)
            .withTutorialId(VALID_TUTORIAL_ID_BOB)
            .build();

    //public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

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
