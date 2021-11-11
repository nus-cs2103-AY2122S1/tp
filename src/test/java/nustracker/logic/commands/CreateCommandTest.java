package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustracker.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.AddressBook;
import nustracker.model.ReadOnlyAddressBook;
import nustracker.model.event.Event;
import nustracker.testutil.EventBuilder;
import nustracker.testutil.ModelStub;
import nustracker.ui.MainWindow.CurrentlyShownList;

class CreateCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub =
                new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new CreateCommand(validEvent).execute(modelStub,
                CurrentlyShownList.STUDENTS_LIST);

        Assertions.assertEquals(String.format(CreateCommand.MESSAGE_SUCCESS, validEvent),
                commandResult.getFeedbackToUser());
        Assertions.assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        CreateCommand createCommand = new CreateCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class, CreateCommand.MESSAGE_DUPLICATE_EVENT, () ->
                createCommand.execute(modelStub, CurrentlyShownList.STUDENTS_LIST));
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
    private class ModelStubAcceptingEventAdded extends ModelStub {
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
