package seedu.fast.model.person;

public class AppointmentVenue {
    public final String venue;

    /**
     * Constructs a {@code AppointmentVenue}.
     *
     * @param venue A valid AppointmentVenue.
     */
    public AppointmentVenue(String venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return venue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentVenue // instanceof handles nulls
                && venue.equals(((AppointmentVenue) other).venue)); // state check
    }

    @Override
    public int hashCode() {
        return venue.hashCode();
    }
}
