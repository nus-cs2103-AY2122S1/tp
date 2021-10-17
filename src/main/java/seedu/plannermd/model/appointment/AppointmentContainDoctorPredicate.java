package seedu.plannermd.model.appointment;

import java.util.List;
import java.util.function.Predicate;

public class AppointmentContainDoctorPredicate implements Predicate<Appointment> {

    private final List<String> keywords;

    public AppointmentContainDoctorPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainDoctorPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentContainDoctorPredicate) other).keywords)); // state check
    }
}
