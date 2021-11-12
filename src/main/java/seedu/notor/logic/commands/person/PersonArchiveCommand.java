package seedu.notor.logic.commands.person;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonArchiveExecutor;
import seedu.notor.logic.executors.person.PersonExecutor;

/**
 * Archives a person identified using its displayed index.
 */
public class PersonArchiveCommand extends PersonCommand {
    public static final String COMMAND_WORD = "archive";
    public static final List<String> COMMAND_WORDS = Arrays.asList("archive", "ar");

    private static final String COMMAND_DESCRIPTION = ": Archives the person specified by the index number.\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " INDEX /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: none\n"
            + "Example: "
            + PersonCommand.COMMAND_WORD + " 1 /" + COMMAND_WORD;

    private final PersonExecutor executor;

    /**
     * Constructor for a PersonArchiveCommand instance.
     *
     * @param index Index of the person to be archived.
     */
    public PersonArchiveCommand(Index index) {
        super(index);
        requireNonNull(index);
        this.executor = new PersonArchiveExecutor(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonArchiveCommand // instanceof handles nulls
                && executor.equals(((PersonArchiveCommand) other).executor)); // state check
    }
}
