package seedu.notor.logic.executors.group;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;

public class GroupDeleteExecutor extends GroupExecutor {
    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Deleted Group: %1$s";

    public GroupDeleteExecutor(Index index) {
        super(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        // TODO: To update after list is done.
        if (true) {
            SuperGroup deletedGroup = super.getGroup();
            model.deleteSuperGroup(deletedGroup);
            return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, deletedGroup));
        } else if (model.isSubGroupView()) {
            SubGroup deletedSubGroup = super.getSubGroup();
            model.deleteSubGroup(deletedSubGroup);
            return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, deletedSubGroup));
        } else {
            throw new ExecuteException(Messages.MESSAGE_GROUPS_NOT_LISTED);
        }
    }
}
