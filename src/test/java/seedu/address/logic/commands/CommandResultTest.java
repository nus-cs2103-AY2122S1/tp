package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {

    public static final String FEEDBACK = "feedback";
    public static final String DIFFERENT_FEEDBACK = "different feedback";

    @Test
    public void getFeedBackToUser_validArg_success() {
        CommandResult commandResult = new CommandResult(FEEDBACK);
        assertEquals(commandResult.getFeedbackToUser(), FEEDBACK);
    }

    @Test
    public void isShowHelp_showHelpFalse_failure() {
        CommandResult commandResult = new CommandResult(FEEDBACK);
        assertFalse(commandResult.isShowHelp());
    }

    @Test
    public void isExit_exitFalse_failure() {
        CommandResult commandResult = new CommandResult(FEEDBACK);
        assertFalse(commandResult.isExit());
    }

    @Test
    public void isTelegram_telegramFalse_failure() {
        CommandResult commandResult = new CommandResult(FEEDBACK);
        assertFalse(commandResult.isTelegram());
    }

    @Test
    public void isGithub_githubFalse_failure() {
        CommandResult commandResult = new CommandResult(FEEDBACK);
        assertFalse(commandResult.isGithub());
    }

    @Test
    public void isShowHelp_showHelpTrue_success() {
        CommandResult commandResult = new CommandResult(FEEDBACK, true, true, true, true);
        assertTrue(commandResult.isShowHelp());
    }

    @Test
    public void isExit_exitTrue_success() {
        CommandResult commandResult = new CommandResult(FEEDBACK, true, true, true, true);
        assertTrue(commandResult.isExit());
    }

    @Test
    public void isTelegram_telegramTrue_success() {
        CommandResult commandResult = new CommandResult(FEEDBACK, true, true, true, true);
        assertTrue(commandResult.isTelegram());
    }

    @Test
    public void isGithub_githubTrue_success() {
        CommandResult commandResult = new CommandResult(FEEDBACK, true, true, true, true);
        assertTrue(commandResult.isGithub());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult(FEEDBACK);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult(FEEDBACK)));
        assertTrue(commandResult.equals(new CommandResult(FEEDBACK, false, false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult(DIFFERENT_FEEDBACK)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult(FEEDBACK, true, false, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult(FEEDBACK, false, true, false, false)));

        // different isTelegram value -> returns false
        assertFalse(commandResult.equals(new CommandResult(FEEDBACK, false, false, true, false)));

        // different isGithub value -> returns false
        assertFalse(commandResult.equals(new CommandResult(FEEDBACK, false, false, false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult(FEEDBACK);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult(FEEDBACK).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(DIFFERENT_FEEDBACK).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(FEEDBACK, true, false, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(FEEDBACK, false, true, false, false).hashCode());

        // different isTelegram value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(FEEDBACK, false, false, true, false).hashCode());

        // different isGithub value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(FEEDBACK, false, false, false, true).hashCode());
    }
}
