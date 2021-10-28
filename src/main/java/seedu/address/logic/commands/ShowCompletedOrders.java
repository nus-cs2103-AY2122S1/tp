package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_COMPLETED_ORDERS;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Lists all the completed orders.
 */
public class ShowCompletedOrders extends Command {

    public static final String COMMAND_WORD = "completedorders";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(PREDICATE_SHOW_COMPLETED_ORDERS);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()),
                CommandResult.DisplayState.ORDER);
    }

}
