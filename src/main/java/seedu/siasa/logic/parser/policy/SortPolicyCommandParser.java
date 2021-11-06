package seedu.siasa.logic.parser.policy;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.siasa.model.policy.PolicyComparator.POLICY_SORT_BY_ALPHA_ASC;
import static seedu.siasa.model.policy.PolicyComparator.POLICY_SORT_BY_ALPHA_DSC;
import static seedu.siasa.model.policy.PolicyComparator.POLICY_SORT_BY_COMMISSION_ASC;
import static seedu.siasa.model.policy.PolicyComparator.POLICY_SORT_BY_COMMISSION_DSC;
import static seedu.siasa.model.policy.PolicyComparator.POLICY_SORT_BY_EXPIRY_ASC;
import static seedu.siasa.model.policy.PolicyComparator.POLICY_SORT_BY_EXPIRY_DSC;
import static seedu.siasa.model.policy.PolicyComparator.POLICY_SORT_BY_PAYMENT_ASC;
import static seedu.siasa.model.policy.PolicyComparator.POLICY_SORT_BY_PAYMENT_DSC;

import seedu.siasa.logic.commands.policy.SortPolicyCommand;
import seedu.siasa.logic.parser.Parser;
import seedu.siasa.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortPolicyCommand object
 */
public class SortPolicyCommandParser implements Parser<SortPolicyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortPolicyCommand
     * and returns a SortPolicyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortPolicyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortPolicyCommand.MESSAGE_USAGE));
        }

        switch (trimmedArgs) {

        case "titleasc":
            return new SortPolicyCommand(POLICY_SORT_BY_ALPHA_ASC);

        case "titledsc":
            return new SortPolicyCommand(POLICY_SORT_BY_ALPHA_DSC);

        case "paymentasc":
            return new SortPolicyCommand(POLICY_SORT_BY_PAYMENT_ASC);

        case "paymentdsc":
            return new SortPolicyCommand(POLICY_SORT_BY_PAYMENT_DSC);

        case "commasc":
            return new SortPolicyCommand(POLICY_SORT_BY_COMMISSION_ASC);

        case "commdsc":
            return new SortPolicyCommand(POLICY_SORT_BY_COMMISSION_DSC);

        case "expiryasc":
            return new SortPolicyCommand(POLICY_SORT_BY_EXPIRY_ASC);

        case "expirydsc":
            return new SortPolicyCommand(POLICY_SORT_BY_EXPIRY_DSC);

        default:
            return new SortPolicyCommand();
        }
    }
}
