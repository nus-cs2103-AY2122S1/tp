package seedu.unify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.unify.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

public class UndoneCommandTest {

    @Test
    public void equals() {
        UndoneCommand firstUndoneCommand = new UndoneCommand(INDEX_FIRST_TASK);
        UndoneCommand secondUndoneCommand = new UndoneCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(firstUndoneCommand.equals(firstUndoneCommand));

        // same values -> returns true
        UndoneCommand firstCopy = new UndoneCommand(INDEX_FIRST_TASK);
        assertTrue(firstUndoneCommand.equals(firstCopy));

        // different types -> returns false
        assertFalse(firstUndoneCommand.equals(INDEX_FIRST_TASK));

        // null -> returns false
        assertFalse(firstUndoneCommand.equals(null));

        // different commands and different indexes -> returns false
        assertFalse(firstUndoneCommand.equals(secondUndoneCommand));
    }
}
