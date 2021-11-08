package tutoraid.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_NAME_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_PHONE_BOB;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.StudentBuilder;
import tutoraid.testutil.TypicalStudents;

public class StudentTest {
    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(TypicalStudents.ALICE.isSameStudent(TypicalStudents.ALICE));

        // null -> returns false
        assertFalse(TypicalStudents.ALICE.isSameStudent(null));

        // same name, all other attributes different -> returns true
        Student editedAlice = new StudentBuilder(TypicalStudents.ALICE).withStudentPhone(VALID_STUDENT_PHONE_BOB)
                .withParentName(VALID_PARENT_NAME_BOB).withParentPhone(VALID_PARENT_PHONE_BOB).build();
        assertTrue(TypicalStudents.ALICE.isSameStudent(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withStudentName(VALID_STUDENT_NAME_BOB).build();
        assertFalse(TypicalStudents.ALICE.isSameStudent(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Student editedBob = new StudentBuilder(TypicalStudents.BOB).withStudentName(
                VALID_STUDENT_NAME_BOB.toLowerCase()).build();
        assertTrue(TypicalStudents.BOB.isSameStudent(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_STUDENT_NAME_BOB + " ";
        editedBob = new StudentBuilder(TypicalStudents.BOB).withStudentName(nameWithTrailingSpaces).build();
        assertFalse(TypicalStudents.BOB.isSameStudent(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(TypicalStudents.ALICE).build();
        assertTrue(TypicalStudents.ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TypicalStudents.ALICE.equals(TypicalStudents.ALICE));

        // null -> returns false
        assertFalse(TypicalStudents.ALICE.equals(null));

        // different type -> returns false
        assertFalse(TypicalStudents.ALICE.equals(5));

        // different student -> returns false
        assertFalse(TypicalStudents.ALICE.equals(TypicalStudents.BOB));

        // different student name -> returns false
        Student editedAlice = new StudentBuilder(TypicalStudents.ALICE).withStudentName(VALID_STUDENT_NAME_BOB).build();
        assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different student phone -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withStudentPhone(VALID_STUDENT_PHONE_BOB).build();
        assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different parent name -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withParentName(VALID_PARENT_NAME_BOB).build();
        assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different parent phone -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withParentPhone(VALID_PARENT_PHONE_BOB).build();
        assertFalse(TypicalStudents.ALICE.equals(editedAlice));
    }

    @Test
    public void toNameString() {
        assertEquals(TypicalStudents.ALICE.getStudentName().toString(), TypicalStudents.ALICE.toNameString());
        assertEquals(TypicalStudents.BOB.getStudentName().toString(), TypicalStudents.BOB.toNameString());
    }
}
