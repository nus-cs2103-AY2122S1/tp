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
    private LocalDateTime time;

    /**
     * Creates a reservation
     */
    public Reservation(Phone phone, int numberOfPeople, LocalDateTime time) {
        requireAllNonNull(phone, numberOfPeople, time);
        this.phone = phone;
        this.numberOfPeople = numberOfPeople;
        this.time = time;
    }

    public Phone getPhone() {
        return phone;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public LocalDateTime getTime() {
        return time;
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
                && time.equals(that.time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(phone, numberOfPeople, time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("Reservation{phone=%s, numberOfPeople=%s, time=%s}", phone, numberOfPeople, time);
    }
}
