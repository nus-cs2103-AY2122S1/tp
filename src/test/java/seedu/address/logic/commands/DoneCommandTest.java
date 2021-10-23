package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DoneCommand.MESSAGE_EVENT_ALREADY_DONE;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBookCopy;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DoneCommand}.
 */
class DoneCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookCopy(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_EVENT);
        ModelManager expectedModel = new ModelManager(getTypicalAddressBookCopy(), new UserPrefs());
        Event eventMarkedAsDone = expectedModel.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        eventMarkedAsDone.markAsDone();

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_EVENT_SUCCESS,
                eventMarkedAsDone);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_eventAlreadyDone_throwsCommandException() {
        model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased()).markAsDone();
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_EVENT);
        assertCommandFailure(doneCommand, model, MESSAGE_EVENT_ALREADY_DONE);
    }

    @Test
    public void equals() {
        DoneCommand doneFirstCommand = new DoneCommand(INDEX_FIRST_EVENT);
        DoneCommand doneSecondCommand = new DoneCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneCommand doneFirstCommandCopy = new DoneCommand(INDEX_FIRST_EVENT);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }
}
