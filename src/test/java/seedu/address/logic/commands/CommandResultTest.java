package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandResult.DisplayType.EXIT;
import static seedu.address.logic.commands.CommandResult.DisplayType.HELP;
import static seedu.address.logic.commands.CommandResult.DisplayType.REMINDER;
import static seedu.address.logic.commands.CommandResult.DisplayType.STUDENTS;
import static seedu.address.logic.commands.CommandResult.DisplayType.TAGS;
import static seedu.address.logic.commands.CommandResult.DisplayType.WEEK;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));

        assertTrue(commandResult.equals(
                new CommandResult("feedback", STUDENTS)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different displayType enum value -> returns false
        assertFalse(commandResult.equals(
                new CommandResult("feedback", HELP)));

        // different displayType enum value -> returns false
        assertFalse(commandResult.equals(
                new CommandResult("feedback", REMINDER)));

        // different displayType enum value -> returns false
        assertFalse(commandResult.equals(
                new CommandResult("feedback", TAGS)));

        // different displayType enum value -> returns false
        assertFalse(commandResult.equals(
                new CommandResult("feedback", WEEK)));

        // different displayType enum value -> returns false
        assertFalse(commandResult.equals(
                new CommandResult("feedback", EXIT)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different displayType enum value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", HELP).hashCode());

        // different displayType enum value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", TAGS).hashCode());

        // different displayType enum value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", REMINDER).hashCode());

        // different displayType enum value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", WEEK).hashCode());

        // different displayType enum value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", EXIT).hashCode());
    }
}
