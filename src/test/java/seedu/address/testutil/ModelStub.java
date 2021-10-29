package seedu.address.testutil;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyClassmate;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;


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
    public Path getClassmateFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setClassmateFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addStudent(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addTutorialClass(TutorialClass tutorialClass) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addTutorialGroup(TutorialGroup tutorialGroup) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortTutorialGroups() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setClassmate(ReadOnlyClassmate newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyClassmate getClassmate() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasStudent(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTutorialClass(TutorialClass tutorialClass) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteStudent(Student target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTutorialClass(TutorialClass target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTutorialGroup(TutorialGroup target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Student> getUnfilteredStudentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<TutorialClass> getFilteredTutorialClassList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateUnfilteredStudentList(List<Student> students) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTutorialClassList(Predicate<TutorialClass> predicate) {
        throw new AssertionError("This method should not be called.");
    }

}
