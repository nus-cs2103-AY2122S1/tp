package seedu.unify.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.unify.commons.core.index.Index;

public class DoneCommandTest {

    @Test
    public void equals() {
        Index firstIndex = Index.fromOneBased(1);
        Index secondIndex = Index.fromOneBased(2);
        DoneCommand firstDoneCommand = new DoneCommand(firstIndex);
        DoneCommand secondDoneCommand = new DoneCommand(secondIndex);

        // same object -> returns true
        assertTrue(firstDoneCommand.equals(firstDoneCommand));

        // same value -> returns true
        DoneCommand firstCopy = new DoneCommand(firstIndex);
        assertTrue(firstDoneCommand.equals(firstCopy));

        // different type -> returns false
        assertFalse(firstDoneCommand.equals(firstIndex));

        // null -> returns false
        assertFalse(firstDoneCommand.equals(null));

        // different commands with different index -> returns false
        assertFalse(firstDoneCommand.equals(secondDoneCommand));
    }
}
