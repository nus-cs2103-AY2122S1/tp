package seedu.address.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.participant.Participant;

public class DefaultModelStub implements Model {
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
