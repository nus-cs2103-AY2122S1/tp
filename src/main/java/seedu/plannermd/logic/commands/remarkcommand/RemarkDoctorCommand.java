package seedu.plannermd.logic.commands.remarkcommand;

import static seedu.plannermd.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.plannermd.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.model.person.Remark;

import java.util.List;

public class RemarkDoctorCommand extends RemarkCommand {

    public static final String MESSAGE_ADD_DOCTOR_REMARK_SUCCESS = "Added remark to Doctor: %1$s";
    public static final String MESSAGE_DELETE_DOCTOR_REMARK_SUCCESS = "Removed remark from Doctor: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the doctor identified "
            + "by the index number used in the doctor records. "
            + "Existing remark will be overwritten by the input.\n" + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n" + "Example: " + COMMAND_WORD + " 1 " + "r/ Likes to swim.";

    private final Index index;
    private final Remark remark;

    public RemarkDoctorCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Doctor doctorToEdit = lastShownList.get(index.getZeroBased());
        Doctor editedDoctor = new Doctor(doctorToEdit.getName(), doctorToEdit.getPhone(), doctorToEdit.getEmail(),
                doctorToEdit.getAddress(), doctorToEdit.getBirthDate(), remark, doctorToEdit.getTags());

        model.setDoctor(doctorToEdit, editedDoctor);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedDoctor));
    }

    /**
     * Generates a command execution success message based on whether the remark is
     * added to or removed from {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !remark.value.isEmpty()
                ? MESSAGE_ADD_DOCTOR_REMARK_SUCCESS
                : MESSAGE_DELETE_DOCTOR_REMARK_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkDoctorCommand)) {
            return false;
        }

        // state check
        RemarkDoctorCommand e = (RemarkDoctorCommand) other;
        return index.equals(e.index) && remark.equals(e.remark);
    }

}
