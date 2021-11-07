package seedu.address.model.facility;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Facility's name.
 */
public class FacilityName {

    public static final String MESSAGE_CONSTRAINTS =
            "Facility names should only contain up to 50 alphanumeric characters and spaces,"
            + " and should not be blank.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum} ]{1,50}";

    public final String facilityName;

    /**
     * Creates a FacilityName object with the specified facilityName.
     *
     * @param facilityName A valid name.
     */
    public FacilityName(String facilityName) {
        requireNonNull(facilityName);
        checkArgument(isValidFacilityName(facilityName), MESSAGE_CONSTRAINTS);
        this.facilityName = facilityName;
    }

    /**
     * Returns true if a given string is a valid facility name.
     */
    public static boolean isValidFacilityName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this)
                || (obj instanceof FacilityName
                && facilityName.equalsIgnoreCase(((FacilityName) obj).facilityName));
    }

    @Override
    public String toString() {
        return facilityName;
    }
}
