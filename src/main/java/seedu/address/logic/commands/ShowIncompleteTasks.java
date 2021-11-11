package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_INCOMPLETE_TASKS;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Lists all the incomplete tasks.
 */
public class ShowIncompleteTasks extends Command {

    public static final String COMMAND_WORD = "incompletetasks";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_INCOMPLETE_TASKS);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()),
                CommandResult.DisplayState.TASK);
    }
}
