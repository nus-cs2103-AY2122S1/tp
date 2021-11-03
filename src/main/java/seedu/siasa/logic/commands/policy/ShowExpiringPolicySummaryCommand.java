package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;
import seedu.siasa.model.policy.Policy;

/**
 * Lists a summary of policies that will be expiring in a month to the user.
 */
public class ShowExpiringPolicySummaryCommand extends Command {

    public static final String COMMAND_WORD = "expiringpolicysummary";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        int noOfExpiringPolicies = 0;
        for (Policy p : model.getFilteredPolicyList()) {
            if (p.getCoverageExpiryDate().value.isBefore(LocalDate.now().plusMonths(1))) {
                noOfExpiringPolicies++;
            }
        }
        String listOfExpiringPolicies = String.format("There are %d policies expiring within 1 month.\n"
                + "use the 'expiringpolicy' command to see which ones.", noOfExpiringPolicies);

        return new CommandResult(listOfExpiringPolicies);
    }
}
