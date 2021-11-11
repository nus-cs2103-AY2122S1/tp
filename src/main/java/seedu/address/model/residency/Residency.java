package seedu.address.model.residency;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.residency.exceptions.AlreadyCheckedOutException;
import seedu.address.model.residency.exceptions.CheckOutBeforeCheckInException;
import seedu.address.model.room.Room;

/**
 * Encapsulates the stay of a guest in a room.
 */
public class Residency {

    private final Room room;
    private final Set<Person> guests;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    /**
     * Constructor for Residency object.
     * Automatically sets the check in time to the current time.
     *
     * @param room Room object where guests stay in.
     * @param guests Set of Person objects who stayed in this room.
     */
    public Residency(Room room, Set<Person> guests) {
        this(room, guests, LocalDateTime.now(), null);
    }

    /**
     * Constructor for Residency object.
     * Allows for setting a custom check in time.
     *
     * @param room Room object where guests stay in.
     * @param guests Set of Person objects who stayed in this room.
     */
    public Residency(Room room, Set<Person> guests, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        requireNonNull(room);
        requireAllNonNull(guests);
        this.room = room;
        this.guests = guests;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    /**
     * Returns the Room object in this Residency object.
     * @return Room object.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Returns the Set of Person objects in this Residency object.
     *
     * @return An unmodifiable set of guests.
     */
    public Set<Person> getGuests() {
        return Collections.unmodifiableSet(guests);
    }

    /**
     * Replaces a guest object with its updated Person object.
     *
     * @param personToEdit The Person object to replace
     * @param editedPerson The Person object to replace with
     */
    public void setGuest(Person personToEdit, Person editedPerson) {
        guests.remove(personToEdit);
        guests.add(editedPerson);
    }

    /**
     * Sets the check out time to the current time.
     * This cannot be changed in the future.
     *
     * @throws AlreadyCheckedOutException if this method has been called before.
     */
    public void checkOut() {
        if (checkOutTime != null) {
            throw new AlreadyCheckedOutException();
        }
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isBefore(checkInTime)) {
            throw new CheckOutBeforeCheckInException();
        }
        checkOutTime = currentTime;
    }

    public LocalDateTime getCheckInTime() {
        return this.checkInTime;
    }

    public Optional<LocalDateTime> getCheckOutTime() {
        return Optional.ofNullable(this.checkOutTime);
    }

    public boolean isCheckedIn() {
        return checkOutTime == null;
    }

    @Override
    public String toString() {
        String message = String.format("Room [%s], Guests [%s], CheckInTime [%s], CheckOutTime [%s]",
                room, guests, checkInTime.truncatedTo(ChronoUnit.DAYS), checkOutTime.truncatedTo(ChronoUnit.DAYS));
        return message;
    }

    @Override
    public boolean equals(Object other) {
        if (this.checkOutTime == null) {
            return other == this
                    || (other instanceof Residency
                    && ((Residency) other).room.equals(this.room)
                    && ((Residency) other).guests.equals(this.guests)
                    && ((Residency) other).checkInTime.truncatedTo(ChronoUnit.MINUTES)
                    .equals(this.checkInTime.truncatedTo(ChronoUnit.MINUTES)));
        } else {
            return other == this
                    || (other instanceof Residency
                    && ((Residency) other).room.equals(this.room)
                    && ((Residency) other).guests.equals(this.guests)
                    && ((Residency) other).checkInTime.truncatedTo(ChronoUnit.MINUTES)
                    .equals(this.checkInTime.truncatedTo(ChronoUnit.MINUTES))
                    && ((Residency) other).checkOutTime.truncatedTo(ChronoUnit.MINUTES)
                    .equals(this.checkOutTime.truncatedTo(ChronoUnit.MINUTES)));
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(room, guests, checkInTime, checkOutTime);
    }
}
