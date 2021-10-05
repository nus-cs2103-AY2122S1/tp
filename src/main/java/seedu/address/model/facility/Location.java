package seedu.address.model.facility;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Facility's location.
 */
public class Location {
    public final String location;

    /**
     * Creates a Location object with specified location.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireNonNull(location);
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this)
                || (obj instanceof Location
                && location.equals(((Location) obj).location));
    }

    @Override
    public String toString() {
        return location;
    }
}
