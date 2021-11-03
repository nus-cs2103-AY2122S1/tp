package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.summary.Summary;

public class CommandResultTest {
    private Summary summary = new Summary(getTypicalAddressBook());
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");
        CommandResult commandResultSummary = new CommandResult("feedback", summary);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));
        assertTrue(commandResult.equals(new CommandResult("feedback", false,
                false, false)));

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

        // different exit value with showCommandSummary-> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false,
                false, true)));

        // different showHelp value with showCommandSummary-> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false,
                true, false)));

        // different showCommandSummary value with showCommandSummary-> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true,
                false, false)));

        // same object -> returns true
        assertTrue(commandResultSummary.equals(new CommandResult("feedback", summary)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");
        CommandResult commandResultSummary = new CommandResult("feedback", summary);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, true).hashCode());

        // different exit value with showCommandSummary-> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, true).hashCode());

        // different showHelp value with showCommandSummary-> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, true, false).hashCode());

        // different showCommandSummary value with showCommandSummary-> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                true, false, false).hashCode());

        // same values -> returns same hashcode (summary)
        assertEquals(commandResultSummary.hashCode(), new CommandResult("feedback", summary).hashCode());

        // different summary value with Command Result-> returns different hashcode
        assertNotEquals(commandResultSummary.hashCode(), new CommandResult("feedback",
                true, false, false).hashCode());
    }
}
