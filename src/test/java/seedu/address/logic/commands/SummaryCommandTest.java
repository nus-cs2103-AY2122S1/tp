package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import org.junit.jupiter.api.Test;

import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SummaryCommand}.
 */
public class SummaryCommandTest {
    private Model model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());

    @Test
    public void equals() {

        SummaryCommand summaryFirstCommand = new SummaryCommand();

        // same object -> returns true
        assertTrue(summaryFirstCommand.equals(summaryFirstCommand));

        // same values -> returns true
        SummaryCommand summaryFirstCommandCopy = new SummaryCommand();
        assertTrue(summaryFirstCommand.equals(summaryFirstCommandCopy));

        // different types -> returns false
        assertFalse(summaryFirstCommand.equals(1));

        // null -> returns false
        assertFalse(summaryFirstCommand.equals(null));
    }

    @Test
    public void execute() {
        SummaryCommand command = new SummaryCommand();
        assertEquals(command.execute(model),
                new CommandResult(SummaryCommand.SHOWING_SUMMARY_MESSAGE, false, false, false, true));
    }
}
