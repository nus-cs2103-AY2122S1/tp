package seedu.plannermd.logic.commands.tagcommand;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.tag.Tag;

/**
 * Deletes a tag from an existing doctor in the plannermd.
 */
public class DeleteDoctorTagCommand extends DeleteTagCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a tag from the doctor identified "
            + "by the index number used in the displayed doctor list.\n" + "Parameters: " + PREFIX_ID
            + "ID (must be a positive integer) " + PREFIX_TAG + "TAG\n" + "Example: " + COMMAND_WORD + " " + PREFIX_ID
            + "1 " + PREFIX_TAG + "healthy";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag from Doctor: %1$s";
    public static final String MESSAGE_INVALID_TAG = "The tag does not exist.";

    private final Index index;
    private final Tag tag;

    /**
     * @param index of the person in the filtered person list for deleting a tag
     * @param tag the tag to be deleted
     */
    public DeleteDoctorTagCommand(Index index, Tag tag) {
        requireNonNull(index);
        requireNonNull(tag);

        this.index = index;
        this.tag = tag;
    }

    /**
     * Executes the command to delete a tag from a doctor.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} which represents the result after executing
     *         this command.
     * @throws CommandException if index or tag is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Doctor doctorToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> existingTags = new HashSet<>(doctorToEdit.getTags());

        if (!existingTags.contains(tag)) {
            throw new CommandException(MESSAGE_INVALID_TAG);
        }

        existingTags.remove(tag);
        Doctor editedDoctor = setDoctorTags(model, doctorToEdit, existingTags);

        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, editedDoctor));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteDoctorTagCommand)) {
            return false;
        }

        // state check
        DeleteDoctorTagCommand c = (DeleteDoctorTagCommand) other;
        return index.equals(c.index) && tag.equals(c.tag);
    }
}
