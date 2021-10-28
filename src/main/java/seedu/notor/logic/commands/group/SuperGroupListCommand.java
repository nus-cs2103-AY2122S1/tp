package seedu.notor.logic.commands.group;

import seedu.notor.logic.commands.Command;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.notor.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Lists all supergroups in Notor to the user.
 */
public class SuperGroupListCommand implements Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all groups";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGroupList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
