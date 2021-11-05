package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ID;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.exceptions.StudentNotFoundException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.UniqueTaskList;

/**
 * Marks a task of a student as done.
 */
public class MarkTaskDoneCommand extends MarkTaskCommand {

    public static final String COMMAND_WORD = "mark done";

    public static final String MESSAGE_MODULE_NOT_FOUND = "This module is not found";

    public static final String MESSAGE_STUDENT_NOT_FOUND = "This student is not found";

    public static final String MESSAGE_TASK_NOT_FOUND = "This task is not found";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task of a student as done.\n"
            + "Parameters: "
            + PREFIX_MODULE_NAME + "MODULE NAME "
            + PREFIX_STUDENT_ID + "STUDENT ID "
            + PREFIX_TASK_ID + "TASK ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_NAME + "CS2103 "
            + PREFIX_STUDENT_ID + "A0123456X "
            + PREFIX_TASK_ID + "T1";

    public static final String MESSAGE_SUCCESS = "Task %1$s of student %2$s has been marked as done.";
    public static final String MESSAGE_IS_ALREADY_DONE = "This task has already been marked as done.";

    private final ModuleName moduleName;
    private final StudentId studentId;
    private final TaskId taskId;

    /**
     * Creates a MarkTaskDoneCommand.
     *
     * @param moduleName The name of the module the task belongs to.
     * @param studentId The ID of the student the task is assigned to.
     * @param taskId The ID of the task to be marked as done.
     */
    public MarkTaskDoneCommand(ModuleName moduleName, StudentId studentId, TaskId taskId) {
        requireAllNonNull(moduleName, studentId, taskId);
        this.moduleName = moduleName;
        this.studentId = studentId;
        this.taskId = taskId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Module> lastShownList = model.getFilteredModuleList();

        if (!isPresentModule(moduleName, lastShownList)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        Module module = findModule(moduleName, lastShownList);
        List<Student> studentList = module.getFilteredStudentList();

        if (!isPresentStudent(studentId, studentList)) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        }

        Student student = findStudent(studentId, studentList);
        UniqueTaskList taskList = student.getTaskList();

        if (!isPresentTask(taskId, taskList)) {
            throw new CommandException(MESSAGE_TASK_NOT_FOUND);
        }

        if (model.isDone(moduleName, studentId, taskId)) {
            throw new CommandException(MESSAGE_IS_ALREADY_DONE);
        }

        setTaskAsComplete(student, taskId);

        return new CommandResult(String.format(MESSAGE_SUCCESS, taskId, studentId));
    }

    /**
     * A helper method that checks if a list of modules contains a module with a specified name.
     *
     * @param moduleName The name of the module to be searched.
     * @param moduleList The list of modules that may potentially contain the specified module.
     * @return A boolean stating whether the module is present in the module list.
     */
    public boolean isPresentModule(ModuleName moduleName, List<Module> moduleList) {
        for (Module module : moduleList) {
            if (module.getName().equals(moduleName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * A helper method that checks if a list of students contains a student with a specified student ID.
     *
     * @param studentId The student ID of the student to be searched.
     * @param studentList The list of students that may potentially contain the specified student.
     * @return A boolean stating whether the student is present in the student list.
     */
    public boolean isPresentStudent(StudentId studentId, List<Student> studentList) {
        for (Student student : studentList) {
            if (student.getStudentId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * A helper method that checks if a UniqueTaskList contains a task with a specified task ID.
     *
     * @param taskId The task ID of the task to be searched.
     * @param taskList The list of the tasks that may potentially contain the specified task.
     * @return A boolean stating whether the task is present in the task list.
     */
    public boolean isPresentTask(TaskId taskId, UniqueTaskList taskList) {
        for (Task task : taskList) {
            if (task.getTaskId().equals(taskId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * A helper method that finds a module from a list of modules according to module name.
     *
     * @param moduleName The name of the module to be found.
     * @param moduleList The list of modules that may potentially contain the specified module.
     * @return The module to be found.
     * @throws ModuleNotFoundException if the module is not found.
     */
    public Module findModule(ModuleName moduleName, List<Module> moduleList) throws ModuleNotFoundException {
        for (Module module : moduleList) {
            if (module.getName().equals(moduleName)) {
                return module;
            }
        }
        throw new ModuleNotFoundException();
    }

    /**
     * A helper method that finds a student from a list of students according to student ID.
     *
     * @param studentId The student ID of the student to be found.
     * @param studentList The list of students that may potentially contain the specified student.
     * @return The student to be found.
     * @throws StudentNotFoundException if the student is not found.
     */
    public Student findStudent(StudentId studentId, List<Student> studentList) throws StudentNotFoundException {
        for (Student student : studentList) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        throw new StudentNotFoundException();
    }

    public void setTaskAsComplete(Student student, TaskId taskId) {
        UniqueTaskList taskList = student.getTaskList();
        for (Task t : taskList) {
            if (t.getTaskId().equals(taskId)) {
                ModuleName moduleName = t.getTaskModuleName();
                TaskName taskName = t.getTaskName();
                TaskDeadline taskDeadline = t.getTaskDeadline();
                Task newTask = new Task(moduleName, taskId, taskName, taskDeadline, true);
                taskList.setTask(t, newTask);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkTaskDoneCommand // instanceof handles nulls
                && studentId.equals(((MarkTaskDoneCommand) other).studentId)
                && moduleName.equals(((MarkTaskDoneCommand) other).moduleName))
                && taskId.equals(((MarkTaskDoneCommand) other).taskId);
    }
}
