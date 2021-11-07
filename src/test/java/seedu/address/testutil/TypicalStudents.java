package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_CHARLIE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.module.student.Student;

public class TypicalStudents {

    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withStudentId(VALID_STUDENT_ID_AMY)
            .withTeleHandle(VALID_TELE_HANDLE_AMY).withEmail(VALID_EMAIL_AMY).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withStudentId(VALID_STUDENT_ID_BOB)
            .withTeleHandle(VALID_TELE_HANDLE_BOB).withEmail(VALID_EMAIL_BOB).build();
    public static final Student CHARLIE = new StudentBuilder().withName(VALID_NAME_CHARLIE)
            .withStudentId(VALID_STUDENT_ID_CHARLIE)
            .withTeleHandle(VALID_TELE_HANDLE_CHARLIE)
            .withEmail(VALID_EMAIL_CHARLIE).build();

    /**
     * Returns a list of students.
     *
     * @return A list of students
     */
    public static List<Student> getTypicalStudents() {
        Student newAmy = new StudentBuilder(AMY).build();
        Student newBob = new StudentBuilder(BOB).build();
        return new ArrayList<>(Arrays.asList(newAmy, newBob));
    }
}
