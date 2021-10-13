package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
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
import seedu.address.model.data.event.Event;
import seedu.address.model.data.member.Member;
import seedu.address.model.task.Task;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.MemberBuilder;


class TDelCommandTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TDelCommand(null, null));
    }

    @Test
    public void execute_taskDeletedByModel_deleteSuccessful() throws Exception {
        Index validMemberID = Index.fromOneBased(1);
        Index validTaskID = Index.fromOneBased(1);
        Task validTask = new Task("Do homework");
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TAddCommand tAddCommand = new TAddCommand(validMemberID, validTask);
        ModelStub modelStub = new ModelStubWithTask(addressBook, validTask, validMemberID);
        tAddCommand.execute(modelStub);
        CommandResult commandResult = new TDelCommand(validMemberID, validTaskID).execute(modelStub);

        assertEquals(String.format(TDelCommand.MESSAGE_SUCCESS, validMember.getName(), validTask.getTaskName()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_taskNotPresent_throwsTaskNotFoundException() {
        Index validMemberID = Index.fromOneBased(1);
        Index validTaskID = Index.fromOneBased(1);
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TDelCommand tDelCommand = new TDelCommand(validMemberID, validTaskID);
        ModelStubWithoutTask modelStub = new ModelStubWithoutTask(addressBook, validMemberID);

        assertThrows(CommandException.class, TDelCommand.MESSAGE_TASK_NOT_FOUND, () ->
                tDelCommand.execute(modelStub));
    }

    //TODO
    @Test
    void equals() {
        Index validMemberID = Index.fromOneBased(1);
        Index validTaskID1 = Index.fromOneBased(1);
        Index validTaskID2 = Index.fromOneBased(2);
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TDelCommand tDelCommand1 = new TDelCommand(validMemberID, validTaskID1);
        TDelCommand tDelCommand2 = new TDelCommand(validMemberID, validTaskID2);

        // same object -> returns true
        assertTrue(tDelCommand1.equals(tDelCommand1));

        // same values -> returns true
        TDelCommand tDelCommand1Copy = new TDelCommand(validMemberID, validTaskID1);
        assertTrue(tDelCommand1.equals(tDelCommand1Copy));

        // different types -> returns false
        assertFalse(tDelCommand1.equals(1));

        // null -> returns false
        assertFalse(tDelCommand1.equals(null));

        // different member -> returns false
        assertFalse(tDelCommand1.equals(tDelCommand2));
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
        public void addEventMembers(Event event, Set<Member> memberSet) {
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
    private class ModelStubWithTask extends ModelStub {
        private final AddressBook addressBook;
        private final Member member;
        private final Task task;
        private final TaskListManager taskListManager;
        private final FilteredList<Member> filteredMembers;

        ModelStubWithTask(ReadOnlyAddressBook addressBook, Task task, Index memberID) {
            this.addressBook = new AddressBook(addressBook);
            requireNonNull(memberID);
            this.filteredMembers = new FilteredList<>(this.addressBook.getMemberList());
            this.member = filteredMembers.get(memberID.getZeroBased());
            requireNonNull(task);
            this.task = task;
            this.taskListManager = new TaskListManager();
        }

        @Override
        public ObservableList<Member> getFilteredMemberList() {
            return filteredMembers;
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

        @Override
        public void deleteTask(Member member, int index) {
            loadTaskList(member);
            taskListManager.removeTask(index);
        }
    }

    /**
     * A Model stub that contains a single member with task.
     */
    private class ModelStubWithoutTask extends ModelStub {
        private final AddressBook addressBook;
        private final Member member;
        private final TaskListManager taskListManager;
        private final FilteredList<Member> filteredMembers;

        ModelStubWithoutTask(ReadOnlyAddressBook addressBook, Index memberID) {
            this.addressBook = new AddressBook(addressBook);
            requireNonNull(memberID);
            this.filteredMembers = new FilteredList<>(this.addressBook.getMemberList());
            this.member = filteredMembers.get(memberID.getZeroBased());
            this.taskListManager = new TaskListManager();
        }

        @Override
        public ObservableList<Member> getFilteredMemberList() {
            return filteredMembers;
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

        @Override
        public void deleteTask(Member member, int index) {
            loadTaskList(member);
            taskListManager.removeTask(index);
        }
    }
}
