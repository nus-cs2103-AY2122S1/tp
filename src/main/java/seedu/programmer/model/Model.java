package seedu.programmer.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.model.student.LabResult;
import seedu.programmer.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' ProgrammerError file path.
     */
    Path getProgrammerErrorFilePath();

    /**
     * Sets the user prefs' ProgrammerError file path.
     */
    void setProgrammerErrorFilePath(Path programmerErrorFilePath);

    /**
     * Replaces ProgrammerError data with the data in {@code programmerError}.
     */
    void setProgrammerError(ReadOnlyProgrammerError programmerError);

    /** Returns the ProgrammerError */
    ReadOnlyProgrammerError getProgrammerError();

    /**
     * Returns true if a student with the same identity as {@code student} exists in ProgrammerError.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in ProgrammerError.
     */
    void deleteStudent(Student target);

    /**
     * Shows the given student's lab results.
     * The student must exist in ProgrammerError.
     */
    ObservableList<LabResult> showLabResultList(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the ProgrammerError.
     * */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the ProgrammerError.
     * The student identity of {@code editedStudent} must not be the same as another existing student
     * in the ProgrammerError.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);
}
