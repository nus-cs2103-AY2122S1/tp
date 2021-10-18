package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PARTICIPANT_NOT_FOUND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalParticipants.ALEX;
import static seedu.address.testutil.TypicalParticipants.BERNICE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.participant.Participant;
import seedu.address.testutil.DefaultModelStub;
import seedu.address.testutil.ParticipantBuilder;

public class ViewCommandTest {
    private static final Participant participantWithSimilarId =
            new ParticipantBuilder(ALEX).withName("Alex Yeo").build();
    private final ModelStubWithParticipants modelStub = new ModelStubWithParticipants();

    @Test
    public void execute_findParticipantSuccessful() throws Exception {
        CommandResult commandResult = new ViewCommand("beryu1").execute(modelStub);
        assertEquals(BERNICE.toString(), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_participantNotInList_throwsCommandException() {
        ViewCommand viewCommand = new ViewCommand("mikerowe1");
        assertThrows(CommandException.class,
                String.format(MESSAGE_PARTICIPANT_NOT_FOUND, "mikerowe1",
                        ListCommand.COMMAND_WORD), () -> viewCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        ViewCommand findFirstCommand = new ViewCommand("aleyeo1");
        ViewCommand findSecondCommand = new ViewCommand("aleyeo2");

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        ViewCommand findFirstCommandCopy = new ViewCommand("aleyeo1");
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /**
     * A Model stub that contains a several participants.
     */
    private static class ModelStubWithParticipants extends DefaultModelStub {
        private final List<Participant> participantList = new ArrayList<>();

        ModelStubWithParticipants() {
            this.participantList.add(ALEX);
            this.participantList.add(BERNICE);
            this.participantList.add(participantWithSimilarId);
        }

        @Override
        public Optional<Participant> findParticipant(Predicate<Participant> predicate) {
            return this.participantList.stream().filter(predicate).findFirst();
        }
    }
}
