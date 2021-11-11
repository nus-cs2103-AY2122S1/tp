package nustracker.logic.commands;

import static nustracker.logic.commands.CommandTestUtil.assertCommandFailureShownStudentList;
import static nustracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static nustracker.testutil.TypicalAddressBook.getTypicalAddressBook;
import static nustracker.testutil.TypicalEvents.EVENTNAME_INVALID;
import static nustracker.testutil.TypicalEvents.EVENTNAME_ONE;
import static nustracker.testutil.TypicalEvents.EVENTNAME_TWO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nustracker.commons.core.Messages;
import nustracker.model.Model;
import nustracker.model.ModelManager;
import nustracker.model.UserPrefs;
import nustracker.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteEventCommand}.
 */
class DeleteEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validEventUnfilteredList_success() {
        Event eventToDelete = model.getEvent(EVENTNAME_ONE);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(EVENTNAME_ONE);

        String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                eventToDelete.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);

        assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEventUnfilteredList_throwsCommandException() {
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(EVENTNAME_INVALID);

        assertCommandFailureShownStudentList(deleteEventCommand, model,
                String.format(Messages.MESSAGE_INVALID_EVENT_NAME, EVENTNAME_INVALID));
    }

    @Test
    public void execute_validEventFilteredList_success() {
        // To implement when event filtering feature is implemented
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        // To implement when event filtering feature is implemented
    }

    @Test
    public void equals() {
        DeleteEventCommand deleteFirstCommand = new DeleteEventCommand(EVENTNAME_ONE);
        DeleteEventCommand deleteSecondCommand = new DeleteEventCommand(EVENTNAME_TWO);

        // same object -> returns true
        Assertions.assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteEventCommand deleteFirstCommandCopy = new DeleteEventCommand(EVENTNAME_ONE);
        Assertions.assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        Assertions.assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        Assertions.assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        Assertions.assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredEventList(p -> false);

        Assertions.assertTrue(model.getFilteredEventList().isEmpty());
    }

}
