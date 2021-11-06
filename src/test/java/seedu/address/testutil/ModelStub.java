package seedu.address.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyCsBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Name;
import seedu.address.model.student.Note;
import seedu.address.model.student.Student;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
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
