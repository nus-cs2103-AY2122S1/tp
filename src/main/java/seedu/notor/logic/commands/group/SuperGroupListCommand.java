package seedu.notor.logic.commands.group;

import java.util.Arrays;
import java.util.List;

import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.commands.person.PersonListCommand;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.group.SuperGroupListExecutor;

/**
 * Lists all groups in Notor.
 */
public class SuperGroupListCommand extends GroupCommand {
    public static final String COMMAND_WORD = "list";
    public static final List<String> COMMAND_WORDS = Arrays.asList("list", "l");

    private static final String COMMAND_DESCRIPTION =
            ": Lists all groups\n";

    public static final String MESSAGE_USAGE = GroupCommand.COMMAND_WORD + " /" + COMMAND_WORD + " "
            + COMMAND_DESCRIPTION
            + "Example: "
            + GroupCommand.COMMAND_WORD + " /" + COMMAND_WORD;

    private final SuperGroupListExecutor executor;

    /**
     * Constructor for a PersonListCommand.
     */
    public SuperGroupListCommand() {
        super(null);
        this.executor = new SuperGroupListExecutor();
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return (other instanceof PersonListCommand); // instanceof handles nulls
    }
}
