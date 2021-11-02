package safeforhall.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandFailure;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;
import static safeforhall.logic.commands.CommandTestUtil.showEventAtIndex;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.commons.core.index.Index;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.model.event.Event;
import safeforhall.testutil.TypicalEvents;
import safeforhall.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteEventCommand}.
 */
public class DeleteEventCommandTest {

    private Model model = new ModelManager(TypicalEvents.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        int firstEventIndex = TypicalIndexes.INDEX_FIRST_EVENT.getZeroBased();
        int secondEventIndex = TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased();
        Event firstEventToDelete = model.getFilteredEventList().get(firstEventIndex);
        Event secondEventToDelete = model.getFilteredEventList().get(secondEventIndex);
        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(TypicalIndexes.INDEX_FIRST_EVENT);
        indexArray.add(TypicalIndexes.INDEX_SECOND_EVENT);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(indexArray);

        String deletedEvent = "1.\t" + firstEventToDelete.getEventName() + "\n"
                + "2.\t" + secondEventToDelete.getEventName() + "\n";
        String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, deletedEvent);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEvent(firstEventToDelete);
        expectedModel.deleteEvent(secondEventToDelete);

        assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(outOfBoundIndex);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(indexArray);

        assertCommandFailure(deleteEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showEventAtIndex(model, TypicalIndexes.INDEX_FIRST_EVENT);

        Event eventToDelete = model.getFilteredEventList().get(TypicalIndexes.INDEX_FIRST_EVENT.getZeroBased());
        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(TypicalIndexes.INDEX_FIRST_EVENT);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(indexArray);

        String deletedEvent = "1.\t" + eventToDelete.getEventName() + "\n";
        String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, deletedEvent);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);
        showNoEvent(expectedModel);

        assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEventAtIndex(model, TypicalIndexes.INDEX_FIRST_EVENT);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getEventList().size());

        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(outOfBoundIndex);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(indexArray);

        assertCommandFailure(deleteEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ArrayList<Index> firstIndexArray = new ArrayList<>();
        firstIndexArray.add(TypicalIndexes.INDEX_FIRST_EVENT);
        DeleteEventCommand deleteFirstCommand = new DeleteEventCommand(firstIndexArray);
        ArrayList<Index> secondIndexArray = new ArrayList<>();
        secondIndexArray.add(TypicalIndexes.INDEX_SECOND_EVENT);
        DeleteEventCommand deleteSecondCommand = new DeleteEventCommand(secondIndexArray);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteEventCommand deleteFirstCommandCopy = new DeleteEventCommand(firstIndexArray);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredEventList(p -> false);

        assertTrue(model.getFilteredEventList().isEmpty());
    }
}
