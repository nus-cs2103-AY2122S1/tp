package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;

import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;
import seedu.siasa.model.policy.Policy;

/**
 * Lists a summary of policies that will be expiring in a month to the user.
 */
public class ShowExpiringPolicySummaryCommand extends Command {

    public static final String COMMAND_WORD = "expiringpolicysummary";

    private static final String pattern = "There {0} expired or expiring.\nUse the "
            + ShowExpiringPolicyCommand.COMMAND_WORD + " command to see more.";
    private static final double[] policyLimits = {0, 1, 2};
    private static final String[] policyStrings = {
        "are no policies that are",
        "is 1 policy that is",
        "are {1} policies that are"
    };
    private static final ChoiceFormat choiceForm = new ChoiceFormat(policyLimits, policyStrings);
    private static final Format[] formats = {choiceForm, null};
    public static final MessageFormat MESSAGE_FORM = new MessageFormat(pattern);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        MESSAGE_FORM.setFormats(formats);

        int noOfExpiringPolicies = 0;
        for (Policy p : model.getFilteredPolicyList()) {
            if (p.isExpiringBefore(ShowExpiringPolicyCommand.CUT_OFF_DATE)) {
                noOfExpiringPolicies++;
            }
        }

        return new CommandResult(MESSAGE_FORM.format(new Object[]{noOfExpiringPolicies, noOfExpiringPolicies}));
    }
}
