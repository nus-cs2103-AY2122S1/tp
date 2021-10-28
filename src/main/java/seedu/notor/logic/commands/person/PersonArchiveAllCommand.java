package seedu.notor.logic.commands.person;

import java.util.Arrays;
import java.util.List;

import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonArchiveAllExecutor;
import seedu.notor.logic.executors.person.PersonExecutor;

/**
 * Archives all persons currently displayed.
 */
public class PersonArchiveAllCommand extends PersonCommand {
    public static final String COMMAND_WORD = "archive";
    public static final List<String> COMMAND_WORDS = Arrays.asList("archive", "ar");

    private static final String COMMAND_DESCRIPTION =
            ": Archives the persons currently displayed in Notor.\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: none\n"
            + "Example: "
            + PersonCommand.COMMAND_WORD + " /" + COMMAND_WORD;

    private final PersonExecutor executor;

    /**
     * Constructor for a PersonArchiveAllCommand instance.
     */
    public PersonArchiveAllCommand() {
        super(null);
        this.executor = new PersonArchiveAllExecutor();
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonArchiveAllCommand // instanceof handles nulls
                && executor.equals(((PersonArchiveAllCommand) other).executor)); // state check
    }
}
