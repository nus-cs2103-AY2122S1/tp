package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.plannermd.commons.util.StringUtil;

public class AppointmentContainsDoctorPredicate implements Predicate<Appointment> {

    private final List<String> keywords;

    public AppointmentContainsDoctorPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(appointment.getDoctor().getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsDoctorPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentContainsDoctorPredicate) other).keywords)); // state check
    }
}
