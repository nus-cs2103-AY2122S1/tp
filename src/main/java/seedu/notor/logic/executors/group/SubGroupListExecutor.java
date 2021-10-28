package seedu.notor.logic.executors.group;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;

public class SubGroupListExecutor extends GroupExecutor {
    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Deleted Group: %1$s";
    public static final String MESSAGE_DELETE_GROUP_CANCEL = "Deleting of Group: %1$s has been cancelled.";
    public static final String CONFIRMATION_MESSAGE = "Do you want to proceed with deleting of Group: %1$s?";


    public SubGroupListExecutor(Index index) {
        super(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        requireNonNull(model);
        if (!model.isSuperGroupList()) {
            throw new ExecuteException(Messages.MESSAGE_GROUPS_NOT_LISTED);
        }
        // TODO: Should we create a new method lol in model.
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
