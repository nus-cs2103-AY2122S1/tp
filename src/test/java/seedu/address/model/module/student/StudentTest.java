package seedu.address.model.module.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_BOB;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentTest {
    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(AMY.isSameStudent(AMY));

        // null -> returns false
        assertFalse(AMY.isSameStudent(null));

        // different student objects -> returns false
        assertFalse(AMY.isSameStudent(BOB));

        // same student ID, all other attributes different -> returns true
        Student student = new StudentBuilder().withStudentId(VALID_STUDENT_ID_BOB).withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY).withTeleHandle(VALID_TELE_HANDLE_AMY).build();
        assertTrue(BOB.isSameStudent(student));

        // same attributes except studentId -> returns false
        Student editedAmy = new StudentBuilder().withName(VALID_NAME_AMY).withStudentId(VALID_STUDENT_ID_BOB)
                .withTeleHandle(VALID_TELE_HANDLE_AMY).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(AMY.isSameStudent(editedAmy));
    }

    @Test
    public void equals() {
        Student copyAmy = new StudentBuilder().withName(VALID_NAME_AMY).withStudentId(VALID_STUDENT_ID_AMY)
                .withTeleHandle(VALID_TELE_HANDLE_AMY).withEmail(VALID_EMAIL_AMY).build();
        // same attributes -> returns true
        assertTrue(AMY.equals(copyAmy));
        // same object -> returns true
        assertTrue(AMY.equals(AMY));

        // null -> returns false
        assertFalse(AMY.equals(null));

        // different type -> returns false
        assertFalse(AMY.equals(5));

        // different student -> returns false
        assertFalse(AMY.equals(BOB));

        // different studentId -> returns false
        Student editedAmy = new StudentBuilder().withStudentId(VALID_STUDENT_ID_BOB).withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY).withTeleHandle(VALID_TELE_HANDLE_AMY).build();
        assertFalse(AMY.equals(editedAmy));

        // different name -> returns false
        editedAmy = new StudentBuilder().withStudentId(VALID_STUDENT_ID_AMY).withName(VALID_NAME_BOB)
                .withEmail(VALID_EMAIL_AMY).withTeleHandle(VALID_TELE_HANDLE_AMY).build();
        assertFalse(AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new StudentBuilder().withStudentId(VALID_STUDENT_ID_AMY).withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_BOB).withTeleHandle(VALID_TELE_HANDLE_AMY).build();
        assertFalse(AMY.equals(editedAmy));

        // different tele handle -> returns false
        editedAmy = new StudentBuilder().withStudentId(VALID_STUDENT_ID_AMY).withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY).withTeleHandle(VALID_TELE_HANDLE_BOB).build();
        assertFalse(AMY.equals(editedAmy));

        // same studentId, other attributes different -> returns false
        editedAmy = new StudentBuilder().withStudentId(VALID_STUDENT_ID_AMY).build();
        assertFalse(AMY.equals(editedAmy));
    }
}
