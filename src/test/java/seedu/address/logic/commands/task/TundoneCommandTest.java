package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

class TundoneCommandTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TundoneCommand(null));
    }

    @Test
    void execute_markOneTaskAsUndone_markSuccessful() throws Exception {
        Index validMemberId = Index.fromOneBased(1);
        Set<Index> validMemberIdList = new HashSet<>();
        validMemberIdList.add(validMemberId);
        Index validTaskId = Index.fromOneBased(1);
        Set<Index> validTaskIdList = new HashSet<>();
        validTaskIdList.add(validTaskId);
        Task validTask = new TaskBuilder().build();
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TaddCommand tAddCommand = new TaddCommand(validMemberIdList, validTask);
        TlistCommand tlistCommand = new TlistCommand(validMemberId);
        TdoneCommand tdoneCommand = new TdoneCommand(validTaskIdList);
        ModelStubAcceptingWithOneTask modelStub =
                new ModelStubAcceptingWithOneTask(addressBook, validTask, validMemberIdList);
        tAddCommand.execute(modelStub);
        tlistCommand.execute(modelStub);
        tdoneCommand.execute(modelStub);
        CommandResult commandResult = new TundoneCommand(validTaskIdList).execute(modelStub);

        assertEquals(String.format(TundoneCommand.MESSAGE_UNDONE_TASK_SUCCESS, validTask),
                commandResult.getFeedbackToUser());
    }

    @Test
    void execute_markMultipleTasksAsUndone_markSuccessful() throws Exception {
        Index validMemberId = Index.fromOneBased(1);
        Set<Index> validMemberIdList = new HashSet<>();
        validMemberIdList.add(validMemberId);
        Index validTaskId = Index.fromOneBased(1);
        Index validTaskId2 = Index.fromOneBased(2);
        Index validTaskId3 = Index.fromOneBased(3);
        Set<Index> validTaskIdList = new HashSet<>();
        validTaskIdList.add(validTaskId2);
        validTaskIdList.add(validTaskId3);
        validTaskIdList.add(validTaskId);
        Task validTask = new TaskBuilder().build();
        Task validTask2 = new TaskBuilder().withName("Test2").withDeadline("19/02/2022 23:59").build();
        Task validTask3 = new TaskBuilder().withName("Test3").withDeadline("19/02/2022 23:59").build();
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TaddCommand tAddCommand = new TaddCommand(validMemberIdList, validTask);
        TaddCommand tAddCommand2 = new TaddCommand(validMemberIdList, validTask2);
        TaddCommand tAddCommand3 = new TaddCommand(validMemberIdList, validTask3);
        TlistCommand tlistCommand = new TlistCommand(validMemberId);
        TdoneCommand tdoneCommand = new TdoneCommand(validTaskIdList);
        ModelStubAcceptingWithOneTask modelStub =
                new ModelStubAcceptingWithOneTask(addressBook, validTask, validMemberIdList);
        tAddCommand.execute(modelStub);
        tAddCommand2.execute(modelStub);
        tAddCommand3.execute(modelStub);
        tlistCommand.execute(modelStub);
        tdoneCommand.execute(modelStub);

        CommandResult commandResult = new TundoneCommand(validTaskIdList).execute(modelStub);

        String expectedMessage = String.format(TundoneCommand.MESSAGE_UNDONE_TASK_SUCCESS, validTask)
                + String.format(TundoneCommand.MESSAGE_UNDONE_TASK_SUCCESS, validTask2)
                + String.format(TundoneCommand.MESSAGE_UNDONE_TASK_SUCCESS, validTask3);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    void execute_markOneTaskAndItDoesNotExist_throwsCommandException() throws Exception {
        Index validMemberId = Index.fromOneBased(1);
        Set<Index> validMemberIdList = new HashSet<>();
        validMemberIdList.add(validMemberId);
        Index invalidTaskId = Index.fromOneBased(1);
        Set<Index> validTaskIdList = new HashSet<>();
        validTaskIdList.add(invalidTaskId);
        Task validTask = new TaskBuilder().build();
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TlistCommand tlistCommand = new TlistCommand(validMemberId);
        ModelStubAcceptingWithOneTask modelStub =
                new ModelStubAcceptingWithOneTask(addressBook, validTask, validMemberIdList);
        tlistCommand.execute(modelStub);

        assertThrows(CommandException.class,
                String.format(TundoneCommand.MESSAGE_TASK_NOT_FOUND, invalidTaskId.getOneBased()), () ->
                        new TundoneCommand(validTaskIdList).execute(modelStub));

    }

    @Test
    void execute_markMultipleTasksAndOneDoesNotExist_throwsCommandException() throws Exception {
        Index validMemberId = Index.fromOneBased(1);
        Set<Index> validMemberIdList = new HashSet<>();
        validMemberIdList.add(validMemberId);
        Index validTaskId = Index.fromOneBased(1);
        Index validTaskId2 = Index.fromOneBased(2);
        Index invalidTaskId3 = Index.fromOneBased(3);
        Set<Index> taskIdListWithOneInvalidId = new HashSet<>();
        taskIdListWithOneInvalidId.add(validTaskId2);
        taskIdListWithOneInvalidId.add(invalidTaskId3);
        taskIdListWithOneInvalidId.add(validTaskId);
        Task validTask = new TaskBuilder().build();
        Task validTask2 = new TaskBuilder().withName("Test2").withDeadline("19/02/2022 23:59").build();
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TaddCommand tAddCommand = new TaddCommand(validMemberIdList, validTask);
        TaddCommand tAddCommand2 = new TaddCommand(validMemberIdList, validTask2);
        TlistCommand tlistCommand = new TlistCommand(validMemberId);
        ModelStubAcceptingWithOneTask modelStub =
                new ModelStubAcceptingWithOneTask(addressBook, validTask, validMemberIdList);
        tAddCommand.execute(modelStub);
        tAddCommand2.execute(modelStub);
        tlistCommand.execute(modelStub);

        assertThrows(CommandException.class,
                String.format(TundoneCommand.MESSAGE_TASK_NOT_FOUND, invalidTaskId3.getOneBased()), () ->
                        new TundoneCommand(taskIdListWithOneInvalidId).execute(modelStub));
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
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces the task specified by {@code index} with {@code editedTask} in the given {@code member}'s task list.
         */
        @Override
        public void setTask(int index, Task editedTask) {
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
     * A Model stub that always accept the member being added.
     */
    private class ModelStubAcceptingWithOneTask extends ModelStub {
        private final AddressBook addressBook;
        private final Set<Member> members = new HashSet<>();
        private final Task task;
        private TaskList taskListManager;
        private final FilteredList<Member> filteredMembers;
        private FilteredList<Task> filteredTasks;


        ModelStubAcceptingWithOneTask(ReadOnlyAddressBook addressBook, Task task, Set<Index> memberIdList) {
            this.addressBook = new AddressBook(addressBook);
            requireNonNull(memberIdList);
            this.filteredMembers = new FilteredList<>(this.addressBook.getMemberList());
            for (Index memberId: memberIdList) {
                this.members.add(filteredMembers.get(memberId.getZeroBased()));
            }

            requireNonNull(task);
            this.task = task;
            this.taskListManager = new TaskList();
            filteredTasks = new FilteredList<>(this.taskListManager.asUnmodifiableObservableList());
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
        public ReadOnlyAddressBook getAddressBook() {
            return this.addressBook;
        }

        @Override
        public ObservableList<Member> getFilteredMemberList() {
            return filteredMembers;
        }

        @Override
        public void loadTaskList(Member member) {
            requireNonNull(member);
            if (this.taskListManager != member.getTaskList()) {
                this.taskListManager = member.getTaskList();
                this.filteredTasks = new FilteredList<>(this.taskListManager.asUnmodifiableObservableList());

            }
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            taskListManager.setTask(target, editedTask);
        }

        @Override
        public ObservableList<Task> getFilteredTaskList(Member member) {
            loadTaskList(member);
            return filteredTasks;
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return filteredTasks;
        }

        @Override
        public void updateFilteredTaskList(Member member, Predicate<Task> predicate) {
            requireNonNull(predicate);
            loadTaskList(member);
            filteredTasks.setPredicate(predicate);
        }
    }
}
