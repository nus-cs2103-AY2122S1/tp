package seedu.notor.logic.commands.person;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.group.PersonGroupListExecutor;

/**
 * List all persons in a particular group.
 */
public class PersonGroupListCommand extends PersonCommand {
    public static final String COMMAND_WORD = "list";
    public static final List<String> COMMAND_WORDS = Arrays.asList("list", "l");

    private static final String COMMAND_DESCRIPTION =
            ": Lists all persons in a group\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + "INDEX /" + COMMAND_WORD + " "
            + COMMAND_DESCRIPTION
            + "Example: " + PersonCommand.COMMAND_WORD + "1 /" + COMMAND_WORD;

    private final PersonGroupListExecutor executor;

    /**
     * Constructor for a PersonGroupListCommand.
     */
    public PersonGroupListCommand(Index index) {
        super(index);
        requireNonNull(index);
        this.executor = new PersonGroupListExecutor(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return (other instanceof PersonGroupListCommand); // instanceof handles nulls
    }
}
