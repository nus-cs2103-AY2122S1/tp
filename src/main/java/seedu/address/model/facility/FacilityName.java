package seedu.address.model.facility;


import static java.util.Objects.requireNonNull;

/**
 * Represents a Facility's name in the address book.
 */
public class FacilityName {

    public static final String MESSAGE_CONSTRAINTS =
            "Facility names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String facilityName;

    /**
     * Creates a FacilityName object with the specified facilityName.
     *
     * @param facilityName A valid name.
     */
    public FacilityName(String facilityName) {
        requireNonNull(facilityName);
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
                && facilityName.equals(((FacilityName) obj).facilityName));
    }

    @Override
    public String toString() {
        return facilityName;
    }
}
