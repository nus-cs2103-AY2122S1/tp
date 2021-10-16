package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ANOTHER_EVENT;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.testutil.DefaultModelStub;

class AddEventCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addEventSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = SAMPLE_EVENT;

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_ADD_EVENT_SUCCESS, validEvent),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = ANOTHER_EVENT;
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);
        DefaultModelStub modelStub = new AddEventCommandTest.ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class, AddEventCommand.MESSAGE_DUPLICATE_EVENT, () ->
                addEventCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddEventCommand addSampleEvent = new AddEventCommand(SAMPLE_EVENT);
        AddEventCommand addAnotherEvent = new AddEventCommand(ANOTHER_EVENT);

        // same object -> returns true
        assertTrue(addSampleEvent.equals(addSampleEvent));

        // same values -> returns true
        AddEventCommand addSampleEventCopy = new AddEventCommand(SAMPLE_EVENT);
        assertTrue(addSampleEvent.equals(addSampleEventCopy));

        // different types -> returns false
        assertFalse(addSampleEvent.equals(1));

        // null -> returns false
        assertFalse(addSampleEvent.equals(null));

        // different person -> returns false
        assertFalse(addSampleEvent.equals(addAnotherEvent));
    }

    /**
     * A Model stub that contains a single event.
     */
    private class ModelStubWithEvent extends DefaultModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.event.isSameEvent(event);
        }
    }

    /**
     * A Model stub that always accept the event being added.
     */
    private class ModelStubAcceptingEventAdded extends DefaultModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::isSameEvent);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
