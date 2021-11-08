package seedu.notor.logic.commands.group;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.group.GroupExecutor;
import seedu.notor.logic.executors.group.GroupNoteExecutor;

/**
 *
 */
public class GroupNoteCommand extends GroupCommand {
    public static final String COMMAND_WORD = "note";
    public static final List<String> COMMAND_WORDS = Arrays.asList("note", "n");

    private static final String COMMAND_DESCRIPTION =
            ": Edits the notes of the group specified by the index.\n";

    public static final String MESSAGE_USAGE = GroupCommand.COMMAND_WORD + " INDEX /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: none"
            + "Example: "
            + GroupCommand.COMMAND_WORD + " 1 /" + COMMAND_WORD;

    private final GroupExecutor executor;

    /**
     * @param index Index of the group to edit note for.
     */
    public GroupNoteCommand(Index index) {
        super(index);
        requireAllNonNull(index);
        this.executor = new GroupNoteExecutor(index);
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
        if (!(other instanceof GroupNoteCommand)) {
            return false;
        }
        // state check
        GroupNoteCommand e = (GroupNoteCommand) other;
        return executor.equals(e.executor);
    }
}
