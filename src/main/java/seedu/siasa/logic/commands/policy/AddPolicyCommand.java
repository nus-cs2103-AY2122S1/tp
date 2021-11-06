package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_EXPIRY;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_PAYMENT;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.siasa.commons.core.Messages;
import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.logic.commands.Warning;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.model.Model;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.CoverageExpiryDate;
import seedu.siasa.model.policy.PaymentStructure;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.Title;
import seedu.siasa.model.tag.Tag;


/**
 * Adds a policy to the SIASA.
 */
public class AddPolicyCommand extends Command {

    public static final String COMMAND_WORD = "addpolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a policy to the policy list. "
            + "\n"
            + "Parameters: "
            + PREFIX_TITLE + "POLICY_NAME "
            + PREFIX_PAYMENT + "PMT_AMOUNT_CENTS [PMTS_PER_YR] [NUM_OF_PMTS] "
            + PREFIX_COMMISSION + "COMMISSION_% NUM_OF_COMM "
            + PREFIX_CONTACT_INDEX + "CONTACT_INDEX "
            + "[" + PREFIX_EXPIRY + "COVERAGE_EXPIRY_DATE] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Life Policy "
            + PREFIX_PAYMENT + "1000 12 120 "
            + PREFIX_COMMISSION + "20 12 "
            + PREFIX_CONTACT_INDEX + "1 "
            + PREFIX_EXPIRY + "2021-06-13 "
            + PREFIX_TAG + "AIA";

    public static final String MESSAGE_SUCCESS = "New policy added: %1$s";
    public static final String MESSAGE_DUPLICATE_POLICY = "This policy already exists for the specified contact";
    public static final String MESSAGE_NOT_FUTURE_EXPIRY_DATE = "Expiry Date is not in the future";
    public static final String MESSAGE_SIMILAR_POLICY = "A similar policy: %1$s already exists in the SIASA";

    private final Title title;
    private final PaymentStructure paymentStructure;
    private final CoverageExpiryDate coverageExpiryDate;
    private final Index index;
    private final Commission commission;
    private final Set<Tag> tagList = new HashSet<>();

    /**
     * Creates an AddPolicyCommand that adds a policy with the following fields.
     */
    public AddPolicyCommand(Title title, PaymentStructure paymentStructure, CoverageExpiryDate coverageExpiryDate,
                            Commission commission, Index index, Set<Tag> tagList) {
        requireAllNonNull(title, paymentStructure, index, tagList);
        this.title = title;
        this.paymentStructure = paymentStructure;
        this.coverageExpiryDate = coverageExpiryDate;
        this.index = index;
        this.commission = commission;
        this.tagList.addAll(tagList);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Contact> lastShownList = model.getFilteredContactList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        if (coverageExpiryDate != null && !CoverageExpiryDate.isFutureExpiryDate(coverageExpiryDate.value)) {
            boolean response = Warning.isUserConfirmingCommand(MESSAGE_NOT_FUTURE_EXPIRY_DATE);
            if (!response) {
                return new CommandResult(Messages.MESSAGE_CANCELLED_COMMAND);
            }
        }

        Contact owner = lastShownList.get(index.getZeroBased());

        Policy toAdd = new Policy(title, paymentStructure, coverageExpiryDate, commission, owner, tagList);

        if (model.hasPolicy(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_POLICY);
        }

        if (model.getSimilarPolicy(toAdd).isPresent()) {
            Policy similarPolicy = model.getSimilarPolicy(toAdd).get();
            boolean response = Warning.isUserConfirmingCommand(
                    String.format(MESSAGE_SIMILAR_POLICY, similarPolicy.getTitle()));
            if (!response) {
                return new CommandResult(Messages.MESSAGE_CANCELLED_COMMAND);
            }
        }

        model.addPolicy(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPolicyCommand // instanceof handles nulls
                && title.equals(((AddPolicyCommand) other).title)
                && commission.equals(((AddPolicyCommand) other).commission)
                && index.equals(((AddPolicyCommand) other).index)
                && paymentStructure.equals(((AddPolicyCommand) other).paymentStructure)
                && coverageExpiryDate.equals(((AddPolicyCommand) other).coverageExpiryDate)
                && tagList.equals(((AddPolicyCommand) other).tagList));
    }
}
