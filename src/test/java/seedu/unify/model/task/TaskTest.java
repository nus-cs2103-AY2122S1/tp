package seedu.unify.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TIME_BOB;
import static seedu.unify.testutil.TypicalTasks.ALICE;
import static seedu.unify.testutil.TypicalTasks.BOB;

import org.junit.jupiter.api.Test;

import seedu.unify.testutil.TaskBuilder;

public class TaskTest {


    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(ALICE.isSameTask(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedAlice = new TaskBuilder(ALICE).withTime(VALID_TIME_BOB)
                .withDate(VALID_DATE_BOB).withTag(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameTask(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new TaskBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameTask(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Task editedBob = new TaskBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameTask(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new TaskBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameTask(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different task -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Task editedAlice = new TaskBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new TaskBuilder(ALICE).withTime(VALID_TIME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different date -> returns false
        editedAlice = new TaskBuilder(ALICE).withDate(VALID_DATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new TaskBuilder(ALICE).withTag(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
