package seedu.academydirectory.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        // has optional string message -> return true
        assertTrue(commandResult.equals(new CommandResult("feedback", Optional.empty())));

        // has optional string message and all false -> return true
        assertTrue(commandResult.equals(new CommandResult("feedback",
                false, false, Optional.empty())));

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

        // different type of optional
        CommandResult commandResult1 = new CommandResult("this is a feedback", Optional.of("feedback"));
        CommandResult commandResult2 = new CommandResult("this is a feedback", Optional.of("different"));

        assertEquals(commandResult1, commandResult2);
        assertEquals(commandResult1, new CommandResult("this is a feedback"));

        // same optional object, different feedback
        assertNotEquals(commandResult1, new CommandResult("another feedback", Optional.of("feedback")));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // same value but different optional -> same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback", Optional.of("feedback"))
                .hashCode());

        // different value but same optional -> different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("another", Optional.empty()).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }
}
