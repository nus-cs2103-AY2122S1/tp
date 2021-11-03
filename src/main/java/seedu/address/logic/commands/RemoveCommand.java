package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
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
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the given item(s) from the inventory.\n"
            + " Parameters: [ NAME | "
            + PREFIX_ID + "ID ]"
            + " (" + PREFIX_COUNT + "COUNT" + ")\n"
            + " Example: " + COMMAND_WORD + " Apple Pie "
            + PREFIX_COUNT + "1";

    public static final String MESSAGE_SUCCESS = "Item removed: %d x %s";
    public static final String MESSAGE_ITEM_NOT_FOUND = "No such item in the inventory";
    public static final String MESSAGE_INSUFFICIENT_ITEM = "Only %d of %s in the inventory!";
    public static final String MESSAGE_ID_NOT_FOUND = "Name provided exists but id provided is nonexistent";
    public static final String MESSAGE_NAME_NOT_FOUND = "Id provided exists but name provided is nonexistent";
    public static final String MESSAGE_MULTIPLE_MATCHES =
            "Multiple candidates found, which one did you mean to remove?";

    private final ItemDescriptor toRemoveDescriptor;

    /**
     * Creates a RemoveCommand to remove the Item specified by the {@code ItemDescriptor}
     */
    public RemoveCommand(ItemDescriptor itemDescriptor) {
        requireNonNull(itemDescriptor);
        toRemoveDescriptor = itemDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert(toRemoveDescriptor.getCount().isPresent());

        List<Item> matchingItems = model.getItems(toRemoveDescriptor);

        // Check if item exists in inventory
        if (matchingItems.size() == 0) {
            throw new CommandException(MESSAGE_ITEM_NOT_FOUND);
        }
        //check that id and name given matches
        if (!toRemoveDescriptor.getName().equals(Optional.empty())
                && !toRemoveDescriptor.getId().equals(Optional.empty())) {
            toRemoveDescriptor.setCostPrice(1.0);
            toRemoveDescriptor.setSalesPrice(1.0);
            //check that id exists
            if (!model.hasId(toRemoveDescriptor.buildItem())) {
                throw new CommandException(MESSAGE_ID_NOT_FOUND);
            }
            //check that name exists
            if (!model.hasName(toRemoveDescriptor.buildItem())) {
                throw new CommandException(MESSAGE_NAME_NOT_FOUND);
            }
        }

        // Check that only 1 item fit the description
        if (matchingItems.size() > 1) {
            model.updateFilteredItemList(DISPLAY_INVENTORY, toRemoveDescriptor::isMatch);
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

        model.removeItem(target, amount);
        return new CommandResult(String.format(MESSAGE_SUCCESS, amount, target.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && toRemoveDescriptor.equals(((RemoveCommand) other).toRemoveDescriptor));
    }
}
