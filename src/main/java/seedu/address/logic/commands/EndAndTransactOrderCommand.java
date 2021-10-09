package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class EndAndTransactOrderCommand extends Command {
    public static final String COMMAND_WORD = "eorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": exit ordering mode and make transactions."
            + "Example: " + COMMAND_WORD;


    public static final String MESSAGE_SUCCESS = "Order is placed.";

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

        if (model.hasUnclosedOrder()) {
            model.transactAndClearOrder();
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            // Not in ordering mode, tell user to enter ordering mode first.
            return new CommandResult(MESSAGE_NO_UNCLOSED_ORDER);
        }
    }
}
