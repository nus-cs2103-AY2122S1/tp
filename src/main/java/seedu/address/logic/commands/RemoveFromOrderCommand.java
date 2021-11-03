package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;

/**
 * Removes an item from the order list.
 */
public class RemoveFromOrderCommand extends Command {
    public static final String COMMAND_WORD = "corder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes an item from current order list . "
            + "\n Parameters: "
            + "[ NAME | "
            + PREFIX_ID + "ID ]"
            + " (" + PREFIX_COUNT + "COUNT)"
            + "\n Example: " + COMMAND_WORD + " "
            + "Milk "
            + PREFIX_COUNT + "10 ";


    public static final String MESSAGE_SUCCESS = "Item removed from order: %d x %s";
    public static final String MESSAGE_ITEM_NOT_FOUND = "Item is not in the current order";
    public static final String MESSAGE_INSUFFICIENT_ITEM = "There isn't that much of the item in the current order";
    public static final String MESSAGE_NO_UNCLOSED_ORDER = "Please use `sorder` to enter ordering mode first.";
    public static final String MESSAGE_MULTIPLE_MATCHES =
            "Multiple candidates found, check item's details again?";

    private final ItemDescriptor toRemoveDescriptor;

    /**
     * Instantiates a command to remove {@code Item} from the current {@code Order}
     */
    public RemoveFromOrderCommand(ItemDescriptor toRemoveDescriptor) {
        requireNonNull(toRemoveDescriptor);

        this.toRemoveDescriptor = toRemoveDescriptor;
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

        if (!model.hasUnclosedOrder()) {
            // Not in ordering mode, tell user to enter ordering mode first.
            throw new CommandException(MESSAGE_NO_UNCLOSED_ORDER);
        }

        List<Item> matchingItems = model.getFromOrder(toRemoveDescriptor);

        // Check if item exists in order
        if (matchingItems.size() == 0) {
            throw new CommandException(MESSAGE_ITEM_NOT_FOUND);
        }

        // Check that only 1 item fit the description
        if (matchingItems.size() > 1) {
            throw new CommandException(MESSAGE_MULTIPLE_MATCHES);
        }

        Item target = matchingItems.get(0);
        int amount = toRemoveDescriptor.getCount().get();
        // Check that enough of item to remove
        if (target.getCount() < amount) {
            throw new CommandException(
                    String.format(MESSAGE_INSUFFICIENT_ITEM, target.getCount(), target.getName())
            );
        }

        model.removeFromOrder(target, amount);
        return new CommandResult(String.format(MESSAGE_SUCCESS, amount, target.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveFromOrderCommand // instanceof handles nulls
                && toRemoveDescriptor.equals(((RemoveFromOrderCommand) other).toRemoveDescriptor));
    }
}
