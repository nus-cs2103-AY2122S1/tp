package seedu.address.model.room;

import java.util.function.Predicate;

/**
 * Tests that a {@code Room}'s {@code Vacancy} status is vacant.
 */
public class RoomIsVacantPredicate implements Predicate<Room> {

    @Override
    public boolean test(Room room) {
        return room.isVacant();
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof RoomIsVacantPredicate);
    }
}
