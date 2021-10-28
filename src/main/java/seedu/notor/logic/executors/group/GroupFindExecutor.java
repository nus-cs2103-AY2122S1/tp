package seedu.notor.logic.executors.group;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.Messages;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.group.GroupContainsPredicate;

public class GroupFindExecutor extends GroupExecutor {
    private final GroupContainsPredicate predicate;

    public GroupFindExecutor(GroupContainsPredicate predicate) {
        super(null);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        requireNonNull(model);
        model.updateFilteredGroupList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_GROUPS_LISTED_OVERVIEW, model.getFilteredGroupList().size()));
    }
}
