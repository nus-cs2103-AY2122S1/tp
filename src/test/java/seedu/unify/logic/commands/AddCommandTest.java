package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.unify.commons.core.GuiSettings;
import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.model.Model;
import seedu.unify.model.ReadOnlyUniFy;
import seedu.unify.model.ReadOnlyUserPrefs;
import seedu.unify.model.UniFy;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.WeeklyTasks;
import seedu.unify.testutil.TaskBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new AddCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }


    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        AddCommand addCommand = new AddCommand(validTask);
        ModelStub modelStub = new ModelStubWithTask(validTask);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_TASK, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Task alice = new TaskBuilder().withName("Alice").build();
        Task bob = new TaskBuilder().withName("Bob").build();
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

        // different task -> returns false
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
        public Path getUniFyFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUniFyFilePath(Path uniFyFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUniFy(ReadOnlyUniFy newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUniFy getUniFy() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortTasks(Comparator<Task> f) {

        }


        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public WeeklyTasks getWeeklyTasks() {
            return null;
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateWeeklyTaskList(Integer week) {

        }

        @Override
        public void updateWeeklyProgress() {

        }
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends ModelStub {
        private final Task task;

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return this.task.isSameTask(task);
        }
    }

    /**
     * A Model stub that always accept the task being added.
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.stream().anyMatch(task::isSameTask);
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public ReadOnlyUniFy getUniFy() {
            return new UniFy();
        }
    }

}
