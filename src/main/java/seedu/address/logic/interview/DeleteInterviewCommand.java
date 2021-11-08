package seedu.address.logic.interview;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

public class DeleteInterviewCommand extends Command {
    public static final String COMMAND_WORD = "delete_i";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the interview identified by the index number used in the displayed interview list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INTERVIEW_SUCCESS = "Deleted Interview: %1$s";

    private final Index targetIndex;

    public DeleteInterviewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Interview> lastShownList = model.getFilteredInterviewList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interviewToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteInterview(interviewToDelete);
        model.deleteInterviewFromPerson(interviewToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_INTERVIEW_SUCCESS, interviewToDelete.getDisplayString()),
                CommandResult.CommandType.INTERVIEW);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteInterviewCommand// instanceof handles nulls
                && targetIndex.equals(((DeleteInterviewCommand) other).targetIndex)); // state check
    }
}
