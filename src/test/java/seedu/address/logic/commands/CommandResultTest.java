package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));

        assertTrue(commandResult.equals(new CommandResult("feedback",
                true, false, false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different isShowPersonList value -> return false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, false, false, false, false)));

        // different isShowHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, true, false, false, false)));

        // different isShowTagList value -> return false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, false, true, false, false)));

        // different isShowSchedule value -> return false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, false, false, true, false)));

        // different isExit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, false, false, false, true)));

    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different isShowPersonList value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, false, false, false).hashCode());

        // different isShowHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, true, false, false, false).hashCode());

        // different isShowTagList value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, true, false, false).hashCode());

        // different isSchedule value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, false, true, false).hashCode());

        // different isExit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, false, false, true).hashCode());
    }
}
