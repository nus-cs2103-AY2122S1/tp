package dash.logic.commands.taskcommand;

import static java.util.Objects.requireNonNull;

import dash.commons.core.Messages;
import dash.logic.commands.Command;
import dash.logic.commands.CommandResult;
import dash.model.Model;
import dash.model.task.TaskDateAfterCurrentDatePredicate;

public class UpcomingTaskCommand extends Command {
    public static final String COMMAND_WORD = "upcoming";

    public static final String MESSAGE_SUCCESS = "Listed all upcoming tasks.";

    private final TaskDateAfterCurrentDatePredicate predicate = new TaskDateAfterCurrentDatePredicate();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortTaskList();
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }
}
