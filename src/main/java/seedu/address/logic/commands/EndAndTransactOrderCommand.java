package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.model.display.DisplayMode.DISPLAY_OPEN_ORDER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class EndAndTransactOrderCommand extends Command {
    public static final String COMMAND_WORD = "eorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": exit ordering mode and make transactions.";


    public static final String MESSAGE_SUCCESS = "Order is placed.";

    public static final String MESSAGE_EMPTY_ORDER = "Current order is empty. Order is closed.";

    public static final String MESSAGE_NO_UNCLOSED_ORDER = "Please use `sorder` to enter ordering mode first.";


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

        // Check if there's an open order
        if (!model.hasUnclosedOrder()) {
            throw new CommandException(MESSAGE_NO_UNCLOSED_ORDER);
        }

        // If current displaying order, return to displaying inventory
        if (model.getDisplayMode() == DISPLAY_OPEN_ORDER) {
            model.updateFilteredDisplayList(DISPLAY_INVENTORY, PREDICATE_SHOW_ALL_ITEMS);
        }

        // Check if order is empty
        if (model.getOrder().isEmpty()) {
            model.closeOrder();
            return new CommandResult(MESSAGE_EMPTY_ORDER);
        }

        // Transact order
        model.transactAndCloseOrder();

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
