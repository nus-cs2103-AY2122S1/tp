package seedu.address.model.reservation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.model.person.Phone;

/**
 * Represents a reservation
 */
public class Reservation {
    private Phone phone;
    private int numberOfPeople;
    private LocalDateTime dateTime;

    /**
     * Creates a reservation
     */
    public Reservation(Phone phone, int numberOfPeople, LocalDateTime dateTime) {
        requireAllNonNull(phone, numberOfPeople, dateTime);
        this.phone = phone;
        this.numberOfPeople = numberOfPeople;
        this.dateTime = dateTime;
    }

    public Phone getPhone() {
        return phone;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Reservation)) {
            return false;
        }

        Reservation that = (Reservation) o;
        return numberOfPeople == that.numberOfPeople
                && phone.equals(that.phone)
                && dateTime.equals(that.dateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(phone, numberOfPeople, dateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("phone=%s; numberOfPeople=%s; time=%s", phone, numberOfPeople, dateTime);
    }
}
