package tutoraid.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_NAME_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_PHONE_BOB;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.PersonBuilder;
import tutoraid.testutil.TypicalPersons;

public class StudentTest {
    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(TypicalPersons.ALICE.isSamePerson(TypicalPersons.ALICE));

        // null -> returns false
        assertFalse(TypicalPersons.ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Student editedAlice = new PersonBuilder(TypicalPersons.ALICE).withStudentPhone(VALID_STUDENT_PHONE_BOB)
                .withParentName(VALID_PARENT_NAME_BOB).withParentPhone(VALID_PARENT_PHONE_BOB).build();
        assertTrue(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withStudentName(VALID_STUDENT_NAME_BOB).build();
        assertFalse(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Student editedBob = new PersonBuilder(TypicalPersons.BOB).withStudentName(VALID_STUDENT_NAME_BOB.toLowerCase()).build();
        assertFalse(TypicalPersons.BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_STUDENT_NAME_BOB + " ";
        editedBob = new PersonBuilder(TypicalPersons.BOB).withStudentName(nameWithTrailingSpaces).build();
        assertFalse(TypicalPersons.BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new PersonBuilder(TypicalPersons.ALICE).build();
        assertTrue(TypicalPersons.ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TypicalPersons.ALICE.equals(TypicalPersons.ALICE));

        // null -> returns false
        assertFalse(TypicalPersons.ALICE.equals(null));

        // different type -> returns false
        assertFalse(TypicalPersons.ALICE.equals(5));

        // different student -> returns false
        assertFalse(TypicalPersons.ALICE.equals(TypicalPersons.BOB));

        // different student name -> returns false
        Student editedAlice = new PersonBuilder(TypicalPersons.ALICE).withStudentName(VALID_STUDENT_NAME_BOB).build();
        assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different student phone -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withStudentPhone(VALID_STUDENT_PHONE_BOB).build();
        assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different parent name -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withParentName(VALID_PARENT_NAME_BOB).build();
        assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different parent phone -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withParentPhone(VALID_PARENT_PHONE_BOB).build();
        assertFalse(TypicalPersons.ALICE.equals(editedAlice));
    }
}
