package seedu.tuitione.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_REMARK_HUSBAND;
import static seedu.tuitione.testutil.Assert.assertThrows;
import static seedu.tuitione.testutil.TypicalStudents.ALICE;
import static seedu.tuitione.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.tuitione.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getRemarks().remove(0));
    }

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        // same name, all other attributes different -> returns true
        Student editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withRemarks(VALID_REMARK_HUSBAND).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // name is all lower-cased, but will be created with capitalisation, all other attributes same -> returns true
        Student editedBob = new StudentBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameStudent(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StudentBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSameStudent(editedBob));
    }

    @Test
    public void createClone() {
        Student expectedStudent = new StudentBuilder().build();
        assertEquals(expectedStudent, expectedStudent);
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(ALICE).build();
        assertEquals(ALICE, aliceCopy);

        // same object -> returns true
        assertEquals(ALICE, ALICE);

        // null -> returns false
        assertNotEquals(null, ALICE);

        // different type -> returns false
        assertNotEquals(new Object(), ALICE);

        // different student -> returns false
        assertNotEquals(ALICE, BOB);

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different tuitione -> returns false
        editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different remarks -> returns false
        editedAlice = new StudentBuilder(ALICE).withRemarks(VALID_REMARK_HUSBAND).build();
        assertNotEquals(ALICE, editedAlice);
    }
}
