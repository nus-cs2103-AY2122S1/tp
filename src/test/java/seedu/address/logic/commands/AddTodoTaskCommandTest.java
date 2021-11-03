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
import seedu.address.model.group.Group;
import seedu.address.model.student.Student;
import seedu.address.model.task.Task;
import seedu.address.model.task.TodoTask;
import seedu.address.testutil.TodoTaskBuilder;

public class AddTodoTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTodoTaskCommand(null));
    }


    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        AddTodoTaskCommandTest.ModelStubAcceptingTaskAdded modelStub =
                new AddTodoTaskCommandTest.ModelStubAcceptingTaskAdded();
        TodoTask validTask = new TodoTaskBuilder().build();

        CommandResult commandResult = new AddTodoTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddTodoTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }


    @Test
    public void execute_duplicateTask_throwsCommandException() {
        TodoTask validTask = new TodoTaskBuilder().build();
        AddTodoTaskCommand addTodoTaskCommand = new AddTodoTaskCommand(validTask);
        AddTodoTaskCommandTest.ModelStub modelStub = new AddTodoTaskCommandTest.ModelStubWithTask(validTask);

        assertThrows(CommandException.class,
                AddTodoTaskCommand.MESSAGE_DUPLICATE_TASK, () -> addTodoTaskCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        TodoTask todoIp = new TodoTaskBuilder().withName("Complete iP").build();
        TodoTask todoTp = new TodoTaskBuilder().withName("Complete tP").build();
        TodoTask todoTpFinalFeature = new TodoTaskBuilder().withName("Complete tP")
                .withDescription("Implement the final feature").build();
        AddTodoTaskCommand todoIpCommand = new AddTodoTaskCommand(todoIp);
        AddTodoTaskCommand todoTpCommand = new AddTodoTaskCommand(todoTp);
        AddTodoTaskCommand todoFinalFeature = new AddTodoTaskCommand(todoTpFinalFeature);

        // same object -> returns true
        assertTrue(todoIpCommand.equals(todoIpCommand));

        // same values -> returns true
        AddTodoTaskCommand addAliceCommandCopy = new AddTodoTaskCommand(todoIp);
        assertTrue(todoIpCommand.equals(addAliceCommandCopy));

        // tasks have different description -> returns false
        assertFalse(todoFinalFeature.equals(todoTpCommand));

        // different types -> returns false
        assertFalse(todoIpCommand.equals(1));

        // null -> returns false
        assertFalse(todoIpCommand.equals(null));

        // different task -> returns false
        assertFalse(todoIpCommand.equals(todoTpCommand));
    }

    /**
     * A default model stub that has all methods failing.
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
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearTasks() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAnotherStudent(Student student, Student toIgnore) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getStudentAttendance(Student target, int week) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markStudentAttendance(Student target, int week) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getStudentParticipation(Student target, int week) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markStudentParticipation(Student target, int week) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
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
        public void toggleTaskIsDone(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public DisplayType getDisplayType() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGroup(Group target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMember(Student student, Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGroup(Group target, Group editedGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Group> getFilteredGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMember(Student target, Group group) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends AddTodoTaskCommandTest.ModelStub {
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
    private class ModelStubAcceptingTaskAdded extends AddTodoTaskCommandTest.ModelStub {
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
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
