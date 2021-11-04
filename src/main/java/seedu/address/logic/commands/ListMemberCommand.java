package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import seedu.address.model.Model;

/**
 * Lists all members in the address book to the user.
 */
public class ListMemberCommand extends Command {

    public static final String COMMAND_WORD = "listm";

    public static final String MESSAGE_SUCCESS = "Listed all members";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
