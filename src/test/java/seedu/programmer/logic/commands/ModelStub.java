package seedu.programmer.logic.commands;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.ReadOnlyUserPrefs;
import seedu.programmer.model.student.DisplayableObject;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;

/**
 * A default model stub that has all of its methods failing.
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
    public Path getProgrammerErrorFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setProgrammerErrorFilePath(Path programmerErrorFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addStudent(Student student) throws CommandException {
        throw new CommandException(AddCommand.MESSAGE_DUPLICATE_STUDENT_ID);
    }

    @Override
    public void setProgrammerError(ReadOnlyProgrammerError programmerError) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyProgrammerError getProgrammerError() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasStudent(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasSameStudentId(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasSameStudentEmail(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasOtherStudent(Student studentToEdit, Student editedStudent) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasOtherSameStudentId(Student studentToEdit, Student editedStudent) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasOtherSameStudentEmail(Student studentToEdit, Student editedStudent) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasLab(Lab lab) {
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
    public ObservableList<Student> getAllStudents() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasNoStudents() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<DisplayableObject> getSelectedInformation() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Student getSelectedStudent() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSelectedStudent(Student target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSelectedLabs(List<Lab> labs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void clearSelectedInformation() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setLabsTracker(List<Lab> labs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void clearLabsTracker() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
