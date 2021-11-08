package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String COMMAND_DESCRIPTION = "Clears all entries from the address book.\n";
    public static final String COMMAND_EXAMPLE = "Example: " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + COMMAND_DESCRIPTION + COMMAND_EXAMPLE;

    /**
     * Executes the {@code ClearCommand} which clears all contacts.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} regarding the status of the {@code ClearCommand}.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        model.clearBirthdayReminderList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
