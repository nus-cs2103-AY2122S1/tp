package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;

public class RemoveFromOrderCommand extends Command {
    public static final String COMMAND_WORD = "corder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes an item from current order list . "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Milk ";


    public static final String MESSAGE_SUCCESS = " has been removed from order.";

    public static final String MESSAGE_NO_UNCLOSED_ORDER = "Please use `sorder` to enter ordering mode first.";


    private Item itemToRemove;

    /**
     * Instantiates a command to remove {@code Item} from the current {@code Order}
     */
    public RemoveFromOrderCommand(Item item) {
        requireNonNull(item);

        itemToRemove = item;
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
            model.removeFromOrder(itemToRemove);
            return new CommandResult(itemToRemove.getName() + MESSAGE_SUCCESS);
        } else {
            // Not in ordering mode, tell user to enter ordering mode first.
            return new CommandResult(MESSAGE_NO_UNCLOSED_ORDER);
        }
    }
}
