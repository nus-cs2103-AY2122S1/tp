package seedu.notor.logic.executors.group;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.common.Note;
import seedu.notor.model.group.Group;
import seedu.notor.ui.WarningWindow;

/**
 * Executor for a GroupClearNoteCommand.
 */
public class GroupClearNoteExecutor extends GroupExecutor {
    public static final String MESSAGE_CLEAR_GROUP_NOTE_SUCCESS = "Opened note for Group: %1$s";
    public static final String MESSAGE_CLEAR_GROUP_NOTE_CANCEL = "Clearing of Note for Group: %1$s has been cancelled.";
    public static final String CONFIRMATION_MESSAGE = "Do you want to proceed with Clearing of Note for Group: %1$s?";


    /**
     * Constructor for a GroupNoteClearExecutor instance.
     *
     * @param index Index of the person to add a note to.
     *
     */
    public GroupClearNoteExecutor(Index index) {
        super(index);
        requireAllNonNull(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        Group group = super.getGroup();
        WarningWindow warningWindow = new WarningWindow(String.format(CONFIRMATION_MESSAGE, group));
        warningWindow.show();
        if (warningWindow.canContinue()) {
            group.setNote(Note.EMPTY_NOTE);
            return new CommandResult(String.format(MESSAGE_CLEAR_GROUP_NOTE_SUCCESS, group));
        }
        return new CommandResult(MESSAGE_CLEAR_GROUP_NOTE_CANCEL);
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Group group) {
        return String.format(MESSAGE_CLEAR_GROUP_NOTE_SUCCESS, group);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupClearNoteExecutor)) {
            return false;
        }

        GroupClearNoteExecutor e = (GroupClearNoteExecutor) other;

        return super.equals(other);
    }
}
