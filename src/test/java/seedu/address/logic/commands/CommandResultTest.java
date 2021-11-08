package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalContacts.getTypicalContacts;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.Contact;
import seedu.address.model.summary.Summary;

public class CommandResultTest {
    private Summary summary = new Summary(getTypicalAddressBook());
    private Contact contact = getTypicalContacts().get(0);
    private Contact otherContact = getTypicalContacts().get(1);

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");
        CommandResult commandResultSummary = new CommandResult("feedback", summary);
        CommandResult commandResultContact = new CommandResult("feedback", contact);

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

        // different Contact value with showCommandContact-> returns false
        assertFalse(commandResultContact.equals(new CommandResult("feedback", otherContact)));

        // same object -> returns true
        assertTrue(commandResultContact.equals(new CommandResult("feedback", contact)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");
        CommandResult commandResultSummary = new CommandResult("feedback", summary);
        CommandResult commandResultContact = new CommandResult("feedback", contact);

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

        // same values -> returns same hashcode (contact)
        assertEquals(commandResultContact.hashCode(), new CommandResult("feedback", contact).hashCode());

        // different contact value with Command Result-> returns different hashcode
        assertNotEquals(commandResultContact.hashCode(), new CommandResult("feedback",
                otherContact).hashCode());
    }
}
