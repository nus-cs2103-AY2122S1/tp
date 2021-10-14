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
<<<<<<< HEAD
        assertTrue(commandResult.equals(new CommandResult("feedback", true, false, false, false)));
=======
        assertTrue(commandResult.equals(
                new CommandResult("feedback", false, false, false)));
>>>>>>> master

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

<<<<<<< HEAD
        // different isShowPersonList value -> return false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, false)));

        // different isShowHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false, false)));

        // different isShowTagList value -> return false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, true, false)));

        // different isExit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, true)));
=======
        // different showHelp value -> returns false
        assertFalse(commandResult.equals(
                new CommandResult("feedback", true, false, false)));

        // different showSchedule value -> returns false
        assertFalse(commandResult.equals(
                new CommandResult("feedback", false, true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(
                new CommandResult("feedback", false, false, true)));
>>>>>>> master
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

<<<<<<< HEAD
        // different isShowPersonList value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, false, false).hashCode());

        // different isShowHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, true, false, false).hashCode());

        // different isShowTagList value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, true, false).hashCode());

        // different isExit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, false, true).hashCode());
=======
        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", true, false, false).hashCode());

        // different showSchedule value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, true).hashCode());
>>>>>>> master
    }
}
