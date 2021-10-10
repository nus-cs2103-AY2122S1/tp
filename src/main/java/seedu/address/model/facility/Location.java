package seedu.address.model.facility;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Facility's location.
 */
public class Location {

    public static final String MESSAGE_CONSTRAINTS =
            "Locations should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

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

    /**
     * Returns true if a given string is a valid location.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
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
