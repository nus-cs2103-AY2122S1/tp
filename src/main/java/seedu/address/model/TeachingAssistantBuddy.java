package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.UniqueStudentList;
import seedu.address.model.module.student.exceptions.StudentNotFoundException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/**
 * Wraps all data at the TAB level.
 * Duplicates are not allowed (by .isSameModule, .isSameStudent, and .isSameTask comparison)
 */
public class TeachingAssistantBuddy implements ReadOnlyTeachingAssistantBuddy {


    private final UniqueModuleList modules;
    private final UniqueStudentList students;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        modules = new UniqueModuleList();
        students = new UniqueStudentList();
    }

    public TeachingAssistantBuddy() {}

    /**
     * Creates an TeachingAssistantBuddy using the TAB information in the {@code toBeCopied}
     * */
    public TeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the module list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code TeachingAssistantBuddy} with {@code newData}.
     */
    public void resetData(ReadOnlyTeachingAssistantBuddy newData) {
        requireNonNull(newData);
        setStudents(newData.getStudentList());
        setModules(newData.getModuleList());
    }

    //// student-level operations

    /**
     * Returns true if a module with the same identity as {@code module} exists in TAB.
     */
    public boolean hasModuleName(ModuleName module) {
        requireNonNull(module);
        for (Module m : modules) {
            if (m.getName().equals(module)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a module with the same identity as {@code module} exists in TAB.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Returns true if a student with the same identity as {@code student} exists in TAB.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
    * Returns true if the given module has the given task {@code task}.
    */
    public boolean hasTask(ModuleName moduleName, Task task) {
        requireAllNonNull(moduleName, task);
        for (Module m : modules) {
            if (m.getName().equals(moduleName)) {
                return m.hasTask(task);
            }
        }
        return false;
    }

    /**
     * Returns true if the given task is marked as complete, else false.
     */
    public boolean isDone(ModuleName moduleName, StudentId studentId, TaskId taskId) {
        requireAllNonNull(moduleName, studentId, taskId);

        Module module = findModule(moduleName, modules);

        UniqueStudentList studentList = module.getUniqueStudentList();

        Student student = findStudent(studentId, studentList);

        UniqueTaskList taskList = student.getTaskList();

        Task task = findTask(taskId, taskList);

        return task.isComplete();
    }

    /**
     * A helper method that finds a module from a UniqueModuleList modules according to module name.
     */
    public Module findModule(ModuleName moduleName, UniqueModuleList moduleList) throws ModuleNotFoundException {
        for (Module module : moduleList) {
            if (module.getName().equals(moduleName)) {
                return module;
            }
        }
        throw new ModuleNotFoundException();
    }

    /**
     * A helper method that finds a student from a UniqueStudentList students according to student ID.
     */
    public Student findStudent(StudentId studentId, UniqueStudentList studentList) throws StudentNotFoundException {
        for (Student student : studentList) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        throw new StudentNotFoundException();
    }

    /**
     * A helper method that finds a task from a UniqueTaskList according to task ID.
     */
    public Task findTask(TaskId taskId, UniqueTaskList taskList) throws TaskNotFoundException {
        for (Task task : taskList) {
            if (task.getTaskId().equals(taskId)) {
                return task;
            }
        }
        throw new TaskNotFoundException();
    }

    /**
     * Adds a module to TAB.
     * The module must not already exist in TAB.
     */
    public void addModule(Module module) {
        modules.add(module);
    }

    /**
     * Adds a student to TAB.
     * The student must not already exist in TAB.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
    * Adds a task to a module in TAB.
    * The task must not already exist in TAB.
    */
    public void addTask(ModuleName moduleName, Task task) {
        for (Module m : modules) {
            if (m.getName().equals(moduleName)) {
                m.addTask(task);
            }
        }
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in TAB.
     * The module identity of {@code editedModule} must not be the same
     * as another existing module in TAB.
     */
    public void setModule(Module target, Module editedModule) {
        requireNonNull(editedModule);
        modules.setModule(target, editedModule);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in TAB.
     * The student identity of {@code editedStudent} must not be the same
     * as another existing student in TAB.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code TeachingAssistantBuddy}.
     * {@code key} must exist in TAB.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    /**
     * Removes {@code key} from this {@code TeachingAssistantBuddy}.
     * {@code key} must exist in TAB.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeachingAssistantBuddy // instanceof handles nulls
                && students.equals(((TeachingAssistantBuddy) other).students)
                && modules.equals(((TeachingAssistantBuddy) other).modules));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
