package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.PROJECT;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
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

class TaddCommandTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaddCommand(null, null));
    }

    @Test
    public void execute_taskForOneMemberAcceptedByModel_addSuccessful() throws Exception {
        Index validMemberId = Index.fromOneBased(1);
        Set<Index> validMemberIdList = new HashSet<>();
        validMemberIdList.add(validMemberId);
        Task validTask = new TaskBuilder().build();
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        ModelStubAcceptingTaskAdded modelStub =
                new ModelStubAcceptingTaskAdded(addressBook, validTask, validMemberIdList);
        CommandResult commandResult = new TaddCommand(validMemberIdList, validTask).execute(modelStub);

        assertEquals(String.format(TaddCommand.MESSAGE_SUCCESS, validTask),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_taskForMultipleMembersAcceptedByModel_addSuccessful() throws Exception {
        Index validMemberId1 = Index.fromOneBased(1);
        Index validMemberId2 = Index.fromOneBased(2);
        Set<Index> validMemberIdList = new HashSet<>();
        validMemberIdList.add(validMemberId1);
        validMemberIdList.add(validMemberId2);
        Task validTask = new TaskBuilder().build();
        Member validMember1 = new MemberBuilder().build();
        Member validMember2 = new MemberBuilder()
                .withName("Amy").withEmail("amy@fakemail")
                .withPhone("12312312").withAddress("Block23 St Andrew Street").build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember1).build();
        addressBook.addMember(validMember2);
        ModelStubAcceptingTaskAdded modelStub =
                new ModelStubAcceptingTaskAdded(addressBook, validTask, validMemberIdList);
        CommandResult commandResult = new TaddCommand(validMemberIdList, validTask).execute(modelStub);

        assertEquals(String.format(TaddCommand.MESSAGE_SUCCESS, validTask),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_taskForMultipleMembersWithOneDuplicate_throwsCommandException() {
        Index validMemberId1 = Index.fromOneBased(1);
        Index validMemberId2 = Index.fromOneBased(2);
        Set<Index> validMemberIdList = new HashSet<>();
        validMemberIdList.add(validMemberId1);
        validMemberIdList.add(validMemberId2);
        Task validTask = new TaskBuilder().build();
        Member validMember1 = new MemberBuilder().build();
        Member validMember2 = new MemberBuilder()
                .withName("Amy").withEmail("amy@fakemail")
                .withPhone("12312312").withAddress("Block23 St Andrew Street").build();
        TaskList taskList = new TaskList();
        taskList.add(validTask);
        validMember1.setTaskList(taskList);
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember1).build();
        addressBook.addMember(validMember2);
        ModelStubAcceptingTaskAdded modelStub =
                new ModelStubAcceptingTaskAdded(addressBook, validTask, validMemberIdList);
        assertThrows(CommandException.class,
            String.format(Messages.MESSAGE_DUPLICATE_TASK, validMember1.getName().toString()), () ->
            new TaddCommand(validMemberIdList, validTask).execute(modelStub));
    }

    @Test
    public void equals() {
        Index validMemberId = Index.fromOneBased(1);
        Set<Index> validMemberIdList = new HashSet<>();
        validMemberIdList.add(validMemberId);
        Task validTask1 = new TaskBuilder().build();
        Task validTask2 = new TaskBuilder(PROJECT).build();
        Member validMember = new MemberBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withMember(validMember).build();
        TaddCommand addHomeworkCommand = new TaddCommand(validMemberIdList, validTask1);
        TaddCommand addPoemCommand = new TaddCommand(validMemberIdList, validTask2);

        // same object -> returns true
        assertTrue(addHomeworkCommand.equals(addHomeworkCommand));

        // same values -> returns true
        TaddCommand addHomeworkCommandCopy = new TaddCommand(validMemberIdList, validTask1);
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
     * A Model stub that always accept the member being added.
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        private final AddressBook addressBook;
        private final Set<Member> members = new HashSet<>();
        private final Task task;
        private TaskList taskListManager;
        private final FilteredList<Member> filteredMembers;


        ModelStubAcceptingTaskAdded(ReadOnlyAddressBook addressBook, Task task, Set<Index> memberIdList) {
            this.addressBook = new AddressBook(addressBook);
            requireNonNull(memberIdList);
            this.filteredMembers = new FilteredList<>(this.addressBook.getMemberList());
            for (Index memberId: memberIdList) {
                this.members.add(filteredMembers.get(memberId.getZeroBased()));
            }

            requireNonNull(task);
            this.task = task;
            this.taskListManager = new TaskList();
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
            }
        }
    }
}
