package seedu.address.logic.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandResult;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback", CommandResult.CommandType.GENERAL);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback", CommandResult.CommandType.GENERAL)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different",
                CommandResult.CommandType.GENERAL)));

        // CommandType.HELP -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", CommandResult.CommandType.HELP)));

        // CommandType.EXIT -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", CommandResult.CommandType.EXIT)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback", CommandResult.CommandType.GENERAL);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandResult.CommandType.GENERAL).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different",
                CommandResult.CommandType.GENERAL).hashCode());

        // CommandType.HELP -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(
                "feedback", CommandResult.CommandType.HELP).hashCode());

        // CommandType.EXIT -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(
                "feedback", CommandResult.CommandType.EXIT).hashCode());
    }
}
