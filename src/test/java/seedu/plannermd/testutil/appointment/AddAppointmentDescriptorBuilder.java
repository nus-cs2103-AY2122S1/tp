package seedu.plannermd.testutil.appointment;

import seedu.plannermd.logic.commands.apptcommand.AddAppointmentCommand;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.AppointmentDate;
import seedu.plannermd.model.appointment.Duration;
import seedu.plannermd.model.appointment.Session;
import seedu.plannermd.model.person.Remark;

/**
 * A utility class to help with building EditAppointmentDescriptor objects.
 */
public class AddAppointmentDescriptorBuilder {

    private AddAppointmentCommand.AddAppointmentDescriptor descriptor;

    public AddAppointmentDescriptorBuilder() {
        descriptor = new AddAppointmentCommand.AddAppointmentDescriptor();
    }

    public AddAppointmentDescriptorBuilder(AddAppointmentCommand.AddAppointmentDescriptor descriptor) {
        this.descriptor = new AddAppointmentCommand.AddAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code AddAppointmentDescriptor} with fields containing
     * {@code Appointment}'s details
     */
    public AddAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new AddAppointmentCommand.AddAppointmentDescriptor();
        descriptor.setAppointmentDate(appointment.getAppointmentDate());
        descriptor.setSession(appointment.getSession());
        descriptor.setRemark(appointment.getRemark());
    }

    /**
     * Sets the {@code appointment date} of the {@code AddAppointmentDescriptorBuilder} that we are
     * building.
     */
    public seedu.plannermd.testutil.appointment.AddAppointmentDescriptorBuilder withAppointmentDate(String date) {
        descriptor.setAppointmentDate(new AppointmentDate(date));
        return this;
    }

    /**
     * Sets the {@code session} of the {@code AddAppointmentDescriptorBuilder} that we are
     * building.
     */
    public seedu.plannermd.testutil.appointment.AddAppointmentDescriptorBuilder withSession(String time, int duration) {
        descriptor.setSession(new Session(time, new Duration(duration)));
        return this;
    }

    /**
     * Sets the {@code remark} of the {@code AddAppointmentDescriptor} that we are
     * building.
     */
    public seedu.plannermd.testutil.appointment.AddAppointmentDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    public AddAppointmentCommand.AddAppointmentDescriptor build() {
        return descriptor;
    }
}
