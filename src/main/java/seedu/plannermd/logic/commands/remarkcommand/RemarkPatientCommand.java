package seedu.plannermd.logic.commands.remarkcommand;

import static seedu.plannermd.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.plannermd.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.model.person.Remark;

public class RemarkPatientCommand extends RemarkCommand {

    public static final String MESSAGE_ADD_PATIENT_REMARK_SUCCESS = "Added remark to Patient: %1$s";
    public static final String MESSAGE_DELETE_PATIENT_REMARK_SUCCESS = "Removed remark from Patient: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the patient identified "
            + "by the index number used in the patient records. "
            + "Existing remark will be overwritten by the input.\n" + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n" + "Example: " + COMMAND_WORD + " 1 " + "r/ Likes to swim.";

    private final Index index;
    private final Remark remark;

    /**
     * Creates a RemarkPatientCommand.
     * @param index Index of the patient in the filtered patient list to add the remark to.
     * @param remark The remark to add.
     */
    public RemarkPatientCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPatient = new Patient(patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getEmail(),
                patientToEdit.getAddress(), patientToEdit.getBirthDate(), remark, patientToEdit.getTags(),
                patientToEdit.getRisk());

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPatient));
    }

    /**
     * Generates a command execution success message based on whether the remark is
     * added to or removed from {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !remark.value.isEmpty()
                ? MESSAGE_ADD_PATIENT_REMARK_SUCCESS
                : MESSAGE_DELETE_PATIENT_REMARK_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkPatientCommand)) {
            return false;
        }

        // state check
        RemarkPatientCommand e = (RemarkPatientCommand) other;
        return index.equals(e.index) && remark.equals(e.remark);
    }

}
