package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.logic.commands.RemoveEventCommand.MESSAGE_REMOVE_EVENT_SUCCESS;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemoveEventCommand}.
 */
class RemoveEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToRemove = model.getFilteredEventList()
                .get(INDEX_FIRST_EVENT.getZeroBased());
        RemoveEventCommand removeEventCommand = new RemoveEventCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(MESSAGE_REMOVE_EVENT_SUCCESS, eventToRemove);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.removeEvent(eventToRemove);

        assertCommandSuccess(removeEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        RemoveEventCommand removeEventCommand = new RemoveEventCommand(outOfBoundIndex);

        assertCommandFailure(removeEventCommand, model, MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventToRemove = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        RemoveEventCommand removeEventCommand = new RemoveEventCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(MESSAGE_REMOVE_EVENT_SUCCESS, eventToRemove);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.removeEvent(eventToRemove);
        showNoEvent(expectedModel);

        assertCommandSuccess(removeEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getEventList().size());

        RemoveEventCommand removeEventCommand = new RemoveEventCommand(outOfBoundIndex);

        assertCommandFailure(removeEventCommand, model, MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        RemoveEventCommand deleteFirstCommand = new RemoveEventCommand(INDEX_FIRST_EVENT);
        RemoveEventCommand deleteSecondCommand = new RemoveEventCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        RemoveEventCommand deleteFirstCommandCopy = new RemoveEventCommand(INDEX_FIRST_EVENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no events.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredEventList(p -> false);

        assertTrue(model.getFilteredEventList().isEmpty());
    }
}
