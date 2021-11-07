package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.member.Member;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskList;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.MemberBuilder;
import seedu.address.testutil.TaskBuilder;


class TdelCommandTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TdelCommand(null));
    }

    @Test
    public void execute_taskDeletedByModel_deleteSuccessful() throws Exception {
        Index validMemberId = Index.fromOneBased(1);
        Set<Index> validMemberIdList = new HashSet<>();
        validMemberIdList.add(validMemberId);
        Index validTaskId = Index.fromOneBased(1);
        Task validTask = new TaskBuilder().build();
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TaddCommand tAddCommand = new TaddCommand(validMemberIdList, validTask);
        ModelStub modelStub = new ModelStubWithTask(addressBook, validTask, validMemberId);
        tAddCommand.execute(modelStub);
        CommandResult commandResult = new TdelCommand(validTaskId).execute(modelStub);

        assertEquals(String.format(TdelCommand.MESSAGE_SUCCESS, validMember.getName(), validTask.getName()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_taskNotPresent_throwsTaskNotFoundException() throws CommandException {
        Index validMemberId = Index.fromOneBased(1);
        Index validTaskId = Index.fromOneBased(1);
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TdelCommand tDelCommand = new TdelCommand(validTaskId);
        ModelStubWithoutTask modelStub = new ModelStubWithoutTask(addressBook, validMemberId);
        TlistCommand tlistCommand = new TlistCommand(validMemberId);
        tlistCommand.execute(modelStub);
        assertThrows(CommandException.class,
                String.format(TdelCommand.MESSAGE_TASK_NOT_FOUND, validTaskId.getOneBased()), () ->
                tDelCommand.execute(modelStub));
    }

    //TODO
    @Test
    void equals() {
        Index validMemberId = Index.fromOneBased(1);
        Index validTaskId1 = Index.fromOneBased(1);
        Index validTaskId2 = Index.fromOneBased(2);
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TdelCommand tDelCommand1 = new TdelCommand(validTaskId1);
        TdelCommand tDelCommand2 = new TdelCommand(validTaskId2);

        // same object -> returns true
        assertTrue(tDelCommand1.equals(tDelCommand1));

        // same values -> returns true
        TdelCommand tDelCommand1Copy = new TdelCommand(validTaskId1);
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
        public void deleteTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList(Member member) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Member member, Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Member> getCurrentMember() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentEvent(Event currentEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Event> getCurrentEvent() {
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
        private TaskList taskListManager;
        private FilteredList<Task> filteredTasks;
        private final FilteredList<Member> filteredMembers;
        // The current selected member
        private Member currentMember;

        ModelStubWithTask(ReadOnlyAddressBook addressBook, Task task, Index memberId) {
            this.addressBook = new AddressBook(addressBook);
            requireNonNull(memberId);
            this.filteredMembers = new FilteredList<>(this.addressBook.getMemberList());
            this.member = filteredMembers.get(memberId.getZeroBased());
            requireNonNull(task);
            this.task = task;
            this.taskListManager = new TaskList();
            this.filteredTasks = new FilteredList<>(this.taskListManager.asUnmodifiableObservableList());
        }

        @Override
        public ObservableList<Member> getFilteredMemberList() {
            return filteredMembers;
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return filteredTasks;
        }

        @Override
        public boolean hasTask(Member member, Task task) {
            loadTaskList(member);
            return taskListManager.contains(task);
        }

        @Override
        public void addTask(Member member, Task task) {
            requireNonNull(member);
            loadTaskList(member);
            taskListManager.add(task);
        }

        @Override
        public void deleteTask(Task task) {
            taskListManager.remove(task);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return this.addressBook;
        }

        @Override
        public void loadTaskList(Member member) {
            requireNonNull(member);
            if (this.taskListManager != member.getTaskList()) {
                this.taskListManager = member.getTaskList();
                this.filteredTasks = new FilteredList<>(this.taskListManager.asUnmodifiableObservableList());
                this.currentMember = member;
            }
        }

        @Override
        public Optional<Member> getCurrentMember() {
            return Optional.ofNullable(currentMember);
        }
    }

    /**
     * A Model stub that contains a single member with task.
     */
    private class ModelStubWithoutTask extends ModelStub {
        private final AddressBook addressBook;
        private final Member member;
        private TaskList taskListManager;
        private final FilteredList<Member> filteredMembers;
        // The current selected member
        private Member currentMember;
        private FilteredList<Task> filteredTasks;

        ModelStubWithoutTask(ReadOnlyAddressBook addressBook, Index memberId) {
            this.addressBook = new AddressBook(addressBook);
            requireNonNull(memberId);
            this.filteredMembers = new FilteredList<>(this.addressBook.getMemberList());
            this.member = filteredMembers.get(memberId.getZeroBased());
            this.taskListManager = new TaskList();
            filteredTasks = new FilteredList<>(this.taskListManager.asUnmodifiableObservableList());
        }

        @Override
        public ObservableList<Member> getFilteredMemberList() {
            return filteredMembers;
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return filteredTasks;
        }

        @Override
        public boolean hasTask(Member member, Task task) {
            loadTaskList(member);
            return taskListManager.contains(task);
        }

        @Override
        public void addTask(Member member, Task task) {
            requireNonNull(member);
            loadTaskList(member);
            taskListManager.add(task);
        }

        @Override
        public void deleteTask(Task task) {
            taskListManager.remove(task);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return this.addressBook;
        }

        @Override
        public void loadTaskList(Member member) {
            requireNonNull(member);
            if (this.taskListManager != member.getTaskList()) {
                this.taskListManager = member.getTaskList();
                this.filteredTasks = new FilteredList<>(this.taskListManager.asUnmodifiableObservableList());
                this.currentMember = member;
            }
        }

        @Override
        public Optional<Member> getCurrentMember() {
            return Optional.ofNullable(currentMember);
        }

        @Override
        public void updateFilteredTaskList(Member member, Predicate<Task> predicate) {
            requireNonNull(predicate);
            loadTaskList(member);
            filteredTasks.setPredicate(predicate);
        }
    }
}
