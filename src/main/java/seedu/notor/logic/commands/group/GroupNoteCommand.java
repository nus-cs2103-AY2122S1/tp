package seedu.notor.logic.commands.group;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.group.GroupExecutor;
import seedu.notor.logic.executors.group.GroupNoteExecutor;

/**
 *
 */
public class GroupNoteCommand extends GroupCommand {
    public static final String COMMAND_WORD = "note";

    private static final String COMMAND_DESCRIPTION =
            ": Edits the notes of the group identified by the index number used in the current group listing.\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " INDEX " + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: none"
            + "Example: "
            + PersonCommand.COMMAND_WORD + " 1 " + COMMAND_WORD;

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
