package seedu.notor.logic.executors.group;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.group.PeopleInGroupPredicate;

/**
 * Executor for a PersonGroupListCommand.
 */
public class PersonGroupListExecutor extends GroupExecutor {
    private PeopleInGroupPredicate peopleInGroupPredicate;

    public PersonGroupListExecutor(Index index) {
        super(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        requireNonNull(model);
        if (model.isPersonList() && model.isArchiveList()) {
            throw new ExecuteException(Messages.MESSAGE_GROUPS_NOT_LISTED);
        }
        peopleInGroupPredicate = new PeopleInGroupPredicate(super.getGroup());
        model.updateFilteredPersonList(peopleInGroupPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
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
