package dash.logic.commands.taskcommand;

import static java.util.Objects.requireNonNull;

import dash.logic.commands.Command;
import dash.logic.commands.CommandResult;
import dash.logic.commands.exceptions.CommandException;
import dash.model.Model;

public class ClearDoneTaskCommand extends Command {

    public static final String COMMAND_WORD = "cleardone";

    public static final String MESSAGE_SUCCESS = "Task List has been cleared of all completed tasks!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.deleteDoneTasks();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        return (other instanceof ClearDoneTaskCommand);
    }
}
