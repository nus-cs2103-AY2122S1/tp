package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.participant.Participant;
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
        ModelStub modelStub = new ModelStubWithParticipant(validParticipant);

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
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addParticipant(Participant participant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasParticipant(Participant participant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteParticipant(Participant target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setParticipant(Participant target, Participant editedParticipant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Participant> getFilteredParticipantList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredParticipantList(Predicate<Participant> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markEventAsDone(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortEvents() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single participant.
     */
    private class ModelStubWithParticipant extends ModelStub {
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
    private class ModelStubAcceptingParticipantAdded extends ModelStub {
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
