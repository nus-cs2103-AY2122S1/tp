package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.model.display.DisplayMode.DISPLAY_OPEN_ORDER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.display.DisplayMode;


/**
 * Lists the items in either the inventory or any unclosed order.
 */
public class ListInventoryCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS_INVENTORY = "Listed all items";
    public static final String MESSAGE_SUCCESS_ORDER = "Listed all items in current order";

    public static final String ORDER_KEYWORD = "order";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": lists all the items in the inventory.\n"
            + COMMAND_WORD + " " + ORDER_KEYWORD + ": list items in the current order.";

    public static final String MESSAGE_NO_UNCLOSED_ORDER = "No open order.\n"
            + "Please use `sorder` to enter ordering mode first.";

    private final DisplayMode displayMode;

    /**
     * Creates a ListInventoryCommand with the given {@code displayMode}.
     *
     * {@code displayMode} must either be DISPLAY_INVENTORY or DISPLAY_OPEN_ORDER.
     */
    public ListInventoryCommand(DisplayMode displayMode) {
        assert displayMode == DISPLAY_INVENTORY || displayMode == DISPLAY_OPEN_ORDER;

        this.displayMode = displayMode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (displayMode == DISPLAY_OPEN_ORDER && !model.hasUnclosedOrder()) {
            throw new CommandException(MESSAGE_NO_UNCLOSED_ORDER);
        }

        model.updateFilteredDisplayList(displayMode, PREDICATE_SHOW_ALL_ITEMS);

        if (displayMode == DISPLAY_INVENTORY) {
            // Message: Display inventory
            return new CommandResult(MESSAGE_SUCCESS_INVENTORY);
        } else {
            // Message: Display open order
            return new CommandResult(MESSAGE_SUCCESS_ORDER);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListInventoryCommand // instanceof handles nulls
                && displayMode == ((ListInventoryCommand) other).displayMode); // state check
    }
}
