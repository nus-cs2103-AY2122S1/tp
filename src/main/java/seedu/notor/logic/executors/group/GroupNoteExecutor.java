package seedu.notor.logic.executors.group;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonNoteExecutor;
import seedu.notor.model.group.Group;

/**
 * Executor for a GroupNoteCommand.
 */
public class GroupNoteExecutor extends GroupExecutor {
    public static final String MESSAGE_OPEN_NOTE_SUCCESS = "Opened note for Group: %1$s";


    /**
     * Constructor for a GroupNoteExecutor instance.
     *
     * @param index Index of the person to add a note to.
     *
     */
    public GroupNoteExecutor(Index index) {
        super(index);
        requireAllNonNull(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        Group group = super.getGroup();
        return new CommandResult(generateSuccessMessage(group), false, true, false, group);
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Group group) {
        return String.format(MESSAGE_OPEN_NOTE_SUCCESS, group);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonNoteExecutor)) {
            return false;
        }

        PersonNoteExecutor e = (PersonNoteExecutor) other;

        // state check
        return super.equals(other);
    }
}
