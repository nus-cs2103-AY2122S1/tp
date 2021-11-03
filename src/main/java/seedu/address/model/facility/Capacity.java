package seedu.address.model.facility;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Facility's capacity.
 */
public class Capacity {

    public static final String MESSAGE_CONSTRAINTS =
            "Capacity should be a positive integer which is less than or equal to 50";
    public static final String VALIDATION_REGEX = "^([1-9]|[1-4]\\d|50)$";
    public final String capacity;

    /**
     * Creates a Capacity object with specified capacity value.
     *
     * @param capacity A valid capacity.
     */
    public Capacity(String capacity) {
        requireNonNull(capacity);
        checkArgument(isValidCapacity(capacity), MESSAGE_CONSTRAINTS);
        this.capacity = capacity;
    }

    /**
     * Returns true if the facility is at max capacity.
     *
     * @param numberOfPerson Current number of persons allocated.
     * @return Boolean value of whether capacity has been reached.
     */
    public boolean isMaxCapacity(int numberOfPerson) {
        Integer max = Integer.parseInt(capacity);
        return numberOfPerson >= max;
    }

    /**
     * Returns true if a given string is a valid capacity.
     */
    public static boolean isValidCapacity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public int getCapacityAsInt() {
        return Integer.parseInt(capacity);
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
