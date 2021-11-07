package seedu.address.logic.commands.guest;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.guest.GuestBook;

/**
 * Clears the address book.
 */
public class ClearGuestCommand extends Command {

    public static final String COMMAND_WORD = "clearguest";
    public static final String MESSAGE_SUCCESS = "Guest list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setGuestBook(new GuestBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
