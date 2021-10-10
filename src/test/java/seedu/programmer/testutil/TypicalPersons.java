package seedu.programmer.testutil;

import static seedu.programmer.logic.commands.CommandTestUtil.VALID_CLASSID_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_CLASSID_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_GRADE_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withStudentId("A0212425H").withClassId("B01")
            .withGrade("A").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withStudentId("A0212325H").withClassId("B02")
            .withGrade("B+").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withStudentId("A0112425H").withClassId("B03")
            .withGrade("A-").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withStudentId("A0512425H").withClassId("B02")
            .withGrade("A+").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withStudentId("A0612425H").withClassId("B01")
            .withGrade("D").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withStudentId("A0912425H").withClassId("B11")
            .withGrade("C").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withStudentId("A0852425H").withClassId("B11")
            .withGrade("B").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withStudentId("A0782425H").withClassId("B01")
            .withGrade("B").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withStudentId("A0852425H").withClassId("B01")
            .withGrade("A").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withStudentId(VALID_STUDENTID_AMY)
            .withClassId(VALID_CLASSID_AMY).withGrade(VALID_GRADE_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withStudentId(VALID_STUDENTID_BOB)
            .withClassId(VALID_CLASSID_BOB).withGrade(VALID_GRADE_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static ProgrammerError getTypicalAddressBook() {
        ProgrammerError ab = new ProgrammerError();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
