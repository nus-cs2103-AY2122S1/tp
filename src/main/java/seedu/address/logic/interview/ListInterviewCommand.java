package seedu.address.logic.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;

import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all interviews in the HR Manager to the user.
 */
public class ListInterviewCommand extends Command {
    public static final String COMMAND_WORD = "list_i";

    public static final String MESSAGE_SUCCESS = "Listed all interviews";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.CommandType.LIST_I);
    }
}
