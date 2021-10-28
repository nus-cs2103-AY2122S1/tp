package seedu.notor.logic.executors.person;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;

/**
 * Executor for a PersonGroupListCommand.
 */
public class PersonGroupListExecutor extends PersonExecutor {

    public PersonGroupListExecutor(Index index) {
        super(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        requireNonNull(model);
        if (!model.isSuperGroupList()) {
            throw new ExecuteException(Messages.MESSAGE_GROUPS_NOT_LISTED);
        }
        // TODO: Should we create a new method lol in model.
        model.listPersonInGroup(super.index);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredGroupList().size()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonGroupListExecutor)) {
            return false;
        }

        // state check
        return super.equals(other);
    }
}
