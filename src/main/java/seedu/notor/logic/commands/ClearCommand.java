package seedu.notor.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.notor.model.AddressBook;
import seedu.notor.model.Model;

/**
 * Clears Notor of all entries
 */
public class ClearCommand implements Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Notor has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
