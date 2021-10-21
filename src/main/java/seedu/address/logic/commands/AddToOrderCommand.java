package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;

import java.util.List;

/**
 * Adds item to the order list.
 */
public class AddToOrderCommand extends Command {
    public static final String COMMAND_WORD = "iorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to current order list. "
            + "Parameters: "
            + "NAME "
            + "Or " + PREFIX_ID + "ID"
            + PREFIX_COUNT + "COUNT "
            + "Example: " + COMMAND_WORD + " "
            + "Milk "
            + PREFIX_COUNT + "10 ";


    public static final String MESSAGE_SUCCESS = " has been added to order.";
    public static final String MESSAGE_ITEM_NOT_FOUND = "No such item in the inventory";
    public static final String MESSAGE_NO_UNCLOSED_ORDER = "Please use `sorder` to enter ordering mode first.";
    public static final String MESSAGE_MULTIPLE_MATCHES =
            "Multiple candidates found, check item's details again?";

    private final ItemDescriptor toAddDescriptor;

    /**
     * Instantiates a command to add {@code Item} to the current {@code Order}
     */
    public AddToOrderCommand(ItemDescriptor toAddDescriptor) {
        requireNonNull(toAddDescriptor);
        this.toAddDescriptor = toAddDescriptor;
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
        assert(toAddDescriptor.getCount().isPresent());

        if (!model.hasUnclosedOrder()) {
            // Not in ordering mode, tell user to enter ordering mode first.
            return new CommandResult(MESSAGE_NO_UNCLOSED_ORDER);
        }

        List<Item> matchingItems = model.getItems(toAddDescriptor);

        // Check if item exists in inventory
        if (matchingItems.size() == 0) {
            throw new CommandException(MESSAGE_ITEM_NOT_FOUND);
        }

        // Check that only 1 item fit the description
        if (matchingItems.size() > 1) {
            model.updateFilteredItemList(toAddDescriptor::isMatch);
            throw new CommandException(MESSAGE_MULTIPLE_MATCHES);
        }

        model.addToOrder(matchingItems.get(0).updateCount(toAddDescriptor.getCount().get()));
        return new CommandResult(toAddDescriptor.getName() + MESSAGE_SUCCESS);
    }
}
