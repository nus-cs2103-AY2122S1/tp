package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_MANUFACTURER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_TYPE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AddProductCommand extends Command {

    public static final String COMMAND_WORD = "addProduct";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a product to MyCRM. "
            + "Parameters: "
            + PREFIX_PRODUCT_NAME + "NAME "
            + "[" + PREFIX_PRODUCT_TYPE + "Type "
            + "[" + PREFIX_PRODUCT_MANUFACTURER + "MANUFACTURER "
            + "[" + PREFIX_PRODUCT_DESCRIPTION + "DESCRIPTION "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PRODUCT_NAME + "Intel i5-10400F "
            + PREFIX_PRODUCT_TYPE + "CPU "
            + PREFIX_PRODUCT_MANUFACTURER + "Intel "
            + PREFIX_PRODUCT_DESCRIPTION + "2.90GHz";

    public static final String MESSAGE_ADD_PRODUCT_SUCCESS = "New product added:";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_ADD_PRODUCT_SUCCESS);
    }
}
