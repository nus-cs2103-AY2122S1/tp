package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.Name;

import java.util.List;

/**
 * Adds an item to the inventory.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds item(s) to the inventory. "
            + "Parameters: "
            + "NAME "
            + PREFIX_ID + "ID "
            + PREFIX_COUNT + "COUNT "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "Banana Bread "
            + PREFIX_ID + "019381 "
            + PREFIX_COUNT + "10 "
            + PREFIX_TAG + "baked "
            + PREFIX_TAG + "popular";

    public static final String MESSAGE_SUCCESS_NEW = "New item added: %1$s";
    public static final String MESSAGE_SUCCESS_REPLENISH = "Item replenished: %dx %s";
    public static final String MESSAGE_INCOMPLETE_INFO = "Item has not been added before, please provide both a name and id";
    public static final String MESSAGE_MULTIPLE_MATCHES = "Multiple candidates found, which one did you mean to add?";

    private final ItemDescriptor toAddDescriptor;

    /**
     * Creates an AddCommand to add the Item specified by the {@code ItemDescriptor}
     */
    public AddCommand(ItemDescriptor itemDescriptor) {
        requireNonNull(itemDescriptor);
        toAddDescriptor = itemDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Item> matchingItems = model.getItems(toAddDescriptor);

        // Check if item exists in inventory
        if (matchingItems.size() == 0) {
            // Check name and id are specified
            if (toAddDescriptor.getName().isEmpty() || toAddDescriptor.getId().isEmpty()) {
                throw new CommandException(MESSAGE_INCOMPLETE_INFO);
            }

            // Add the new item into inventory
            Item newItem = toAddDescriptor.buildItem();
            model.addItem(newItem);
            return new CommandResult(String.format(MESSAGE_SUCCESS_NEW, newItem));
        }

        // Check that only 1 item fit the description
        if (matchingItems.size() > 1) {
            model.updateFilteredItemList(toAddDescriptor::isMatch);
            throw new CommandException(MESSAGE_MULTIPLE_MATCHES);
        }

        Item target = matchingItems.get(0);
        int amount = toAddDescriptor.getCount().get();
        model.restockItem(target, toAddDescriptor.getCount().get());
        return new CommandResult(String.format(MESSAGE_SUCCESS_REPLENISH, amount, target.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAddDescriptor.equals(((AddCommand) other).toAddDescriptor));
    }
}
