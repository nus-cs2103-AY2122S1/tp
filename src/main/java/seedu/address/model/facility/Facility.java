package seedu.address.model.facility;

/**
 * Represents a Facility in the application.
 */
public class Facility {
    private final String location;

    public Facility(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
