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
        CommandResult warnedResult = new CommandResult("feedback", CommandWarning.PAST_NEXT_VISIT_WARNING);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, true)));

        // check for warnings
        assertFalse(commandResult.equals(warnedResult));
        assertFalse(warnedResult.equals(new CommandResult("feedback", CommandWarning.FUTURE_LAST_VISIT_WARNING)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, false, false).hashCode());

        // different showDownload value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, false, true).hashCode());
    }

    @Test
    public void isShowSummary() {
        CommandResult positiveCommandResult = new CommandResult("feedback", true, false, false, false);
        CommandResult negativeCommandResult = new CommandResult("feedback", false, false, false, false);

        assertTrue(positiveCommandResult.isShowSummary());
        assertFalse(negativeCommandResult.isShowSummary());
    }

    @Test
    public void isShowHelp() {
        CommandResult positiveCommandResult = new CommandResult("feedback", false, true, false, false);
        CommandResult negativeCommandResult = new CommandResult("feedback", false, false, false, false);

        assertTrue(positiveCommandResult.isShowHelp());
        assertFalse(negativeCommandResult.isShowHelp());
    }

    @Test
    public void isShowDownload() {
        CommandResult positiveCommandResult = new CommandResult("feedback", false, false, true, false);
        CommandResult negativeCommandResult = new CommandResult("feedback", false, false, false, false);

        assertTrue(positiveCommandResult.isShowDownload());
        assertFalse(negativeCommandResult.isShowDownload());
    }

    @Test
    public void isExit() {
        CommandResult positiveCommandResult = new CommandResult("feedback", false, false, false, true);
        CommandResult negativeCommandResult = new CommandResult("feedback", false, false, false, false);

        assertTrue(positiveCommandResult.isExit());
        assertFalse(negativeCommandResult.isExit());
    }
}
