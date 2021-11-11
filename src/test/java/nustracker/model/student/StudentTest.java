package nustracker.model.student;

import static nustracker.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_YEAR_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nustracker.testutil.StudentBuilder;
import nustracker.testutil.TypicalStudents;


public class StudentTest {

    @Test
    public void hasDuplicateCredentials() {
        // same object -> returns true
        Assertions.assertTrue(TypicalStudents.ALICE.hasDuplicateCredentials(TypicalStudents.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalStudents.ALICE.hasDuplicateCredentials(null));

        // same studentId, phone number, email, all other attributes different -> returns true
        Student editedAmy = new StudentBuilder(TypicalStudents.AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withYear(VALID_YEAR_AMY)
                .withMajor(VALID_MAJOR_AMY)
                .withStudentId(VALID_STUDENTID_AMY).build();
        Assertions.assertTrue(TypicalStudents.AMY.hasDuplicateCredentials(editedAmy));

        // different studentId, all other attributes same -> returns false (since email and phone is the same)
        editedAmy = new StudentBuilder(TypicalStudents.ALICE).withStudentId(VALID_STUDENTID_BOB).build();
        Assertions.assertTrue(TypicalStudents.ALICE.hasDuplicateCredentials(editedAmy));

        // different phone, all other attributes same -> returns true (since studentId and email is the same)
        editedAmy = new StudentBuilder(TypicalStudents.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertTrue(TypicalStudents.ALICE.hasDuplicateCredentials(editedAmy));

        // different email, all other attributes same -> returns true (since studentId and phone is the same)
        editedAmy = new StudentBuilder(TypicalStudents.ALICE).withEmail(VALID_EMAIL_BOB).build();
        Assertions.assertTrue(TypicalStudents.ALICE.hasDuplicateCredentials(editedAmy));
    }

    @Test
    public void pseudoStudent() {
        Student student = new StudentBuilder().withStudentId("e1234567").build();
        Student pseudoStudent = Student.pseudoStudent(new StudentId("e1234567"));
        assertTrue(pseudoStudent.hasDuplicateCredentials(student));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(TypicalStudents.ALICE).build();
        Assertions.assertTrue(TypicalStudents.ALICE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(TypicalStudents.ALICE.equals(TypicalStudents.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalStudents.ALICE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalStudents.ALICE.equals(5));

        // different student -> returns false
        Assertions.assertFalse(TypicalStudents.ALICE.equals(TypicalStudents.BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(TypicalStudents.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different phone -> returns true
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withEmail(VALID_EMAIL_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different year -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withYear(VALID_YEAR_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different major -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withMajor(VALID_MAJOR_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different studentId -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withStudentId(VALID_STUDENTID_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));
    }
}
