package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.summary.Summary;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getAddressBook().equals(new AddressBook())) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLEAR);
        } else {
            model.setAddressBook(new AddressBook());
            Summary summary = new Summary(model.getAddressBook());
            return new CommandResult(MESSAGE_SUCCESS, summary);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || other instanceof ClearCommand; // instanceof handles nulls
    }
}
