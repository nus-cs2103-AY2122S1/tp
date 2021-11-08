package seedu.plannermd.testutil.patient;

import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.plannermd.testutil.PersonUtil.getEditPersonDescriptorDetails;
import static seedu.plannermd.testutil.PersonUtil.getPersonDetails;

import seedu.plannermd.logic.commands.addcommand.AddPatientCommand;
import seedu.plannermd.logic.commands.editcommand.EditPatientCommand;
import seedu.plannermd.model.patient.Patient;

public class PatientUtil {

    /**
     * Returns an add command string for adding the {@code patient}.
     */
    public static String getAddCommand(Patient patient) {
        return AddPatientCommand.COMMAND_WORD + " " + getPatientDetails(patient);
    }

    /**
     * Returns the part of command string for the given {@code patient}'s details.
     */
    public static String getPatientDetails(Patient patient) {
        StringBuilder sb = new StringBuilder();
        sb.append(getPersonDetails(patient));
        sb.append(PREFIX_RISK + patient.getRisk().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditPatientDescriptorDetails(EditPatientCommand.EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        sb.append(getEditPersonDescriptorDetails(descriptor));
        descriptor.getRisk().ifPresent(risk -> sb.append(PREFIX_RISK).append(risk).append(" "));
        return sb.toString();
    }
}
