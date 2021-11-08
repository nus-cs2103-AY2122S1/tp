package seedu.address.model.facility;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Facility's location.
 */
public class Location {

    public static final String MESSAGE_CONSTRAINTS =
            "Locations should only contain up to 50 alphanumeric characters and spaces,"
                    + " and should not be blank.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum} ]{1,50}";

    public final String location;

    /**
     * Creates a Location object with specified location.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
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
                && location.equalsIgnoreCase(((Location) obj).location));
    }

    @Override
    public String toString() {
        return location;
    }
}
