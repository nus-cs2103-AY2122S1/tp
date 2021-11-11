package seedu.address.model.room;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class Room {
    private final RoomNumber roomNumber;
    private final Vacancy vacancy;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code Room}.
     *
     * @param roomNumber A valid room number.
     */
    public Room(RoomNumber roomNumber, Set<Tag> tags) {
        this.roomNumber = roomNumber;
        vacancy = Vacancy.VACANT;
        this.tags.addAll(tags);
    }

    /**
     * Constructs a Room given its roomNumber, vacancy, status and list of guests.
     *
     * @param roomNumber RoomNumber roomNumber
     * @param vacancy Vacant if room has no guests.
     */
    public Room(RoomNumber roomNumber, Vacancy vacancy, Set<Tag> tags) {
        requireAllNonNull(roomNumber, vacancy, tags);
        this.roomNumber = roomNumber;
        this.vacancy = vacancy;
        this.tags.addAll(tags);
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public Vacancy getVacancy() {
        return this.vacancy;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public boolean isVacant() {
        return vacancy.isVacant();
    }

    /**
     * Returns true if both rooms have the same {@code RoomNumber}.
     */
    public boolean isSameRoom(Room otherRoom) {
        if (otherRoom == this) {
            return true;
        }

        return otherRoom != null
                && otherRoom.getRoomNumber().equals(getRoomNumber());
    }

    @Override
    public String toString() {
        return roomNumber.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Room
                && roomNumber.equals(((Room) other).roomNumber)
                && vacancy.equals(((Room) other).vacancy)
                && tags.equals(((Room) other).tags));
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, vacancy);
    }
}
