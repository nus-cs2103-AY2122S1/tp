package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.core.Messages.MESSAGE_POLICIES_LIST_EMPTY;

import java.util.Comparator;

import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;
import seedu.siasa.model.policy.Policy;

/**
 * Lists all policies in the SIASA to the user.
 */
public class SortPolicyCommand extends Command {

    public static final String COMMAND_WORD = "sortpolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the policy list alphabetically by the order specified.\n"
            + "Parameters: SORTING_METHOD (titleasc, titledsc, paymentasc, paymentasc, commasc,"
            + "commdsc, expiryasc, expirydsc)\n"
            + "Example: " + COMMAND_WORD + " expirydsc";

    public static final String MESSAGE_SUCCESS = "Sorted policies";

    public static final String MESSAGE_NO_SUCH_COMPARATOR = "No such sorting order";

    private final Comparator<Policy> comparator;

    public SortPolicyCommand() {
        this.comparator = null;
    }

    public SortPolicyCommand(Comparator<Policy> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (comparator != null) {
            model.updateFilteredPolicyList(comparator);

            if (model.getFilteredPolicyList().size() > 0) {
                return new CommandResult(MESSAGE_SUCCESS);
            } else {
                return new CommandResult(MESSAGE_POLICIES_LIST_EMPTY);
            }
        } else {
            return new CommandResult(MESSAGE_NO_SUCH_COMPARATOR);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SortPolicyCommand
                && comparator.equals(((SortPolicyCommand) other).comparator));
    }
}
