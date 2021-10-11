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
import seedu.address.model.participant.Participant;
import seedu.address.testutil.DefaultModelStub;
import seedu.address.testutil.ParticipantBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_participantAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingParticipantAdded modelStub = new ModelStubAcceptingParticipantAdded();
        Participant validParticipant = new ParticipantBuilder().build();

        CommandResult commandResult = new AddCommand(validParticipant).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validParticipant), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validParticipant), modelStub.participantsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Participant validParticipant = new ParticipantBuilder().build();
        AddCommand addCommand = new AddCommand(validParticipant);
        DefaultModelStub modelStub = new ModelStubWithParticipant(validParticipant);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PARTICIPANT, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Participant alice = new ParticipantBuilder().withName("Alice").build();
        Participant bob = new ParticipantBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single participant.
     */
    private class ModelStubWithParticipant extends DefaultModelStub {
        private final Participant participant;

        ModelStubWithParticipant(Participant participant) {
            requireNonNull(participant);
            this.participant = participant;
        }

        @Override
        public boolean hasParticipant(Participant participant) {
            requireNonNull(participant);
            return this.participant.isSameParticipant(participant);
        }
    }

    /**
     * A Model stub that always accept the participant being added.
     */
    private class ModelStubAcceptingParticipantAdded extends DefaultModelStub {
        final ArrayList<Participant> participantsAdded = new ArrayList<>();

        @Override
        public boolean hasParticipant(Participant participant) {
            requireNonNull(participant);
            return participantsAdded.stream().anyMatch(participant::isSameParticipant);
        }

        @Override
        public void addParticipant(Participant participant) {
            requireNonNull(participant);
            participantsAdded.add(participant);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
