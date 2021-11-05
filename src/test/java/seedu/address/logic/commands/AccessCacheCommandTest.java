package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskListManager;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public class AccessCacheCommandTest {
    /**
     * A default model stub that have all the methods failing.
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
        public ObservableList<Person> getObservablePersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateObservablePersonList() {
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
