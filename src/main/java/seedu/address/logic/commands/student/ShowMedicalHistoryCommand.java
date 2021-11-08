package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.student.Student;

/**
 * Opens a pop-up window showing the full medical history of the student identified by the index specified.
 */
public class ShowMedicalHistoryCommand extends Command {
    public static final String COMMAND_WORD = "showMedical";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the full medical history of the student "
            + "identified by the index number used in the currently displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String SHOWING_MEDICAL_HISTORY_MESSAGE = "Opened medical history window.";

    private final Index targetIndex;

    public ShowMedicalHistoryCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToShow = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(SHOWING_MEDICAL_HISTORY_MESSAGE, false, false, true, studentToShow,
                false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowMedicalHistoryCommand // instanceof handles nulls
                && targetIndex.equals(((ShowMedicalHistoryCommand) other).targetIndex)); // state check
    }
}
