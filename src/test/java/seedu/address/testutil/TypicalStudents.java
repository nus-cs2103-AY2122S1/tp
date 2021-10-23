package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;
import static seedu.address.testutil.TypicalGroups.TYPICAL_GROUP_CS2101;
import static seedu.address.testutil.TypicalGroups.TYPICAL_GROUP_CS2103T;
import static seedu.address.testutil.TypicalGroups.TYPICAL_GROUP_CS2103T_NAME;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.CsBook;
import seedu.address.model.group.Group;
import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline").withEmail("alice@example.com")
            .withTelegramHandle("@alice_pauline")
            .withGroupName(TYPICAL_GROUP_CS2103T_NAME).build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier").withEmail("johnd@example.com")
            .withTelegramHandle("@benson_meier")
            .withGroupName(TYPICAL_GROUP_CS2103T_NAME).build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withEmail("heinz@example.com")
            .withTelegramHandle("@carl_kurz")
            .withGroupName(TYPICAL_GROUP_CS2103T_NAME).build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withEmail("cornelia@example.com")
            .withTelegramHandle("@daniel_meier")
            .withGroupName(TYPICAL_GROUP_CS2103T_NAME).build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withTelegramHandle("@elle_meyer")
            .withEmail("werner@example.com")
            .withGroupName(TYPICAL_GROUP_CS2103T_NAME).build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withTelegramHandle("@fiona_kunz")
            .withEmail("lydia@example.com")
            .withGroupName(TYPICAL_GROUP_CS2103T_NAME).build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withTelegramHandle("@george_best")
            .withEmail("anna@example.com")
            .withGroupName(TYPICAL_GROUP_CS2103T_NAME).build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withTelegramHandle("@hoon_meier")
            .withEmail("stefan@example.com").withGroupName(TYPICAL_GROUP_CS2103T_NAME).build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withTelegramHandle("@ida_mueller")
            .withEmail("hans@example.com").withGroupName(TYPICAL_GROUP_CS2103T_NAME).build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY)
            .withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY).withEmail(VALID_EMAIL_AMY)
            .withGroupName(VALID_GROUP_NAME_AMY)
            .build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB)
            .withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB).withEmail(VALID_EMAIL_BOB)
            .withGroupName(VALID_GROUP_NAME_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code CsBook} with all the typical students.
     */
    public static CsBook getTypicalCsBook() {
        CsBook ab = new CsBook();

        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }

        for (Group group : getTypicalGroups()) {
            ab.addGroup(group);
        }

        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(List.of(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(List.of(TYPICAL_GROUP_CS2103T, TYPICAL_GROUP_CS2101));
    }
}
