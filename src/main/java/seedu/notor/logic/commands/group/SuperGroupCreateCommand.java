package seedu.notor.logic.commands.group;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.group.GroupExecutor;
import seedu.notor.logic.executors.group.SuperGroupCreateExecutor;
import seedu.notor.model.group.SuperGroup;

public class SuperGroupCreateCommand extends GroupCommand {

    public static final String COMMAND_WORD = "create";
    public static final List<String> COMMAND_WORDS = Arrays.asList("create", "c");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a group. "
            + "Parameters: "
            + COMMAND_WORD
            + "GROUP \n"
            + "Example: " + COMMAND_WORD
            + "Orbital";

    private final GroupExecutor executor;

    /**
     * Creates a SuperGroupCreateCommand to add the specified {@code Group}
     */
    public SuperGroupCreateCommand(Index index, SuperGroup superGroup) {
        super(index);
        requireNonNull(superGroup);
        this.executor = new SuperGroupCreateExecutor(index, superGroup);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SuperGroupCreateCommand)) {
            return false;
        }

        SuperGroupCreateCommand c = (SuperGroupCreateCommand) other;
        // state check
        return super.equals(other) && executor.equals(c.executor);
    }
}
