package seedu.unify.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.unify.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;


public class DoneCommandTest {

    @Test
    public void execute_doneCommand_success() {

    }

    @Test
    public void equals() {
        DoneCommand firstDoneCommand = new DoneCommand(INDEX_FIRST_TASK);
        DoneCommand secondDoneCommand = new DoneCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(firstDoneCommand.equals(firstDoneCommand));

        // same values -> returns true
        DoneCommand firstCopy = new DoneCommand(INDEX_FIRST_TASK);
        assertTrue(firstDoneCommand.equals(firstCopy));

        // different types -> returns false
        assertFalse(firstDoneCommand.equals(INDEX_FIRST_TASK));

        // null -> returns false
        assertFalse(firstDoneCommand.equals(null));

        // different tasks -> returns false
        assertFalse(firstDoneCommand.equals(secondDoneCommand));
    }
}
