package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends UndoableCommand {

    public static final String COMMAND_ACTION = "Clear All Data";

    public static final String COMMAND_WORD = "clear";

    public static final String USER_TIP = "You may clear all the data with: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "TAB's data has been cleared!";

    private ReadOnlyAddressBook previousAddressBook;

    @Override
    public CommandResult executeUndoableCommand() {
        requireNonNull(model);
        previousAddressBook = new AddressBook(model.getAddressBook());
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    protected void undo() {
        requireNonNull(model);

        model.setAddressBook(previousAddressBook);
    }

    /**
     * Redoes command.
     * {@code previousAddressBook} becomes an empty address book.
     */
    @Override
    protected void redo() {
        requireNonNull(model);

        executeUndoableCommand();
    }
}
