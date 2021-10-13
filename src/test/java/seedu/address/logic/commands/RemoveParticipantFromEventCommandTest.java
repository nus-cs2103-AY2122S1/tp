package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EVENT_NOT_FOUND_IN_FILTERED_LIST;
import static seedu.address.commons.core.Messages.MESSAGE_PARTICIPANT_NOT_FOUND;
import static seedu.address.logic.commands.RemoveParticipantFromEventCommand.MESSAGE_PARTICIPANT_NOT_IN_EVENT;
import static seedu.address.logic.commands.RemoveParticipantFromEventCommand
        .MESSAGE_REMOVE_PARTICIPANT_FROM_EVENT_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ANOTHER_EVENT;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT;
import static seedu.address.testutil.TypicalParticipants.ALEX;
import static seedu.address.testutil.TypicalParticipants.BERNICE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.ParticipantId;
import seedu.address.testutil.DefaultModelStub;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.ParticipantBuilder;

class RemoveParticipantFromEventCommandTest {

    @Test
    public void execute_removeParticipantFromEventSuccessful() throws Exception {
        Participant validParticipant = new ParticipantBuilder().build();
        EventBuilder eventBuilder = new EventBuilder();
        eventBuilder.addParticipant(validParticipant);
        EventName eventName = SAMPLE_EVENT.getName();

        ModelStubWithEventAndParticipant modelStub =
                new ModelStubWithEventAndParticipant(validParticipant, eventBuilder.build());

        CommandResult commandResult =
                new RemoveParticipantFromEventCommand(validParticipant.getParticipantId(),
                        eventName).execute(modelStub);

        assertEquals(String.format(MESSAGE_REMOVE_PARTICIPANT_FROM_EVENT_SUCCESS,
                validParticipant.getFullName(), eventName), commandResult.getFeedbackToUser());
        assertFalse(modelStub.event.hasParticipant(validParticipant));
    }

    @Test
    public void execute_participantNotInModel_throwsCommandException() {
        Participant validParticipant = new ParticipantBuilder().build();
        EventName eventName = SAMPLE_EVENT.getName();
        ModelStubWithEventAndParticipant modelStub =
                new ModelStubWithEventAndParticipant(ALEX, new EventBuilder().build());

        RemoveParticipantFromEventCommand removeParticipantFromEventCommand =
                new RemoveParticipantFromEventCommand(validParticipant.getParticipantId(), eventName);

        assertThrows(CommandException.class,
                String.format(MESSAGE_PARTICIPANT_NOT_FOUND, validParticipant.getIdValue(), ListCommand.COMMAND_WORD),
                () -> removeParticipantFromEventCommand.execute(modelStub));
    }

    @Test
    public void execute_eventNotInModel_throwsCommandException() {
        Participant validParticipant = new ParticipantBuilder().build();
        EventName eventName = ANOTHER_EVENT.getName();
        ModelStubWithEventAndParticipant modelStub =
                new ModelStubWithEventAndParticipant(validParticipant, new EventBuilder().build());

        RemoveParticipantFromEventCommand removeParticipantFromEventCommand =
                new RemoveParticipantFromEventCommand(validParticipant.getParticipantId(), eventName);

        assertThrows(CommandException.class,
                String.format(MESSAGE_EVENT_NOT_FOUND_IN_FILTERED_LIST, eventName, ListEventCommand.COMMAND_WORD),
                () -> removeParticipantFromEventCommand.execute(modelStub));
    }

    @Test
    public void execute_eventDoesNotContainParticipant_throwsCommandException() {
        Participant validParticipant = new ParticipantBuilder().build();
        EventName eventName = SAMPLE_EVENT.getName();

        ModelStubWithEventAndParticipant modelStub =
                new ModelStubWithEventAndParticipant(validParticipant, new EventBuilder().build());

        RemoveParticipantFromEventCommand removeParticipantFromEventCommand =
                new RemoveParticipantFromEventCommand(validParticipant.getParticipantId(), eventName);

        assertThrows(CommandException.class,
                String.format(MESSAGE_PARTICIPANT_NOT_IN_EVENT, validParticipant.getFullName()),
                () -> removeParticipantFromEventCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        ParticipantId alexId = ALEX.getParticipantId();
        ParticipantId berniceId = BERNICE.getParticipantId();
        EventName sampleEventName = SAMPLE_EVENT.getName();
        RemoveParticipantFromEventCommand removeAlexFromSampleEvent =
                new RemoveParticipantFromEventCommand(alexId, sampleEventName);
        RemoveParticipantFromEventCommand removeALexFromAnotherEvent =
                new RemoveParticipantFromEventCommand(alexId, ANOTHER_EVENT.getName());
        RemoveParticipantFromEventCommand removeBerniceFromSampleEvent =
                new RemoveParticipantFromEventCommand(berniceId, sampleEventName);

        // same object -> returns true
        assertTrue(removeAlexFromSampleEvent.equals(removeAlexFromSampleEvent));

        // same values -> returns true
        RemoveParticipantFromEventCommand removeAlexFromSampleEventCopy =
                new RemoveParticipantFromEventCommand(alexId, sampleEventName);
        assertTrue(removeAlexFromSampleEvent.equals(removeAlexFromSampleEventCopy));

        // different types -> returns false
        assertFalse(removeAlexFromSampleEvent.equals(1));

        // null -> returns false
        assertFalse(removeAlexFromSampleEvent.equals(null));

        // different participant -> returns false
        assertFalse(removeAlexFromSampleEvent.equals(removeBerniceFromSampleEvent));

        // different event -> returns false
        assertFalse(removeAlexFromSampleEvent.equals(removeALexFromAnotherEvent));
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
}