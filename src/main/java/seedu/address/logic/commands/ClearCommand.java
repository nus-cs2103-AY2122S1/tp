package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMS;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

/**
 * Clears the address book.
 */
public class ClearCommand extends UndoableCommand {

    public static final String COMMAND_ACTION = "Clear All Data";

    public static final String COMMAND_WORD = "clear";

    public static final String USER_TIP = "You may clear all the data with: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "TAB's data has been cleared!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all data.\n" + MESSAGE_NO_PARAMS;

    private ReadOnlyAddressBook previousAddressBook;

    /**
     * Constructs a Clear Command.
     */
    public ClearCommand() {
        super(COMMAND_ACTION);
    }

    @Override
    public CommandResult executeUndoableCommand() {
        requireNonNull(model);
        previousAddressBook = new AddressBook(model.getAddressBook());
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    protected Person undo() {
        requireNonNull(model);

        model.setAddressBook(previousAddressBook);
        return null;
    }

    /**
     * Redoes command.
     * {@code previousAddressBook} becomes an empty address book.
     */
    @Override
    protected Person redo() {
        requireNonNull(model);

        model.setAddressBook(new AddressBook());
        return null;
    }
}
