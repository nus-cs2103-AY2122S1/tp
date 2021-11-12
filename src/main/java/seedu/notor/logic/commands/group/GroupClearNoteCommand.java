package seedu.notor.logic.commands.group;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.group.GroupClearNoteExecutor;
import seedu.notor.logic.executors.group.GroupExecutor;

/**
 * Clears the note of a group at the given index.
 */
public class GroupClearNoteCommand extends GroupCommand {
    public static final String COMMAND_WORD = "clearnote";
    public static final List<String> COMMAND_WORDS = Arrays.asList("clearnote", "cn");

    private static final String COMMAND_DESCRIPTION =
            ": Clears the notes of the group specified by the group index.\n";

    public static final String MESSAGE_USAGE = GroupCommand.COMMAND_WORD + " INDEX /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: none"
            + "Example: "
            + GroupCommand.COMMAND_WORD + " 1 /" + COMMAND_WORD;

    private final GroupExecutor executor;

    /**
     * @param index Index of the group to clear note for.
     */
    public GroupClearNoteCommand(Index index) {
        super(index);
        requireAllNonNull(index);
        this.executor = new GroupClearNoteExecutor(index);
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
        if (!(other instanceof GroupClearNoteCommand)) {
            return false;
        }
        // state check
        GroupClearNoteCommand e = (GroupClearNoteCommand) other;
        return executor.equals(e.executor);
    }
}
