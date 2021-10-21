package seedu.notor.logic.commands.person;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonDeleteExecutor;
import seedu.notor.logic.executors.person.PersonExecutor;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class PersonDeleteCommand extends PersonCommand {
    public static final String COMMAND_WORD = "delete";
    public static final List<String> COMMAND_WORDS = Arrays.asList("delete", "d");

    private static final String COMMAND_DESCRIPTION =
            ": Deletes the person identified by the index number used in the displayed person list.\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " INDEX " + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: none"
            + "Example: "
            + PersonCommand.COMMAND_WORD + " 1 " + COMMAND_WORD;

    private final PersonExecutor executor;

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
