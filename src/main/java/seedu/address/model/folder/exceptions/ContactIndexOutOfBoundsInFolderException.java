package seedu.address.model.folder.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Signals that the user specified index for contact
 * deletion in folder is out of range.
 */
public class ContactIndexOutOfBoundsInFolderException extends CommandException {
    public ContactIndexOutOfBoundsInFolderException(int index) {
        super("No contact available at index " + index);
    }
}
