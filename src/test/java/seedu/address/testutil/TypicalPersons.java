package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Shortcut;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withPrice("300.00").withInfo("none").withStatus("Confirmed")
            .withTags("Florist", "friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withPrice("0.00").withInfo("none")
            .withStatus("Pending").withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withPrice("0.00").withInfo("none")
            .withStatus("Confirmed").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withPrice("650.00").withInfo("none").withStatus("Confirmed")
            .withTags("caterer", "friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withPrice("0.00").withInfo("none")
            .withTags("Colleague").withStatus("Declined").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withPrice("0.00").withInfo("none")
            .withStatus("Confirmed").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withPrice("250.00").withInfo("none")
            .withTags("Photographer").withStatus("Confirmed").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withPrice("0.00").withInfo("none")
            .withStatus("Confirmed").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withPrice("0.00").withInfo("none")
            .withStatus("Pending").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withInfo(VALID_INFO_AMY).withPrice(VALID_PRICE_AMY).withStatus(VALID_STATUS_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withInfo(VALID_INFO_AMY)
            .withPrice(VALID_PRICE_AMY).withStatus(VALID_STATUS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    //Shortcut INPUTS and OUTPUTS
    public static final String VALID_KEY = "valid";
    public static final String VALID_COMMAND = "list";
    public static final String VALID_RESPONSE = ListCommand.MESSAGE_SUCCESS;
    public static final String UNKNOWN_KEY = "unknown command";
    public static final String UNKNOWN_COMMAND = "a";
    public static final String INVALID_KEY = "invalid command";
    public static final String INVALID_COMMAND = "delete 100";
    public static final String INVALID_RESPONSE = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

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

    /**
     * Returns a {@code Shortcut} with all the typical shortcuts.
     */
    public static Shortcut getTypicalShortcut() {
        Shortcut sc = new Shortcut();
        sc.addShortcut(VALID_KEY, VALID_COMMAND);
        sc.addShortcut(UNKNOWN_KEY, UNKNOWN_COMMAND);
        sc.addShortcut(INVALID_KEY, INVALID_COMMAND);
        return sc;
    }

    /**
     * Returns a {@code Shortcut} with shortcut VALID removed.
     */
    public static Shortcut getRemovedShortcut() {
        Shortcut sc = new Shortcut();
        sc.addShortcut(UNKNOWN_KEY, UNKNOWN_COMMAND);
        sc.addShortcut(INVALID_KEY, INVALID_COMMAND);
        return sc;
    }
}
