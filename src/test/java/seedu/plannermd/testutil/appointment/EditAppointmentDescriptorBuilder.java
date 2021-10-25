package seedu.plannermd.testutil.appointment;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.apptcommand.EditAppointmentCommand;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.AppointmentDate;
import seedu.plannermd.model.appointment.Duration;
import seedu.plannermd.model.person.Remark;

/**
 * A utility class to help with building EditAppointmentDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {

    private final EditAppointmentCommand.EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentCommand.EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentCommand.EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentCommand.EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing
     * {@code Appointment}'s details
     */
    public EditAppointmentDescriptorBuilder(Index patientIndex, Index doctorIndex, Appointment appointment) {
        descriptor = new EditAppointmentCommand.EditAppointmentDescriptor();
        descriptor.setPatientIndex(patientIndex);
        descriptor.setDoctorIndex(doctorIndex);
        descriptor.setAppointmentDate(appointment.getAppointmentDate());
        descriptor.setStartTime(appointment.getSession().getFormattedStartTime());
        descriptor.setDuration(appointment.getSession().duration);
        descriptor.setRemark(appointment.getRemark());
    }

    /**
     * Sets the {@code patient index} of the {@code EditAppointmentDescriptor} that we are
     * building.
     */
    public EditAppointmentDescriptorBuilder withPatientIndex(String patientIndex) {
        int index = Integer.parseInt(patientIndex);
        descriptor.setPatientIndex(Index.fromOneBased(index));
        return this;
    }

    /**
     * Sets the {@code doctor index} of the {@code EditAppointmentDescriptor} that we are
     * building.
     */
    public EditAppointmentDescriptorBuilder withDoctorIndex(String doctorIndex) {
        int index = Integer.parseInt(doctorIndex);
        descriptor.setDoctorIndex(Index.fromOneBased(index));
        return this;
    }

    /**
     * Sets the {@code AppointmentDate} of the {@code EditAppointmentDescriptor} that we are
     * building.
     */
    public EditAppointmentDescriptorBuilder withAppointmentDate(String appointmentDate) {
        descriptor.setAppointmentDate(new AppointmentDate(appointmentDate));
        return this;
    }

    /**
     * Sets the {@code start time} of the {@code EditAppointmentDescriptor} that we are
     * building.
     */
    public EditAppointmentDescriptorBuilder withStartTime(String startTime) {
        descriptor.setStartTime(startTime);
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code EditAppointmentDescriptor}
     * that we are building.
     */
    public EditAppointmentDescriptorBuilder withDuration(String duration) {
        int dur = Integer.parseInt(duration);
        descriptor.setDuration(new Duration(dur));
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditAppointmentDescriptor}
     * that we are building.
     */
    public EditAppointmentDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    public EditAppointmentCommand.EditAppointmentDescriptor build() {
        return descriptor;
    }
}
