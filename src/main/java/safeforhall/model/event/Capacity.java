package safeforhall.model.event;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.util.AppUtil.checkArgument;

public class Capacity implements Comparable<Capacity> {
    public static final String MESSAGE_CONSTRAINTS = "Capacity should be an integer greater than 0. Maximum allowed"
        + " capacity is 2147483647";

    public static final String DESC = "Capacity: ";
    public static final String FIELD = "c";

    public final String inputCapacity;
    public final int capacity;

    /**
     * Constructs a {@code Capacity}.
     *
     * @param inputCapacity A valid capacity.
     */
    public Capacity(String inputCapacity) {
        requireNonNull(inputCapacity);
        checkArgument(isValidCapacity(inputCapacity), MESSAGE_CONSTRAINTS);
        this.inputCapacity = inputCapacity;
        this.capacity = Integer.parseInt(inputCapacity);
    }

    /**
     * Returns true if a given string is a valid capacity.
     */
    public static boolean isValidCapacity(String test) {
        try {
            int cap = Integer.parseInt(test);
            return cap > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return inputCapacity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Capacity // instanceof handles nulls
                && inputCapacity.equals(((Capacity) other).inputCapacity)); // state check
    }

    @Override
    public int hashCode() {
        return inputCapacity.hashCode();
    }

    @Override
    public int compareTo(Capacity c) {
        return Integer.compare(this.capacity, c.capacity);
    }
}
