package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.facility.LocationContainsKeywordsPredicate;

/**
 * Contains unit tests for {@code FindFacilityCommand}.
 */
public class FindFacilityCommandTest {
    @Test
    public void equals() {
        LocationContainsKeywordsPredicate firstPred =
                new LocationContainsKeywordsPredicate(Collections.singletonList("first"));
        LocationContainsKeywordsPredicate secondPred =
                new LocationContainsKeywordsPredicate(Collections.singletonList("second"));

        FindFacilityCommand findFacilFirstCommand = new FindFacilityCommand(firstPred);
        FindFacilityCommand findFacilSecondCommand = new FindFacilityCommand(secondPred);

        // same object -> returns true
        assertTrue(findFacilFirstCommand.equals(findFacilFirstCommand));

        // same values -> returns true
        FindFacilityCommand findFacilFirstCommandCopy = new FindFacilityCommand(firstPred);
        assertTrue(findFacilFirstCommand.equals(findFacilFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFacilFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFacilFirstCommand.equals(null));

        // different facility -> returns false
        assertFalse(findFacilFirstCommand.equals(findFacilSecondCommand));
    }
}
