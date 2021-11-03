package seedu.notor.logic.executors.group;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.Messages;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;

/**
 * Executor for a PersonListCommand.
 */
public class SuperGroupListExecutor extends GroupExecutor {

    public SuperGroupListExecutor() {
        super(null);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        requireNonNull(model);
        model.listSuperGroup();
        return new CommandResult(
                String.format(Messages.MESSAGE_GROUPS_LISTED_OVERVIEW, model.getFilteredGroupList().size()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SuperGroupListExecutor)) {
            return false;
        }
        // state check
        return super.equals(other);
    }
}
