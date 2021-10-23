package seedu.address.logic.commands;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number or name used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n or NAME (must be full name)"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    @Override
    public boolean equals(Object other) {
        return other == this; // short circuit if same object
    }
}
