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
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }

    @Test
    public void isShowHelp() {
        CommandResult commandResult;

        // Cases where isShowHelp is true
        commandResult = new CommandResult("feedback", true, true); // exit is true
        assertTrue(commandResult.isShowHelp());

        commandResult = new CommandResult("feedback", true, false); // exit is false
        assertTrue(commandResult.isShowHelp());

        // Cases where isShowHelp is false
        commandResult = new CommandResult("feedback", false, true); // exit is true
        assertFalse(commandResult.isShowHelp());

        commandResult = new CommandResult("feedback", false, false); // exit is false
        assertFalse(commandResult.isShowHelp());
    }

    @Test
    public void isExit() {
        CommandResult commandResult;

        // Cases where isExit is true
        commandResult = new CommandResult("feedback", true, true); // showHelp is true
        assertTrue(commandResult.isExit());

        commandResult = new CommandResult("feedback", false, true); // showHelp is false
        assertTrue(commandResult.isExit());

        // Cases where isExit is false
        commandResult = new CommandResult("feedback", true, false); // showHelp is true
        assertFalse(commandResult.isExit());

        commandResult = new CommandResult("feedback", false, false); // showHelp is false
        assertFalse(commandResult.isExit());
    }
}
