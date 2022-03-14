package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_E_JOGGING;
import static seedu.address.logic.commands.CommandTestUtil.DESC_E_SLEEP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_JOGGING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditEventCommand.
 */
public class EditEventCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredEventList_success() {
        Event editedEvent = new EventBuilder().build();
        EditEventDescriptor editEventDescriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, editEventDescriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEvent = Index.fromOneBased(model.getFilteredEventList().size());
        Event lastEvent = model.getFilteredEventList().get(indexLastEvent.getZeroBased());

        EventBuilder eventInList = new EventBuilder(lastEvent);
        Event editedEvent = eventInList.withEventName(VALID_EVENT_NAME_JOGGING).build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withEventName(VALID_EVENT_NAME_JOGGING).build();
        EditEventCommand editEventCommand = new EditEventCommand(indexLastEvent, descriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEvent(lastEvent, editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, new EditEventDescriptor());
        Event editedEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventInFilteredList = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Event editedEvent = new EventBuilder(eventInFilteredList).withEventName(VALID_EVENT_NAME_JOGGING).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder().withEventName(VALID_EVENT_NAME_JOGGING).build());

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showEventAtIndex(expectedModel, INDEX_FIRST_EVENT);
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEventUnfilteredList_failure() {
        Event firstEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(firstEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_SECOND_EVENT, descriptor);

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_duplicateEventFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        // edit event in filtered list into a duplicate in address book
        Event eventInList = model.getAddressBook().getEventList().get(INDEX_SECOND_EVENT.getZeroBased());
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder(eventInList).build());

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withEventName(VALID_EVENT_NAME_JOGGING).build();
        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidEventIndexFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getEventList().size());

        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex,
                new EditEventDescriptorBuilder().withEventName(VALID_EVENT_NAME_JOGGING).build());

        assertCommandFailure(editEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditEventCommand standardEditEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, DESC_E_JOGGING);

        // same values -> returns true
        EditEventDescriptor copyDescriptor = new EditEventDescriptor(DESC_E_JOGGING);
        EditEventCommand commandWithSameValues = new EditEventCommand(INDEX_FIRST_EVENT, copyDescriptor);
        assertTrue(standardEditEventCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardEditEventCommand.equals(standardEditEventCommand));

        // null -> returns false
        assertFalse(standardEditEventCommand.equals(null));

        // different command type -> returns false
        assertFalse(standardEditEventCommand.equals(new SortEventCommand()));

        // different index -> false
        assertFalse(standardEditEventCommand.equals(new EditEventCommand(INDEX_SECOND_EVENT, DESC_E_JOGGING)));

        // different descriptor -> false
        assertFalse(standardEditEventCommand.equals(new EditEventCommand(INDEX_FIRST_EVENT, DESC_E_SLEEP)));
    }

}
