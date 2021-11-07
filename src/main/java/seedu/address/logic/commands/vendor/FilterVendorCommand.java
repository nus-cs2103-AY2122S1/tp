package seedu.address.logic.commands.vendor;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.vendor.VendorPredicate;

/**
 * Finds and lists all vendors in vendor book whose fields match any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterVendorCommand extends Command {

    public static final String COMMAND_WORD = "filtervendor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the vendor list by any given fields: "
            + "Requires at least one parameter\n"
            + "Parameters: "
            + "[" + PREFIX_VENDOR_ID + "VENDOR_ID" + "] "
            + "[" + PREFIX_NAME + "NAME" + "] "
            + "[" + PREFIX_ADDRESS + "ADDRESS" + "] "
            + "[" + PREFIX_PHONE + "PHONE" + "] "
            + "[" + PREFIX_EMAIL + "EMAIL" + "] "
            + "[" + PREFIX_SERVICE_NAME + "SERVICE_NAME" + "] "
            + "[" + PREFIX_COST + "COST" + "] "
            + "[" + PREFIX_OPERATING_HOURS + "OPERATING_HOURS" + "] "
            + "[" + PREFIX_TAG + "TAG" + "]" + "...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SERVICE_NAME + "Massage "
            + PREFIX_COST + "10 "
            + "\nThis filters the list by those vendors who offer Massage services at a cost of 10.\n";

    private final VendorPredicate predicate;

    /**
     * Creates a filter vendor command.
     *
     * @param predicate The predicates that filters the vendors.
     */
    public FilterVendorCommand(VendorPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredVendorList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_VENDORS_LISTED_OVERVIEW, model.getFilteredVendorList().size()),
                "Vendors");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterVendorCommand // instanceof handles nulls
                && predicate.equals(((FilterVendorCommand) other).predicate)); // state check
    }

}
