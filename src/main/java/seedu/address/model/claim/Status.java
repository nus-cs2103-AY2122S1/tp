package seedu.address.model.claim;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Claim's status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */

public class Status {
    public static final String MESSAGE_CONSTRAINTS = "A status can only be pending or completed";
    private final String status;

    private enum StatusState {
        PENDING, COMPLETED
    }

    /**
     * Constructs a {@Code Status}
     *
     * @param status A valid status
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.status = status;
    }

    /**
     * Returns if a given string is valid
     */
    public static boolean isValidStatus(String test) {
        for (StatusState state : StatusState.values()) {
            if (state.name().equals(test.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.status.toUpperCase();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && this.status.equalsIgnoreCase(((Status) other).status)); // state check
    }

}
