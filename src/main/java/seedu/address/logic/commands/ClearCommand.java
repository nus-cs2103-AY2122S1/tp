package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.storage.RoleReqStorage;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());

        try {
            RoleReqStorage.reset();
        } catch (IOException e) {
            throw new CommandException(Messages.FILE_NOT_FOUND + "\n" + RoleReqStorage.FILEPATH);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
