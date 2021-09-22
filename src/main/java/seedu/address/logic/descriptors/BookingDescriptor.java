package seedu.address.logic.descriptors;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import seedu.address.exception.NotFoundException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.client.Client;
import seedu.address.model.client.Phone;

public class BookingDescriptor {
    private Phone phone;

    public BookingDescriptor() {};

    public BookingDescriptor(BookingDescriptor toCopy) {
        setPhone(toCopy.phone);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    /**
     * Converts a Booking Descriptor to a Booking model type.
     *
     * @param model Model.
     * @return Booking.
     * @throws NotFoundException If a phone for the booking is not found.
     */
    public Booking toModelType(Model model) throws NotFoundException {
        requireAllNonNull(phone);
        Optional<Client> optionalClient = model.getClientByPhone(phone);
        if (!(optionalClient.isPresent())) {
            throw new NotFoundException(Phone.class.getName());
        }

        return new Booking(optionalClient.get());
    }
}
