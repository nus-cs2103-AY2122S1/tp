package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_TASK_HISTORY;

import seedu.address.model.Model;

public class ListTaskHistoryCommand extends Command {
    public static final String COMMAND_WORD = "taskHistory";

    public static final String MESSAGE_SUCCESS = "Listed recent five task change logs";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskHistoryList(PREDICATE_SHOW_TASK_HISTORY);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
