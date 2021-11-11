package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.intern.commons.core.Messages;
import seedu.intern.commons.core.selection.Selection;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;
import seedu.intern.model.applicant.Applicant;

/**
 * Deletes an applicant identified using it's displayed index from Intern Watcher.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the applicant identified by the index number used in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer) or ALL\n"
            + "Example: " + COMMAND_WORD + " 1 or"
            + COMMAND_WORD + "ALL";

    public static final String MESSAGE_DELETE_APPLICANT_SUCCESS = "Deleted applicant: %1$s";
    public static final String MESSAGE_DELETE_ALL_SUCCESS = " %1$s applicants deleted!";
    public static final String MESSAGE_COMMIT_DELETE = "Delete applicant: %1$s";
    public static final String MESSAGE_COMMIT_DELETE_ALL = "Delete %1$s applicants";
    private final Selection targetSelection;

    public DeleteCommand(Selection targetSelection) {
        this.targetSelection = targetSelection;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (targetSelection.hasIndex()) {
            if (targetSelection.getIndexZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
            }
        }
        if (targetSelection.hasAllSelectFlag() && targetSelection.checkAllSelected()) {
            int length = lastShownList.size();
            for (int i = 0; i < length; i++) {
                Applicant applicantToDelete = lastShownList.get(0);
                model.deleteApplicant(applicantToDelete);
            }
            model.commitInternWatcher(String.format(MESSAGE_COMMIT_DELETE_ALL, String.valueOf(length)));
            return new CommandResult(String.format(MESSAGE_DELETE_ALL_SUCCESS, String.valueOf(length)));
        } else {
            Applicant applicantToDelete = lastShownList.get(targetSelection.getIndexZeroBased());
            model.deleteApplicant(applicantToDelete);
            model.commitInternWatcher(String.format(MESSAGE_COMMIT_DELETE, applicantToDelete));
            return new CommandResult(String.format(MESSAGE_DELETE_APPLICANT_SUCCESS, applicantToDelete));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetSelection.equals(((DeleteCommand) other).targetSelection)); // state check
    }
}
