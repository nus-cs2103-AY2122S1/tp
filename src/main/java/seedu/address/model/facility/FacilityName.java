package seedu.address.model.facility;


import static java.util.Objects.requireNonNull;

/**
 * Represents a Facility's name in the address book.
 */
public class FacilityName {
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
