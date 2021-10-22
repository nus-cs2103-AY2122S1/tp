package seedu.notor.logic.executors.group;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.exceptions.DuplicateItemException;
import seedu.notor.model.group.Group;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;

public class SubGroupCreateExecutor extends GroupExecutor {
    public static final String MESSAGE_SUCCESS = "Subgroup %s created";
    public static final String MESSAGE_DUPLICATE_GROUP = "Subgroup already exists";

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
            if (model.getFilteredGroupList().size() > index.getOneBased()) {
                Group group = model.getFilteredGroupList().get(index.getZeroBased());
                if (group instanceof SuperGroup) {
                    SuperGroup superGroup = (SuperGroup) group;
                    superGroup.addSubGroup(subGroup);
                    subGroup.setParent(superGroup);
                    return new CommandResult(String.format(MESSAGE_SUCCESS, subGroup));
                }
                throw new ExecuteException(Messages.MESSAGE_GROUPS_NOT_LISTED);
            } else {
                // TODO: stub error message, this is supposed to be for when index is out of bounds.
                throw new ExecuteException("");
            }
        } catch (DuplicateItemException e) {
            throw new ExecuteException(MESSAGE_DUPLICATE_GROUP);
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
