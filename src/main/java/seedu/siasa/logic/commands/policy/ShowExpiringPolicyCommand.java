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

    public static final int NUM_MONTHS_CUT_OFF = 1;

    public static final LocalDate CUT_OFF_DATE = LocalDate.now().plusMonths(NUM_MONTHS_CUT_OFF).plusDays(1);

    public static final String MESSAGE_SUCCESS = "Showing policies that have expired or will be expiring "
            + NUM_MONTHS_CUT_OFF + " month.";

    public static final String MESSAGE_NO_POLICIES = "There are no policies expired or expiring in "
            + NUM_MONTHS_CUT_OFF + " month.";



    public static final Predicate<Policy> EXPIRING_POLICIES_MONTH = (p) -> p.isExpiringBefore(CUT_OFF_DATE);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPolicyList(EXPIRING_POLICIES_MONTH);

        if (model.getFilteredPolicyList().size() > 0) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_NO_POLICIES);
        }
    }
}
