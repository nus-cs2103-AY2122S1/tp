package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;

/**
 * Represents the in-memory model of the TAB data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TeachingAssistantBuddy teachingAssistantBuddy;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Module> filteredModules;

    /**
     * Initializes a ModelManager with the given teachingAssistantBuddy and userPrefs.
     */
    public ModelManager(ReadOnlyTeachingAssistantBuddy tab, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(tab, userPrefs);

        logger.fine("Initializing with TAB: " + tab + " and user prefs " + userPrefs);
        //The string name is hardcoded for now. Ability to change teachingAssistantBuddy name will come later
        this.teachingAssistantBuddy = new TeachingAssistantBuddy(tab);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.teachingAssistantBuddy.getStudentList());
        filteredModules = new FilteredList<>(this.teachingAssistantBuddy.getModuleList());
    }

    public ModelManager() {
        this(new TeachingAssistantBuddy(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAssistantBuddyFilePath() {
        return userPrefs.getAssistantBuddyFilePath();
    }

    @Override
    public void setAssistantBuddyFilePath(Path assistantBuddyFilePath) {
        requireNonNull(assistantBuddyFilePath);
        userPrefs.setAssistantBuddyFilePath(assistantBuddyFilePath);
    }

    //=========== TeachingAssistantBuddy ==================================================================

    @Override
    public void setBuddy(ReadOnlyTeachingAssistantBuddy module) {
        this.teachingAssistantBuddy.resetData(module);
    }

    @Override
    public ReadOnlyTeachingAssistantBuddy getBuddy() {
        return teachingAssistantBuddy;
    }

    /**
     * Deletes the given module.
     * The module must exist in the TAB.
     *
     * @param target The module to be deleted.
     */
    @Override
    public void deleteModule(Module target) {
        teachingAssistantBuddy.removeModule(target);
    }

    @Override
    public boolean hasModuleName(ModuleName moduleName) {
        requireNonNull(moduleName);
        return teachingAssistantBuddy.hasModuleName(moduleName);
    }

    /**
     * Checks whether the specified student is inside the TAB model.
     *
     * @param student The students to be found.
     * @return True if current teachingAssistantBuddy has specified student.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return teachingAssistantBuddy.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        teachingAssistantBuddy.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        teachingAssistantBuddy.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        teachingAssistantBuddy.setStudent(target, editedStudent);
    }

    @Override
    public void addTask(ModuleName moduleName, Task task) {
        requireAllNonNull(moduleName, task);
        teachingAssistantBuddy.addTask(moduleName, task);
    }

    @Override
    public boolean hasTask(ModuleName moduleName, Task task) {
        requireAllNonNull(moduleName, task);
        return teachingAssistantBuddy.hasTask(moduleName, task);
    }

    @Override
    public boolean isDone(ModuleName moduleName, StudentId studentId, TaskId taskId) {
        requireAllNonNull(moduleName, studentId, taskId);
        return teachingAssistantBuddy.isDone(moduleName, studentId, taskId);
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return teachingAssistantBuddy.hasModule(module);
    }

    @Override
    public void addModule(Module module) {
        requireNonNull(module);
        teachingAssistantBuddy.addModule(module);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);
        teachingAssistantBuddy.setModule(target, editedModule);
    }

    //=========== Filtered List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the filtered student list.
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    /**
     * Returns an unmodifiable view of the filtered module list.
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return teachingAssistantBuddy.equals(other.teachingAssistantBuddy)
                && userPrefs.equals(other.userPrefs)
                && filteredModules.equals(other.filteredModules);
    }

}
