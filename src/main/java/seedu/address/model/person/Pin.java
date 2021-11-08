package seedu.address.model.person;

/** Represents a Person's pin status. */
public class Pin {
    public static final String MESSAGE_CONSTRAINTS = "Pin status should be either true or false";
    public static final String PINNED_STRING = "true";
    public static final String NOT_PINNED_STRING = "false";

    private final boolean isPinned;

    /**
     * Constructs a pin.
     *
     * @param isPinned if pinned or not.
     */
    public Pin(boolean isPinned) {
        this.isPinned = isPinned;
    }

    /**
     * Constructs a pin based on the pin status.
     * Used for {@code JsonAdaptedPerson}.
     *
     * @param pinString string indicating if pinned or not.
     */
    public Pin(String pinString) {
        assert(isValidPinStatus(pinString)) : "Invalid pin status used";
        isPinned = pinString.equals(PINNED_STRING);
    }

    public boolean isPinned() {
        return isPinned;
    }

    /**
     * Checks if given string is in valid pin status.
     * Used for {@code JsonAdaptedPerson}.
     *
     * @param pinStatus input that is to be checked if it is a valid pin status.
     * @return if a given string is a valid pin status.
     */
    public static boolean isValidPinStatus(String pinStatus) {
        return pinStatus.equals(PINNED_STRING) || pinStatus.equals(NOT_PINNED_STRING);
    }

    /**
     * Checks if {@code other} is equal to {@code this}.
     *
     * @param other the object to check if it is equal to {@code this}.
     * @return {@code boolean} indicating if it is equal.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pin)) {
            return false;
        }
        Pin otherPin = (Pin) other;
        return otherPin.isPinned == this.isPinned;
    }

    /**
     * Returns a pin, whose pin status is opposite of current pin status.
     * Eg. If {@this} {@code isPinned} is {@code false}, will return a pin whose {@code isPinned} is {@code true}.
     *
     * @return pin whose pin status is opposite of current pin status.
     */
    public Pin togglePin() {
        return new Pin(!isPinned);
    }

    @Override
    public String toString() {
        return isPinned
                ? PINNED_STRING
                : NOT_PINNED_STRING;
    }
}
