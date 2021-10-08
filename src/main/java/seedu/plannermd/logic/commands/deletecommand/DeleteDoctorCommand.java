package seedu.plannermd.logic.commands.deletecommand;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.doctor.Doctor;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeleteDoctorCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the doctor identified by the index number used in the displayed doctor list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DOCTOR_SUCCESS = "Deleted Doctor: %1$s";

    private final Index targetIndex;

    public DeleteDoctorCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }

        Doctor doctorToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteDoctor(doctorToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_DOCTOR_SUCCESS, doctorToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDoctorCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteDoctorCommand) other).targetIndex)); // state check
    }

}
