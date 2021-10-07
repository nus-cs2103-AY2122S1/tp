package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBook;
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
 * {@code DoneEventCommand}.
 */
class DoneEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Event eventToMarkAsDone = model.getFilteredEventList()
                .get(INDEX_FIRST_EVENT.getZeroBased());
        DoneEventCommand doneCommand = new DoneEventCommand(INDEX_FIRST_EVENT);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.markEventAsDone(eventToMarkAsDone);

        String expectedMessage = String.format(DoneEventCommand.MESSAGE_DONE_EVENT_SUCCESS,
                eventToMarkAsDone.markAsDone());

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        DoneEventCommand doneCommand = new DoneEventCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DoneEventCommand doneFirstCommand = new DoneEventCommand(INDEX_FIRST_EVENT);
        DoneEventCommand doneSecondCommand = new DoneEventCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneEventCommand doneFirstCommandCopy = new DoneEventCommand(INDEX_FIRST_EVENT);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }
}
