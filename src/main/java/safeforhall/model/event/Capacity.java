package safeforhall.model.event;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.util.AppUtil.checkArgument;

public class Capacity {
    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{1,3}";
    public final String capacity;

    /**
     * Constructs a {@code Phone}.
     *
     * @param capacity A valid capacity.
     */
    public Capacity(String capacity) {
        requireNonNull(capacity);
        // checkArgument(isValidCapacity(capacity), MESSAGE_CONSTRAINTS);
        this.capacity = capacity;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidCapacity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return capacity.toString();
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
