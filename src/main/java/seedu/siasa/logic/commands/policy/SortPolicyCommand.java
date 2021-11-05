package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;

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

    public static final String MESSAGE_NO_POLICIES = "There are no policies to sort";

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
            if (model.getFilteredPolicyList().isEmpty()) {
                return new CommandResult(MESSAGE_NO_POLICIES);
            } else {
                return new CommandResult(MESSAGE_SUCCESS);
            }
        } else {
            return new CommandResult(MESSAGE_NO_SUCH_COMPARATOR);
        }
    }
}
