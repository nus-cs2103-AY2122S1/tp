package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.siasa.commons.core.Messages;
import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;
import seedu.siasa.model.policy.Policy;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeletePolicyCommand extends Command {

    public static final String COMMAND_WORD = "deletepolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the policy identified by the index number used in the displayed policy list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_POLICY_SUCCESS = "Deleted Policy: %1$s";

    private final Index targetIndex;

    public DeletePolicyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Policy> lastShownList = model.getFilteredPolicyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POLICY_DISPLAYED_INDEX);
        }

        Policy policyToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePolicy(policyToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_POLICY_SUCCESS, policyToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePolicyCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePolicyCommand) other).targetIndex)); // state check
    }
}
