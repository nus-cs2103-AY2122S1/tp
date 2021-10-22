package seedu.address.model.reservation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.model.person.Phone;
import seedu.address.model.table.Table;

/**
 * Represents a reservation
 */
public class Reservation {
    private static final DateTimeFormatter DATE_TIME_PRINTING_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");

    private Phone phone;
    private int numberOfPeople;
    private LocalDateTime dateTime;
    private Table table;

    /**
     * Creates a reservation
     */
    public Reservation(Phone phone, int numberOfPeople, LocalDateTime dateTime, Table table) {
        requireAllNonNull(phone, numberOfPeople, dateTime);
        this.phone = phone;
        this.numberOfPeople = numberOfPeople;
        this.dateTime = dateTime;
        this.table = table;
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

    public int getTableId() {
        return table.getTableId();
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
                && dateTime.equals(that.dateTime)
                && table.equals(that.table);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(phone, numberOfPeople, dateTime, table);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("phone: %s, numberOfPeople: %s, time: %s, table: %s",
                phone, numberOfPeople, dateTime.format(DATE_TIME_PRINTING_FORMAT), table);
    }
}
