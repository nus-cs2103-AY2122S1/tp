package seedu.notor.logic.commands.person;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.commands.group.GroupCommand;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.group.GroupDeleteExecutor;

/**
 * Deletes a group identified using it's displayed index from the notor.
 */
public class GroupDeleteCommand extends GroupCommand {

    public static final String COMMAND_WORD = "delete";

    private static final String COMMAND_DESCRIPTION =
            ": Deletes the group identified by the index number used in the displayed group list.\n";

    public static final String MESSAGE_USAGE = GroupCommand.COMMAND_WORD + " INDEX " + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: none"
            + "Example: "
            + GroupCommand.COMMAND_WORD + " 1 " + COMMAND_WORD;

    private final GroupDeleteExecutor executor;

    /**
     * Constructor for a GroupDeleteCommand.
     *
     * @param index Index of the person to be deleted.
     */
    public GroupDeleteCommand(Index index) {
        super(index);
        requireNonNull(index);
        this.executor = new GroupDeleteExecutor(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupDeleteCommand // instanceof handles nulls
                && executor.equals(((GroupDeleteCommand) other).executor)); // state check
    }
}
