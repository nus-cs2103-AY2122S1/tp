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

    public static final String MESSAGE_NO_POLICIES = "There are no policies that are expiring";

    public static final String MESSAGE_SUCCESS_PLURAL = "There are %d policies expiring within 1 month.\n"
            + "use the 'expiringpolicy' command to see which ones.";

    public static final String MESSAGE_SUCCESS_SINGULAR = "There is %d policy expiring within 1 month.\n"
            + "use the 'expiringpolicy' command to see it.";

    private static final LocalDate CUT_OFF_DATE = LocalDate.now().plusMonths(1).plusDays(1);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        int noOfExpiringPolicies = 0;
        for (Policy p : model.getFilteredPolicyList()) {
            if (p.isExpiringBefore(CUT_OFF_DATE)) {
                noOfExpiringPolicies++;
            }
        }
        if (noOfExpiringPolicies == 0) {
            return new CommandResult(MESSAGE_NO_POLICIES);
        } else if (noOfExpiringPolicies == 1) {
            return new CommandResult(String.format(MESSAGE_SUCCESS_SINGULAR, noOfExpiringPolicies));
        } else {
            return new CommandResult(String.format(MESSAGE_SUCCESS_PLURAL, noOfExpiringPolicies));
        }
    }
}
