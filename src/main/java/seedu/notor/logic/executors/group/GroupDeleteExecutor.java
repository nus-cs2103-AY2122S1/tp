package seedu.notor.logic.executors.group;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.group.Group;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.ui.WarningWindow;
import seedu.notor.ui.note.NoteWindow;

public class GroupDeleteExecutor extends GroupExecutor {
    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Deleted Group: %1$s";
    public static final String MESSAGE_DELETE_GROUP_CANCEL = "Cancelled deleting of Group: %1$s";
    public static final String MESSAGE_DELETE_GROUP_FAILURE =
            "Unable to delete Group: %1$s as note window for the Group is currently opened.";
    public static final String CONFIRMATION_MESSAGE = "Do you want to proceed with deleting of Group: %1$s?";


    public GroupDeleteExecutor(Index index) {
        super(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        Group deletedGroup = super.getGroup();
        if (NoteWindow.contains(deletedGroup)) {
            throw new ExecuteException(String.format(MESSAGE_DELETE_GROUP_FAILURE, deletedGroup));
        }
        WarningWindow warningWindow = new WarningWindow(String.format(CONFIRMATION_MESSAGE, deletedGroup));
        warningWindow.show();
        if (warningWindow.canContinue()) {
            if (deletedGroup instanceof SuperGroup) {
                model.deleteSuperGroup((SuperGroup) deletedGroup);
            } else if (deletedGroup instanceof SubGroup) {
                model.deleteSubGroup((SubGroup) deletedGroup);
            } else {
                throw new ExecuteException(Messages.MESSAGE_UNEXPECTED_ERROR);
            }
            return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, deletedGroup));
        }
        return new CommandResult(String.format(MESSAGE_DELETE_GROUP_CANCEL, deletedGroup));
    }
}
