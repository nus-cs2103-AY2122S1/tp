package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TaskListManager;
import seedu.address.model.event.Event;
import seedu.address.model.member.Member;
import seedu.address.model.task.Task;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.MemberBuilder;

class TAddCommandTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TAddCommand(null, null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        Index validMemberID = Index.fromOneBased(1);
        Task validTask = new Task("Do homework");
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded(addressBook, validTask, validMemberID);
        CommandResult commandResult = new TAddCommand(validMemberID, validTask).execute(modelStub);

        assertEquals(String.format(TAddCommand.MESSAGE_SUCCESS, validMember.getName(), validTask),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Index validMemberID = Index.fromOneBased(1);
        Task duplicateTask = new Task("Do homework");
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TAddCommand tAddCommand = new TAddCommand(validMemberID, duplicateTask);
        ModelStub modelStub = new ModelStubWithTask(addressBook, duplicateTask, validMemberID);

        assertThrows(CommandException.class, TAddCommand.MESSAGE_DUPLICATE_TASK, () ->
                tAddCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Index validMemberID = Index.fromOneBased(1);
        Task validTask1 = new Task("Do homework");
        Task validTask2 = new Task("Write a poem");
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TAddCommand addHomeworkCommand = new TAddCommand(validMemberID, validTask1);
        TAddCommand addPoemCommand = new TAddCommand(validMemberID, validTask2);

        // same object -> returns true
        assertTrue(addHomeworkCommand.equals(addHomeworkCommand));

        // same values -> returns true
        TAddCommand addHomeworkCommandCopy = new TAddCommand(validMemberID, validTask1);
        assertTrue(addHomeworkCommand.equals(addHomeworkCommandCopy));

        // different types -> returns false
        assertFalse(addHomeworkCommand.equals(1));

        // null -> returns false
        assertFalse(addHomeworkCommand.equals(null));

        // different member -> returns false
        assertFalse(addHomeworkCommand.equals(addPoemCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
        public void addMember(Member member) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMember(Member member) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMember(Member target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMember(Member target, Member editedMember) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Member> getFilteredMemberList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMemberList(Predicate<Member> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void loadTaskList(Member member) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Member member, Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Member member, Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Member member, Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Member member, int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Member member, Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces the task specified by {@code index} with {@code editedTask} in the given {@code member}'s task list.
         */
        @Override
        public void setTask(Member member, int index, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList(Member member) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Member member, Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single member with task.
     */
    private class ModelStubWithTask extends TAddCommandTest.ModelStub {
        private final Member member;
        private final Task task;
        private final AddressBook addressBook;

        ModelStubWithTask(ReadOnlyAddressBook addressBook, Task task, Index memberID) {
            this.addressBook = new AddressBook(addressBook);
            requireNonNull(memberID);
            ObservableList<Member> members = addressBook.getMemberList();
            this.member = members.get(memberID.getZeroBased());
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return addressBook;
        }

        @Override
        public boolean hasTask(Member member, Task task) {
            requireNonNull(member);
            requireNonNull(task);
            return this.member.equals(member) && this.task.equals(task);
        }
    }

    /**
     * A Model stub that always accept the member being added.
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        private final AddressBook addressBook;
        private final Member member;
        private final Task task;
        private final TaskListManager taskListManager;


        ModelStubAcceptingTaskAdded(ReadOnlyAddressBook addressBook, Task task, Index memberID) {
            this.addressBook = new AddressBook(addressBook);
            requireNonNull(memberID);
            ObservableList<Member> members = addressBook.getMemberList();
            this.member = members.get(memberID.getZeroBased());
            requireNonNull(task);
            this.task = task;
            this.taskListManager = new TaskListManager();
        }

        @Override
        public boolean hasTask(Member member, Task task) {
            loadTaskList(member);
            return taskListManager.hasTask(task);
        }

        @Override
        public void addTask(Member member, Task task) {
            requireNonNull(member);
            loadTaskList(member);
            taskListManager.addTask(task);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return this.addressBook;
        }

        @Override
        public void loadTaskList(Member member) {
            requireNonNull(member);
            taskListManager.loadTaskList(member.getTaskList());
        }
    }
}
