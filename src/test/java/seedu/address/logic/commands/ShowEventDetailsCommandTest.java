package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_EVENT_NOT_FOUND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalParticipants.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventNamePredicate;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.participant.Participant;

/**
 * Contains integration tests (interaction with the Model) for {@code ShowEventDetailsCommand}.
 */
public class ShowEventDetailsCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShowEventDetailsCommand(null));
    }

    @Test
    public void execute_eventNotInList_throwsCommandException() {
        EventNamePredicate predicate = preparePredicate(" ");
        ShowEventDetailsCommand command = new ShowEventDetailsCommand(predicate);
        assertThrows(CommandException.class, MESSAGE_EVENT_NOT_FOUND, () -> command.execute(model));
    }

    /**
     * Checks that the UI is unaffected by the use of a ShowEventDetailsCommand.
     *
     * @throws CommandException if model provided to ShowEventDetailsCommand execute method is invalid.
     */
    @Test
    public void execute_eventInList_noUiChangeSuccessful() throws CommandException {
        Event sampleEvent = new Event(
                new EventName("Cooking class"),
                new EventDate("2020-11-11"),
                new EventTime("1900"));
        model.addEvent(sampleEvent);
        expectedModel.addEvent(sampleEvent);
        EventNamePredicate predicate = preparePredicate("Cooking class");
        CommandResult commandResult = new ShowEventDetailsCommand(predicate).execute(model);
        assertEquals(model.getFilteredEventList(), expectedModel.getFilteredEventList());
    }

    /**
     * Tests CommandResult output of ShowEventDetailsCommand.
     *
     * @throws CommandException if model provided to ShowEventDetailsCommand execute method is invalid.
     */
    @Test
    public void execute_eventInList_showDetailsSuccessful() throws CommandException {
        Event sampleEvent = new Event(
                new EventName("Cooking class"),
                new EventDate("2020-11-11"),
                new EventTime("1900"));
        model.addEvent(sampleEvent);
        EventNamePredicate predicate = preparePredicate("Cooking class");
        CommandResult commandResult = new ShowEventDetailsCommand(predicate).execute(model);
        String expectedOutput = "Event Name: Cooking class\n"
                + "Event Date: 2020-11-11\n"
                + "Event Time: 1900\n"
                + "Completion Status: Uncompleted";
        assertEquals(commandResult.getFeedbackToUser(), expectedOutput);
    }

    /**
     * Tests CommandResult output of ShowEventDetailsCommand using a ModelStub.
     *
     * @throws CommandException if model provided to ShowEventDetailsCommand execute method is invalid.
     */
    @Test
    public void execute_eventInListUsingModelStub_showDetailsSuccessful() throws CommandException {
        Event sampleEvent = new Event(
                new EventName("Cooking class"),
                new EventDate("2020-11-11"),
                new EventTime("1900"));
        ModelStubWithEvent modelStub = new ModelStubWithEvent(sampleEvent);
        EventNamePredicate predicate = preparePredicate("Cooking class");
        CommandResult commandResult = new ShowEventDetailsCommand(predicate).execute(modelStub);
        String expectedOutput = "Event Name: Cooking class\n"
                + "Event Date: 2020-11-11\n"
                + "Event Time: 1900\n"
                + "Completion Status: Uncompleted";
        assertEquals(commandResult.getFeedbackToUser(), expectedOutput);
    }

    /**
     * A Model stub that contains a single Event.
     */
    private class ModelStubWithEvent extends ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            UniqueEventList eventList = new UniqueEventList();
            eventList.add(event);
            return new FilteredList<>(eventList.asUnmodifiableObservableList());
        }
    }

    /**
     * Parses {@code userInput} into a {@code EventNamePredicate}.
     */
    private EventNamePredicate preparePredicate(String userInput) {
        return new EventNamePredicate(userInput.trim().replaceAll("\\s+", " "));
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
}
