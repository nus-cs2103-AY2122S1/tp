package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all orders in the application to the user.
 */
public class ListOrderCommand extends Command {

    public static final String COMMAND_WORD = "listorders";

    public static final String MESSAGE_SUCCESS = "Listed all order(s)";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetOrderView();
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.DisplayState.ORDER);
    }
}
