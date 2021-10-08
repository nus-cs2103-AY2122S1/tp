package seedu.plannermd.testutil.doctor;

import static seedu.plannermd.testutil.PersonUtil.getPersonDetails;

import seedu.plannermd.logic.commands.addcommand.AddCommand;
import seedu.plannermd.logic.commands.editcommand.EditPatientCommand.EditPatientDescriptor;
import seedu.plannermd.model.doctor.Doctor;

/**
 * A utility class for Doctor.
 */
public class DoctorUtil {

    /**
     * Returns an add command string for adding the {@code doctor}.
     */
    public static String getAddCommand(Doctor doctor) {
        return AddCommand.COMMAND_WORD + " " + getDoctorDetails(doctor);
    }

    /**
     * Returns the part of command string for the given {@code doctor}'s details.
     */
    public static String getDoctorDetails(Doctor doctor) {
        StringBuilder sb = new StringBuilder();
        sb.append(getPersonDetails(doctor));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditDoctorDescriptorDetails(EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        sb.append(getEditDoctorDescriptorDetails(descriptor));
        return sb.toString();
    }
}
