package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;

/**
 * Adds item to the order list.
 */
public class AddToOrderCommand extends Command {
    public static final String COMMAND_WORD = "iorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to current order list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_COUNT + "COUNT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Milk "
            + PREFIX_COUNT + "10 ";


    public static final String MESSAGE_SUCCESS = " has been added to order.";

    public static final String MESSAGE_NO_UNCLOSED_ORDER = "Please use `sorder` to enter ordering mode first.";


    private Item itemToAdd;

    /**
     * Instantiates a command to add {@code Item} to the current {@code Order}
     */
    public AddToOrderCommand(Item item) {
        requireNonNull(item);

        itemToAdd = item;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasUnclosedOrder()) {
            model.addToOrder(itemToAdd);
            return new CommandResult(itemToAdd.getName() + MESSAGE_SUCCESS);
        } else {
            // Not in ordering mode, tell user to enter ordering mode first.
            return new CommandResult(MESSAGE_NO_UNCLOSED_ORDER);
        }
    }
}
