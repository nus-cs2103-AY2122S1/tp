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

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.vendor.Vendor;

public class AddVendorCommand extends Command {

    public static final String COMMAND_WORD = "addvendor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a vendor that operates with the hotel.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_VENDOR_ID + "VENDOR_ID "
            + PREFIX_COST + "COST "
            + PREFIX_SERVICE_NAME + "SERVICE_NAME "
            + PREFIX_OPERATING_HOURS + "OPERATING_HOURS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_PHONE + "85948321 "
            + PREFIX_ADDRESS + "Clementi Road 123 "
            + PREFIX_VENDOR_ID + "123 "
            + PREFIX_COST + "50.00 "
            + PREFIX_SERVICE_NAME + "Massage "
            + PREFIX_OPERATING_HOURS + "12345 0800-1700 "
            + PREFIX_TAG + "Vaccinated ";

    public static final String MESSAGE_SUCCESS = "New vendor added: %1$s";
    public static final String MESSAGE_DUPLICATE_VENDOR = "This vendor already exists.";

    private final Vendor toAdd;

    /**
     * Creates an AddVendorCommand to add the specified {@code Vendor}
     */
    public AddVendorCommand(Vendor vendor) {
        requireNonNull(vendor);
        toAdd = vendor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasVendor(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_VENDOR);
        }

        model.addVendor(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddVendorCommand // instanceof handles nulls
                && toAdd.equals(((AddVendorCommand) other).toAdd));
    }

}
