package seedu.notor.logic.commands.group;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.group.GroupExecutor;
import seedu.notor.logic.executors.group.SubGroupCreateExecutor;
import seedu.notor.model.group.SubGroup;

public class SubGroupCreateCommand extends GroupCommand {
    public static final String COMMAND_WORD = "create";
    public static final List<String> COMMAND_WORDS = Arrays.asList("create", "c");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a group. "
            + "Parameters: "
            + COMMAND_WORD
            + " GROUP_NAME \n"
            + "Example: " + COMMAND_WORD
            + "Team 1";

    private final GroupExecutor executor;

    /**
     * constructor for a SubGroupCreateCommand instance.
     *
     * @param index Index of the Group to create a SubGroup for.
     * @param subGroup SubGroup to be created.
     */
    public SubGroupCreateCommand(Index index, SubGroup subGroup) {
        super(index);
        this.executor = new SubGroupCreateExecutor(index, subGroup);
    }


    @Override public CommandResult execute() throws CommandException, ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SubGroupCreateCommand)) {
            return false;
        }

        SubGroupCreateCommand c = (SubGroupCreateCommand) other;
        // state check
        return super.equals(other) && executor.equals(c.executor);
    }
}
