package seedu.address.testutil;


import static seedu.address.logic.commands.CommandTestUtil.VALID_AVAILABILITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AVAILABILITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AVAILABILITY_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EXCO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_Y2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.Person;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withPhone("94351253").build();
    public static final Person ALICE_TAN = new PersonBuilder().withName("Alice Tan").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withPhone("98765432").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withPhone("9482442").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withPhone("8482424").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withPhone("8482131").withTodayAttendance(false).withTotalAttendance(0).build();
    public static final Person ALICE_DIFFERENT_PHONE = new PersonBuilder().withName("Alice Pauline")
            .withPhone("94839542").build();
    public static final Person CHOO = new PersonBuilder().withName("Choo")
            .withPhone("90909090").build();
    public static final Person DOO = new PersonBuilder().withName("Doo")
            .withPhone("99889898").withTags("y3").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withAvailability(VALID_AVAILABILITY_AMY).withTags(VALID_TAG_Y2).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withAvailability(VALID_AVAILABILITY_BOB)
            .withTags(VALID_TAG_EXCO, VALID_TAG_Y2).build();
    public static final Person CHARLIE = new PersonBuilder().withName(VALID_NAME_CHARLIE)
            .withPhone(VALID_PHONE_CHARLIE).withAvailability(VALID_AVAILABILITY_CHARLIE).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Person> getTypicalPersonsToFind() {
        return new ArrayList<>(Arrays.asList(ALICE, ALICE_TAN, BENSON));
    }

    public static List<Person> getTypicalPersonsUnsortedName() {
        return new ArrayList<>(Arrays.asList(GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE));
    }

    public static List<Person> getTypicalPersonsUnsortedTag() {
        return new ArrayList<>(Arrays.asList(AMY, BOB, CHOO, DOO));
    }
}
