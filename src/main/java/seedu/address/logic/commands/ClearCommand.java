package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears UNIon.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "rm -contacts";
    public static final String MESSAGE_SUCCESS = "UNIon's contacts have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        AddressBook newAddressBook = AddressBook.withFolders(model);
        model.setAddressBook(newAddressBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
