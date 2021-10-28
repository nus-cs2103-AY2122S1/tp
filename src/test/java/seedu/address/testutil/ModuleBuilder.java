package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Student;
import seedu.address.model.task.Task;

public class ModuleBuilder {
    public static final String DEFAULT_NAME = "CS2103";
    public static final List<Student> DEFAULT_STUDENTS = new ArrayList<>();
    public static final List<Task> DEFAULT_TASKS = new ArrayList<>();

    private ModuleName moduleName;
    private List<Student> students;
    private List<Task> tasks;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleName = new ModuleName(DEFAULT_NAME);
        students = DEFAULT_STUDENTS;
        tasks = DEFAULT_TASKS;
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleName = moduleToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.moduleName = new ModuleName(name);
        return this;
    }

    /**
     * Sets the {@code students} of the {@code Module} that we are building.
     */
    public ModuleBuilder withStudents(List<Student> students) {
        this.students = students;
        return this;
    }

    /**
     * Sets the {@code tasks} of the {@code Module} that we are building.
     */
    public ModuleBuilder withTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    /**
     * Build a typical module.
     * @return A {@code Module} Object.
     */
    public Module build() {
        Module module = new Module(moduleName);
        module.setStudents(students);
        for (Task task : tasks) {
            module.addTask(task);
        }
        return module;
    }
}
