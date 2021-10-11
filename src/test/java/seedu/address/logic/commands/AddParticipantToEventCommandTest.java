package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.ANOTHER_EVENT;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT;
import static seedu.address.testutil.TypicalParticipants.ALEX;
import static seedu.address.testutil.TypicalParticipants.BERNICE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.ParticipantId;
import seedu.address.testutil.DefaultModelStub;
import seedu.address.testutil.ParticipantBuilder;

class AddParticipantToEventCommandTest {

    @Test
    public void execute_addParticipantToEventSuccessful() throws Exception {
        Participant validParticipant = new ParticipantBuilder().build();
        EventName eventName = SAMPLE_EVENT.getName();
        ModelStubWithEventAndParticipant modelStub =
                new ModelStubWithEventAndParticipant(validParticipant, SAMPLE_EVENT);

        CommandResult commandResult =
                new AddParticipantToEventCommand(validParticipant.getParticipantId(), eventName).execute(modelStub);

        assertEquals(String.format(AddParticipantToEventCommand.MESSAGE_ADD_PARTICIPANT_TO_EVENT_SUCCESS,
                validParticipant.getFullName(), eventName), commandResult.getFeedbackToUser());
        assertTrue(modelStub.event.hasParticipant(validParticipant));
    }

    @Test
    public void equals() {
        ParticipantId alexId = ALEX.getParticipantId();
        ParticipantId berniceId = BERNICE.getParticipantId();
        EventName sampleEventName = SAMPLE_EVENT.getName();
        AddParticipantToEventCommand addAlexToSampleEvent =
                new AddParticipantToEventCommand(alexId, sampleEventName);
        AddParticipantToEventCommand addAlexToAnotherEvent =
                new AddParticipantToEventCommand(alexId, ANOTHER_EVENT.getName());
        AddParticipantToEventCommand addBerniceToSampleEvent =
                new AddParticipantToEventCommand(berniceId, sampleEventName);

        // same object -> returns true
        assertTrue(addAlexToSampleEvent.equals(addAlexToSampleEvent));

        // same values -> returns true
        AddParticipantToEventCommand addAlexToSampleEventCopy =
                new AddParticipantToEventCommand(alexId, sampleEventName);
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
     * A Model stub that contains a single event.
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
}