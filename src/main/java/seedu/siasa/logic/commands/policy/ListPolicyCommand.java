package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.core.Messages.MESSAGE_POLICIES_LISTED_OVERVIEW;
import static seedu.siasa.commons.core.Messages.MESSAGE_POLICIES_LIST_EMPTY;
import static seedu.siasa.model.Model.PREDICATE_SHOW_ALL_POLICIES;

import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;

/**
 * Lists all policies in the SIASA to the user.
 */
public class ListPolicyCommand extends Command {

    public static final String COMMAND_WORD = "allpolicy";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPolicyList(PREDICATE_SHOW_ALL_POLICIES);

        if (model.getFilteredPolicyList().size() > 0) {
            return new CommandResult(String.format(MESSAGE_POLICIES_LISTED_OVERVIEW,
                    model.getFilteredPolicyList().size()));
        } else {
            return new CommandResult(MESSAGE_POLICIES_LIST_EMPTY);
        }
    }
}
