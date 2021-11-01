package seedu.notor.logic.executors.group;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.exceptions.DuplicateItemException;
import seedu.notor.model.group.SuperGroup;

public class SuperGroupCreateExecutor extends GroupExecutor {
    public static final String MESSAGE_SUCCESS = "Group %s created";
    public static final String MESSAGE_DUPLICATE_GROUP = "Group already exists";

    private final SuperGroup superGroup;

    /**
     * Constructor for a SuperGroupCreateExecutor instance.
     *
     * @param index Null value since Command requires no Index.
     * @param superGroup SuperGroup to be created.
     */
    public SuperGroupCreateExecutor(Index index, SuperGroup superGroup) {
        super(index);
        this.superGroup = superGroup;
    }

    @Override public CommandResult execute() throws ExecuteException {
        try {
            model.addSuperGroup(superGroup);
            return new CommandResult(String.format(MESSAGE_SUCCESS, superGroup));
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
        if (!(other instanceof SuperGroupCreateExecutor)) {
            return false;
        }

        SuperGroupCreateExecutor e = (SuperGroupCreateExecutor) other;

        // state check
        return super.equals(other) && superGroup.equals(e.superGroup);
    }
}
