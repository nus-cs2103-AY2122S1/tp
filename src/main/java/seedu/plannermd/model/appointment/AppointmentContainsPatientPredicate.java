package seedu.plannermd.model.appointment;

import java.util.List;
import java.util.function.Predicate;

public class AppointmentContainsPatientPredicate implements Predicate<Appointment> {

    private final List<String> keywords;

    public AppointmentContainsPatientPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsPatientPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentContainsPatientPredicate) other).keywords)); // state check
    }
}
