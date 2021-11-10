package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.ui.ConfirmationWindow;


/**
 * Warns the user with a prompt window before the actual clear command executed.
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    // new message added to indicate if the address book is already empty
    public static final String MESSAGE_UNNECESSARY = "Address book is already empty!";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!"
            + "\n" + "Type 'undo' if you would like to retract the change.";
    public static final String MESSAGE_FAILURE = "Address book is not cleared!";
    private static ReadOnlyAddressBook prevBook;
    private UndoCommand commandToUndo;

    public ReadOnlyAddressBook getPrevBook() {
        return prevBook;
    }

    /**
     * Executes will create a warning panel to give the user one more chance
     * to reconsider before clearing all the data.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Object CommandResult with the specific message
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        commandToUndo = new UndoCommand();
        commandToUndo.setPrevCommand(this);
        prevBook = new AddressBook(model.getAddressBook());
        if (model.size() == 0) {
            return new CommandResult(MESSAGE_UNNECESSARY);
        }
        model.setAddressBook(new AddressBook());
        ConfirmationWindow.setConfirmationMessage(MESSAGE_SUCCESS);
        ConfirmationWindow response = new ConfirmationWindow();
        response.show();
        return new CommandResult("Opened Confirmation Window");
    }
}
