package seedu.address.model.person;

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

    public static boolean isValidPinStatus(String pinStatus) {
        return pinStatus.equals(PINNED_STRING) || pinStatus.equals(NOT_PINNED_STRING);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pin)) {
            return false;
        }
        Pin otherPin = (Pin) other;
        return otherPin.isPinned == this.isPinned;
    }

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
