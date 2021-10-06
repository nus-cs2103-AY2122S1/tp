package seedu.plannermd.logic.commands.tagcommand;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.plannermd.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.tag.Tag;

/**
 * Deletes a tag from an existing patient in the plannermd.
 */
public class DeletePatientTagCommand extends DeleteTagCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a tag from the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: "
            + PREFIX_ID + "ID (must be a positive integer) "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ID + "1 "
            + PREFIX_TAG + "healthy";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag from Patient: %1$s";
    public static final String MESSAGE_INVALID_TAG = "The tag does not exist.";

    private final Index index;
    private final Tag tag;

    /**
     * @param index of the person in the filtered person list for deleting a tag
     * @param tag   the tag to be deleted
     */
    public DeletePatientTagCommand(Index index, Tag tag) {
        requireNonNull(index);
        requireNonNull(tag);

        this.index = index;
        this.tag = tag;
    }

    /**
     * Executes the command to delete a tag from a patient.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} which represents the result after executing this command.
     * @throws CommandException if index or tag is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> existingTags = new HashSet<>(patientToEdit.getTags());

        if (!existingTags.contains(tag)) {
            throw new CommandException(MESSAGE_INVALID_TAG);
        }

        existingTags.remove(tag);
        Patient editedPatient = new Patient(
                patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getEmail(),
                patientToEdit.getAddress(), patientToEdit.getRemark(), existingTags, patientToEdit.getRisk()
        );

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, editedPatient));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePatientTagCommand)) {
            return false;
        }

        // state check
        DeletePatientTagCommand c = (DeletePatientTagCommand) other;
        return index.equals(c.index)
                && tag.equals(c.tag);
    }
}
