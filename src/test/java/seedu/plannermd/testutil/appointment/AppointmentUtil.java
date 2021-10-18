package seedu.plannermd.testutil.appointment;

import seedu.plannermd.logic.commands.editcommand.EditDoctorCommand;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.testutil.PersonUtil;

public class AppointmentUtil {
    /**
     * Returns an add command string for adding the {@code appointment}.
     */
    public static String getAddAppointmentCommand(Doctor doctor) {
        //TODO: implement this
        return "";
    }

    /**
     * Returns the part of command string for the given {@code appointment}'s details.
     */
    public static String getAppointmentDetails(Appointment appointment) {
        //TODO: implement this
        StringBuilder sb = new StringBuilder();
        sb.append(getAppointmentDetails(appointment));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditAppointmentDescriptor}'s details.
     */
    public static String getEditAppointmentDescriptorDetails(EditDoctorCommand.EditDoctorDescriptor descriptor) {
        //TODO: implement this
        StringBuilder sb = new StringBuilder();
        sb.append(PersonUtil.getEditDoctorDescriptorDetails(descriptor));
        return sb.toString();
    }
}




