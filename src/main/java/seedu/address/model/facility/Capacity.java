package seedu.address.model.facility;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Facility's capacity.
 */
public class Capacity {
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
