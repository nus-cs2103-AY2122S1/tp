package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.model.display.DisplayMode.DISPLAY_OPEN_ORDER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.display.DisplayMode;


/**
 * Lists all items in the inventory to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_INVENTORY = "Listed all items";
    public static final String MESSAGE_SUCCESS_ORDER = "Listed all items";
    public static final String MESSAGE_SUCCESS_TXNS = "Listed all past transactions";

    public static final String ORDER_KEYWORD = "order";
    public static final String TRANSACTIONS_KEYWORD = "txns";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": lists all the items in the inventory.\n"
            + COMMAND_WORD + " " + ORDER_KEYWORD + ": list items in the current order.\n"
            + COMMAND_WORD + " " + TRANSACTIONS_KEYWORD + ": list past transactions";

    public static final String MESSAGE_NO_UNCLOSED_ORDER = "No open order.\n"
            + "Please use `sorder` to enter ordering mode first.";

    private final DisplayMode displayMode;
    /**
     * Creates a ListCommand with the given {@code displayMode}.
     */
    public ListCommand(DisplayMode displayMode) {
        this.displayMode = displayMode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (displayMode == DISPLAY_OPEN_ORDER && !model.hasUnclosedOrder()) {
            throw new CommandException(MESSAGE_NO_UNCLOSED_ORDER);
        }

        String successMessage;
        if (displayMode == DISPLAY_INVENTORY) {
            successMessage = MESSAGE_SUCCESS_INVENTORY;
        } else if (displayMode == DISPLAY_OPEN_ORDER) {
            successMessage = MESSAGE_SUCCESS_ORDER;
        } else {
            successMessage = MESSAGE_SUCCESS_TXNS;
        }

        model.updateFilteredDisplayList(displayMode, PREDICATE_SHOW_ALL_ITEMS);

        return new CommandResult(successMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && displayMode == ((ListCommand) other).displayMode); // state check
    }
}
