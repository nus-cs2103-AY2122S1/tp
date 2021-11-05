package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_TASK_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ID;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Student;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;

/**
 * Deletes a task identified using it's displayed index from a module in TAB.
 */
public class DeleteTaskCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "delete task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task from the module identified by the task ID used in the displayed task list.\n"
            + "Parameters: "
            + PREFIX_MODULE_NAME + "MODULE NAME "
            + PREFIX_TASK_ID + "TASK ID\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE_NAME + "CS2103 "
            + PREFIX_TASK_ID + "T1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private static Logger logger = Logger.getLogger("Delete Task Logger");

    private final TaskId targetTaskId;
    private final ModuleName moduleName;

    /**
     * Deletes a task identified using its task ID from a module in TAB.
     *
     * @param targetTaskId The task ID of the task to be deleted.
     * @param moduleName The name of the module that the task will be deleted from.
     */
    public DeleteTaskCommand(TaskId targetTaskId, ModuleName moduleName) {
        requireAllNonNull(targetTaskId, moduleName);
        this.targetTaskId = targetTaskId;
        this.moduleName = moduleName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
        for (Module module : lastShownList) {
            if (module.getName().equals(moduleName)) {
                List<Student> studentList = module.getStudentList();
                for (Student student : studentList) {
                    deleteTaskFromStudent(student);
                }
                deleteTaskFromModule(module);
                return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, targetTaskId));
            }
        }
        throw new CommandException(String.format(Messages.MESSAGE_MODULE_NAME_NOT_FOUND, moduleName));
    }

    /**
     * Deletes task from student's taskList.
     *
     * @param student The student with the task to be deleted.
     * @throws CommandException if the task is not found in the student's taskList.
     */
    public void deleteTaskFromStudent(Student student) throws CommandException {
        for (Task task : student.getTaskList()) {
            if (task.getTaskId().equals(targetTaskId)) {
                logger.log(Level.INFO, "deleting task from student's taskList: " + task.getTaskId());
                student.removeTask(task);
                return;
            }
        }
        throw new CommandException(String.format(MESSAGE_TASK_NOT_FOUND, targetTaskId));
    }

    /**
     * Deletes task from module's taskList.
     *
     * @param module The module with the task to be deleted.
     */
    public void deleteTaskFromModule(Module module) {
        for (Task task : module.getTaskList()) {
            if (task.getTaskId().equals(targetTaskId)) {
                logger.log(Level.INFO, "deleting task from module taskList: " + task.getTaskId());
                module.deleteTask(task);
                return;
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetTaskId.equals(((DeleteTaskCommand) other).targetTaskId)); // state check
    }
}
