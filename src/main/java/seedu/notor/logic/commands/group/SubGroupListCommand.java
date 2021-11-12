package seedu.notor.logic.commands.group;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.group.SubGroupListExecutor;

/**
 * Lists all subgroups in Notor.
 */
public class SubGroupListCommand extends GroupCommand {
    public static final String COMMAND_WORD = "list";
    public static final List<String> COMMAND_WORDS = Arrays.asList("list", "l");

    private static final String COMMAND_DESCRIPTION =
            ": Lists all subgroups\n";

    public static final String MESSAGE_USAGE = GroupCommand.COMMAND_WORD + " INDEX /" + COMMAND_WORD + " "
            + COMMAND_DESCRIPTION
            + "Example: "
            + GroupCommand.COMMAND_WORD + " 1 /" + COMMAND_WORD;

    private final SubGroupListExecutor executor;

    /**
     * Constructor for a SubgroupListCommand.
     */
    public SubGroupListCommand(Index index) {
        super(index);
        requireNonNull(index);
        this.executor = new SubGroupListExecutor(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return (other instanceof SubGroupListCommand); // instanceof handles nulls
    }
}
