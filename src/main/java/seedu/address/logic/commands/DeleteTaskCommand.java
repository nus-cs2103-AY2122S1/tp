package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the address book.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "delete task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task from the module identified by the index number used in the displayed task list.\n"
            + "Parameters: "
            + PREFIX_MODULE_NAME + "MODULE NAME "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE_NAME + "CS2103 " + "1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private final Index targetIndex;
    private final ModuleName moduleName;

    /**
     *
     */
    public DeleteTaskCommand(Index targetIndex, ModuleName moduleName) {
        this.targetIndex = targetIndex;
        this.moduleName = moduleName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        for (Module module : lastShownList) {
            if (module.getName().equals(moduleName)) {
                List<Task> taskList = module.getTaskList().asModifiableObservableList();
                Task taskToDelete = taskList.get(targetIndex.getZeroBased());
                module.deleteTask(taskToDelete);
                return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
            }
        }
        throw new CommandException(Messages.MESSAGE_MODULE_NAME_NOT_FOUND);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaskCommand) other).targetIndex)); // state check
    }
}
