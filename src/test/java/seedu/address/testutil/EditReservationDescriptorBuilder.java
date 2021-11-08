package seedu.address.testutil;

import seedu.address.logic.commands.EditReservationCommand.EditReservationDescriptor;
import seedu.address.model.reservation.DateTime;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;

/**
 * A utility class to help with building EditReservationDescriptor objects.
 */
public class EditReservationDescriptorBuilder {

    private EditReservationDescriptor descriptor;

    public EditReservationDescriptorBuilder() {
        descriptor = new EditReservationDescriptor();
    }

    public EditReservationDescriptorBuilder(EditReservationDescriptor descriptor) {
        this.descriptor = new EditReservationDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditReservationDescriptor} with fields containing {@code reservation}'s details
     */
    public EditReservationDescriptorBuilder(Reservation reservation) {
        descriptor = new EditReservationDescriptor();
        descriptor.setDateTime(reservation.getDateTime());
        descriptor.setRemark(reservation.getRemark());
    }

    /**
     * Sets the {@code DateTime} of the {@code EditReservationDescriptor} that we are building.
     */
    public EditReservationDescriptorBuilder withTimestamp(String dateTime) {
        descriptor.setDateTime(new DateTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditReservationDescriptor} that we are building.
     */
    public EditReservationDescriptorBuilder withBilling(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    public EditReservationDescriptor build() {
        return descriptor;
    }
}
