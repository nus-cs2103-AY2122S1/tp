package seedu.siasa.logic.commands.client;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.siasa.commons.core.Messages;
import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.model.Model;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.PolicyIsOwnedByPredicate;

/**
 * Deletes a client's policies identified using it's displayed index from the client list.
 */
public class ClearClientPolicyCommand extends Command {

    public static final String COMMAND_WORD = "clearpolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the policies belonging to a client as given by the "
            + "index number used in the displayed user list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CLEAR_CLIENT_POLICY_SUCCESS = "Deleted %1$s policies belonging to client %1$s";

    private final Index targetIndex;

    public ClearClientPolicyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person owner = lastShownList.get(targetIndex.getZeroBased());

        model.updateFilteredPolicyList(new PolicyIsOwnedByPredicate(owner));

        List<Policy> policyList = model.getFilteredPolicyList();

        int deletedPolicies = policyList.size();

        for (int i = deletedPolicies - 1; i >= 0; i--) {
            Policy toDelete = policyList.get(i);
            model.deletePolicy(toDelete);
        }

        // Remove filter to display policies correctly in UI
        model.updateFilteredPolicyList(x -> true);

        return new CommandResult(
                String.format(MESSAGE_CLEAR_CLIENT_POLICY_SUCCESS,
                        deletedPolicies,
                        targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClearClientPolicyCommand // instanceof handles nulls
                && targetIndex.equals(((ClearClientPolicyCommand) other).targetIndex)); // state check
    }
}
