package seedu.plannermd.logic.commands.tagcommand;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.plannermd.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.tag.Tag;

/**
 * Adds a tag to an existing patient in the plannermd.
 */
public class AddPatientTagCommand extends AddTagCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the person identified "
            + "by the index number used in the displayed person list.\n" + "Parameters: " + PREFIX_ID
            + "ID (must be a positive integer) " + PREFIX_TAG + "TAG\n" + "Example: " + COMMAND_WORD + " " + PREFIX_ID
            + "1 " + PREFIX_TAG + "healthy";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag to Patient: %1$s";
    public static final String MESSAGE_NOT_ADDED = "A tag must be provided.";

    private final Index index;
    private final Tag tag;

    /**
     * @param index of the person in the filtered person list to be added a tag
     * @param tag   the tag to be added
     */
    public AddPatientTagCommand(Index index, Tag tag) {
        requireNonNull(index);
        requireNonNull(tag);

        this.index = index;
        this.tag = tag;
    }

    /**
     * Executes the command to add a tag to a patient.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} which represents the result after executing
     *         this command.
     * @throws CommandException if index is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> newTags = new HashSet<>(patientToEdit.getTags());
        newTags.add(tag);
        Patient editedPatient = new Patient(patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getEmail(),
                patientToEdit.getAddress(), patientToEdit.getBirthDate(), patientToEdit.getRemark(), newTags,
                patientToEdit.getRisk());

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, editedPatient));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPatientTagCommand)) {
            return false;
        }

        // state check
        AddPatientTagCommand c = (AddPatientTagCommand) other;
        return index.equals(c.index) && tag.equals(c.tag);
    }
}
