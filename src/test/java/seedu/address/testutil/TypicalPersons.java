package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEALTH_CONDITION_DEMENTIA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEALTH_CONDITION_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LAST_VISIT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

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
            .withAddress("123, Jurong West Ave 6, #08-111").withLanguage("English")
            .withPhone("94351253").withVisit("2022-10-10 12:00").withLastVisit("2021-07-07 12:00")
            .withFrequency("").withOccurrence(1).withHealthConditions("diabetes").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withLanguage("French").withPhone("98765432").withLastVisit("2021-08-08 12:00")
            .withFrequency("").withOccurrence(1).withHealthConditions("dementia", "diabetes").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withFrequency("").withOccurrence(1).withLanguage("English").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withFrequency("").withOccurrence(1).withLanguage("Chinese")
            .withAddress("10th street").withHealthConditions("high blood pressure").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822240")
            .withFrequency("").withOccurrence(1).withLanguage("English").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94824427")
            .withFrequency("").withOccurrence(1).withLanguage("Hokkien").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("94820442")
            .withFrequency("").withOccurrence(1).withLanguage("Cantonese").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("84822424")
            .withLanguage("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("84821310")
            .withLanguage("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withLanguage(VALID_LANGUAGE_AMY).withAddress(VALID_ADDRESS_AMY)
            .withHealthConditions(VALID_HEALTH_CONDITION_DIABETES).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withLanguage(VALID_LANGUAGE_BOB).withAddress(VALID_ADDRESS_BOB).withLastVisit(VALID_LAST_VISIT_BOB)
            .withHealthConditions(VALID_HEALTH_CONDITION_DEMENTIA, VALID_HEALTH_CONDITION_DIABETES).build();

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
