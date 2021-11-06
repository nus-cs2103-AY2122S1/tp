package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskListManager;

public class AccessCacheCommandTest {

    private final Model model = new AccessCacheCommandTest.ModelStub();

    @Test
    public void constructor_nullCommand_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AccessCacheCommand(null));
    }

    @Test
    public void upKey_givesBefore() throws Exception {
        CommandResult command = new AccessCacheCommand("UP").execute(model);
        assertEquals("Before", command.getAdditionalText());
        assertEquals("", command.getFeedbackToUser());
    }

    @Test
    public void downKey_givesAfter() throws Exception {
        CommandResult command = new AccessCacheCommand("DOWN").execute(model);
        assertEquals("After", command.getAdditionalText());
        assertEquals("", command.getFeedbackToUser());
    }

    @Test
    public void equals() {
        AccessCacheCommand command = new AccessCacheCommand("DOWN");
        AccessCacheCommand commandSame = new AccessCacheCommand("DOWN");
        AccessCacheCommand commandDifferent = new AccessCacheCommand("UP");

        //null
        assertNotEquals(null, command);

        //same object
        assertEquals(command, command);

        //same direction
        assertEquals(command, commandSame);

        //different direction
        assertNotEquals(command, commandDifferent);
    }

    /**
     * A default model stub that have all themethods failing.
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
        public void addPerson(Person person) {
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
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void displayPersonTaskList(Person person) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void displayFilteredPersonTaskList(Person person, Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void displayFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ObservableList<Task> getDisplayTaskList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateSortedPersonList(boolean isReverseOrder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<PieChart.Data> getStatistics() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCommand(String command) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TaskListManager getTaskListManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getBefore() {
            return "Before";
        }

        @Override
        public String getAfter() {
            return "After";
        }

        @Override
        public ObservableList<Person> getViewAllTaskListPersons() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateViewAllTaskListPersons() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setViewAllTasksFindPred(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIsViewAllTasks(boolean isViewAllTasks) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean getIsViewAllTasks() {
            throw new AssertionError("This method should not be called.");
        }
    }
}
