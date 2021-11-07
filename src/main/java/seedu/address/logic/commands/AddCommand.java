package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COSTPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALESPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;

import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.Name;

/**
 * Adds an item to the inventory.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds item(s) to the inventory. "
            + "\n Parameters: "
            + "[ NAME |"
            + PREFIX_ID + "ID ]"
            + " (" + PREFIX_COUNT + "COUNT)"
            + " (" + PREFIX_COSTPRICE + "COSTPRICE) "
            + " (" + PREFIX_SALESPRICE + "SALESPRICE) "
            + " (" + PREFIX_TAG + "TAG)...\n"
            + " Example: " + COMMAND_WORD + " "
            + "Banana Bread "
            + PREFIX_ID + "019381 "
            + PREFIX_COUNT + "10 "
            + PREFIX_COSTPRICE + "3.2 "
            + PREFIX_SALESPRICE + "6.4 "
            + PREFIX_TAG + "baked "
            + PREFIX_TAG + "popular";

    public static final String MESSAGE_SUCCESS_NEW = "New item added: %1$s";
    public static final String MESSAGE_SUCCESS_REPLENISH = "Item replenished: %d x %s";
    public static final String MESSAGE_EXTRA_PRICE_FLAGS = "There seems to be extra price fields. If you wish to "
            + "edit prices, please use 'edit' instead.";
    public static final String MESSAGE_EXTRA_TAG_FLAGS = "There seems to be extra tag field. If you wish to "
            + "edit tags, please use 'edit' instead.";
    public static final String MESSAGE_INCOMPLETE_INFO = "Item has not been added before,"
            + " please provide name, id, cost price, and sales price";
    public static final String MESSAGE_ID_NOT_FOUND = "Name provided exists but id provided is nonexistent";
    public static final String MESSAGE_NAME_NOT_FOUND = "Id provided exists but name provided is nonexistent";
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
        assert(toAddDescriptor.getCount().isPresent());

        List<Item> matchingItems = model.getItems(toAddDescriptor);
        boolean extraCostFlags = false;
        boolean extraTagFlag = false;
        boolean nameEmpty = true;
        boolean idEmpty = true;
        // Check if item exists in inventory
        if (matchingItems.size() == 0) {
            // Check name and id are specified
            if (toAddDescriptor.getName().isEmpty() || toAddDescriptor.getId().isEmpty()
                || toAddDescriptor.getSalesPrice().isEmpty() || toAddDescriptor.getCostPrice().isEmpty()) {
                throw new CommandException(MESSAGE_INCOMPLETE_INFO);
            }

            // Add the new item into inventory
            Item newItem = toAddDescriptor.buildItem();
            model.addItem(newItem);

            model.addCostBookKeeping(newItem.getCostPrice(), newItem.getCount());

            return new CommandResult(String.format(MESSAGE_SUCCESS_NEW, newItem));
        }
        if (toAddDescriptor.getCostPrice().equals(Optional.empty())) {
            toAddDescriptor.setCostPrice(1.0);
        } else {
            extraCostFlags = true;
        }
        if (toAddDescriptor.getSalesPrice().equals(Optional.empty())) {
            toAddDescriptor.setSalesPrice(1.0);
        } else {
            extraCostFlags = true;
        }
        if (!toAddDescriptor.getTags().equals(Optional.empty())) {
            extraTagFlag = true;
        }
        // Check that only 1 item fit the description
        if (matchingItems.size() > 1) {
            model.updateFilteredItemList(DISPLAY_INVENTORY, toAddDescriptor::isMatch);
            throw new CommandException(MESSAGE_MULTIPLE_MATCHES);
        }
        if (!toAddDescriptor.getId().equals(Optional.empty())
                && !toAddDescriptor.getName().equals(Optional.empty())) {
            if (!model.hasId(toAddDescriptor.buildItem())) {
                throw new CommandException(MESSAGE_ID_NOT_FOUND);
            }
            if (!model.hasName(toAddDescriptor.buildItem())) {
                throw new CommandException(MESSAGE_NAME_NOT_FOUND);
            }
        }

        Item target = matchingItems.get(0);
        int amount = toAddDescriptor.getCount().get();
        model.restockItem(target, amount);
        model.addCostBookKeeping(target.getCostPrice(), amount);
        String replenishedMessage = String.format(MESSAGE_SUCCESS_REPLENISH, amount, target.getName());
        if (extraCostFlags) {
            return new CommandResult(replenishedMessage + "\n" + MESSAGE_EXTRA_PRICE_FLAGS);
        }
        if (extraTagFlag) {
            return new CommandResult(replenishedMessage + "\n" + MESSAGE_EXTRA_TAG_FLAGS);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS_REPLENISH, amount, target.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAddDescriptor.equals(((AddCommand) other).toAddDescriptor));
    }
}
