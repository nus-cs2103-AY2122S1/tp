package socialite.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void getFeedbackToUser() {
        CommandResult commandResult = new CommandResult("feedback");
        String expectedFeedback = "feedback";
        assertEquals(expectedFeedback, commandResult.getFeedbackToUser());
    }

    @Test
    public void isShowHelp() {
        CommandResult helpCommandResult = new CommandResult("feedback", true, false, false);
        CommandResult notHelpCommandResult = new CommandResult("feedback", false, false, false);
        assertTrue(helpCommandResult.isHelpCommand());
        assertFalse(notHelpCommandResult.isHelpCommand());
    }

    @Test
    public void isExit() {
        CommandResult exitCommandResult = new CommandResult("feedback", false, true, false);
        CommandResult notExitCommandResult = new CommandResult("feedback", false, false, false);
        assertTrue(exitCommandResult.isExitCommand());
        assertFalse(notExitCommandResult.isExitCommand());
    }

    @Test
    public void isPictureCommand() {
        CommandResult pictureCommandResult = new CommandResult("feedback", false, false, true);
        CommandResult notPictureCommandResult = new CommandResult("feedback", false, false, false);
        assertTrue(pictureCommandResult.isPictureCommand());
        assertFalse(notPictureCommandResult.isPictureCommand());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", true, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, true, false).hashCode());
    }

    @Test
    public void result_has_parameter() {
        CommandResult cr = new CommandResult("help", true, false, false);

        assertTrue(cr.isHelpCommand());
        assertFalse(cr.isExitCommand());
        assertFalse(cr.isPictureCommand());

    }
}
