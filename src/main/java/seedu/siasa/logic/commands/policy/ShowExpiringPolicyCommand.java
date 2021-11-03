package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;
import seedu.siasa.model.policy.Policy;

/**
 * Filters the list of policies to only those expiring in a month to the user.
 */
public class ShowExpiringPolicyCommand extends Command {

    public static final String COMMAND_WORD = "expiringpolicy";

    public static final String MESSAGE_SUCCESS = "Showing policies that will be expiring in 1 month";

    public static final Predicate<Policy> EXPIRING_POLICIES_MONTH = (
            p) -> p.getCoverageExpiryDate().value.isBefore(LocalDate.now().plusMonths(1));

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPolicyList(EXPIRING_POLICIES_MONTH);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
