package seedu.notor.logic.commands.person;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonExecutor;
import seedu.notor.logic.executors.person.PersonUnarchiveExecutor;

public class PersonUnarchiveCommand extends PersonCommand {
    public static final String COMMAND_WORD = "unarchive";
    public static final List<String> COMMAND_WORDS = Arrays.asList("unarchive", "uar");

    private static final String COMMAND_DESCRIPTION =
            ": Unarchives the person specified by the index.\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " INDEX /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: none\n"
            + "Example: "
            + PersonCommand.COMMAND_WORD + " 1 /" + COMMAND_WORD;

    private final PersonExecutor executor;

    /**
     * Constructor for a PersonUnarchiveCommand.
     *
     * @param index Index of the person to be unarchived.
     */
    public PersonUnarchiveCommand(Index index) {
        super(index);
        requireNonNull(index);
        this.executor = new PersonUnarchiveExecutor(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonUnarchiveCommand // instanceof handles nulls
                && executor.equals(((PersonUnarchiveCommand) other).executor)); // state check
    }
}
