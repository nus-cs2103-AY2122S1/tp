package seedu.notor.logic.executors.group;

import java.util.List;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.executors.Executor;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.group.Group;

public abstract class GroupExecutor extends Executor {
    protected final Index index;

    public GroupExecutor(Index index) {
        this.index = index;
    }

    protected Group getGroup() throws ExecuteException {
        checkGroupView();
        List<? extends Group> lastShownList = model.getFilteredGroupList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new ExecuteException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        return lastShownList.get(index.getZeroBased());
    }

    protected void checkGroupView() throws ExecuteException {
        if (model.isPersonList() || model.isArchiveList()) {
            throw new ExecuteException(Messages.MESSAGE_GROUPS_OR_SUBGROUP_NOT_LISTED);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupExecutor)) {
            return false;
        }

        GroupExecutor e = (GroupExecutor) other;

        if (index == null) {
            return e.index == null;
        }

        // state check
        return index.equals(e.index);
    }
}
