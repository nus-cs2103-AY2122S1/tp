package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.ModelStub;

class EventCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addEventSuccessful() throws Exception {
        ModelStubAcceptingEvent modelStub = new ModelStubAcceptingEvent();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new EventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(EventCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        EventCommand eventCommand = new EventCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class,
                EventCommand.MESSAGE_DUPLICATE_EVENT, () -> eventCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Event partyEvent = new EventBuilder().withEventName("Party").build();
        Event dinnerEvent = new EventBuilder().withEventName("Dinner").build();
        EventCommand addPartyEvent = new EventCommand(partyEvent);
        EventCommand addDinnerEvent = new EventCommand(dinnerEvent);

        // equivalence partitions testing
        // same object -> returns true
        assertTrue(addPartyEvent.equals(addPartyEvent));

        // same values -> returns true
        EventCommand addPartyEventCopy = new EventCommand(partyEvent);
        assertTrue(addPartyEvent.equals(addPartyEventCopy));

        // different types -> returns false
        assertFalse(addPartyEvent.equals(1));

        // null -> returns false
        assertFalse(addPartyEvent.equals(null));

        // different event -> returns false
        assertFalse(addPartyEvent.equals(addDinnerEvent));
    }

    /**
     * A Model stub that contains a single event.
     */
    private class ModelStubWithEvent extends ModelStub {
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
    private class ModelStubAcceptingEvent extends ModelStub {
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
