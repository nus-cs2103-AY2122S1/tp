package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyCsBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Name;
import seedu.address.model.student.Note;
import seedu.address.model.student.Student;
import seedu.address.testutil.GroupBuilder;

public class AddGroupCommandTest {

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupCommand(null));
    }

    @Test
    public void execute_validGroup_addSuccessful() throws Exception {
        ModelStubAcceptingGroupsAdded modelStub = new ModelStubAcceptingGroupsAdded();
        Group validGroup = new GroupBuilder().build();
        CommandResult commandResult = new AddGroupCommand(validGroup).execute(modelStub);

        assertEquals(String.format(AddGroupCommand.MESSAGE_SUCCESS, validGroup), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validGroup), modelStub.groupsAdded);

    }

    @Test
    public void execute_duplicateGroup_throwsCommandException() throws Exception {
        Group validGroup = new GroupBuilder().build();
        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroup);
        AddGroupCommand addGroupCommand = new AddGroupCommand(validGroup);

        assertThrows(CommandException.class,
                AddGroupCommand.MESSAGE_DUPLICATE_GROUP, () -> addGroupCommand.execute(modelStub));
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
        public Path getCsBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCsBookFilePath(Path csBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Student getStudentByName(Name studentName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCsBook(ReadOnlyCsBook csBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCsBook getCsBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void changeStudentGroup(Student student, Group newGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Student updateStudentNote(Student student, Note note) {
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
        public ObservableList<Student> getFilteredStudentList() {
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
        public boolean hasGroup(GroupName groupName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateGroupStudent(Group group, Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGroup(Group target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group getGroupByGroupName(GroupName groupName) {
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
        public boolean hasAssessment(Student student, Assessment assessment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAssessment(Student student, Assessment assessment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAssessment(Student student, Assessment assessment) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single student.
     */
    private class ModelStubWithGroup extends ModelStub {
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
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingGroupsAdded extends ModelStub {
        final ArrayList<Group> groupsAdded = new ArrayList<>();

        @Override
        public boolean hasGroup(Group group) {
            return groupsAdded.contains(group);
        }

        @Override
        public void addGroup(Group group) {
            groupsAdded.add(group);
        }

    }
}
