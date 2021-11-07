package seedu.address.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class RoomNumber {

    public static final String MESSAGE_CONSTRAINTS = "Room numbers must be a number greater than 0.";
    public static final String VALIDATION_REGEX = "^[1-9][0-9]*$";

    public final String value;

    /**
     * Constructs an {@code RoomNumber}.
     *
     * @param roomNumber A valid room number.
     */
    public RoomNumber(String roomNumber) {
        requireNonNull(roomNumber);
        checkArgument(isValidRoomNumber(roomNumber), MESSAGE_CONSTRAINTS);
        this.value = roomNumber;
    }

    /**
     * Returns true if a given string is a valid room number.
     */
    public static boolean isValidRoomNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomNumber // instanceof handles nulls
                && value.equals(((RoomNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
