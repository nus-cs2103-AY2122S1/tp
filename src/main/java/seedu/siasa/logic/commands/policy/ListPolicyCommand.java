package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.model.Model.PREDICATE_SHOW_ALL_POLICIES;

import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListPolicyCommand extends Command {

    public static final String COMMAND_WORD = "listpolicy";

    public static final String MESSAGE_SUCCESS = "Listed all policies";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPolicyList(PREDICATE_SHOW_ALL_POLICIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
