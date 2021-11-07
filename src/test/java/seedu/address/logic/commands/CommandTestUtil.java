package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupContainsKeywordsPredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_EMAIL_AMY = "amy@u.nus.edu";
    public static final String VALID_EMAIL_BOB = "bob@u.nus.edu";
    public static final String VALID_STUDENTNUMBER_AMY = "A1112223B";
    public static final String VALID_STUDENTNUMBER_BOB = "C1102923T";
    public static final String VALID_USERNAME_AMY = "ai_mee";
    public static final String VALID_USERNAME_BOB = "bob-the-builder";
    public static final String VALID_REPONAME_AMY = "aimee.repo";
    public static final String VALID_REPONAME_BOB = "bob_repo";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_DESCRIPTION_FUN = "fun";
    public static final String VALID_DESCRIPTION_BORING = "boring";
    public static final String VALID_GROUPNAME_G1 = "w14-4";
    public static final String VALID_GROUPNAME_G2 = "f01-3";
    public static final String VALID_YEAR_G1 = "AY20212022";
    public static final String VALID_YEAR_G2 = "AY20222023";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;

    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String DESCRIPTION_DESC_FUN = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_FUN;
    public static final String DESCRIPTION_DESC_BORING = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BORING;
    public static final String STUDENTNUMBER_DESC_AMY = " " + PREFIX_STUDENTNUMBER + VALID_STUDENTNUMBER_AMY;
    public static final String STUDENTNUMBER_DESC_BOB = " " + PREFIX_STUDENTNUMBER + VALID_STUDENTNUMBER_BOB;
    public static final String USERNAME_DESC_AMY = " " + PREFIX_USERNAME + VALID_USERNAME_AMY;
    public static final String USERNAME_DESC_BOB = " " + PREFIX_USERNAME + VALID_USERNAME_BOB;
    public static final String REPONAME_DESC_AMY = " " + PREFIX_REPO + VALID_REPONAME_AMY;
    public static final String REPONAME_DESC_BOB = " " + PREFIX_REPO + VALID_REPONAME_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String VALID_WEEK = " " + PREFIX_WEEK + 3;
    public static final String GROUPNAME_DESC_G1 = " " + PREFIX_GROUP + VALID_GROUPNAME_G1;
    public static final String GROUPNAME_DESC_G2 = " " + PREFIX_GROUP + VALID_GROUPNAME_G2;
    public static final String YEAR_DESC_G1 = " " + PREFIX_YEAR + VALID_YEAR_G1;
    public static final String YEAR_DESC_G2 = " " + PREFIX_YEAR + VALID_YEAR_G2;



    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + "play&"; // &' not allowed in desc
    public static final String INVALID_STUDENTNUMBER_DESC = " " + PREFIX_STUDENTNUMBER + "A1233222";
    // missing final character
    public static final String INVALID_USERNAME_DESC = " " + PREFIX_USERNAME + "a@my"; // '@' not allowed in username
    public static final String INVALID_REPONAME_DESC = " " + PREFIX_REPO + "boB!"; // '!' not allowed in reponame
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_GROUPNAME_DESC = " " + PREFIX_GROUP + "@A1-2"; // '@' not allowed in group name
    public static final String INVALID_YEAR_DESC = " " + PREFIX_YEAR + "2@212022"; // '@' not allowed in year

    public static final String VALID_TASK_NAME_STUDY = "Study";
    public static final String VALID_TASK_NAME_PLAY = "Play";
    public static final String VALID_TASK_DESCRIPTION_STUDY = "Revise for CS2103";
    public static final String VALID_TASK_DESCRIPTION_PLAY = "Play soccer with Alex";
    public static final String VALID_TASK_TAG_STUDY = "study";
    public static final String VALID_TASK_TAG_WORK = "work";
    public static final String VALID_TASK_TAG_FUN = "fun";
    public static final String VALID_TASK_TAG_EXERCISE = "exercise";
    public static final String VALID_TASK_DATE = "2021-10-10";


    public static final String TASK_NAME_DESC_STUDY = " " + PREFIX_NAME + VALID_TASK_NAME_STUDY;
    public static final String TASK_NAME_DESC_PLAY = " " + PREFIX_NAME + VALID_TASK_NAME_PLAY;
    public static final String TASK_DESCRIPTION_DESC_STUDY = " " + PREFIX_DESCRIPTION + VALID_TASK_DESCRIPTION_STUDY;
    public static final String TASK_DESCRIPTION_DESC_PLAY = " " + PREFIX_DESCRIPTION + VALID_TASK_DESCRIPTION_PLAY;
    public static final String TASKNAME_DESC_STUDY = " " + PREFIX_NAME + VALID_TASK_NAME_STUDY;
    public static final String TASKNAME_DESC_PLAY = " " + PREFIX_NAME + VALID_TASK_NAME_PLAY;
    public static final String TASK_TAG_DESC_STUDY = " " + PREFIX_TAG + VALID_TASK_TAG_STUDY;
    public static final String TASK_TAG_DESC_WORK = " " + PREFIX_TAG + VALID_TASK_TAG_WORK;
    public static final String TASK_TAG_DESC_FUN = " " + PREFIX_TAG + VALID_TASK_TAG_FUN;
    public static final String TASK_TAG_DESC_EXERCISE = " " + PREFIX_TAG + VALID_TASK_TAG_EXERCISE;
    public static final String TASK_DEADLINE_DESC = " " + PREFIX_DEADLINE + VALID_TASK_DATE;
    public static final String TASK_EVENTDATE_DESC = " " + PREFIX_EVENT + VALID_TASK_DATE;

    public static final String INVALID_TASK_NAME_DESC = " " + PREFIX_NAME + "Study&";
    public static final String INVALID_TASK_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + "&";
    public static final String INVALID_TASK_TAG_DESC = " " + PREFIX_TAG + "fun*";
    public static final String INVALID_TASK_DEADLINE_DESC = " " + PREFIX_DEADLINE + "2020-02-02$";
    public static final String INVALID_TASK_EVENTDATE_DESC = " " + PREFIX_EVENT + "2020-02-02$";


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStudentCommand.EditStudentDescriptor DESC_AMY;
    public static final EditStudentCommand.EditStudentDescriptor DESC_BOB;

    public static final EditTaskCommand.EditTaskDescriptor DESC_STUDY;
    public static final EditTaskCommand.EditTaskDescriptor DESC_PLAY;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).withEmail(VALID_EMAIL_AMY)
                .withStudentNumber(VALID_STUDENTNUMBER_AMY).withUserName(VALID_USERNAME_AMY)
                .withRepoName(VALID_REPONAME_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).withEmail(VALID_EMAIL_BOB)
                .withStudentNumber(VALID_STUDENTNUMBER_BOB).withUserName(VALID_USERNAME_BOB)
                .withRepoName(VALID_REPONAME_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_STUDY = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_STUDY).withTags(VALID_TAG_FRIEND).build();
        DESC_PLAY = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_PLAY).withTags(VALID_TAG_HUSBAND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException | ParseException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertTaskCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(List.of(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().taskName.split("\\s+");
        model.updateFilteredTaskList(new TaskNameContainsKeywordsPredicate(List.of(splitName[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the groups at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showGroupAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredGroupList().size());

        Group group = model.getFilteredGroupList().get(targetIndex.getZeroBased());
        final String[] splitName = group.getName().name.split("\\s+");
        model.updateFilteredGroupList(new GroupContainsKeywordsPredicate(List.of(splitName[0])));

        assertEquals(1, model.getFilteredGroupList().size());
    }

    /**
     * A default model stub that has all methods failing.
     */
    public static class ModelStub implements Model {
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
        public void addMember(Student target, Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getAllStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
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
        public ObservableList<Group> getAllGroupList() {
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
        public void updateFilteredGroupList(Predicate<Group> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single task.
     */
    public static class ModelStubWithTask extends ModelStub {
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
    public static class ModelStubAcceptingTaskAdded extends ModelStub {
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

    /**
     * A Model stub that contains a single group.
     */
    public static class ModelStubWithGroup extends ModelStub {
        private final Group group;

        ModelStubWithGroup(Group group) {
            requireNonNull(group);
            this.group = group;
        }

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.isSameGroup(group);
        }
    }

    /**
     * A Model stub that always accept the group being added.
     */
    public static class ModelStubAcceptingGroupAdded extends ModelStub {
        final ArrayList<Group> groupsAdded = new ArrayList<>();

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return groupsAdded.stream().anyMatch(group::isSameGroup);
        }

        @Override
        public void addGroup(Group group) {
            requireNonNull(group);
            groupsAdded.add(group);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A Model stub that contains a single student.
     */
    public static class ModelStubWithStudent extends ModelStub {
        private final Student student;

        ModelStubWithStudent(Student student) {
            requireNonNull(student);
            this.student = student;
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return this.student.isSameStudent(student);
        }
    }

    /**
     * A Model stub that always accept the student being added.
     */
    public static class ModelStubAcceptingStudentAdded extends ModelStub {
        final ArrayList<Student> studentsAdded = new ArrayList<>();

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return studentsAdded.stream().anyMatch(student::isSameStudent);
        }

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            studentsAdded.add(student);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
