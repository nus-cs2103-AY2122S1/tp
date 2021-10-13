package seedu.notor.logic.commands.person;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonDeleteExecutor;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class PersonDeleteCommand extends PersonCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final PersonDeleteExecutor executor;

    /**
     * Constructor for a PersonDeleteCommand.
     *
     * @param index Index of the person to be deleted.
     */
    public PersonDeleteCommand(Index index) {
        super(index);
        requireNonNull(index);
        this.executor = new PersonDeleteExecutor(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonDeleteCommand // instanceof handles nulls
                && executor.equals(((PersonDeleteCommand) other).executor)); // state check
    }
}
