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
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task of a student as done.\n"
            + "Parameters: "
            + PREFIX_MODULE_NAME + "MODULE NAME "
            + PREFIX_STUDENT_ID + "STUDENT ID "
            + PREFIX_TASK_ID + "TASK ID "
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
        if (model.isDone(moduleName, studentId, taskId)) {
            throw new CommandException(MESSAGE_IS_ALREADY_DONE);
        }

        List<Module> lastShownList = model.getFilteredModuleList();

        for (Module module : lastShownList) {
            if (module.getName().equals(moduleName)) {
                List<Student> studentList = module.getFilteredStudentList();
                for (Student student : studentList) {
                    if (student.getStudentId().equals(studentId)) {
                        setTaskAsComplete(student, taskId);
                    }
                }
            }
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, taskId, studentId));
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
