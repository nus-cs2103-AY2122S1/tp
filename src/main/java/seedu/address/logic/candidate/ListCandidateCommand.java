package seedu.address.logic.candidate;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all candidates in the HR Manager to the user.
 */
public class ListCandidateCommand extends Command {

    public static final String COMMAND_WORD = "list_c";

    public static final String MESSAGE_SUCCESS = "Listed all candidates";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.CommandType.LIST_C);
    }
}
