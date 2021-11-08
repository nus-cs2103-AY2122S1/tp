package seedu.edrecord.testutil;

import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_INFO_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_INFO_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withInfo("Has surname Pauline").withEmail("alice@example.com")
            .withPhone("94351253").withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withInfo("Struggles with visualization")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withInfo("Has great hair").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withInfo("Very fashionable").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822245")
            .withEmail("werner@example.com").withInfo("Quick to pick up on concepts").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94824217")
            .withEmail("lydia@example.com").withInfo("Helpful to other students").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("94824142")
            .withEmail("anna@example.com").withInfo("Has potential to do better").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("84821424")
            .withEmail("stefan@example.com").withInfo("May be disruptive to other students").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("84821331")
            .withEmail("hans@example.com").withInfo("Needs examples to understand concepts well")
            .withModuleAndGroup("CS3230", "T02").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withInfo(VALID_INFO_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withInfo(VALID_INFO_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code EdRecord} with all the typical persons.
     */
    public static EdRecord getTypicalEdRecord() {
        EdRecord er = new EdRecord();
        er.setPersons(getTypicalPersons());
        return er;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
