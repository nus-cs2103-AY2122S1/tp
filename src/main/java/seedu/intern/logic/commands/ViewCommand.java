// @@author nicolej2122
package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.intern.commons.core.Messages;
import seedu.intern.commons.core.selection.Index;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;
import seedu.intern.model.applicant.Applicant;

/**
 * User can view applicant details in Intern Watcher.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the applicant details identified by the index number used in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer) [T] (case sensitive)\n"
            + "Example: " + COMMAND_WORD + " 1 T";


    public static final String MESSAGE_VIEW_APPLICANT_SUCCESS = "Displayed Applicant details: %1$s";

    private final Index targetIndex;
    private final Boolean toggle;

    /**
     * User can view applicant details in Intern Watcher.
     */
    public ViewCommand(Index index, Boolean toggle) {
        this.targetIndex = index;
        this.toggle = toggle;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (targetIndex != null) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
            }
        }
        if (toggle) {
            Applicant applicantToView = lastShownList.get(targetIndex.getZeroBased());
            model.displayApplicant(applicantToView, true);
            return new CommandResult(String.format(MESSAGE_VIEW_APPLICANT_SUCCESS,
                    applicantToView), false, false, true);
        } else {
            Applicant applicantToView = lastShownList.get(targetIndex.getZeroBased());
            model.displayApplicant(applicantToView, false);
            return new CommandResult(String.format(MESSAGE_VIEW_APPLICANT_SUCCESS,
                    applicantToView), false, false, true);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }
}
