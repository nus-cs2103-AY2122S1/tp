package safeforhall.model.event;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.util.AppUtil.checkArgument;

public class Capacity {
    public static final String MESSAGE_CONSTRAINTS =
            "Capacity should be between 1 and 999";
    public static final String VALIDATION_REGEX = "\\d{1,3}"; // add check for capacity = 0
    public final String capacity;

    /**
     * Constructs a {@code Capacity}.
     *
     * @param capacity A valid capacity.
     */
    public Capacity(String capacity) {
        requireNonNull(capacity);
        checkArgument(isValidCapacity(capacity), MESSAGE_CONSTRAINTS);
        this.capacity = capacity;
    }

    /**
     * Returns true if a given string is a valid capacity.
     */
    public static boolean isValidCapacity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return capacity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Capacity // instanceof handles nulls
                && (capacity == ((Capacity) other).capacity)); // state check
    }

    @Override
    public int hashCode() {
        return capacity.hashCode();
    }
}
