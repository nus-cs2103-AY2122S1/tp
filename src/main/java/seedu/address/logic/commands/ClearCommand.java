package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.OrderBook;
import seedu.address.model.TaskBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All data has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        model.setTaskBook(new TaskBook());
        model.setOrderBook(new OrderBook());
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.DisplayState.CLIENT);
    }
}
