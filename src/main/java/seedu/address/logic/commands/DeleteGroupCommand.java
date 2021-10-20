package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class DeleteGroupCommand extends Command {
    public static final String COMMAND_WORD = "deletecg";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the group identified by the index number used in the displayed group list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Deleted Group: %1$s";

    private final Index targetIndex;

    /**
     * Constructor for DeleteClassCommand
     *
     * @param targetIndex Index to be deleted.
     */
    public DeleteGroupCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TutorialGroup> lastShownList = model.getFilteredTutorialGroupList();

        assert targetIndex.getZeroBased() >= 0;
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        TutorialGroup groupToDelete = lastShownList.get(targetIndex.getZeroBased());
        assert groupToDelete != null;
        model.deleteTutorialGroup(groupToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteGroupCommand
                && targetIndex.equals(((DeleteGroupCommand) other).targetIndex));
    }
}
