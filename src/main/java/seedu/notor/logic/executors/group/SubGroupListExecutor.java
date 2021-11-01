package seedu.notor.logic.executors.group;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.group.Group;

public class SubGroupListExecutor extends GroupExecutor {

    public SubGroupListExecutor(Index index) {
        super(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        requireNonNull(model);
        if (!model.isSuperGroupList()) {
            throw new ExecuteException(Messages.MESSAGE_GROUPS_NOT_LISTED);
        }
        List<? extends Group> lastShownList = model.getFilteredGroupList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new ExecuteException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }
        model.listSubGroup(super.index);
        return new CommandResult(
                String.format(Messages.MESSAGE_SUBGROUPS_LISTED_OVERVIEW, model.getFilteredGroupList().size()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SubGroupListExecutor)) {
            return false;
        }
        // state check
        return super.equals(other);
    }
}
