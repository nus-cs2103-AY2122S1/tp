package seedu.notor.logic.executors.group;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.exceptions.DuplicateItemException;
import seedu.notor.model.group.SubGroup;

public class SubGroupCreateExecutor extends GroupExecutor {
    public static final String MESSAGE_SUCCESS = "Subgroup %s created.";
    public static final String MESSAGE_DUPLICATE_GROUP = "Subgroup %1$s already exists.";

    private final SubGroup subGroup;

    /**
     * Constructor for a SubGroupCreateExecutor instance.
     *
     * @param index Index of the Group to create a SubGroup for.
     * @param subGroup SubGroup to be created.
     */
    public SubGroupCreateExecutor(Index index, SubGroup subGroup) {
        super(index);
        this.subGroup = subGroup;
    }

    @Override public CommandResult execute() throws ExecuteException {
        try {
            if (model.isSuperGroupList()) {
                if (model.getFilteredGroupList().size() >= index.getOneBased()) {
                    model.addSubGroup(index, subGroup);
                    return new CommandResult(String.format(MESSAGE_SUCCESS, subGroup));
                } else {
                    throw new ExecuteException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
                }
            }
            throw new ExecuteException(Messages.MESSAGE_GROUPS_NOT_LISTED);
        } catch (DuplicateItemException e) {
            throw new ExecuteException(String.format(MESSAGE_DUPLICATE_GROUP, subGroup));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SubGroupCreateExecutor)) {
            return false;
        }

        SubGroupCreateExecutor e = (SubGroupCreateExecutor) other;

        // state check
        return super.equals(other) && subGroup.equals(e.subGroup);
    }
}
