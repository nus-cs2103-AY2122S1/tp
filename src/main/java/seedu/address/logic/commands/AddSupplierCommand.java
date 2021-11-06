package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUPPLY_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.model.Model;
import seedu.address.model.person.supplier.Supplier;

/**
 * Adds a supplier to RHRH.
 */
public class AddSupplierCommand extends Command {
    public static final String COMMAND_WORD = "adds";

    public static final String MESSAGE_USAGE = CommandUtil.formatCommandWord(COMMAND_WORD)
            + ": Adds a person to RHRH. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG] "
            + PREFIX_SUPPLY_TYPE + "TYPE OF SUPPLY "
            + PREFIX_DELIVERY_DETAILS + "DELIVERY DETAILS...\n"
            + "Example: " + CommandUtil.formatCommandWord(COMMAND_WORD) + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_SUPPLY_TYPE + "Chicken "
            + PREFIX_DELIVERY_DETAILS + "12:00 10-11-2021";

    public static final String MESSAGE_SUCCESS = "New supplier added: %1$s";
    public static final String MESSAGE_DUPLICATE_SUPPLIER = "This supplier already exists in RHRH";

    private final Supplier toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Supplier}
     */
    public AddSupplierCommand(Supplier supplier) {
        requireNonNull(supplier);
        toAdd = supplier;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasSupplier(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SUPPLIER);
        }

        model.addSupplier(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd),
                false, false, false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSupplierCommand // instanceof handles nulls
                && toAdd.equals(((AddSupplierCommand) other).toAdd));
    }
}
