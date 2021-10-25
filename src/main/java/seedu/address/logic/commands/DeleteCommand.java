package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.model.Model.DisplayMode.DISPLAY_INVENTORY;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;

/**
 * Deletes an item identified using it's displayed index from the inventory.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item from the inventory entirely.\n"
            + "Parameters: NAME "
            + "Or " + PREFIX_ID + "ID"
            + "Example: " + COMMAND_WORD + "Apple Pie";

    public static final String MESSAGE_SUCCESS = "Item deleted: %1$s";
    public static final String MESSAGE_ITEM_NOT_FOUND = "No such item in the inventory";
    public static final String MESSAGE_MULTIPLE_MATCHES =
            "Multiple candidates found, which one did you mean to delete?";

    private final ItemDescriptor toDeleteDescriptor;

    /**
     * Creates a DeleteCommand to remove the Item specified by the {@code ItemDescriptor}
     */
    public DeleteCommand(ItemDescriptor itemDescriptor) {
        requireNonNull(itemDescriptor);
        toDeleteDescriptor = itemDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Item> matchingItems = model.getItems(toDeleteDescriptor);

        // Check if item exists in inventory
        if (matchingItems.size() == 0) {
            throw new CommandException(MESSAGE_ITEM_NOT_FOUND);
        }

        // Check that only 1 item fit the description
        if (matchingItems.size() > 1) {
            model.updateFilteredItemList(DISPLAY_INVENTORY, toDeleteDescriptor::isMatch);
            throw new CommandException(MESSAGE_MULTIPLE_MATCHES);
        }

        Item target = matchingItems.get(0);
        model.deleteItem(target);
        return new CommandResult(String.format(MESSAGE_SUCCESS, target));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand) // instanceof handles nulls
                && toDeleteDescriptor.equals(((DeleteCommand) other).toDeleteDescriptor);
    }
}
