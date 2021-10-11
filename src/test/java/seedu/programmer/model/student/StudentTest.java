package seedu.programmer.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_CLASSID_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static seedu.programmer.testutil.TypicalStudents.ALICE;
import static seedu.programmer.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.programmer.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        // same name, all other attributes different -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withStudentId(VALID_STUDENTID_BOB)
                                                       .withClassId(VALID_CLASSID_BOB)
                                                       .withGrade(VALID_GRADE_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // different name, all other attributes same -> returns true
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Student editedBob = new StudentBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameStudent(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StudentBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSameStudent(editedBob));
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
        assertNotEquals(5, ALICE);

        // different student -> returns false
        assertNotEquals(ALICE, BOB);

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withStudentId(VALID_STUDENTID_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withClassId(VALID_CLASSID_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different programmer -> returns false
        editedAlice = new StudentBuilder(ALICE).withGrade(VALID_GRADE_BOB).build();
        assertNotEquals(ALICE, editedAlice);
    }
}
