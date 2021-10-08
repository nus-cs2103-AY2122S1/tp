package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code DeleteFacilityCommand}.
 */
public class DeleteFacilityCommandTest {
    @Test
    public void equals() {
        DeleteFacilityCommand deleteFacilFirstCommand = new DeleteFacilityCommand(INDEX_FIRST_PERSON);
        DeleteFacilityCommand deleteFacilSecondCommand = new DeleteFacilityCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFacilFirstCommand.equals(deleteFacilFirstCommand));

        // same values -> returns true
        DeleteFacilityCommand deleteFacilFirstCommandCopy = new DeleteFacilityCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFacilFirstCommand.equals(deleteFacilFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFacilFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFacilFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFacilFirstCommand.equals(deleteFacilSecondCommand));
    }
}
