package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_ACTION = "Clear All Data";

    public static final String COMMAND_WORD = "clear";

    public static final String USER_TIP = "You may clear all the data with: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    /**
     * Returns the description of what the command does.
     *
     * @return Description of what the command does.
     */
    public String getAction() {
        return COMMAND_ACTION;
    }

    /**
     * Returns the format of the valid command with command word and parameters.
     *
     * @return The format of the valid command.
     */
    public String getFormat() {
        return COMMAND_WORD;
    }

    /**
     * Returns an example usage of the command.
     *
     * @return Example usage of the command.
     */
    public String getExample() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
