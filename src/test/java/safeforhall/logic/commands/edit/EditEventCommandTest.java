package safeforhall.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.commands.CommandTestUtil.DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.DESC_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_DATE_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandFailure;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;
import static safeforhall.logic.commands.CommandTestUtil.showEventAtIndex;
import static safeforhall.testutil.TypicalEvents.getTypicalAddressBook;
import static safeforhall.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static safeforhall.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.ClearCommand;
import safeforhall.logic.commands.edit.EditEventCommand.EditEventDescriptor;
import safeforhall.model.AddressBook;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.model.event.Event;
import safeforhall.testutil.EditEventDescriptorBuilder;
import safeforhall.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Event editedEvent = new EventBuilder().build();
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

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
        Event editedEvent = eventInList.withEventName(VALID_NAME_SWIM_TRAINING)
                .withEventDate(VALID_DATE_SWIM_TRAINING).build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_SWIM_TRAINING)
                .withDate(VALID_DATE_SWIM_TRAINING).build();
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
        Event editedEvent = new EventBuilder(eventInFilteredList)
                .withEventName(VALID_NAME_FOOTBALL_TRAINING).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder().withName(VALID_NAME_FOOTBALL_TRAINING).build());

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
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
    public void execute_exceedEventCapacity_failure() {
        Index indexLastEvent = Index.fromOneBased(model.getFilteredEventList().size());
        EditEventDescriptor eventDescriptor = new EditEventDescriptorBuilder()
                .withCapacity("2").build();
        EditEventCommand editEventCommand = new EditEventCommand(indexLastEvent,
                new EditEventDescriptorBuilder(eventDescriptor).build());

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_EXCEED_CAPACITY);
    }


    @Test
    public void execute_invalidEventIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withName(VALID_NAME_FOOTBALL_TRAINING).build();
        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidEventIndexFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getEventList().size());

        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex,
                new EditEventDescriptorBuilder().withName(VALID_NAME_FOOTBALL_TRAINING).build());

        assertCommandFailure(editEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditEventCommand standardCommand = new EditEventCommand(INDEX_FIRST_EVENT, DESC_FOOTBALL_TRAINING);

        // same values -> returns true
        EditEventDescriptor copyDescriptor = new EditEventDescriptor(DESC_FOOTBALL_TRAINING);
        EditEventCommand commandWithSameValues = new EditEventCommand(INDEX_FIRST_EVENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_SECOND_EVENT, DESC_FOOTBALL_TRAINING)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_FIRST_EVENT, DESC_SWIM_TRAINING)));
    }

}
