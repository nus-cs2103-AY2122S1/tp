package nustracker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nustracker.model.event.EventName;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterEventCommand}.
 */
public class FilterEventCommandTest {

    @Test
    public void equals() {
        EventName firstName = new EventName("first");
        EventName secondName = new EventName("second");

        FilterCommand filterFirstCommand = new FilterEventCommand(firstName);
        FilterCommand filterSecondCommand = new FilterEventCommand(secondName);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterEventCommand(firstName);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different events -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }
}
