package seedu.times.logic.commands.classcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.times.commons.core.index.Index;

/**
 * Note that the changes done by the SelectClassCommand is not reflected in the model, so the changes
 * cannot be tested.
 */
public class SelectClassCommandTest {

    @Test
    void testEquals() {
        SelectClassCommand selectClassCommand = new SelectClassCommand(Index.fromOneBased(1));

        //same object
        assertTrue(selectClassCommand.equals(selectClassCommand));

        //same values
        assertTrue(new SelectClassCommand(Index.fromOneBased(1)).equals(selectClassCommand));

        //different values
        assertFalse(selectClassCommand.equals(new SelectClassCommand(Index.fromOneBased(2))));

        //different types
        assertFalse(selectClassCommand.equals(1));

        //null
        assertFalse(selectClassCommand.equals(null));

    }

}
