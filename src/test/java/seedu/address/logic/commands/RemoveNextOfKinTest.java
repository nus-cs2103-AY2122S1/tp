package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.RemoveNextOfKinCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalParticipants.ALEX;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.participant.NextOfKin;
import seedu.address.model.participant.Participant;
import seedu.address.testutil.DefaultModelStub;
import seedu.address.testutil.ParticipantBuilder;

public class RemoveNextOfKinTest {
    @Test
    public void execute_addNextOfKinSuccessful() throws Exception {
        Participant validParticipant = new ParticipantBuilder(ALEX).build();
        ModelStubWithParticipant modelStub =
                new ModelStubWithParticipant(validParticipant);

        NextOfKin nextOfKinToRemove = validParticipant.getNextOfKin(0);

        CommandResult commandResult =
                new RemoveNextOfKinCommand(Index.fromOneBased(1), Index.fromOneBased(1)).execute(modelStub);

        assertEquals(String.format(MESSAGE_SUCCESS, nextOfKinToRemove.getFullName(),
                ALEX.getFullName()), commandResult.getFeedbackToUser());

        assertFalse(validParticipant.hasNextOfKin(nextOfKinToRemove));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Participant validParticipant = new ParticipantBuilder(ALEX).build();
        ModelStubWithParticipant modelStub =
                new ModelStubWithParticipant(validParticipant);

        assertThrows(CommandException.class,
                String.format(MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX,
                        RemoveNextOfKinCommand.COMMAND_WORD), () ->
                        new RemoveNextOfKinCommand(Index.fromOneBased(2), Index.fromOneBased(1)).execute(modelStub));

        assertThrows(CommandException.class,
                String.format(MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX,
                        RemoveNextOfKinCommand.COMMAND_WORD), () ->
                        new RemoveNextOfKinCommand(Index.fromOneBased(1), Index.fromOneBased(2)).execute(modelStub));
    }

    @Test
    public void equals() {
        Index firstIndex = Index.fromOneBased(1);
        Index secondIndex = Index.fromOneBased(2);

        RemoveNextOfKinCommand removeFirstSample =
                new RemoveNextOfKinCommand(firstIndex, firstIndex);
        RemoveNextOfKinCommand removeFirstFromFirst =
                new RemoveNextOfKinCommand(firstIndex, firstIndex);
        RemoveNextOfKinCommand removeFirstFromSecond =
                new RemoveNextOfKinCommand(firstIndex, secondIndex);
        RemoveNextOfKinCommand removeSecondfromSecond =
                new RemoveNextOfKinCommand(secondIndex, secondIndex);

        // same object -> returns true
        assertTrue(removeFirstSample.equals(removeFirstSample));

        // same values -> returns true
        assertTrue(removeFirstSample.equals(removeFirstFromFirst));

        // different types -> returns false
        assertFalse(removeFirstFromFirst.equals(1));

        // null -> returns false
        assertFalse(removeFirstFromFirst.equals(null));

        // different index -> returns false
        assertFalse(removeFirstFromFirst.equals(removeFirstFromSecond));
        assertFalse(removeFirstFromSecond.equals(removeSecondfromSecond));

        assertFalse(removeFirstFromFirst.equals(removeSecondfromSecond));
    }

    /**
     * A Model stub that contains a single Event and single Participant.
     */
    private static class ModelStubWithParticipant extends DefaultModelStub {
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
    }
}
