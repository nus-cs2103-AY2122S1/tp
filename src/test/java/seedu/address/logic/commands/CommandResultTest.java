package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalClients;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult1 = new CommandResult("feedback");
        CommandResult commandResult2 = new CommandResult("feedback", CommandType.ADD, TypicalClients.BOB, true);
        CommandResult commandResult3 = new CommandResult(commandResult2);

        // same values -> returns true
        assertTrue(commandResult1.equals(new CommandResult("feedback")));
        assertTrue(commandResult1.equals(new CommandResult("feedback", null, null, false)));

        assertTrue(commandResult2.equals(commandResult3));

        // same object -> returns true
        assertTrue(commandResult1.equals(commandResult1));

        // null -> returns false
        assertFalse(commandResult1.equals(null));

        // different types -> returns false
        assertFalse(commandResult1.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult1.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult1.equals(new CommandResult("feedback", CommandType.HELP, null, false)));

        // different exit value -> returns false
        assertFalse(commandResult1.equals(new CommandResult("feedback", CommandType.EXIT, null, false)));

    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandType.HELP, null, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandType.EXIT, null, false).hashCode());
    }
}
