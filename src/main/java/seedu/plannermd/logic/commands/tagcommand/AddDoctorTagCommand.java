package seedu.plannermd.logic.commands.tagcommand;


import static java.util.Objects.requireNonNull;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.tag.Tag;

/**
 * Adds a tag to an existing doctor in the plannermd.
 */
public class AddDoctorTagCommand extends AddTagCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the doctor identified "
            + "by the index number used in the displayed doctor list.\n" + "Parameters: " + PREFIX_ID
            + "ID (must be a positive integer) " + PREFIX_TAG + "TAG\n" + "Example: " + COMMAND_WORD + " " + PREFIX_ID
            + "1 " + PREFIX_TAG + "healthy";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag to Doctor: %1$s";

    private final Index index;
    private final Tag tag;

    /**
     * @param index of the doctor in the filtered doctor list to be added a tag
     * @param tag the tag to be added
     */
    public AddDoctorTagCommand(Index index, Tag tag) {
        requireNonNull(index);
        requireNonNull(tag);

        this.index = index;
        this.tag = tag;
    }

    /**
     * Executes the command to add a tag to a doctor.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} which represents the result after executing
     *         this command.
     * @throws CommandException if index is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Doctor doctorToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> newTags = new HashSet<>(doctorToEdit.getTags());
        newTags.add(tag);
        Doctor editedDoctor = setDoctorTags(model, doctorToEdit, newTags);

        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, editedDoctor));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddDoctorTagCommand)) {
            return false;
        }

        // state check
        AddDoctorTagCommand c = (AddDoctorTagCommand) other;
        return index.equals(c.index) && tag.equals(c.tag);
    }
}

