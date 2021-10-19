package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_NON_CALLED;

import seedu.address.model.Model;

/**
 * Lists all persons who have not been called in the current session.
 */
public class ScheduleCommand extends Command {
    public static final String COMMAND_WORD = "schedule";
    public static final String MESSAGE_SUCCESS = "Listed all persons who have not been called in the current session.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_NON_CALLED);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
