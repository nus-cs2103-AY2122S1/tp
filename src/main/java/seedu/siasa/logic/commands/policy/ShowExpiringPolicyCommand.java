package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Iterator;

import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;
import seedu.siasa.model.policy.Policy;

/**
 * Lists all expired policies to the user.
 */
public class ShowExpiringPolicyCommand extends Command {

    public static final String COMMAND_WORD = "expiringpolicy";

    private static final LocalDate CUT_OFF_DATE = LocalDate.now().plusMonths(1);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Iterator<Policy> policyListIterator = model.getFilteredPolicyList().iterator();
        StringBuilder listOfPolicies = new StringBuilder("Policies expiring soon in 1 month:\n");

        while (policyListIterator.hasNext()) {
            Policy policy = policyListIterator.next();

            if (policy.isExpiringBefore(CUT_OFF_DATE)) {
                listOfPolicies.append(policy);
            }
        }
        return new CommandResult(listOfPolicies.toString());
    }
}
