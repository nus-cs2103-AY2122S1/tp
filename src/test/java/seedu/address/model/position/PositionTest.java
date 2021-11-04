package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATASCIENTIST;
import static seedu.address.testutil.TypicalPositions.DATAENGINEER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PositionBuilder;


public class PositionTest {
    @Test
    public void isSamePosition() {
        // same object -> returns true
        assertTrue(DATAENGINEER.isSamePosition(DATAENGINEER));

        // null -> returns false
        assertFalse(DATAENGINEER.isSamePosition(null));

        // same name, other attributes different -> returns false
        Position editedDataEngineer = new PositionBuilder(DATAENGINEER).withTitle(VALID_TITLE_DATASCIENTIST).build();
        assertFalse(DATAENGINEER.isSamePosition(editedDataEngineer));

    }
}
