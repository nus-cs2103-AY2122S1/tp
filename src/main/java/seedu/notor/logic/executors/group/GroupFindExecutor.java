package seedu.notor.logic.executors.group;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.Messages;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.group.GroupContainsPredicate;

/**
 * Executor for the GroupFindCommand
 */
public class GroupFindExecutor extends GroupExecutor {
    private final GroupContainsPredicate predicate;

    /**
     * Constructor for the executor
     *
     * @param predicate used to filter groups
     */
    public GroupFindExecutor(GroupContainsPredicate predicate) {
        super(null);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        requireNonNull(model);
        if (model.isPersonList() || model.isArchiveList()) {
            throw new ExecuteException(Messages.MESSAGE_GROUPS_NOT_LISTED);
        } else {
            model.updateFilteredGroupList(predicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_GROUPS_LISTED_OVERVIEW, model.getFilteredGroupList().size()));
    }
}
