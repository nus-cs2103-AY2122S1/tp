package seedu.address.logic.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_POSITIONS;

import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all positions in the HR Manager to the user.
 */
public class ListPositionCommand extends Command {

    public static final String COMMAND_WORD = "list_p";

    public static final String MESSAGE_SUCCESS = "Listed all positions";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPositionList(PREDICATE_SHOW_ALL_POSITIONS);
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.CommandType.LIST_P);
    }
}
