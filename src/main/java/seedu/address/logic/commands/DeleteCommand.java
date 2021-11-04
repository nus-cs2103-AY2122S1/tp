package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;

import java.util.List;
import java.util.Optional;

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
            + ": Erases the item from the inventory entirely.\n"
            + "Removes all memory about an item like cost and sales price \n"
            + " Parameters: " + "[ NAME | "
            + PREFIX_ID + "ID ]\n"
            + " Example: " + COMMAND_WORD + " Apple Pie";

    public static final String MESSAGE_SUCCESS = "Item deleted: %1$s";
    public static final String MESSAGE_ITEM_NOT_FOUND = "No such item in the inventory";
    public static final String MESSAGE_ID_NOT_FOUND = "Name provided exists but id provided is nonexistent";
    public static final String MESSAGE_NAME_NOT_FOUND = "Id provided exists but name provided is nonexistent";
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
        //check that id and name given matches
        if (!toDeleteDescriptor.getName().equals(Optional.empty())
                && !toDeleteDescriptor.getId().equals(Optional.empty())) {
            toDeleteDescriptor.setCostPrice(1.0);
            toDeleteDescriptor.setSalesPrice(1.0);
            //check that id exists
            if (!model.hasId(toDeleteDescriptor.buildItem())) {
                throw new CommandException(MESSAGE_ID_NOT_FOUND);
            }
            //check that name exists
            if (!model.hasName(toDeleteDescriptor.buildItem())) {
                throw new CommandException(MESSAGE_NAME_NOT_FOUND);
            }
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
