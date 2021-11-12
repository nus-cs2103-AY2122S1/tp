package seedu.notor.logic.commands.person;

import java.util.Arrays;
import java.util.List;

import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonArchiveShowExecutor;
import seedu.notor.logic.executors.person.PersonExecutor;

public class PersonArchiveShowCommand extends PersonCommand {
    public static final String COMMAND_WORD = "listarchive";
    public static final List<String> COMMAND_WORDS = Arrays.asList("listarchive", "lar");

    private static final String COMMAND_DESCRIPTION =
            ": Lists out all archived persons.\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: none"
            + "Example: "
            + PersonCommand.COMMAND_WORD + " /" + COMMAND_WORD;

    private final PersonExecutor executor;

    /**
     * Constructor for a PersonArchiveShowCommand.
     */
    public PersonArchiveShowCommand() {
        super(null);
        this.executor = new PersonArchiveShowExecutor();
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonArchiveShowCommand // instanceof handles nulls
                && executor.equals(((PersonArchiveShowCommand) other).executor)); // state check
    }
}
