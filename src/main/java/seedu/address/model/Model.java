package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

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
     * Returns the user prefs' assistant buddy file path.
     */
    Path getAssistantBuddyFilePath();

    /**
     * Sets the user prefs' assistant buddy file path.
     */
    void setAssistantBuddyFilePath(Path assistantBuddyFilePath);

    /**
     * Replaces assistant buddy data with the data in {@code assistantBuddy}.
     */
    void setBuddy(ReadOnlyTeachingAssistantBuddy assistantBuddy);

    /** Returns the TeachingAssistantBuddy */
    ReadOnlyTeachingAssistantBuddy getBuddy();

    /**
     * Deletes the given module.
     * The module must exist in the TAB.
     */
    void deleteModule(Module moduleToDelete);

    boolean hasModuleName(ModuleName moduleToCheck);

    /**
     * Returns true if a student with the same identity as {@code student} exists in the TAB.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the TAB.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the TAB.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the TAB.
     * The student identity of {@code editedStudent} must not be the same
     * as another existing student in the TAB.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    void updateFilteredModuleList(Predicate<Module> predicate);

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Adds a task to the given module.
     * {@code task} must not already exist in the module.
     * @param moduleName The module to receive the task.
     * @param task The task to be added.
     */
    void addTask(ModuleName moduleName, Task task);

    /**
     * Returns true if the module with the given name has the task.
     * @param moduleName The given module name.
     * @param task The task to check.
     * @return Whether the given module has the task or not.
     */
    boolean hasTask(ModuleName moduleName, Task task);

    boolean hasModule(Module module);

    void addModule(Module module);

    void setModule(Module target, Module moduleToEdit);

    /**
     * Checks if the given task is done.
     * @param moduleName The name of the module of the given task.
     * @param studentId The ID of the student the given task belongs to.
     * @param taskId The ID of the given task.
     * @return Whether the task is done or not.
     */
    boolean isDone(ModuleName moduleName, StudentId studentId, TaskId taskId);
}
