package seedu.plannermd.logic.commands.tagcommand;

import static seedu.plannermd.logic.parser.CliSyntax.FLAG_ADD;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_DELETE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.plannermd.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Set;

import seedu.plannermd.logic.commands.Command;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.tag.Tag;

/**
 * Represents a command that involves adding or deleting a tag belonging to a Person.
 */
public abstract class TagCommand extends Command {

    public static final String TOO_MANY_TAGS_MESSAGE = "Please enter only one tag.";

    public static final String COMMAND_WORD = "tag";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " {FLAG} {ARGUMENTS}: Executes tag command given by flag tag\n"
            + "Commands: \n"
            + "tag -a: "
                    + "Adds a tag to the person identified by the index number used in the displayed person list.\n"
                    + "Parameters: "
                    + "INDEX (must be a positive integer) " + PREFIX_TAG + "TAG\n"
                    + "Example: " + COMMAND_WORD + " " + FLAG_ADD + " " + "1 " + PREFIX_TAG + "healthy\n"
            + "tag -d: "
                    + "Deletes a tag to the person identified by the index number used in the displayed person list.\n"
                    + "Parameters: "
                    + "INDEX (must be a positive integer) " + PREFIX_TAG + "TAG\n"
                    + "Example: " + COMMAND_WORD + " " + FLAG_DELETE + " " + "1 " + PREFIX_TAG + "healthy";

    /**
     * Updates the tags of the given doctor in the {@code model} and returns the edited doctor
     * with the updated tags.
     * @param model The model containing the doctor list.
     * @param doctorToEdit The doctor whose tag is to be updated.
     * @param tags The updated tags.
     * @return The edited doctor with the updated tag.
     */
    public Doctor setDoctorTags(Model model, Doctor doctorToEdit, Set<Tag> tags) {
        Doctor editedDoctor = new Doctor(doctorToEdit.getName(), doctorToEdit.getPhone(), doctorToEdit.getEmail(),
                doctorToEdit.getAddress(), doctorToEdit.getBirthDate(), doctorToEdit.getRemark(), tags);

        model.setDoctor(doctorToEdit, editedDoctor);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_PERSONS);
        model.editAppointmentsWithPerson(doctorToEdit, editedDoctor);

        return editedDoctor;
    }

    /**
     * Updates the tags of the given Patient in the {@code model} and returns the edited patient
     * with the updated tags.
     * @param model The model containing the patient list.
     * @param patientToEdit The patient whose tag is to be updated.
     * @param tags The updated tags.
     * @return The edited patient with the updated tag.
     */
    public Patient setPatientTags(Model model, Patient patientToEdit, Set<Tag> tags) {
        Patient editedPatient = new Patient(patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getEmail(),
                patientToEdit.getAddress(), patientToEdit.getBirthDate(), patientToEdit.getRemark(), tags,
                patientToEdit.getRisk());

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
        model.editAppointmentsWithPerson(patientToEdit, editedPatient);

        return editedPatient;
    }
}
