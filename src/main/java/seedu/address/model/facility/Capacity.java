package seedu.address.model.facility;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Facility's capacity.
 */
public class Capacity {

    public static final String MESSAGE_CONSTRAINTS =
            "Capacity should only contain numbers and it should be at least 1 digit long";
    public static final String VALIDATION_REGEX = "\\d+";
    public final String capacity;

    /**
     * Creates a Capacity object with specified capacity value.
     *
     * @param capacity A valid capacity.
     */
    public Capacity(String capacity) {
        requireNonNull(capacity);
        this.capacity = capacity;
    }

    /**
     * Returns true if number of members allocated is within
     * capacity.
     *
     * @param numberOfPerson Current number of persons allocated.
     * @return Boolean value of whether capacity has been reached.
     */
    public boolean isWithinCapacity(int numberOfPerson) {
        Integer max = Integer.parseInt(capacity);
        return numberOfPerson <= max;
    }

    /**
     * Returns true if a given string is a valid capacity.
     */
    public static boolean isValidCapacity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this)
                || (obj instanceof Capacity
                && capacity.equals(((Capacity) obj).capacity));
    }

    @Override
    public String toString() {
        return capacity;
    }
}
