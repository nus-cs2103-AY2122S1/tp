package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.naming.CompositeName;

import org.junit.jupiter.api.Test;

public class CommandResultTest {

    @Test
    public void getFeedbackToUser_feedbackPresent_success() {
        CommandResult commandResult = new CommandResult("feedback", false, false, false, false);
        assertEquals(commandResult.getFeedbackToUser(), "feedback");
    }

    @Test
    public void isShowHelp_success() {
        CommandResult commandResult = new CommandResult("feedback", true, false, false, false);
        assertTrue(commandResult.isShowHelp());
    }

    @Test
    public void isExit_success() {
        CommandResult commandResult = new CommandResult("feedback", false, true, false, false);
        assertTrue(commandResult.isExit());
    }

    @Test
    public void isTelegram_success() {
        CommandResult commandResult = new CommandResult("feedback", false, false, true, false);
        assertTrue(commandResult.isTelegram());
    }

    @Test
    public void isGithub_success() {
        CommandResult commandResult = new CommandResult("feedback", false, false, false, true);
        assertTrue(commandResult.isGithub());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

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
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false, false)));

        // different isTelegram value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, true, false)));

        // different isGithub value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, false, false).hashCode());

        // different isTelegram value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, true, false).hashCode());

        // different isGithub value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, false, true).hashCode());
    }
}
