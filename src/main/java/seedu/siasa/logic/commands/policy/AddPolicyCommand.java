package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_CLIENT_INDEX;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_EXPIRY;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_PAYMENT;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;

import seedu.siasa.commons.core.Messages;
import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.model.Model;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.CoverageExpiryDate;
import seedu.siasa.model.policy.PaymentStructure;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.Title;


/**
 * Adds a person to the address book.
 */
public class AddPolicyCommand extends Command {

    public static final String COMMAND_WORD = "addpolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a policy to the policy list. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_EXPIRY + "EXPIRY "
            + PREFIX_PAYMENT + "PAYMENT_AMOUNT PAYMENT_FREQUENCY(OPT) NUM_OF_PAYMENTS(OPT) "
            + PREFIX_COMMISSION + "COMMISSION_PERCENTAGE NUM_OF_PAYMENTS_W_COMM "
            + PREFIX_CLIENT_INDEX + "CLIENT_INDEX "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Life Policy "
            + PREFIX_EXPIRY + "2021-06-13 "
            + PREFIX_PAYMENT + "1000 12 120"
            + PREFIX_COMMISSION + "20 12"
            + PREFIX_CLIENT_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "New policy added: %1$s";
    public static final String MESSAGE_DUPLICATE_POLICY = "This policy already exists for the specified user";

    private final Title title;
    private final PaymentStructure paymentStructure;
    private final CoverageExpiryDate coverageExpiryDate;
    private final Index index;
    private final Commission commission;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddPolicyCommand(Title title, PaymentStructure paymentStructure, CoverageExpiryDate coverageExpiryDate,
                            Commission commission, Index index) {
        requireAllNonNull(title, paymentStructure, coverageExpiryDate, index);
        this.title = title;
        this.paymentStructure = paymentStructure;
        this.coverageExpiryDate = coverageExpiryDate;
        this.index = index;
        this.commission = commission;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person owner = lastShownList.get(index.getZeroBased());

        Policy toAdd = new Policy(title, paymentStructure, coverageExpiryDate, commission, owner);

        if (model.hasPolicy(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_POLICY);
        }

        model.addPolicy(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPolicyCommand // instanceof handles nulls
                && title.equals(((AddPolicyCommand) other).title)
                && paymentStructure.equals(((AddPolicyCommand) other).paymentStructure)
                && coverageExpiryDate.equals(((AddPolicyCommand) other).coverageExpiryDate)
                && commission.equals(((AddPolicyCommand) other).commission));
    }
}
