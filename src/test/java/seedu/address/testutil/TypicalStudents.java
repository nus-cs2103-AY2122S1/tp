package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.module.student.Student;

public class TypicalStudents {

    //some samples, can add more/modify for testing
    public static final Student ALICE = new StudentBuilder()
            .withStudentId("A1234560A")
            .withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withTeleHandle("@Alice")
            .build();
    public static final Student BENSON = new StudentBuilder()
            .withStudentId("A1234561A")
            .withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withTeleHandle("@Benson")
            .build();
    public static final Student CARL = new StudentBuilder()
            .withStudentId("A1234562A")
            .withName("Carl Kurz")
            .withEmail("heinz@example.com")
            .withTeleHandle("@Carl")
            .build();
    public static final Student DANIEL = new StudentBuilder()
            .withStudentId("A1234563A")
            .withName("Daniel Meier")
            .withEmail("cornelia@example.com")
            .withTeleHandle("@Daniel")
            .build();
    public static final Student ELLE = new StudentBuilder()
            .withStudentId("A1234564A")
            .withName("Elle Meyer")
            .withEmail("werner@example.com")
            .withTeleHandle("@Elle")
            .build();
    public static final Student FIONA = new StudentBuilder()
            .withStudentId("A1234565A")
            .withName("Fiona Kunz")
            .withEmail("lydia@example.com")
            .withTeleHandle("@Fiona")
            .build();
    public static final Student GEORGE = new StudentBuilder()
            .withStudentId("A1234566A")
            .withName("George Best")
            .withEmail("anna@example.com")
            .withTeleHandle("@George")
            .build();

    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withStudentId(VALID_STUDENT_ID_AMY)
            .withTeleHandle(VALID_TELE_HANDLE_AMY).withEmail(VALID_EMAIL_AMY).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withStudentId(VALID_STUDENT_ID_BOB)
            .withTeleHandle(VALID_TELE_HANDLE_BOB).withEmail(VALID_EMAIL_BOB).build();

    /**
     * Returns a list of students.
     * @return A list of students
     */
    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(AMY, BOB));
    }
}
