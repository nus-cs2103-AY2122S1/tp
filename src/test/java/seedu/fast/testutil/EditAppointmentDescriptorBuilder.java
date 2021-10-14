package seedu.fast.testutil;


import seedu.fast.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.fast.model.person.Appointment;

public class EditAppointmentDescriptorBuilder {
    private EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentDescriptor();
        descriptor.setDate(appointment.getDate());
        descriptor.setTime(appointment.getTime());
        descriptor.setVenue(appointment.getVenue());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDate(String date) {
        descriptor.setDate(date);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withTime(String time) {
        descriptor.setTime(time);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withVenue(String venue) {
        descriptor.setVenue(venue);
        return this;
    }

    public EditAppointmentDescriptor build() {
        return descriptor;
    }
}
