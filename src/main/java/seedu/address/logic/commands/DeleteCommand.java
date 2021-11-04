package seedu.address.logic.commands;

/**
 * Deletes a contact identified using it's displayed index from the address book.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the contact identified by the index number or name used in the displayed contact list.\n"
            + "Parameters: INDEX (must be a positive integer) or n/CONTACT_NAME (must be full name)"
            + "\nExample: " + COMMAND_WORD + " 1" + ", " + COMMAND_WORD + " n/Marina Bay Sands";

    public static final String INVALID_INDEX = "Index must be a Non-Zero Integer! Delete by name using n/CONTACT_NAME";

    public static final String MESSAGE_DELETE_CONTACT_SUCCESS = "Deleted Contact: %1$s";

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof DeleteCommand; // short circuit if same object
    }
}
