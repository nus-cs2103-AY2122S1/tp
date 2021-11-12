package seedu.notor.testutil;

import static seedu.notor.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.notor.model.Notor;
import seedu.notor.model.common.Note;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withNote("She lives in the Wonderland.", "Thu., 11/11/2011")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withNote("He is Ben's son.", "Fri, 11/11/2010")
            .withTags("owesMoney", "friends")
            .withSuperGroups("Orbital", "CS2103")
            .withSubGroups("Orbital_Group1").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withNote(Note.EMPTY_NOTE).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withTags("friends")
            .withNote(Note.EMPTY_NOTE).build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withNote(Note.EMPTY_NOTE).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withNote(Note.EMPTY_NOTE).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withNote(Note.EMPTY_NOTE).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code Notor} with all the typical persons.
     */
    public static Notor getTypicalNotor() {
        Notor notor = new Notor();
        for (SuperGroup superGroup: TypicalGroups.getSuperGroups()) {
            notor.addSuperGroup(superGroup);
        }
        for (Person person : getTypicalPersons()) {
            notor.addPerson(person);
        }
        return notor;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
