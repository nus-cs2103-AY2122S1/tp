package seedu.plannermd.testutil.doctor;

import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.plannermd.logic.commands.addcommand.AddCommand;
import seedu.plannermd.logic.commands.editcommand.EditPatientCommand.EditPatientDescriptor;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.tag.Tag;

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
        sb.append(PREFIX_NAME + doctor.getName().fullName + " ");
        sb.append(PREFIX_PHONE + doctor.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + doctor.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + doctor.getAddress().value + " ");
        doctor.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditDoctorDescriptorDetails(EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
