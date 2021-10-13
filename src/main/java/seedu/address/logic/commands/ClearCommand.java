package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.model.Inventory;
import seedu.address.model.Model;

/**
 * Clears the inventory.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Inventory has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": clears the current inventory. ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInventory(new Inventory());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
