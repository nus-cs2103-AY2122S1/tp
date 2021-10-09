package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.OrderManager;

/**
 * Creates an order.
 */
public class StartOrderCommand extends Command {
    public static final String COMMAND_WORD = "sorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an order and enter ordering mode. "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Ordering mode: Please enter item name and quantity.";

    public static final String MESSAGE_HAS_UNCLOSED_ORDER = "Already in ordering mode.";

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

        if (model.hasUnclosedOrder()) {
            return new CommandResult(MESSAGE_HAS_UNCLOSED_ORDER);
        } else {
            model.setOrder(new OrderManager());
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
