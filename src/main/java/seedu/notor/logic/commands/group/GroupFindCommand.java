package seedu.notor.logic.commands.group;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.group.GroupFindExecutor;
import seedu.notor.model.group.GroupContainsPredicate;

/**
 * Deletes a group identified using it's displayed index from the notor.
 */
public class GroupFindCommand extends GroupCommand {
    public static final String COMMAND_WORD = "find";
    public static final List<String> COMMAND_WORDS = Arrays.asList("find", "f");

    private static final String COMMAND_DESCRIPTION = ": Finds a group via searching their names for a given query. \n"
            + "Returns the group list filtered by those who match the query\n";

    public static final String MESSAGE_USAGE = GroupCommand.COMMAND_WORD + " /" + COMMAND_WORD + " "
            + COMMAND_DESCRIPTION
            + "Parameters: n:NAME_QUERY \n"
            + "Example: "
            + GroupCommand.COMMAND_WORD + " /" + COMMAND_WORD + "n:Help";

    private final GroupFindExecutor executor;

    /**
     * Constructor for a GroupFindCommand.
     */
    public GroupFindCommand(GroupContainsPredicate predicate) {
        super(null);
        requireNonNull(predicate);
        this.executor = new GroupFindExecutor(predicate);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupFindCommand // instanceof handles nulls
                && executor.equals(((GroupFindCommand) other).executor)); // state check
    }
}
