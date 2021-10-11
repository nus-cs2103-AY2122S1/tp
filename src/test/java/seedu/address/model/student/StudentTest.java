package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARENT_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_PHONE_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StudentTest {
    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Student editedAlice = new PersonBuilder(ALICE).withStudentPhone(VALID_STUDENT_PHONE_BOB)
                .withParentName(VALID_PARENT_NAME_BOB).withParentPhone(VALID_PARENT_PHONE_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withStudentName(VALID_STUDENT_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Student editedBob = new PersonBuilder(BOB).withStudentName(VALID_STUDENT_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_STUDENT_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withStudentName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different student -> returns false
        assertFalse(ALICE.equals(BOB));

        // different student name -> returns false
        Student editedAlice = new PersonBuilder(ALICE).withStudentName(VALID_STUDENT_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different student phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withStudentPhone(VALID_STUDENT_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different parent name -> returns false
        editedAlice = new PersonBuilder(ALICE).withParentName(VALID_PARENT_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different parent phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withParentPhone(VALID_PARENT_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
