package seedu.programmer.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void test_equals_returnsCorrectly() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertEquals(commandResult, new CommandResult("feedback"));

        // same object -> returns true
        assertEquals(commandResult, commandResult);

        // null -> returns false
        assertNotEquals(null, commandResult);

        // different CommandResult type -> returns false
        assertNotEquals(commandResult, new ExitCommandResult("exit"));

        // different feedbackToUser value -> returns false
        assertNotEquals(commandResult, new CommandResult("different"));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different CommandResult type -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new DownloadCommandResult("different").hashCode());
    }
}
