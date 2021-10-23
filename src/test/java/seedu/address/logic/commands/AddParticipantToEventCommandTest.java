package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.AddParticipantToEventCommand.MESSAGE_ADD_PARTICIPANT_TO_EVENT_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ANOTHER_EVENT;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT;
import static seedu.address.testutil.TypicalParticipants.ALEX;
import static seedu.address.testutil.TypicalParticipants.BERNICE;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.ParticipantId;
import seedu.address.testutil.DefaultModelStub;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.ParticipantBuilder;

class AddParticipantToEventCommandTest {

    @Test
    public void execute_addParticipantToEventSuccessful() throws Exception {
        Participant validParticipant = new ParticipantBuilder().build();
        EventName eventName = SAMPLE_EVENT.getName();
        ModelStubWithEventAndParticipant modelStub =
                new ModelStubWithEventAndParticipant(validParticipant, new EventBuilder().build());

        CommandResult commandResult =
                new AddParticipantToEventCommand(Index.fromOneBased(1), Index.fromOneBased(1)).execute(modelStub);

        assertEquals(String.format(MESSAGE_ADD_PARTICIPANT_TO_EVENT_SUCCESS,
                validParticipant.getFullName(), eventName), commandResult.getFeedbackToUser());
        assertTrue(modelStub.event.hasParticipant(validParticipant));
    }

    @Test
    public void execute_participantNotInModel_throwsCommandException() {
        ModelStubWithEvent modelStub = new ModelStubWithEvent(new EventBuilder().build());

        AddParticipantToEventCommand addParticipantToEventCommand =
                new AddParticipantToEventCommand(Index.fromOneBased(1), Index.fromOneBased(1));

        assertThrows(CommandException.class,
                MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX, () -> addParticipantToEventCommand.execute(modelStub));
    }

    @Test
    public void execute_eventNotInModel_throwsCommandException() {
        Participant validParticipant = new ParticipantBuilder().build();
        ModelStubWithParticipant modelStub = new ModelStubWithParticipant(validParticipant);

        AddParticipantToEventCommand addParticipantToEventCommand =
                new AddParticipantToEventCommand(Index.fromOneBased(1), Index.fromOneBased(1));

        assertThrows(CommandException.class,
                MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, () -> addParticipantToEventCommand.execute(modelStub));
    }

    @Test
    public void execute_eventContainsParticipant_throwsCommandException() {
        Participant validParticipant = new ParticipantBuilder().build();
        EventBuilder eventBuilder = new EventBuilder();
        eventBuilder.addParticipant(validParticipant);

        ModelStubWithEventAndParticipant modelStub =
                new ModelStubWithEventAndParticipant(validParticipant, eventBuilder.build());

        AddParticipantToEventCommand addParticipantToEventCommand =
                new AddParticipantToEventCommand(Index.fromOneBased(1), Index.fromOneBased(1));

        assertThrows(CommandException.class,
                Messages.showParticipantExists(validParticipant.getFullName()), () ->
                        addParticipantToEventCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        ParticipantId alexId = ALEX.getParticipantId();
        ParticipantId berniceId = BERNICE.getParticipantId();
        EventName sampleEventName = SAMPLE_EVENT.getName();
        AddParticipantToEventCommandOld addAlexToSampleEvent =
                new AddParticipantToEventCommandOld(alexId, sampleEventName);
        AddParticipantToEventCommandOld addAlexToAnotherEvent =
                new AddParticipantToEventCommandOld(alexId, ANOTHER_EVENT.getName());
        AddParticipantToEventCommandOld addBerniceToSampleEvent =
                new AddParticipantToEventCommandOld(berniceId, sampleEventName);

        // same object -> returns true
        assertTrue(addAlexToSampleEvent.equals(addAlexToSampleEvent));

        // same values -> returns true
        AddParticipantToEventCommandOld addAlexToSampleEventCopy =
                new AddParticipantToEventCommandOld(alexId, sampleEventName);
        assertTrue(addAlexToSampleEvent.equals(addAlexToSampleEventCopy));

        // different types -> returns false
        assertFalse(addAlexToSampleEvent.equals(1));

        // null -> returns false
        assertFalse(addAlexToSampleEvent.equals(null));

        // different participant -> returns false
        assertFalse(addAlexToSampleEvent.equals(addBerniceToSampleEvent));

        // different event -> returns false
        assertFalse(addAlexToSampleEvent.equals(addAlexToAnotherEvent));
    }

    /**
     * A Model stub that contains a single Event and single Participant.
     */
    private class ModelStubWithEventAndParticipant extends DefaultModelStub {
        private final Participant participant;
        private final Event event;

        ModelStubWithEventAndParticipant(Participant participant, Event event) {
            requireNonNull(event);
            this.participant = participant;
            this.event = event;
        }

        @Override
        public ObservableList<Participant> getFilteredParticipantList() {
            ObservableList<Participant> participants = FXCollections.observableArrayList();
            participants.add(participant);
            return participants;
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            ObservableList<Event> events = FXCollections.observableArrayList();
            events.add(event);
            return events;
        }
    }

    /**
     * A Model stub that contains a single Participant.
     */
    private class ModelStubWithParticipant extends DefaultModelStub {
        private final Participant participant;

        ModelStubWithParticipant(Participant participant) {
            requireNonNull(participant);
            this.participant = participant;
        }

        @Override
        public ObservableList<Participant> getFilteredParticipantList() {
            ObservableList<Participant> participants = FXCollections.observableArrayList();
            participants.add(participant);
            return participants;
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            return FXCollections.observableArrayList();
        }
    }

    /**
     * A Model stub that contains a single Event.
     */
    private class ModelStubWithEvent extends DefaultModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public ObservableList<Participant> getFilteredParticipantList() {
            return FXCollections.observableArrayList();
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            ObservableList<Event> events = FXCollections.observableArrayList();
            events.add(event);
            return events;
        }
    }
}
