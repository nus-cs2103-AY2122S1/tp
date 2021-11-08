package seedu.address.logic.commands;

import seedu.address.model.Model;


/**
 * Lists all clients and each of their total orders.
 */
public class TotalOrdersCommand extends Command {

    public static final String COMMAND_WORD = "totalorders";

    public static final String MESSAGE_SUCCESS = COMMAND_WORD + ": Shows total orders for each client.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_TOTAL_ORDERS_MESSAGE = "Opened/Refreshed total orders window.\n"
            + "You might need to restore the window if you have minimized it.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_TOTAL_ORDERS_MESSAGE, CommandResult.DisplayState.ORDER, true, false, false);
    }
}
